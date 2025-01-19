#
# Hello World bb file (Autotools)
#
DESCRIPTION = "Helloworld software (autotools)"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=8ca43cbc842c2336e835926c2166c28b"
#PR = "r1"

#SRC_URI = "http://pficheux.free.fr/pub/tmp/mypack-auto-1.0.tar.gz"
SRC_URI = "http://pficheux.free.fr/pub/tmp/mypack-auto-1.0.tar.gz file://hello.patch"

inherit autotools

SRC_URI[md5sum] = "b282082e4e5cc8634b7c6caa822ce440"
SRC_URI[sha256sum] = "a0a4a982ca95e7b2886da9619d2e45279b9e1b3d07e9ab164baa497ca753560d"
