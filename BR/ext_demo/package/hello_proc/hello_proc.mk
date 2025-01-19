################################################################################
#
# Hello World module
#
################################################################################

HELLO_PROC_SOURCE= hello_proc.tar.gz
HELLO_PROC_SITE= http://pficheux.free.fr/pub/tmp

#HELLO_PROC_DEPENDENCIES = linux

$(eval $(kernel-module))
$(eval $(generic-package))
