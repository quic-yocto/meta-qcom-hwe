#!/bin/sh
# Copyright (c) 2024 Qualcomm Innovation Center, Inc. All rights reserved.
# SPDX-License-Identifier: BSD-3-Clause-Clear

enable_tracing_events()
{
    # timer
    echo 1 > /sys/kernel/debug/tracing/events/timer/timer_expire_entry/enable
    echo 1 > /sys/kernel/debug/tracing/events/timer/timer_expire_exit/enable
    echo 1 > /sys/kernel/debug/tracing/events/timer/hrtimer_cancel/enable
    echo 1 > /sys/kernel/debug/tracing/events/timer/hrtimer_expire_entry/enable
    echo 1 > /sys/kernel/debug/tracing/events/timer/hrtimer_expire_exit/enable
    echo 1 > /sys/kernel/debug/tracing/events/timer/hrtimer_init/enable
    echo 1 > /sys/kernel/debug/tracing/events/timer/hrtimer_start/enable
    #enble FTRACE for softirq events
    echo 1 > /sys/kernel/debug/tracing/events/irq/enable
    #enble FTRACE for Workqueue events
    echo 1 > /sys/kernel/debug/tracing/events/workqueue/enable
    # schedular
    # echo 1 > /sys/kernel/debug/tracing/events/sched/sched_cpu_hotplug/enable
    echo 1 > /sys/kernel/debug/tracing/events/sched/sched_migrate_task/enable
    echo 1 > /sys/kernel/debug/tracing/events/sched/sched_pi_setprio/enable
    echo 1 > /sys/kernel/debug/tracing/events/sched/sched_switch/enable
    echo 1 > /sys/kernel/debug/tracing/events/sched/sched_wakeup/enable
    echo 1 > /sys/kernel/debug/tracing/events/sched/sched_wakeup_new/enable
    echo 1 > /sys/kernel/debug/tracing/events/sched/sched_isolate/enable
    # sound
    # echo 1 > /sys/kernel/debug/tracing/events/asoc/snd_soc_reg_read/enable
    # echo 1 > /sys/kernel/debug/tracing/events/asoc/snd_soc_reg_write/enable
    # mdp
    # echo 1 > /sys/kernel/debug/tracing/events/mdss/mdp_video_underrun_done/enable
    # video
    # echo 1 > /sys/kernel/debug/tracing/events/msm_vidc/enable
    # clock
    echo 1 > /sys/kernel/debug/tracing/events/power/clock_set_rate/enable
    echo 1 > /sys/kernel/debug/tracing/events/power/clock_enable/enable
    echo 1 > /sys/kernel/debug/tracing/events/power/clock_disable/enable
    echo 1 > /sys/kernel/debug/tracing/events/power/cpu_frequency/enable
    # regulator
    echo 1 > /sys/kernel/debug/tracing/events/regulator/enable
    # power
    echo 1 > /sys/kernel/debug/tracing/events/msm_low_power/enable
    # fastrpc
    echo 1 > /sys/kernel/debug/tracing/events/fastrpc/enable
    #thermal
    echo 1 > /sys/kernel/debug/tracing/events/thermal/thermal_device_update/enable
    echo 1 > /sys/kernel/debug/tracing/events/thermal/thermal_zone_trip/enable
    echo 1 > /sys/kernel/debug/tracing/events/thermal/thermal_temperature/enable
    echo 1 > /sys/kernel/debug/tracing/events/thermal/thermal_query_temp/enable
    echo 1 > /sys/kernel/debug/tracing/events/thermal/cdev_update/enable
    echo 1 > /sys/kernel/debug/tracing/events/dcvsh/dcvsh_freq/enable

    #rmph_send_msg
    echo 1 > /sys/kernel/debug/tracing/events/rpmh/rpmh_send_msg/enable

    #enable aop with timestamps

    #memory pressure events/oom
    echo 1 > /sys/kernel/debug/tracing/events/psi/psi_event/enable
    echo 1 > /sys/kernel/debug/tracing/events/psi/psi_window_vmstat/enable
    #Enable irqsoff/preempt tracing
    echo 1 > /sys/kernel/debug/tracing/events/preemptirq_long/enable
    echo stacktrace > /d/tracing/events/preemptirq_long/preempt_disable_long/trigger
    echo stacktrace > /d/tracing/events/preemptirq_long/irq_disable_long/trigger
    echo 1 > /sys/kernel/debug/tracing/tracing_on
}


# function to enable ftrace events
enable_ftrace_event_tracing()
{
    # bail out if its perf config
    #if [ ! -d /sys/module/msm_rtb ]
    #then
    #    return
    #fi

    # bail out if ftrace events aren't present
    if [ ! -d /sys/kernel/debug/tracing/events ]
    then
        return
    fi

    enable_tracing_events
}

#ftrace_disable=`getprop persist.debug.ftrace_events_disable`
enable_debug()
{
    echo "QCM6490 debug"
    #enable_lpm_with_dcvs_tracing
    #if [ ${ftrace_disable} != "Yes" ]; then
        enable_ftrace_event_tracing
    #fi
}

enable_debug
