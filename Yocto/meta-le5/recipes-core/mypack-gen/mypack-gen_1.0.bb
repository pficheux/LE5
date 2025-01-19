#
# Hello World bb file (generic Makefile)
#
DESCRIPTION = "Helloworld software (generic)"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=8ca43cbc842c2336e835926c2166c28b"
PR = "r0"

SRC_URI = "http://pficheux.free.fr/pub/tmp/mypack-gen-1.0.tar.gz"

#do_my_func() {
#	echo "*** my_func"
#}
#
#addtask my_func before do_install
#
#do_compile_prepend() {
#	echo "*** compile_prepend"
#}
#
#do_compile_append() {
#	echo "*** compile_append"
#}

do_install() {
        oe_runmake install DESTDIR=${D}
}

SRC_URI[md5sum] = "2421f06a3ea5c9c35ac1a833f4587499"
