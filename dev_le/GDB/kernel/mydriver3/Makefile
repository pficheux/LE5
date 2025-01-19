# Default for running kernel (x86)
KDIR= /lib/modules/$(shell uname -r)/build

ifeq ($(KDIR),)
$(error Please set KDIR variable to your target kernel source directory)
endif

PWD= $(shell pwd)

EXTRA_CFLAGS += -O0 -g

obj-m := mydriver3.o

all:
	$(MAKE) -C $(KDIR) SUBDIRS=$(PWD) modules

install:
	$(MAKE) -C $(KDIR) SUBDIRS=$(PWD) modules_install

clean:
	rm -f *~
	$(MAKE) -C $(KDIR) SUBDIRS=$(PWD) clean

