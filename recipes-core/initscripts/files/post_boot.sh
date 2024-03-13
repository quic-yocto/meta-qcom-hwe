#!/bin/sh
# Copyright (c) 2023-2024, Qualcomm Innovation Center, Inc. All rights reserved.
# SPDX-License-Identifier: BSD-3-Clause-Clear

echo schedutil > /sys/devices/system/cpu/cpufreq/policy0/scaling_governor
echo schedutil > /sys/devices/system/cpu/cpufreq/policy4/scaling_governor
echo schedutil > /sys/devices/system/cpu/cpufreq/policy7/scaling_governor

echo s2idle > /sys/power/mem_sleep
