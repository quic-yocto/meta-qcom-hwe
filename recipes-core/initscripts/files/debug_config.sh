#!/bin/sh
# Copyright (c) 2024 Qualcomm Innovation Center, Inc. All rights reserved.
# SPDX-License-Identifier: BSD-3-Clause-Clear

if [ -f /sys/devices/soc0/soc_id ]; then
    soc_id=`cat /sys/devices/soc0/soc_id`
fi

case "$soc_id" in
    497|498|475|515)
        /etc/initscripts/debug_config_qcm6490.sh
        ;;
    534|606|674|675)
        /etc/initscripts/debug_config_qcs9100.sh
        ;;

    *)
        # Empty default case
        ;;
esac

