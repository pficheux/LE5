DESCRIPTION = "Linux RPi 3 kernel test"
SECTION = "kernel"
LICENCE = "GPLv2"

require recipes-kernel/linux/linux-yocto.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

LINUX_VERSION ?= "4.4.50"

LINUX_VERSION_EXTENSION = "-le5"

PV = "${LINUX_VERSION}+git${SRCPV}"

SRCREV = "04c8e47067d4873c584395e5cb260b4f170a99ea"

SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.4.y \
           file://0001-fix-dtbo-rules.patch \
"

SRC_URI += "file://defconfig"

COMPATIBLE_MACHINE = "raspberrypi3"
