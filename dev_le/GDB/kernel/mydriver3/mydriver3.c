#include <linux/kernel.h>	/* printk() */
#include <linux/module.h>	/* modules */
#include <linux/init.h>		/* module_{init,exit}() */
#include <linux/fs.h>           /* file_operations */
#include <asm/uaccess.h>	/* copy_{from,to}_user() */
#include <linux/miscdevice.h>   /* misc driver interface */

MODULE_LICENSE("GPL");

/*
 * Global variables
 */

#define BUF_SIZE 64

static struct miscdevice mymisc; /* Misc device handler */
static char buffer[BUF_SIZE]; /* copy_from/to_user buffer */
static size_t num = 0; /* Number of available bytes in the buffer */

/*
 * File operations
 */
static ssize_t mydriver3_read(struct file *file, char *buf, size_t count, loff_t *ppos)
{
  size_t real;

  real = min(num, count);

  if (real)
    if (copy_to_user(buf, buffer, real))
      return -EFAULT;

  num = 0; /* Destructive read (no more data after ONE read) */

  pr_info ("mydriver3: read %d/%d chars %s\n", real, count, buffer);

  return real;
}

static ssize_t mydriver3_write(struct file *file, const char *buf, size_t count, loff_t *ppos)
{
  size_t real;

  real = min((size_t)BUF_SIZE, count);

  if (real)
    if (copy_from_user(buffer, buf, real))
      return -EFAULT;

  num = real; /* Destructive write (overwrite previous data if any) */

  pr_info ("mydriver3: wrote %d/%d chars %s\n", real, count, buffer);

  return real;
}

static int mydriver3_open(struct inode *inode, struct file *file)
{
  pr_info ("mydriver3: open()\n");

  return 0;
}

static int mydriver3_release(struct inode *inode, struct file *file)
{
  pr_info ("mydriver3: release()\n");

  return 0;
}

static struct file_operations mydriver3_fops = {
  .owner   =	THIS_MODULE,
  .read    =	mydriver3_read,
  .write   =	mydriver3_write,
  .open    =	mydriver3_open,
  .release =	mydriver3_release,
};

/*
 * Init and Exit
 */
static int __init mydriver3_init(void)
{
  int ret;

  mymisc.minor = MISC_DYNAMIC_MINOR;
  mymisc.name = "mydriver3";
  mymisc.fops = &mydriver3_fops;

  ret = misc_register(&mymisc);

  if (ret < 0) {
    pr_warn ("mydriver3: unable to get a dynamic minor\n");
    return ret;
  }

  pr_info ("mydriver3: successfully loaded\n");

  return 0;
}

static void __exit mydriver3_exit(void)
{
  misc_deregister(&mymisc);
  pr_info ("mydriver3: successfully unloaded\n");
}

/*
 * Module entry points
 */
module_init(mydriver3_init);
module_exit(mydriver3_exit);
