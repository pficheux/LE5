DESCRIPTION = "MPL115A2 utility"
SECTION = "examples"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=8ca43cbc842c2336e835926c2166c28b"

SRC_URI = "file://mpl115a2.c file://COPYING file://init file://get_power.sh"

DEPENDS = "wiringpi"

inherit update-rc.d

S = "${WORKDIR}"

INITSCRIPT_NAME = "mpl115a2"
INITSCRIPT_PARAMS = "defaults 99"

do_compile() {
	     ${CC} ${CFLAGS} ${LDFLAGS} mpl115a2.c -o mpl115a2 -lwiringPi -lpthread
}
 
do_install() {
	     install -d ${D}${bindir}
	     install -m 0755 mpl115a2 ${D}${bindir}
	     install -m 0755 get_power.sh ${D}${bindir}
	     install -d ${D}${sysconfdir}/init.d
	     install -m 0755 ${WORKDIR}/init  ${D}${sysconfdir}/init.d/mpl115a2
}
