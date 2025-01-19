################################################################################
#
# Hello World (Autotools)
#
################################################################################

HELLO_AUTOTOOLS_SOURCE = hello_autotools.tar.gz
HELLO_AUTOTOOLS_SITE = http://pficheux.free.fr/pub/tmp

HELLO_AUTOTOOLS_AUTORECONF = YES

$(eval $(autotools-package))
