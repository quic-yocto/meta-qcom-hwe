#!/bin/sh
# Copyright (c) 2023-2024, Qualcomm Innovation Center, Inc. All rights reserved.
# SPDX-License-Identifier: BSD-3-Clause-Clear

echo schedutil > /sys/devices/system/cpu/cpufreq/policy0/scaling_governor
echo schedutil > /sys/devices/system/cpu/cpufreq/policy4/scaling_governor
echo schedutil > /sys/devices/system/cpu/cpufreq/policy7/scaling_governor

echo 128 > /proc/sys/kernel/sched_util_clamp_min_rt_default

echo s2idle > /sys/power/mem_sleep
echo 100 > /proc/sys/vm/swappiness
# Disable periodic kcompactd wakeups. We do not use THP, so having many
# huge pages is not as necessary.
echo 0 > /proc/sys/vm/compaction_proactiveness
