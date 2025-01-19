SUMMARY = "xterm + twm + xclock starter"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=8ca43cbc842c2336e835926c2166c28b"

SRC_URI = "file://89xterm.sh file://COPYING"

inherit allarch

RDEPENDS_${PN} = "xterm twm xclock"
 
S = "${WORKDIR}"

do_install() {
	     install -d ${D}/${sysconfdir}/X11/Xsession.d/
	     install -m 0755 89xterm.sh  ${D}/${sysconfdir}/X11/Xsession.d/
}
