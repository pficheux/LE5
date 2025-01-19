DESCRIPTION = "HelloWorld"
SECTION = "examples"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=8ca43cbc842c2336e835926c2166c28b"

SRC_URI = "file://hello_yocto.c file://COPYING"
 
S = "${WORKDIR}"

do_compile() {
	     ${CC} ${CFLAGS} ${LDFLAGS} hello_yocto.c -o hello_yocto
}
 
do_install() {
	     install -d ${D}${bindir}
	     install -m 0755 hello_yocto ${D}${bindir}
}
