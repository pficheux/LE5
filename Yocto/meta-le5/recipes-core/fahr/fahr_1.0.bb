#
# Celsius to Fahrenheit converter
#
DESCRIPTION = "Celsius to Fahrenheit converter"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=8ca43cbc842c2336e835926c2166c28b"
PR = "r0"

inherit ptest

SRC_URI = "http://pficheux.free.fr/pub/tmp/fahr-1.0.tar.gz file://run-ptest"

do_install() {
        oe_runmake install DESTDIR=${D}
}

do_install_ptest () {
        cp ${B}/runtest.sh ${D}${PTEST_PATH}/
        cp ${B}/test.dat ${D}${PTEST_PATH}/
}


SRC_URI[md5sum] = "f77329951bdbb0b7d21f4f24db03eaa6"
