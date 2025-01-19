################################################################################
#
# Hello World (CMake)
#
################################################################################

HELLO_CMAKE_SOURCE = hello_cmake.tar.gz
HELLO_CMAKE_SITE = http://pficheux.free.fr/pub/tmp

$(eval $(cmake-package))
