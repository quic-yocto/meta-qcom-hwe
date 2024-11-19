#!/bin/sh -e
# Copyright (c) 2024 Qualcomm Innovation Center, Inc. All rights reserved.
# SPDX-License-Identifier: MIT

TOPDIR=$(realpath $(dirname $(readlink -f $0))/..)

# Yocto Project layer checking tool
CMD="yocto-check-layer-wrapper"
# Layer to check
CMD="$CMD $TOPDIR"
# Disable auto layer discovery
CMD="$CMD --no-auto"
# Layers to process for dependencies
CMD="$CMD --dependency `pwd`/poky/meta `pwd`/meta-qcom"
# Disable automatic testing of dependencies
CMD="$CMD --no-auto-dependency"
# Set machines to all machines defined in this BSP layer
CMD="$CMD --machines $(echo $(find $TOPDIR/conf/machine/ -maxdepth 1 -name *.conf -exec basename {} .conf \; ))"

exec kas shell $TOPDIR/ci/base.yml --command "$CMD"
