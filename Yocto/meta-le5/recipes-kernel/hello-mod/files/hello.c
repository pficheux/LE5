#include <linux/module.h>

MODULE_LICENSE("GPL");

static int param;

module_param(param, int, 0644);

static int __init hello_init (void)
{
  pr_info ("Hello World, my param is %d!\n", param);
  return 0;
}

static void __exit hello_exit (void)
{
  pr_info ("Goodbye Cruel World!\n");
}

module_init (hello_init);
module_exit (hello_exit);
