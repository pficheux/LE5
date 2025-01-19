################################################################################
#
# PCSENSOR
#
################################################################################

PCSENSOR_VERSION = 1d6a0e7e33a1917b77802591e42cfa775698936b
PCSENSOR_SITE = $(call github,padelt,pcsensor-temper,$(PCSENSOR_VERSION))

define PCSENSOR_BUILD_CMDS
	$(MAKE) -C $(@D)/src CC="$(TARGET_CC)" CFLAGS="$(TARGET_CFLAGS)"
endef

define PCSENSOR_INSTALL_TARGET_CMDS
	cp $(@D)/src/pcsensor $(TARGET_DIR)/usr/bin
endef

define PCSENSOR_CLEAN_CMDS
	$(MAKE) -C $(@D)/src clean
endef

PCSENSOR_DEPENDENCIES = libusb-compat

$(eval $(generic-package))

