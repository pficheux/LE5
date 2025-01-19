# Base this image on core-image-minimal 
include recipes-core/images/core-image-minimal.bb 

IMAGE_INSTALL += " \ 
	kernel-modules \ 
	mypack-auto mypack-gen hello-mod \ 
	" 

IMAGE_FEATURES += "package-management" 
