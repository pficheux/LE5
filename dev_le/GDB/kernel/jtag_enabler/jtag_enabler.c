#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <sys/mman.h>
#include <unistd.h>

#define BCM2708_PERI_BASE   0x20000000
#define GPIO_BASE           (BCM2708_PERI_BASE + 0x200000) /* GPIO controler */

#define PAGE_SIZE (4*1024)
#define BLOCK_SIZE (4*1024)

int  mem_fd;
char *gpio_map;

// I/O access
volatile unsigned *gpio;

// GPIO setup macros
#define INP_GPIO(g) *(gpio+((g)/10)) &= ~(7<<(((g)%10)*3))
#define OUT_GPIO(g) *(gpio+((g)/10)) |=  (1<<(((g)%10)*3))
#define SET_GPIO_ALT(g,a) *(gpio + (((g)/10))) |= (((a)<=3?(a) + 4:(a)==4?3:2)<<(((g)%10)*3))

#define GPIO_ALT_ADDR(g) (gpio + (((g)/10)))
#define GPIO_ALT_VALUE(g, a) (((a)<=3?(a) + 4:(a)==4?3:2)<<(((g)%10)*3))

#define GPIO_SET *(gpio+7)  // sets   bits which are 1 ignores bits which are 0
#define GPIO_CLR *(gpio+10) // clears bits which are 1 ignores bits which are 0

// For GPIO# >= 32 (RPi B+)
#define GPIO_SET_EXT *(gpio+8)  // sets   bits which are 1 ignores bits which are 0
#define GPIO_CLR_EXT *(gpio+11) // clears bits which are 1 ignores bits which are 0

//
// Set up a memory regions to access GPIO
//
void setup_io()
{
  /* open /dev/mem */
  if ((mem_fd = open("/dev/mem", O_RDWR|O_SYNC) ) < 0) {
    printf("can't open /dev/mem \n");
    exit(-1);
  }

  /* mmap GPIO */
  gpio_map = (char *)mmap(
			  NULL,             //Any adddress in our space will do
			  BLOCK_SIZE,       //Map length
			  PROT_READ|PROT_WRITE,// Enable reading & writting to mapped memory
			  MAP_SHARED,       //Shared with other processes
			  mem_fd,           //File to map
			  GPIO_BASE         //Offset to GPIO peripheral
			  );

  close(mem_fd); //No need to keep mem_fd open after mmap

  if (gpio_map == MAP_FAILED) {
    printf("mmap error %d\n", (int)gpio_map);
    exit(-1);
  }

  // Always use volatile pointer!
  gpio = (volatile unsigned *)gpio_map;
}

int main(int ac, char **av)
{
  setup_io();
  unsigned int alt4_mask = 0, alt5_mask = 0;

  printf ("Enabling JTAG...\n");
  SET_GPIO_ALT(22, 4);
  SET_GPIO_ALT(4, 5);
  SET_GPIO_ALT(27, 4);
  SET_GPIO_ALT(25, 4);
  SET_GPIO_ALT(23, 4);
  SET_GPIO_ALT(24, 4);

  //  printf ("%x %x\n", GPIO_ALT_ADDR(22), GPIO_ALT_VALUE(22, 4));
  alt4_mask |= GPIO_ALT_VALUE(22, 4);
  //  printf ("%x %x\n", GPIO_ALT_ADDR(4), GPIO_ALT_VALUE(4, 5));
  alt5_mask |= GPIO_ALT_VALUE(4, 5);
  //  printf ("%x %x\n", GPIO_ALT_ADDR(27), GPIO_ALT_VALUE(27, 4));
  alt4_mask |= GPIO_ALT_VALUE(27, 4);
  //  printf ("%x %x\n", GPIO_ALT_ADDR(25), GPIO_ALT_VALUE(25, 4));
  alt4_mask |= GPIO_ALT_VALUE(25, 4);
  //  printf ("%x %x\n", GPIO_ALT_ADDR(23), GPIO_ALT_VALUE(23, 4));
  alt4_mask |= GPIO_ALT_VALUE(23, 4);
  //  printf ("%x %x\n", GPIO_ALT_ADDR(24), GPIO_ALT_VALUE(24, 4));
  alt4_mask |= GPIO_ALT_VALUE(24, 4);

  printf ("done. (0x%x |= 0x%x,  0x%x |= 0x%x)\n", GPIO_BASE+8, alt4_mask, GPIO_BASE, alt5_mask);

  return 0;
}
