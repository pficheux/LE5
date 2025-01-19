# Base this image on core-image-minimal
include recipes-core/images/core-image-minimal.bb

IMAGE_FEATURES += "package-management allow-empty-password empty-root-password"

# Pi3 / Pi0-W (Wi-Fi)
IMAGE_INSTALL += " kernel-modules wiringpi i2c-tools iw wpa-supplicant mpl115a2 mosquitto-clients linux-firmware-bcm43430"
