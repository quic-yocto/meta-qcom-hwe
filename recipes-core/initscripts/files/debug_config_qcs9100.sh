#!/bin/sh
# Copyright (c) 2024 Qualcomm Innovation Center, Inc. All rights reserved.
# SPDX-License-Identifier: BSD-3-Clause-Clear

config_stm_stp_policy()
{
    mkdir /sys/kernel/config/stp-policy/stm0:p_ost.policy
    mkdir -p /sys/kernel/config/stp-policy/stm0:p_ost.policy/default
}

config_coresight_permission()
{
    chmod 777 /sys/bus/coresight/devices/tmc_etr0/block_size
    chmod 777 /sys/bus/coresight/devices/tmc_etr0/buffer_size
    chmod 777 /sys/bus/coresight/devices/tmc_etr0/enable_sink
    chmod 777 /sys/bus/coresight/devices/tmc_etr1/block_size
    chmod 777 /sys/bus/coresight/devices/tmc_etr1/buffer_size
    chmod 777 /sys/bus/coresight/devices/tmc_etr1/enable_sink
    chmod 777 /sys/bus/coresight/devices/stm0/enable_source
    chmod 777 /dev/byte-cntr
    chmod 777 /dev/byte-cntr1
    chmod 777 /sys/bus/coresight/devices/tmc_etr0/out_mode
    chmod 777 /sys/bus/coresight/devices/tmc_etr1/out_mode
}

#ftrace_disable=`getprop persist.debug.ftrace_events_disable
CONFIG_OPTION="CONFIG_DEBUG_LIST"
debug_build=false
enable_debug()
{
    echo "QCS9100 debug"
    config_stm_stp_policy
    config_coresight_permission
}

enable_debug
