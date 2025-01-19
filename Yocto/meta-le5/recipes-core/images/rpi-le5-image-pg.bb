# Base this image on core-image-minimal
include recipes-core/images/core-image-minimal.bb

IMAGE_FEATURES += "package-management"

IMAGE_INSTALL += "packagegroup-le5"
