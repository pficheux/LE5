#
# Hello World bb file + library (Autotools)
#
DESCRIPTION = "Helloworld software with library (autotools)"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=8ca43cbc842c2336e835926c2166c28b"
PR = "r0"

DEPENDS = "bbexample-lib"

SRC_URI = "http://pficheux.free.fr/pub/tmp/mypack-auto-lib-1.0.tar.gz"

inherit autotools

SRC_URI[md5sum] = "813d7b3d9a3a0d237f84ec1d807ce967"
SRC_URI[sha256sum] = "53f1cdc126ed6f498a4eb444bb658555207c444287ccd6f08f7f6a804d4fade3"
