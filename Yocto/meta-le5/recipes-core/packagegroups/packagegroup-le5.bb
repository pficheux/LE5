DESCRIPTION = "Package group for LE5 demo image"
LICENSE = "MIT"

inherit packagegroup

PACKAGES = "packagegroup-le5"

RDEPENDS_${PN} = "kernel-modules \
	mypack-auto mypack-gen hello-mod \ 
"

RRECOMMENDS_${PN} = "mypack-gen (>= 1.0)"


