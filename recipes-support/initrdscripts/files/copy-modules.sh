#!/bin/sh
# Copyright (c) 2023 Qualcomm Innovation Center, Inc. All rights reserved.
# SPDX-License-Identifier: BSD-3-Clause-Clear

copy_modules_enabled() {
	[ -n "${bootparam_copy_modules}" -a -d /lib/modules/`uname -r` ]
}

copy_modules_run() {
	if [ -n "$ROOTFS_DIR" ]; then
		rm -rf $ROOTFS_DIR/lib/modules/`uname -r`
		mkdir -p $ROOTFS_DIR/lib/modules
		cp -a /lib/modules/`uname -r` $ROOTFS_DIR/lib/modules
	else
		debug "No rootfs has been set"
	fi
}
