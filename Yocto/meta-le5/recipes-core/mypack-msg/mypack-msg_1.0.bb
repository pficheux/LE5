#
# Hello World bb file (Autotools + external file)
#
DESCRIPTION = "Helloworld software (autotools + external file)"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=8ca43cbc842c2336e835926c2166c28b"
PR = "r0"

SRC_URI = "http://pficheux.free.fr/pub/tmp/mypack-msg-1.0.tar.gz file://message.h" 

inherit autotools

EXTRA_OEMAKE = "CFLAGS=-I../"

SRC_URI[md5sum] = "bafdc783aff3793efafde4d5dd8eeecf"
SRC_URI[sha256sum] = "cf85819e704b39532ccae26064e3eacbdcfec671b392275234c2db77f96a25b9"
