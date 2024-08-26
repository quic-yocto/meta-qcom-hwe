#!/bin/sh
# Copyright (c) 2023-2024, Qualcomm Innovation Center, Inc. All rights reserved.
# SPDX-License-Identifier: BSD-3-Clause-Clear

for policy in `ls -d /sys/devices/system/cpu/cpufreq/policy*`;
    do echo schedutil > $policy/scaling_governor;
done

echo 128 > /proc/sys/kernel/sched_util_clamp_min_rt_default

echo s2idle > /sys/power/mem_sleep
echo 100 > /proc/sys/vm/swappiness
# Disable periodic kcompactd wakeups. We do not use THP, so having many
# huge pages is not as necessary.
echo 0 > /proc/sys/vm/compaction_proactiveness
