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
    echo 1 > /sys/kernel/debug/tracing/events/sched/sched_migrate_task/enable
    echo 1 > /sys/kernel/debug/tracing/events/sched/sched_pi_setprio/enable
    echo 1 > /sys/kernel/debug/tracing/events/sched/sched_switch/enable
    echo 1 > /sys/kernel/debug/tracing/events/sched/sched_wakeup/enable
    echo 1 > /sys/kernel/debug/tracing/events/sched/sched_wakeup_new/enable
    # clock
    echo 1 > /sys/kernel/debug/tracing/events/power/clock_set_rate/enable
    echo 1 > /sys/kernel/debug/tracing/events/power/clock_enable/enable
    echo 1 > /sys/kernel/debug/tracing/events/power/clock_disable/enable
    echo 1 > /sys/kernel/debug/tracing/events/power/cpu_frequency/enable
    # regulator
    echo 1 > /sys/kernel/debug/tracing/events/regulator/enable
    #thermal
    echo 1 > /sys/kernel/debug/tracing/events/thermal/thermal_zone_trip/enable
    echo 1 > /sys/kernel/debug/tracing/events/thermal/thermal_temperature/enable
    echo 1 > /sys/kernel/debug/tracing/events/thermal/cdev_update/enable

    #rmph_send_msg
    echo 1 > /sys/kernel/debug/tracing/events/rpmh/rpmh_send_msg/enable

    #Enable irqsoff/preempt tracing
    echo 1 > /sys/kernel/debug/tracing/tracing_on
}

enable_rwmmio_events()
{
    if [ ! -d /sys/kernel/tracing/events/rwmmio ]
    then
        return
    fi

    local instance=/sys/kernel/tracing/instances/rwmmio

    mkdir $instance
    echo > $instance/trace
    echo > $instance/set_event

    echo 1 > $instance/events/rwmmio/rwmmio_read/enable
    echo 1 > $instance/events/rwmmio/rwmmio_write/enable

    echo 1 > $instance/tracing_on
}

# function to enable ftrace events
enable_ftrace_event_tracing()
{
    # bail out if its perf config
    if [ "$debug_build" = false ]
    then
        return
    fi

    # bail out if ftrace events aren't present
    if [ ! -d /sys/kernel/debug/tracing/events ]
    then
        return
    fi

    enable_rwmmio_events
    enable_tracing_events
}

config_dcc_gemnoc()
{
    CONFIG_PATH=$DCC_PATH/$1/config
    echo R 0x9103008 1 > $CONFIG_PATH
    echo L 0x5 2 0x9103010 0x9103014 > $CONFIG_PATH
    echo R 0x9103408 1 > $CONFIG_PATH
    echo L 0x5 2 0x9103410 0x9103414 > $CONFIG_PATH
    echo R 0x9143008 1 > $CONFIG_PATH
    echo L 0x5 2 0x9143010 0x9143014 > $CONFIG_PATH
    echo R 0x9143408 1 > $CONFIG_PATH
    echo L 0x5 2 0x9143410 0x9143414 > $CONFIG_PATH
    echo R 0x91B0008 1 > $CONFIG_PATH
    echo L 0x3 2 0x91B0010 0x91B0014 > $CONFIG_PATH
    echo R 0x91B1008 1 > $CONFIG_PATH
    echo L 0x10 2 0x91B1010 0x91B1014 > $CONFIG_PATH
    echo R 0x9101808 1 > $CONFIG_PATH
    echo R 0x910180C 1 > $CONFIG_PATH
    echo W 0x9101828 0x00000001 apb > $CONFIG_PATH
    echo L 0x41 4 0x9101810 0x9101814 0x9101818 0x910181C > $CONFIG_PATH
    echo R 0x9141808 1 > $CONFIG_PATH
    echo R 0x914180C 1 > $CONFIG_PATH
    echo W 0x9141828 0x00000001 apb > $CONFIG_PATH
    echo L 0x41 4 0x9141810 0x9141814 0x9141818 0x914181C > $CONFIG_PATH
    echo R 0x91A8008 1 > $CONFIG_PATH
    echo R 0x91A800C 1 > $CONFIG_PATH
    echo W 0x91A8028 0x00000001 apb > $CONFIG_PATH
    echo L 0x11 4 0x91A8010 0x91A8014 0x91A8018 0x91A801C > $CONFIG_PATH
    echo R 0x91A8808 1 > $CONFIG_PATH
    echo R 0x91A880C 1 > $CONFIG_PATH
    echo W 0x91A8828 0x00000001 apb > $CONFIG_PATH
    echo L 0x11 4 0x91A8810 0x91A8814 0x91A8818 0x91A881C > $CONFIG_PATH
    echo R 0x9100000   > $CONFIG_PATH
    echo R 0x9100008   > $CONFIG_PATH
    echo R 0x910000C   > $CONFIG_PATH
    echo R 0x9140000   > $CONFIG_PATH
    echo R 0x9140008   > $CONFIG_PATH
    echo R 0x914000C   > $CONFIG_PATH
    echo R 0x9180000   > $CONFIG_PATH
    echo R 0x9180008   > $CONFIG_PATH
    echo R 0x918000C   > $CONFIG_PATH
    echo R 0x9180404   > $CONFIG_PATH
    echo R 0x9180408   > $CONFIG_PATH
    echo R 0x918040C   > $CONFIG_PATH
    echo R 0x9181010   > $CONFIG_PATH
    echo R 0x9181020 8 > $CONFIG_PATH
    echo R 0x91E1048   > $CONFIG_PATH
    echo R 0x9121010   > $CONFIG_PATH
    echo R 0x9122010   > $CONFIG_PATH
    echo R 0x9123010   > $CONFIG_PATH
    echo R 0x9125010   > $CONFIG_PATH
    echo R 0x9161010   > $CONFIG_PATH
    echo R 0x9162010   > $CONFIG_PATH
    echo R 0x9163010   > $CONFIG_PATH
    echo R 0x9165010   > $CONFIG_PATH
    echo R 0x91CF010   > $CONFIG_PATH
    echo R 0x91D0010   > $CONFIG_PATH
    echo R 0x91D1010   > $CONFIG_PATH
    echo R 0x91D2010   > $CONFIG_PATH
    echo R 0x91D3010   > $CONFIG_PATH
    echo R 0x91D4010   > $CONFIG_PATH
    echo R 0x91D5010   > $CONFIG_PATH
    echo R 0x91D6010   > $CONFIG_PATH
    echo R 0x91D7010   > $CONFIG_PATH
    echo R 0x9101408   > $CONFIG_PATH
    echo R 0x9141410   > $CONFIG_PATH
    echo R 0x9100810   > $CONFIG_PATH
    echo R 0x9140810   > $CONFIG_PATH
    echo R 0x9100820   > $CONFIG_PATH
    echo R 0x9140820   > $CONFIG_PATH
    echo R 0x9100828   > $CONFIG_PATH
    echo R 0x910082C   > $CONFIG_PATH
    echo R 0x9140828   > $CONFIG_PATH
    echo R 0x914082C   > $CONFIG_PATH
}

config_aggrenoc()
{
    CONFIG_PATH=$DCC_PATH/$1/config
    echo R 0x16E4008 1 > $CONFIG_PATH
    echo L 0x4 2 0x16E4010 0x16E4014 > $CONFIG_PATH
    echo R 0x1706208 1 > $CONFIG_PATH
    echo L 0x4 2 0x1706210 0x1706214 > $CONFIG_PATH
    echo R 0x16E0000 1 > $CONFIG_PATH
    echo R 0x16E0010 1 > $CONFIG_PATH
    echo R 0x16E0008 1 > $CONFIG_PATH
    echo R 0x16E0020 8 > $CONFIG_PATH
    echo R 0x16E5048 1 > $CONFIG_PATH
    echo R 0x16E5248 1 > $CONFIG_PATH
    echo R 0x16E5448 1 > $CONFIG_PATH
    echo R 0x16E5100 5 > $CONFIG_PATH
    echo R 0x16E5300 2 > $CONFIG_PATH
    echo R 0x16E5500 2 > $CONFIG_PATH
    echo R 0x1700000 1 > $CONFIG_PATH
    echo R 0x1700008 1 > $CONFIG_PATH
    echo R 0x1700010 1 > $CONFIG_PATH
    echo R 0x1700020 8 > $CONFIG_PATH
    echo R 0x170B100 5 > $CONFIG_PATH
    echo R 0x170B048 1 > $CONFIG_PATH
}

config_confignoc()
{
    CONFIG_PATH=$DCC_PATH/$1/config
    echo R 0x01510008 1 > $CONFIG_PATH
    echo L 0x9 4 0x151D010 0x151D014 0x1506010 0x1506014 > $CONFIG_PATH
    echo R 0x151D208 1 > $CONFIG_PATH
    echo R 0x1507008 1 > $CONFIG_PATH
    echo L 0x2 4 0x151D210 0x151D214 0x1507010 0x1507014 > $CONFIG_PATH
    echo R 0x151D308 1 > $CONFIG_PATH
    echo R 0x1509008 1 > $CONFIG_PATH
    echo L 0x3 4 0x1509010 0x1509014 0x151D310 0x151D314 > $CONFIG_PATH
    echo R 0x01514008 1 > $CONFIG_PATH
    echo L 0x3 1 0x01514010 > $CONFIG_PATH
    echo R 0x1500000 1 > $CONFIG_PATH
    echo R 0x1500010 1 > $CONFIG_PATH
    echo R 0x1510010 1 > $CONFIG_PATH
    echo R 0x1500020 8 > $CONFIG_PATH
    echo R 0x1510020 8 > $CONFIG_PATH
    echo R 0x1511048 1 > $CONFIG_PATH
    echo R 0x1501048 2 > $CONFIG_PATH
    echo R 0x1501058 1 > $CONFIG_PATH
    echo R 0x1501248 1 > $CONFIG_PATH
    echo R 0x1511248 1 > $CONFIG_PATH
    echo R 0x1501248 1 > $CONFIG_PATH
    echo R 0x1511B00 1 > $CONFIG_PATH
    echo R 0x151E100 1 > $CONFIG_PATH
    echo R 0x150B100 1 > $CONFIG_PATH
}

config_mmssnoc(){
    CONFIG_PATH=$DCC_PATH/$1/config
    echo R 0x01741008 1 > $CONFIG_PATH
    echo L 0x9 2 0x01741010 0x01741014 > $CONFIG_PATH
    echo R 0x1740000 1 > $CONFIG_PATH
    echo R 0x1740008 1 > $CONFIG_PATH
    echo R 0x1740010 1 > $CONFIG_PATH
    echo R 0x1740020 8 > $CONFIG_PATH
    echo R 0x174B048 1 > $CONFIG_PATH
    echo R 0x174B100 8 > $CONFIG_PATH
    echo R 0x90E0000 > $CONFIG_PATH
    echo R 0x90E0008 > $CONFIG_PATH
    echo R 0x90E0010 > $CONFIG_PATH
    echo R 0x90E0020 8 > $CONFIG_PATH
    echo R 0x90E0248 > $CONFIG_PATH
    echo R 0x90E3100 > $CONFIG_PATH
    echo R 0x90E4100 7 > $CONFIG_PATH
    echo R 0x1750010   > $CONFIG_PATH
    echo R 0x1750190   > $CONFIG_PATH
    echo R 0x1751010   > $CONFIG_PATH
    echo R 0x1752010   > $CONFIG_PATH
    echo R 0x1754010   > $CONFIG_PATH
    echo R 0x1755010   > $CONFIG_PATH
    echo R 0x1756010   > $CONFIG_PATH
    echo R 0x1758010   > $CONFIG_PATH
    echo R 0x1758090   > $CONFIG_PATH
    echo R 0x1759010   > $CONFIG_PATH
    echo R 0x175A010   > $CONFIG_PATH
    echo R 0x175C010   > $CONFIG_PATH
    echo R 0x175D010   > $CONFIG_PATH
    echo R 0x175E010   > $CONFIG_PATH
}

config_sysnoc()
{
    CONFIG_PATH=$DCC_PATH/$1/config
    echo R 0x1680000 1 > $CONFIG_PATH
    echo R 0x1680008 1 > $CONFIG_PATH
    echo R 0x1680010 1 > $CONFIG_PATH
    echo R 0x1680020 8 > $CONFIG_PATH
    echo R 0x1680248 1 > $CONFIG_PATH
    echo R 0x1680B00 6 > $CONFIG_PATH
}

config_dcc_ddr()
{
    CONFIG_PATH=$DCC_PATH/$1/config
    echo R 0x9084208 1 > $CONFIG_PATH
    echo R 0x9084204 1 > $CONFIG_PATH
    echo R 0x9084108 1 > $CONFIG_PATH
    echo R 0x90841C0 1 > $CONFIG_PATH
    echo R 0x10C034 1 > $CONFIG_PATH
    echo R 0x10C038 1 > $CONFIG_PATH
    echo R 0x144018 1 > $CONFIG_PATH
    echo W 0x90841C0 0x1 > $CONFIG_PATH
    echo W 0x10C034 0x1 > $CONFIG_PATH
    echo W 0x10C038 0x1 > $CONFIG_PATH
    echo W 0x144018 0x1 > $CONFIG_PATH
    echo R 0x9050078 1 > $CONFIG_PATH
    echo R 0x9050110 8 > $CONFIG_PATH
    echo R 0x9080058 2 > $CONFIG_PATH
    echo R 0x90800C8 1 > $CONFIG_PATH
    echo R 0x90800D4 1 > $CONFIG_PATH
    echo R 0x90800E0 1 > $CONFIG_PATH
    echo R 0x90800FC 1 > $CONFIG_PATH
    echo R 0x9084030 1 > $CONFIG_PATH
    echo R 0x9084038 2 > $CONFIG_PATH
    echo R 0x90840E4 1 > $CONFIG_PATH
    echo R 0x90840F4 1 > $CONFIG_PATH
    echo R 0x9084104 2 > $CONFIG_PATH
    echo R 0x9084198 1 > $CONFIG_PATH
    echo R 0x9084804 1 > $CONFIG_PATH
    echo R 0x908480C 1 > $CONFIG_PATH
    echo R 0x9084844 1 > $CONFIG_PATH
    echo R 0x9084850 2 > $CONFIG_PATH
    echo R 0x9084860 3 > $CONFIG_PATH
    echo R 0x9084888 1 > $CONFIG_PATH
    echo R 0x908488c 1 > $CONFIG_PATH
    echo R 0x908409c 1 > $CONFIG_PATH
    echo R 0x90840a0 1 > $CONFIG_PATH
    echo R 0x908426c 1 > $CONFIG_PATH
    echo R 0x908439c 1 > $CONFIG_PATH
    echo R 0x9085124 1 > $CONFIG_PATH
    echo R 0x9085134 > $CONFIG_PATH
    echo R 0x9085138 > $CONFIG_PATH
    echo R 0x9084840 > $CONFIG_PATH
    echo R 0x9084834 > $CONFIG_PATH
    echo R 0x9085124 > $CONFIG_PATH
    echo R 0x90BA280 1 > $CONFIG_PATH
    echo R 0x90BA288 7 > $CONFIG_PATH
    echo R 0x9258610 4 > $CONFIG_PATH
    echo R 0x92D8610 4 > $CONFIG_PATH
    echo R 0x9220344 8 > $CONFIG_PATH
    echo R 0x9220370 6 > $CONFIG_PATH
    echo R 0x9220480 1 > $CONFIG_PATH
    echo R 0x9222400 1 > $CONFIG_PATH
    echo R 0x922240C 1 > $CONFIG_PATH
    echo R 0x9223214 2 > $CONFIG_PATH
    echo R 0x9223220 3 > $CONFIG_PATH
    echo R 0x9223308 1 > $CONFIG_PATH
    echo R 0x9223318 1 > $CONFIG_PATH
    echo R 0x9232100 1 > $CONFIG_PATH
    echo R 0x9236040 6 > $CONFIG_PATH
    echo R 0x92360B0 1 > $CONFIG_PATH
    echo R 0x923a004 4 > $CONFIG_PATH
    echo R 0x923E030 2 > $CONFIG_PATH
    echo R 0x9241000 1 > $CONFIG_PATH
    echo R 0x9242028 1 > $CONFIG_PATH
    echo R 0x9242044 3 > $CONFIG_PATH
    echo R 0x9242070 1 > $CONFIG_PATH
    echo R 0x9248030 1 > $CONFIG_PATH
    echo R 0x9248048 8 > $CONFIG_PATH
    echo R 0x9238030 1 > $CONFIG_PATH
    echo R 0x9238060 1 > $CONFIG_PATH
    echo R 0x9238064 1 > $CONFIG_PATH
    echo R 0x9238074 1 > $CONFIG_PATH
    echo R 0x9238088 1 > $CONFIG_PATH
    echo R 0x92380A0 1 > $CONFIG_PATH
    echo R 0x92380B0 1 > $CONFIG_PATH
    echo R 0x92A0344 8 > $CONFIG_PATH
    echo R 0x92A0370 6 > $CONFIG_PATH
    echo R 0x92A0480 1 > $CONFIG_PATH
    echo R 0x92A2400 1 > $CONFIG_PATH
    echo R 0x92A240C 1 > $CONFIG_PATH
    echo R 0x92A3214 2 > $CONFIG_PATH
    echo R 0x92A3220 3 > $CONFIG_PATH
    echo R 0x92A3308 1 > $CONFIG_PATH
    echo R 0x92A3318 1 > $CONFIG_PATH
    echo R 0x92B2100 1 > $CONFIG_PATH
    echo R 0x92B6040 6 > $CONFIG_PATH
    echo R 0x92B60B0 1 > $CONFIG_PATH
    echo R 0x92ba004 4 > $CONFIG_PATH
    echo R 0x92BE030 2 > $CONFIG_PATH
    echo R 0x92C1000 1 > $CONFIG_PATH
    echo R 0x92C2028 1 > $CONFIG_PATH
    echo R 0x92C2044 3 > $CONFIG_PATH
    echo R 0x92C2070 1 > $CONFIG_PATH
    echo R 0x92C8030 1 > $CONFIG_PATH
    echo R 0x92C8048 8 > $CONFIG_PATH
    echo R 0x92B8030 1 > $CONFIG_PATH
    echo R 0x92B8060 1 > $CONFIG_PATH
    echo R 0x92B8064 1 > $CONFIG_PATH
    echo R 0x92B8074 1 > $CONFIG_PATH
    echo R 0x92B8088 1 > $CONFIG_PATH
    echo R 0x92B80A0 1 > $CONFIG_PATH
    echo R 0x92B80B0 1 > $CONFIG_PATH
    echo R 0x92C8064 1 > $CONFIG_PATH
    echo R 0x9270080 1 > $CONFIG_PATH
    echo R 0x9270310 1 > $CONFIG_PATH
    echo R 0x9270400 1 > $CONFIG_PATH
    echo R 0x9270410 6 > $CONFIG_PATH
    echo R 0x9270430 1 > $CONFIG_PATH
    echo R 0x9270440 1 > $CONFIG_PATH
    echo R 0x9270448 1 > $CONFIG_PATH
    echo R 0x92704A0 1 > $CONFIG_PATH
    echo R 0x92704B0 1 > $CONFIG_PATH
    echo R 0x92704B8 2 > $CONFIG_PATH
    echo R 0x92704D0 2 > $CONFIG_PATH
    echo R 0x9271400 1 > $CONFIG_PATH
    echo R 0x9271408 1 > $CONFIG_PATH
    echo R 0x927341C 1 > $CONFIG_PATH
    echo R 0x9273420 1 > $CONFIG_PATH
    echo R 0x92753B0 1 > $CONFIG_PATH
    echo R 0x9275804 1 > $CONFIG_PATH
    echo R 0x9275C18 2 > $CONFIG_PATH
    echo R 0x9275C2C 1 > $CONFIG_PATH
    echo R 0x9275C38 1 > $CONFIG_PATH
    echo R 0x9276418 2 > $CONFIG_PATH
    echo R 0x9279100 1 > $CONFIG_PATH
    echo R 0x9279110 1 > $CONFIG_PATH
    echo R 0x9279120 1 > $CONFIG_PATH
    echo R 0x9279180 2 > $CONFIG_PATH
    echo R 0x92F0080 1 > $CONFIG_PATH
    echo R 0x92F0310 1 > $CONFIG_PATH
    echo R 0x92F0400 1 > $CONFIG_PATH
    echo R 0x92F0410 6 > $CONFIG_PATH
    echo R 0x92F0430 1 > $CONFIG_PATH
    echo R 0x92F0440 1 > $CONFIG_PATH
    echo R 0x92F0448 1 > $CONFIG_PATH
    echo R 0x92F04A0 1 > $CONFIG_PATH
    echo R 0x92F04B0 1 > $CONFIG_PATH
    echo R 0x92F04B8 2 > $CONFIG_PATH
    echo R 0x92F04D0 2 > $CONFIG_PATH
    echo R 0x92F1400 1 > $CONFIG_PATH
    echo R 0x92F1408 1 > $CONFIG_PATH
    echo R 0x92F341C 1 > $CONFIG_PATH
    echo R 0x92F3420 1 > $CONFIG_PATH
    echo R 0x92F53B0 1 > $CONFIG_PATH
    echo R 0x92F5804 1 > $CONFIG_PATH
    echo R 0x92F5C18 2 > $CONFIG_PATH
    echo R 0x92F5C2C 1 > $CONFIG_PATH
    echo R 0x92F5C38 1 > $CONFIG_PATH
    echo R 0x92F6418 2 > $CONFIG_PATH
    echo R 0x92F9100 1 > $CONFIG_PATH
    echo R 0x92F9110 1 > $CONFIG_PATH
    echo R 0x92F9120 1 > $CONFIG_PATH
    echo R 0x92F9180 2 > $CONFIG_PATH
    echo R 0x9260080 1 > $CONFIG_PATH
    echo R 0x9260400 1 > $CONFIG_PATH
    echo R 0x9260410 3 > $CONFIG_PATH
    echo R 0x9260420 2 > $CONFIG_PATH
    echo R 0x9260430 1 > $CONFIG_PATH
    echo R 0x9260440 1 > $CONFIG_PATH
    echo R 0x9260448 1 > $CONFIG_PATH
    echo R 0x92604A0 1 > $CONFIG_PATH
    echo R 0x92604B0 1 > $CONFIG_PATH
    echo R 0x92604B8 2 > $CONFIG_PATH
    echo R 0x92604D0 2 > $CONFIG_PATH
    echo R 0x9261400 1 > $CONFIG_PATH
    echo R 0x9263410 1 > $CONFIG_PATH
    echo R 0x92653B0 1 > $CONFIG_PATH
    echo R 0x9265804 1 > $CONFIG_PATH
    echo R 0x9265B1C 1 > $CONFIG_PATH
    echo R 0x9265B2C 1 > $CONFIG_PATH
    echo R 0x9265B38 1 > $CONFIG_PATH
    echo R 0x9269100 1 > $CONFIG_PATH
    echo R 0x9269108 1 > $CONFIG_PATH
    echo R 0x9269110 1 > $CONFIG_PATH
    echo R 0x9269118 1 > $CONFIG_PATH
    echo R 0x9269120 1 > $CONFIG_PATH
    echo R 0x9269180 2 > $CONFIG_PATH
    echo R 0x92E0080 1 > $CONFIG_PATH
    echo R 0x92E0400 1 > $CONFIG_PATH
    echo R 0x92E0410 3 > $CONFIG_PATH
    echo R 0x92E0420 2 > $CONFIG_PATH
    echo R 0x92E0430 1 > $CONFIG_PATH
    echo R 0x92E0440 1 > $CONFIG_PATH
    echo R 0x92E0448 1 > $CONFIG_PATH
    echo R 0x92E04A0 1 > $CONFIG_PATH
    echo R 0x92E04B0 1 > $CONFIG_PATH
    echo R 0x92E04B8 2 > $CONFIG_PATH
    echo R 0x92E04D0 2 > $CONFIG_PATH
    echo R 0x92E1400 1 > $CONFIG_PATH
    echo R 0x92E3410 1 > $CONFIG_PATH
    echo R 0x92E53B0 1 > $CONFIG_PATH
    echo R 0x92E5804 1 > $CONFIG_PATH
    echo R 0x92E5B1C 1 > $CONFIG_PATH
    echo R 0x92E5B2C 1 > $CONFIG_PATH
    echo R 0x92E5B38 1 > $CONFIG_PATH
    echo R 0x92E9100 1 > $CONFIG_PATH
    echo R 0x92e9108 1 > $CONFIG_PATH
    echo R 0x92E9110 1 > $CONFIG_PATH
    echo R 0x92E9118 1 > $CONFIG_PATH
    echo R 0x92E9120 1 > $CONFIG_PATH
    echo R 0x92E9180 2 > $CONFIG_PATH
    echo R 0x96B0868 1 > $CONFIG_PATH
    echo R 0x96B0870 1 > $CONFIG_PATH
    echo R 0x96B1004 1 > $CONFIG_PATH
    echo R 0x96B100C 1 > $CONFIG_PATH
    echo R 0x96B1014 1 > $CONFIG_PATH
    echo R 0x96B1204 1 > $CONFIG_PATH
    echo R 0x96B120C 1 > $CONFIG_PATH
    echo R 0x96B1214 1 > $CONFIG_PATH
    echo R 0x96B1504 1 > $CONFIG_PATH
    echo R 0x96B150C 1 > $CONFIG_PATH
    echo R 0x96B1514 1 > $CONFIG_PATH
    echo R 0x96B1604 1 > $CONFIG_PATH
    echo R 0x96B8100 1 > $CONFIG_PATH
    echo R 0x96B813C 1 > $CONFIG_PATH
    echo R 0x96B8500 1 > $CONFIG_PATH
    echo R 0x96B853C 1 > $CONFIG_PATH
    echo R 0x96B8A04 1 > $CONFIG_PATH
    echo R 0x96B8A18 1 > $CONFIG_PATH
    echo R 0x96B8EA8 1 > $CONFIG_PATH
    echo R 0x96B9044 1 > $CONFIG_PATH
    echo R 0x96B904C 1 > $CONFIG_PATH
    echo R 0x96B9054 1 > $CONFIG_PATH
    echo R 0x96B905C 1 > $CONFIG_PATH
    echo R 0x96B910C 2 > $CONFIG_PATH
    echo R 0x96B9204 1 > $CONFIG_PATH
    echo R 0x96B920C 1 > $CONFIG_PATH
    echo R 0x96B9238 1 > $CONFIG_PATH
    echo R 0x96B9240 1 > $CONFIG_PATH
    echo R 0x96B926C 1 > $CONFIG_PATH
    echo R 0x96B9394 1 > $CONFIG_PATH
    echo R 0x96B939C 1 > $CONFIG_PATH
    echo R 0x96B9704 1 > $CONFIG_PATH
    echo R 0x96B970C 1 > $CONFIG_PATH
    echo R 0x96F0868 1 > $CONFIG_PATH
    echo R 0x96F0870 1 > $CONFIG_PATH
    echo R 0x96F1004 1 > $CONFIG_PATH
    echo R 0x96F100C 1 > $CONFIG_PATH
    echo R 0x96F1014 1 > $CONFIG_PATH
    echo R 0x96F1204 1 > $CONFIG_PATH
    echo R 0x96F120C 1 > $CONFIG_PATH
    echo R 0x96F1214 1 > $CONFIG_PATH
    echo R 0x96F1504 1 > $CONFIG_PATH
    echo R 0x96F150C 1 > $CONFIG_PATH
    echo R 0x96F1514 1 > $CONFIG_PATH
    echo R 0x96F1604 1 > $CONFIG_PATH
    echo R 0x96F8100 1 > $CONFIG_PATH
    echo R 0x96F813C 1 > $CONFIG_PATH
    echo R 0x96F8500 1 > $CONFIG_PATH
    echo R 0x96F853C 1 > $CONFIG_PATH
    echo R 0x96F8A04 1 > $CONFIG_PATH
    echo R 0x96F8A18 1 > $CONFIG_PATH
    echo R 0x96F8EA8 1 > $CONFIG_PATH
    echo R 0x96F9044 1 > $CONFIG_PATH
    echo R 0x96F904C 1 > $CONFIG_PATH
    echo R 0x96F9054 1 > $CONFIG_PATH
    echo R 0x96F905C 1 > $CONFIG_PATH
    echo R 0x96F910C 2 > $CONFIG_PATH
    echo R 0x96F9204 1 > $CONFIG_PATH
    echo R 0x96F920C 1 > $CONFIG_PATH
    echo R 0x96F9238 1 > $CONFIG_PATH
    echo R 0x96F9240 1 > $CONFIG_PATH
    echo R 0x96F926C 1 > $CONFIG_PATH
    echo R 0x96F9394 1 > $CONFIG_PATH
    echo R 0x96F939C 1 > $CONFIG_PATH
    echo R 0x96F9704 1 > $CONFIG_PATH
    echo R 0x96F970C 1 > $CONFIG_PATH
    echo R 0x9130100 3 > $CONFIG_PATH
    echo R 0x9170100 3 > $CONFIG_PATH
    echo R 0x91DD100 4 > $CONFIG_PATH
    echo R 0x91DF100 1 > $CONFIG_PATH
    echo R 0x610110 5 > $CONFIG_PATH
    echo R 0x9230010 1 > $CONFIG_PATH
    echo R 0x9230020 1 > $CONFIG_PATH
    echo R 0x9230030 1 > $CONFIG_PATH
    echo R 0x9230040 1 > $CONFIG_PATH
    echo R 0x92B0010 1 > $CONFIG_PATH
    echo R 0x92B0020 1 > $CONFIG_PATH
    echo R 0x92B0030 1 > $CONFIG_PATH
    echo R 0x92B0040 1 > $CONFIG_PATH
    echo R 0x9232050 1 > $CONFIG_PATH
    echo R 0x923605C 2 > $CONFIG_PATH
    echo R 0x92360A0 1 > $CONFIG_PATH
    echo R 0x923A018 2 > $CONFIG_PATH
    echo R 0x92B2050 1 > $CONFIG_PATH
    echo R 0x92B605C 2 > $CONFIG_PATH
    echo R 0x92B60A0 1 > $CONFIG_PATH
    echo R 0x92BA018 2 > $CONFIG_PATH
    echo R 0x9222404 2 > $CONFIG_PATH
    echo R 0x9222410 1 > $CONFIG_PATH
    echo R 0x9238004 1 > $CONFIG_PATH
    echo R 0x9238014 1 > $CONFIG_PATH
    echo R 0x923805C 1 > $CONFIG_PATH
    echo R 0x923A014 1 > $CONFIG_PATH
    echo R 0x92A2404 2 > $CONFIG_PATH
    echo R 0x92A2410 1 > $CONFIG_PATH
    echo R 0x92B8004 1 > $CONFIG_PATH
    echo R 0x92B8014 1 > $CONFIG_PATH
    echo R 0x92B805C 1 > $CONFIG_PATH
    echo R 0x92BA014 1 > $CONFIG_PATH
    echo W 0x06E0A00C 0x00600007 apb > $CONFIG_PATH
    echo W 0x06E0A01C 0x00136800 apb > $CONFIG_PATH
    echo R 0x06E0A014 1 apb > $CONFIG_PATH
    echo W 0x06E0A01C 0x00136810 apb > $CONFIG_PATH
    echo R 0x06E0A014 1 apb > $CONFIG_PATH
    echo W 0x06E0A01C 0x00136820 apb > $CONFIG_PATH
    echo R 0x06E0A014 1 apb > $CONFIG_PATH
    echo W 0x06E0A01C 0x00136830 apb > $CONFIG_PATH
    echo R 0x06E0A014 1 apb > $CONFIG_PATH
    echo W 0x06E0A01C 0x00136840 apb > $CONFIG_PATH
    echo R 0x06E0A014 1 apb > $CONFIG_PATH
    echo W 0x06E0A01C 0x00136850 apb > $CONFIG_PATH
    echo R 0x06E0A014 1 apb > $CONFIG_PATH
    echo W 0x06E0A01C 0x00136860 apb > $CONFIG_PATH
    echo R 0x06E0A014 1 apb > $CONFIG_PATH
    echo W 0x06E0A01C 0x00136870 apb > $CONFIG_PATH
    echo R 0x06E0A014 1 apb > $CONFIG_PATH
    echo W 0x06E0A01C 0x0003e9a0 apb > $CONFIG_PATH
    echo W 0x06E0A01C 0x001368a0 apb > $CONFIG_PATH
    echo R 0x06E0A014 1 apb > $CONFIG_PATH
    echo W 0x06E0A01C 0x0003c0a0 apb > $CONFIG_PATH
    echo W 0x06E0A01C 0x001368a0 apb > $CONFIG_PATH
    echo R 0x06E0A014 1 apb > $CONFIG_PATH
    echo W 0x06E0A01C 0x0003d1a0 apb > $CONFIG_PATH
    echo W 0x06E0A01C 0x001368a0 apb > $CONFIG_PATH
    echo R 0x06E0A014 1 apb > $CONFIG_PATH
    echo W 0x06E0A01C 0x0003d2a0 apb > $CONFIG_PATH
    echo W 0x06E0A01C 0x001368a0 apb > $CONFIG_PATH
    echo R 0x06E0A014 1 apb > $CONFIG_PATH
    echo W 0x06E0A01C 0x0003d5a0 apb > $CONFIG_PATH
    echo W 0x06E0A01C 0x001368a0 apb > $CONFIG_PATH
    echo R 0x06E0A014 1 apb > $CONFIG_PATH
    echo W 0x06E0A01C 0x0003d6a0 apb > $CONFIG_PATH
    echo W 0x06E0A01C 0x001368a0 apb > $CONFIG_PATH
    echo R 0x06E0A014 1 apb > $CONFIG_PATH
    echo W 0x06E0A01C 0x0003e8a0 apb > $CONFIG_PATH
    echo W 0x06E0A01C 0x001368a0 apb > $CONFIG_PATH
    echo R 0x06E0A014 1 apb > $CONFIG_PATH
    echo W 0x06E0A01C 0x0003eea0 apb > $CONFIG_PATH
    echo W 0x06E0A01C 0x001368a0 apb > $CONFIG_PATH
    echo R 0x06E0A014 1 apb > $CONFIG_PATH
    echo W 0x06E0A01C 0x0003b1a0 apb > $CONFIG_PATH
    echo W 0x06E0A01C 0x001368a0 apb > $CONFIG_PATH
    echo R 0x06E0A014 1 apb > $CONFIG_PATH
    echo W 0x06E0A01C 0x0003b2a0 apb > $CONFIG_PATH
    echo W 0x06E0A01C 0x001368a0 apb > $CONFIG_PATH
    echo R 0x06E0A014 1 apb > $CONFIG_PATH
    echo W 0x06E0A01C 0x0003b5a0 apb > $CONFIG_PATH
    echo W 0x06E0A01C 0x001368a0 apb > $CONFIG_PATH
    echo R 0x06E0A014 1 apb > $CONFIG_PATH
    echo W 0x06E0A01C 0x0003b6a0 apb > $CONFIG_PATH
    echo W 0x06E0A01C 0x001368a0 apb > $CONFIG_PATH
    echo R 0x06E0A014 1 apb > $CONFIG_PATH
    echo W 0x06E0A01C 0x0003c2a0 apb > $CONFIG_PATH
    echo W 0x06E0A01C 0x001368a0 apb > $CONFIG_PATH
    echo R 0x06E0A014 1 apb > $CONFIG_PATH
    echo W 0x06E0A01C 0x0003c5a0 apb > $CONFIG_PATH
    echo W 0x06E0A01C 0x001368a0 apb > $CONFIG_PATH
    echo R 0x06E0A014 1 apb > $CONFIG_PATH
    echo W 0x06E0A01C 0x0003c6a0 apb > $CONFIG_PATH
    echo W 0x06E0A01C 0x001368a0 apb > $CONFIG_PATH
    echo R 0x06E0A014 1 apb > $CONFIG_PATH
    echo W 0x06E0A014 0x0002000c apb > $CONFIG_PATH
    echo W 0x06E0A01C 0x000368b0 apb > $CONFIG_PATH
    echo W 0x06E0A01C 0x00000ba8 apb > $CONFIG_PATH
    echo W 0x06E0A01C 0x0013B6A0 apb > $CONFIG_PATH
    echo W 0x06E0A01C 0x00F1E000 apb > $CONFIG_PATH
    echo W 0x06E0A008 0x00000007 apb > $CONFIG_PATH
    echo R 0x09067E00 124 > $CONFIG_PATH
    echo R 0x905000C > $CONFIG_PATH
    echo R 0x9050948 > $CONFIG_PATH
    echo R 0x9050078 > $CONFIG_PATH
    echo R 0x9050008 > $CONFIG_PATH
}

config_dcc_pcu()
{
    CONFIG_PATH=$DCC_PATH/$1/config
    echo R 0x18000010 1 > $CONFIG_PATH
    echo R 0x18000024 1 > $CONFIG_PATH
    echo R 0x18000038 6 > $CONFIG_PATH
    echo R 0x18010010 1 > $CONFIG_PATH
    echo R 0x18010024 1 > $CONFIG_PATH
    echo R 0x18010038 6 > $CONFIG_PATH
    echo R 0x18020010 1 > $CONFIG_PATH
    echo R 0x18020024 1 > $CONFIG_PATH
    echo R 0x18020038 6 > $CONFIG_PATH
    echo R 0x18030010 1 > $CONFIG_PATH
    echo R 0x18030024 1 > $CONFIG_PATH
    echo R 0x18030038 6 > $CONFIG_PATH
    echo R 0x18040010 1 > $CONFIG_PATH
    echo R 0x18040024 1 > $CONFIG_PATH
    echo R 0x18040038 6 > $CONFIG_PATH
    echo R 0x18050010 1 > $CONFIG_PATH
    echo R 0x18050024 1 > $CONFIG_PATH
    echo R 0x18050038 6 > $CONFIG_PATH
    echo R 0x18060010 1 > $CONFIG_PATH
    echo R 0x18060024 1 > $CONFIG_PATH
    echo R 0x18060038 6 > $CONFIG_PATH
    echo R 0x18070010 1 > $CONFIG_PATH
    echo R 0x18070024 1 > $CONFIG_PATH
    echo R 0x18070038 6 > $CONFIG_PATH
    echo R 0x18080010 1 > $CONFIG_PATH
    echo R 0x18080024 1 > $CONFIG_PATH
    echo R 0x18080038 6 > $CONFIG_PATH
    echo R 0x1808006C 5 > $CONFIG_PATH
    echo R 0x18080084 1 > $CONFIG_PATH
    echo R 0x180800F4 1 > $CONFIG_PATH
    echo R 0x180800F8 1 > $CONFIG_PATH
    echo R 0x180800FC 1 > $CONFIG_PATH
    echo R 0x18080100 12 > $CONFIG_PATH
    echo R 0x18080130 3 > $CONFIG_PATH
    echo R 0x18080158 1 > $CONFIG_PATH
    echo R 0x1808015C 1 > $CONFIG_PATH
    echo R 0x18080160 1 > $CONFIG_PATH
    echo R 0x18080164 1 > $CONFIG_PATH
    echo R 0x18080168 1 > $CONFIG_PATH
    echo R 0x18080170 1 > $CONFIG_PATH
    echo R 0x18080174 1 > $CONFIG_PATH
    echo R 0x18080188 1 > $CONFIG_PATH
    echo R 0x1808018C 1 > $CONFIG_PATH
    echo R 0x18080190 1 > $CONFIG_PATH
    echo R 0x18080194 1 > $CONFIG_PATH
    echo R 0x18080198 1 > $CONFIG_PATH
    echo R 0x180801AC 1 > $CONFIG_PATH
    echo R 0x180801B0 4 > $CONFIG_PATH
    echo R 0x180801C0 1 > $CONFIG_PATH
    echo R 0x180801C8 1 > $CONFIG_PATH
    echo R 0x180801F0 1 > $CONFIG_PATH
}

config_dcc_epss ()
{
    CONFIG_PATH=$DCC_PATH/$1/config
    echo R 0x18598020 1 > $CONFIG_PATH
    echo R 0x1859001C 1 > $CONFIG_PATH
    echo R 0x18590020 1 > $CONFIG_PATH
    echo R 0x1859002C 1 > $CONFIG_PATH
    echo R 0x18590064 1 > $CONFIG_PATH
    echo R 0x18590068 1 > $CONFIG_PATH
    echo R 0x1859006C 1 > $CONFIG_PATH
    echo R 0x18590070 3 > $CONFIG_PATH
    echo R 0x1859008C 1 > $CONFIG_PATH
    echo R 0x185900DC 1 > $CONFIG_PATH
    echo R 0x185900E8 1 > $CONFIG_PATH
    echo R 0x185900EC 1 > $CONFIG_PATH
    echo R 0x185900F0 1 > $CONFIG_PATH
    echo R 0x18590300 1 > $CONFIG_PATH
    echo R 0x1859030C 1 > $CONFIG_PATH
    echo R 0x18590320 1 > $CONFIG_PATH
    echo R 0x1859034C 1 > $CONFIG_PATH
    echo R 0x185903BC 1 > $CONFIG_PATH
    echo R 0x185903C0 1 > $CONFIG_PATH
    echo R 0x1859101C 1 > $CONFIG_PATH
    echo R 0x18591020 1 > $CONFIG_PATH
    echo R 0x1859102C 1 > $CONFIG_PATH
    echo R 0x18591064 1 > $CONFIG_PATH
    echo R 0x18591068 1 > $CONFIG_PATH
    echo R 0x1859106C 1 > $CONFIG_PATH
    echo R 0x18591070 1 > $CONFIG_PATH
    echo R 0x18591074 1 > $CONFIG_PATH
    echo R 0x18591078 1 > $CONFIG_PATH
    echo R 0x1859108C 1 > $CONFIG_PATH
    echo R 0x185910DC 1 > $CONFIG_PATH
    echo R 0x185910E8 1 > $CONFIG_PATH
    echo R 0x185910EC 1 > $CONFIG_PATH
    echo R 0x185910F0 1 > $CONFIG_PATH
    echo R 0x18591300 1 > $CONFIG_PATH
    echo R 0x1859130C 1 > $CONFIG_PATH
    echo R 0x18591320 1 > $CONFIG_PATH
    echo R 0x1859134C 1 > $CONFIG_PATH
    echo R 0x185913BC 1 > $CONFIG_PATH
    echo R 0x185913C0 1 > $CONFIG_PATH
    echo R 0x1859201C 1 > $CONFIG_PATH
    echo R 0x18592020 1 > $CONFIG_PATH
    echo R 0x1859202C 1 > $CONFIG_PATH
    echo R 0x18592064 1 > $CONFIG_PATH
    echo R 0x18592068 1 > $CONFIG_PATH
    echo R 0x1859206C 1 > $CONFIG_PATH
    echo R 0x18592070 1 > $CONFIG_PATH
    echo R 0x18592074 1 > $CONFIG_PATH
    echo R 0x18592078 1 > $CONFIG_PATH
    echo R 0x1859208C 1 > $CONFIG_PATH
    echo R 0x185920DC 1 > $CONFIG_PATH
    echo R 0x185920E8 1 > $CONFIG_PATH
    echo R 0x185920EC 1 > $CONFIG_PATH
    echo R 0x185920F0 1 > $CONFIG_PATH
    echo R 0x18592300 1 > $CONFIG_PATH
    echo R 0x1859230C 1 > $CONFIG_PATH
    echo R 0x18592320 1 > $CONFIG_PATH
    echo R 0x1859234C 1 > $CONFIG_PATH
    echo R 0x185923BC 1 > $CONFIG_PATH
    echo R 0x185923C0 1 > $CONFIG_PATH
    echo R 0x1859301C 1 > $CONFIG_PATH
    echo R 0x18593020 1 > $CONFIG_PATH
    echo R 0x18593064 1 > $CONFIG_PATH
    echo R 0x18593068 1 > $CONFIG_PATH
    echo R 0x1859306C 1 > $CONFIG_PATH
    echo R 0x18593070 1 > $CONFIG_PATH
    echo R 0x18593074 1 > $CONFIG_PATH
    echo R 0x18593078 1 > $CONFIG_PATH
    echo R 0x1859308C 1 > $CONFIG_PATH
    echo R 0x185930DC 1 > $CONFIG_PATH
    echo R 0x185930E8 1 > $CONFIG_PATH
    echo R 0x185930EC 1 > $CONFIG_PATH
    echo R 0x185930F0 1 > $CONFIG_PATH
    echo R 0x18593300 1 > $CONFIG_PATH
    echo R 0x1859330C 1 > $CONFIG_PATH
    echo R 0x18593320 1 > $CONFIG_PATH
    echo R 0x1859302C 1 > $CONFIG_PATH
    echo R 0x1859334C 1 > $CONFIG_PATH
    echo R 0x185933BC 1 > $CONFIG_PATH
    echo R 0x185933C0 1 > $CONFIG_PATH
    echo R 0x18300000 1 > $CONFIG_PATH
    echo R 0x1830000C 1 > $CONFIG_PATH
    echo R 0x18300018 1 > $CONFIG_PATH
    echo R 0x17C21000 2 > $CONFIG_PATH
    echo R 0x18393A84 2 > $CONFIG_PATH
    echo R 0x183A3A84 2 > $CONFIG_PATH
    echo R 0x18280000 2 > $CONFIG_PATH
    echo R 0x18282000 2 > $CONFIG_PATH
    echo R 0x18284000 2 > $CONFIG_PATH
    echo R 0x18286000 2 > $CONFIG_PATH
    echo R 0x18300000 1 > $CONFIG_PATH
    echo R 0x18200400 3 > $CONFIG_PATH
    echo R 0x18200038 1 > $CONFIG_PATH
    echo R 0x18200040 1 > $CONFIG_PATH
    echo R 0x18200048 1 > $CONFIG_PATH
    echo R 0x18220038 1 > $CONFIG_PATH
    echo R 0x18220040 1 > $CONFIG_PATH
    echo R 0x182200D0 1 > $CONFIG_PATH
    echo R 0x18200030 1 > $CONFIG_PATH
    echo R 0x18200010 1 > $CONFIG_PATH
}

config_dcc_pimem()
{
    CONFIG_PATH=$DCC_PATH/$1/config
    echo R 0x610100 11 > $CONFIG_PATH
}

config_dcc_core()
{
    CONFIG_PATH=$DCC_PATH/$1/config
    echo R 0x18000058 1 > $CONFIG_PATH
    echo R 0x1800005C 1 > $CONFIG_PATH
    echo R 0x18000060 1 > $CONFIG_PATH
    echo R 0x18000064 1 > $CONFIG_PATH
    echo R 0x1800006C 1 > $CONFIG_PATH
    echo R 0x180000F0 2 > $CONFIG_PATH
    echo R 0x18010058 1 > $CONFIG_PATH
    echo R 0x1801005C 1 > $CONFIG_PATH
    echo R 0x18010060 1 > $CONFIG_PATH
    echo R 0x18010064 1 > $CONFIG_PATH
    echo R 0x1801006C 1 > $CONFIG_PATH
    echo R 0x180100F0 2 > $CONFIG_PATH
    echo R 0x18020058 1 > $CONFIG_PATH
    echo R 0x1802005C 1 > $CONFIG_PATH
    echo R 0x18020060 1 > $CONFIG_PATH
    echo R 0x18020064 1 > $CONFIG_PATH
    echo R 0x1802006C 1 > $CONFIG_PATH
    echo R 0x180200F0 2 > $CONFIG_PATH
    echo R 0x18030058 1 > $CONFIG_PATH
    echo R 0x1803005C 1 > $CONFIG_PATH
    echo R 0x18030060 1 > $CONFIG_PATH
    echo R 0x18030064 1 > $CONFIG_PATH
    echo R 0x1803006C 1 > $CONFIG_PATH
    echo R 0x180300F0 2 > $CONFIG_PATH
    echo R 0x18040058 1 > $CONFIG_PATH
    echo R 0x1804005C 1 > $CONFIG_PATH
    echo R 0x18040060 1 > $CONFIG_PATH
    echo R 0x18040064 1 > $CONFIG_PATH
    echo R 0x1804006C 1 > $CONFIG_PATH
    echo R 0x180400F0 2 > $CONFIG_PATH
    echo R 0x18050058 1 > $CONFIG_PATH
    echo R 0x1805005C 1 > $CONFIG_PATH
    echo R 0x18050060 1 > $CONFIG_PATH
    echo R 0x18050064 1 > $CONFIG_PATH
    echo R 0x1805006C 1 > $CONFIG_PATH
    echo R 0x180500F0 2 > $CONFIG_PATH
    echo R 0x18060058 1 > $CONFIG_PATH
    echo R 0x1806005C 1 > $CONFIG_PATH
    echo R 0x18060060 1 > $CONFIG_PATH
    echo R 0x18060064 1 > $CONFIG_PATH
    echo R 0x1806006C 1 > $CONFIG_PATH
    echo R 0x180600F0 2 > $CONFIG_PATH
    echo R 0x18070058 1 > $CONFIG_PATH
    echo R 0x1807005C 1 > $CONFIG_PATH
    echo R 0x18070060 1 > $CONFIG_PATH
    echo R 0x18070064 1 > $CONFIG_PATH
    echo R 0x1807006C 1 > $CONFIG_PATH
    echo R 0x180700F0 2 > $CONFIG_PATH
    echo R 0x18101908 1 > $CONFIG_PATH
    echo R 0x18101C18 1 > $CONFIG_PATH
    echo R 0x18390810 1 > $CONFIG_PATH
    echo R 0x18390C50 1 > $CONFIG_PATH
    echo R 0x18390814 1 > $CONFIG_PATH
    echo R 0x18390C54 1 > $CONFIG_PATH
    echo R 0x18390818 1 > $CONFIG_PATH
    echo R 0x18390C58 1 > $CONFIG_PATH
    echo R 0x18393A84 2 > $CONFIG_PATH
    echo R 0x18100908 1 > $CONFIG_PATH
    echo R 0x18100C18 1 > $CONFIG_PATH
    echo R 0x183A0810 1 > $CONFIG_PATH
    echo R 0x183A0C50 1 > $CONFIG_PATH
    echo R 0x183A0814 1 > $CONFIG_PATH
    echo R 0x183A0C54 1 > $CONFIG_PATH
    echo R 0x183A0818 1 > $CONFIG_PATH
    echo R 0x183A0C58 1 > $CONFIG_PATH
    echo R 0x183A3A84 2 > $CONFIG_PATH
    echo R 0x18393500 1 > $CONFIG_PATH
    echo R 0x18393580 1 > $CONFIG_PATH
    echo R 0x183A3500 1 > $CONFIG_PATH
    echo R 0x183A3580 1 > $CONFIG_PATH
    echo R 0x18282000 4 > $CONFIG_PATH
    echo R 0x18282028 1 > $CONFIG_PATH
    echo R 0x18282038 1 > $CONFIG_PATH
    echo R 0x18282080 5 > $CONFIG_PATH
    echo R 0x18286000 4 > $CONFIG_PATH
    echo R 0x18286028 1 > $CONFIG_PATH
    echo R 0x18286038 1 > $CONFIG_PATH
    echo R 0x18286080 5 > $CONFIG_PATH
    echo R 0x0C201244 1 > $CONFIG_PATH
    echo R 0x0C202244 1 > $CONFIG_PATH
    echo R 0x18300000 1 > $CONFIG_PATH
    echo R 0x1829208C 1 > $CONFIG_PATH
    echo R 0x18292098 1 > $CONFIG_PATH
    echo R 0x18292098 1 > $CONFIG_PATH
    echo R 0x1829608C 1 > $CONFIG_PATH
    echo R 0x18296098 1 > $CONFIG_PATH
    echo R 0x18296098 1 > $CONFIG_PATH
  echo R 0x00784184 1 > $CONFIG_PATH
}

config_dcc_gpu()
{
    CONFIG_PATH=$DCC_PATH/$1/config
    echo R 0x3D9100C >   $CONFIG_PATH
    echo R 0x3D91010 2 > $CONFIG_PATH
    echo R 0x3D9106C 3 > $CONFIG_PATH
    echo R 0x3D91004 >   $CONFIG_PATH
    echo R 0x3D91054 3 > $CONFIG_PATH
    echo R 0x3D91060 2 > $CONFIG_PATH
    echo R 0x3D91070 2 > $CONFIG_PATH
    echo R 0x3D91080 3 > $CONFIG_PATH
    echo R 0x3D91078 2 > $CONFIG_PATH
    echo R 0x3D9108C >   $CONFIG_PATH
    echo R 0x3D91090 >   $CONFIG_PATH
    echo R 0x3D91098 2 > $CONFIG_PATH
    echo R 0x3D910A4 2 > $CONFIG_PATH
    echo R 0x3D910F0 2 > $CONFIG_PATH
    echo R 0x3D91100 > $CONFIG_PATH
    echo R 0x3D91118 >   $CONFIG_PATH
    echo R 0x3D91164 2 > $CONFIG_PATH
    echo R 0x3D91170 >   $CONFIG_PATH
    echo R 0x3D91178 >   $CONFIG_PATH
    echo R 0x3D91204 >   $CONFIG_PATH
    echo R 0x3D9120C >   $CONFIG_PATH
    echo R 0x3D98024 >   $CONFIG_PATH
    echo R 0x3D9802C >   $CONFIG_PATH
    echo R 0x3D98030 >   $CONFIG_PATH
    echo R 0x3D92000 2 > $CONFIG_PATH
    echo R 0x3D93000 2 > $CONFIG_PATH
    echo R 0x3D95000 2 > $CONFIG_PATH
    echo R 0x3D96000 2 > $CONFIG_PATH
    echo R 0x3D97000 2 > $CONFIG_PATH
    echo R 0x119000 > $CONFIG_PATH
    echo R 0x11903C > $CONFIG_PATH
    echo R 0x171004 2 > $CONFIG_PATH
    echo R 0x17100C > $CONFIG_PATH
    echo R 0x171014 > $CONFIG_PATH
    echo R 0x171018 > $CONFIG_PATH
    echo R 0x171154 > $CONFIG_PATH
    echo R 0x171158 > $CONFIG_PATH
    echo R 0x17115C > $CONFIG_PATH
    echo R 0x17A04C > $CONFIG_PATH
    echo R 0x17B000 > $CONFIG_PATH
    echo R 0x17B03C > $CONFIG_PATH
    echo R 0x17C000 > $CONFIG_PATH
    echo R 0x17C03C > $CONFIG_PATH
    echo R 0x17D000 > $CONFIG_PATH
    echo R 0x17D03C > $CONFIG_PATH
    echo R 0x17E000 > $CONFIG_PATH
    echo R 0x17E03C > $CONFIG_PATH
    echo R 0x187000 > $CONFIG_PATH
    echo R 0x18703C > $CONFIG_PATH
    echo R 0x3D91534 > $CONFIG_PATH
    echo R 0x3D002B4 > $CONFIG_PATH
    echo R 0x3D00410 2 > $CONFIG_PATH
    echo R 0x3D00818 > $CONFIG_PATH
    echo R 0x3D7E220 2 > $CONFIG_PATH
}

config_dcc_spmi ()
{
    CONFIG_PATH=$DCC_PATH/$1/config
    echo R 0xC410000 > $CONFIG_PATH
    echo R 0xC40AF04 > $CONFIG_PATH
    echo R 0xC40AF10 > $CONFIG_PATH
    echo R 0xC40A000 > $CONFIG_PATH
    echo R 0xC40A018 > $CONFIG_PATH
    echo R 0xC40A028 > $CONFIG_PATH
    echo R 0xC40A02C > $CONFIG_PATH
    echo R 0xC40A100 > $CONFIG_PATH
    echo R 0xC2A22FC > $CONFIG_PATH
    echo R 0xC2A2300 > $CONFIG_PATH
    echo R 0xC2A2304 > $CONFIG_PATH
    echo R 0xC440200 > $CONFIG_PATH
    echo R 0xC440204 > $CONFIG_PATH
    echo R 0xC442200 > $CONFIG_PATH
    echo R 0xC442204 > $CONFIG_PATH
    echo R 0xC442208 > $CONFIG_PATH
    echo R 0xC44220C > $CONFIG_PATH
}

config_dcc_gcc()
{
    CONFIG_PATH=$DCC_PATH/$1/config
    echo R 0x100000 15 > $CONFIG_PATH
    echo R 0x101000 15 > $CONFIG_PATH
    echo R 0x176000 15 > $CONFIG_PATH
    echo R 0x174000 15 > $CONFIG_PATH
    echo R 0x113000 15 > $CONFIG_PATH
    echo R 0x11A000 15 > $CONFIG_PATH
    echo R 0x11C000 15 > $CONFIG_PATH
    echo R 0x11C048 3  > $CONFIG_PATH
    echo R 0x11E000 15 > $CONFIG_PATH
    echo R 0x10401C    > $CONFIG_PATH
    echo R 0x105074    > $CONFIG_PATH
    echo R 0x183024    > $CONFIG_PATH
    echo R 0x109050    > $CONFIG_PATH
    echo R 0x123020    > $CONFIG_PATH
    echo R 0x117024    > $CONFIG_PATH
    echo R 0x117154    > $CONFIG_PATH
    echo R 0x117284    > $CONFIG_PATH
    echo R 0x1173B4    > $CONFIG_PATH
    echo R 0x1174E4    > $CONFIG_PATH
    echo R 0x117614    > $CONFIG_PATH
    echo R 0x117744    > $CONFIG_PATH
    echo R 0x117874    > $CONFIG_PATH
    echo R 0x118024    > $CONFIG_PATH
    echo R 0x118154    > $CONFIG_PATH
    echo R 0x118284    > $CONFIG_PATH
    echo R 0x1183B4    > $CONFIG_PATH
    echo R 0x1184E4    > $CONFIG_PATH
    echo R 0x118614    > $CONFIG_PATH
    echo R 0x118744    > $CONFIG_PATH
    echo R 0x118874    > $CONFIG_PATH
    echo R 0x129020    > $CONFIG_PATH
    echo R 0x11D020    > $CONFIG_PATH
    echo R 0x134024    > $CONFIG_PATH
    echo R 0x141024    > $CONFIG_PATH
    echo R 0x14415C    > $CONFIG_PATH
    echo R 0x14504C    > $CONFIG_PATH
    echo R 0x18903C    > $CONFIG_PATH
    echo R 0x151000    > $CONFIG_PATH
    echo R 0x151008    > $CONFIG_PATH
    echo R 0x151010    > $CONFIG_PATH
    echo R 0x152000    > $CONFIG_PATH
    echo R 0x152008    > $CONFIG_PATH
    echo R 0x152010    > $CONFIG_PATH
    echo R 0x153020    > $CONFIG_PATH
    echo R 0x153028    > $CONFIG_PATH
    echo R 0x153030    > $CONFIG_PATH
    echo R 0x155000    > $CONFIG_PATH
    echo R 0x155008    > $CONFIG_PATH
    echo R 0x155010    > $CONFIG_PATH
    echo R 0x15B000    > $CONFIG_PATH
    echo R 0x15B008    > $CONFIG_PATH
    echo R 0x15B010    > $CONFIG_PATH
    echo R 0x157000    > $CONFIG_PATH
    echo R 0x157008    > $CONFIG_PATH
    echo R 0x157010    > $CONFIG_PATH
    echo R 0x135020    > $CONFIG_PATH
    echo R 0x135028    > $CONFIG_PATH
    echo R 0x135030    > $CONFIG_PATH
    echo R 0x156000    > $CONFIG_PATH
    echo R 0x156008    > $CONFIG_PATH
    echo R 0x156010    > $CONFIG_PATH
    echo R 0x15A000    > $CONFIG_PATH
    echo R 0x15A008    > $CONFIG_PATH
    echo R 0x15A010    > $CONFIG_PATH
    echo R 0x190004    > $CONFIG_PATH
    echo R 0x109008    > $CONFIG_PATH
    echo R 0x190010    > $CONFIG_PATH
    echo R 0x190020    > $CONFIG_PATH
    echo R 0x190028    > $CONFIG_PATH
    echo R 0x109010    > $CONFIG_PATH
    echo R 0x109018    > $CONFIG_PATH
    echo R 0x109018    > $CONFIG_PATH
    echo R 0x109020    > $CONFIG_PATH
    echo R 0x18D080    > $CONFIG_PATH
    echo R 0x145014    > $CONFIG_PATH
    echo R 0x14501C    > $CONFIG_PATH
    echo R 0x183004    > $CONFIG_PATH
    echo R 0x183008    > $CONFIG_PATH
    echo R 0x183140    > $CONFIG_PATH
    echo R 0x171158 2  > $CONFIG_PATH
    echo R 0x109004 3  > $CONFIG_PATH
    echo R 0x109160 1  > $CONFIG_PATH
    echo R 0x109468 1  > $CONFIG_PATH
    echo R 0x10F004 3  > $CONFIG_PATH
    echo R 0x145000 3  > $CONFIG_PATH
    echo R 0x16B004 3  > $CONFIG_PATH
    echo R 0x18D004 3  > $CONFIG_PATH
    echo R 0x177004 3  > $CONFIG_PATH
    echo R 0x189004 3  > $CONFIG_PATH
    echo R 0x153000 9  > $CONFIG_PATH
    echo R 0x135000 9  > $CONFIG_PATH
    echo R 0x106100    > $CONFIG_PATH
    echo R 0x147004    > $CONFIG_PATH
    echo R 0x17B000 1 > $CONFIG_PATH
    echo R 0x17B004 1 > $CONFIG_PATH
    echo R 0x17B008 1 > $CONFIG_PATH
    echo R 0x17B00C 1 > $CONFIG_PATH
    echo R 0x17B010 1 > $CONFIG_PATH
    echo R 0x17B014 1 > $CONFIG_PATH
    echo R 0x17B018 1 > $CONFIG_PATH
    echo R 0x17B01C 1 > $CONFIG_PATH
    echo R 0x17B020 1 > $CONFIG_PATH
    echo R 0x17B024 1 > $CONFIG_PATH
    echo R 0x17B028 1 > $CONFIG_PATH
    echo R 0x17B02C 1 > $CONFIG_PATH
    echo R 0x17B03C 1 > $CONFIG_PATH
    echo R 0x17B040 1 > $CONFIG_PATH
    echo R 0x17B044 1 > $CONFIG_PATH
    echo R 0x17B048 1 > $CONFIG_PATH
    echo R 0x17B04C 1 > $CONFIG_PATH
    echo R 0x17B050 1 > $CONFIG_PATH
    echo R 0x17B054 1 > $CONFIG_PATH
    echo R 0x17B058 1 > $CONFIG_PATH
    echo R 0x17B05C 1 > $CONFIG_PATH
    echo R 0x17B060 1 > $CONFIG_PATH
    echo R 0x17B064 1 > $CONFIG_PATH
    echo R 0x17B068 1 > $CONFIG_PATH
    echo R 0x17C000 1 > $CONFIG_PATH
    echo R 0x17C004 1 > $CONFIG_PATH
    echo R 0x17C008 1 > $CONFIG_PATH
    echo R 0x17C00C 1 > $CONFIG_PATH
    echo R 0x17C010 1 > $CONFIG_PATH
    echo R 0x17C014 1 > $CONFIG_PATH
    echo R 0x17C018 1 > $CONFIG_PATH
    echo R 0x17C01C 1 > $CONFIG_PATH
    echo R 0x17C020 1 > $CONFIG_PATH
    echo R 0x17C024 1 > $CONFIG_PATH
    echo R 0x17C028 1 > $CONFIG_PATH
    echo R 0x17C02C 1 > $CONFIG_PATH
    echo R 0x17C03C 1 > $CONFIG_PATH
    echo R 0x17C040 1 > $CONFIG_PATH
    echo R 0x17C044 1 > $CONFIG_PATH
    echo R 0x17C048 1 > $CONFIG_PATH
    echo R 0x17C04C 1 > $CONFIG_PATH
    echo R 0x17C050 1 > $CONFIG_PATH
    echo R 0x17C054 1 > $CONFIG_PATH
    echo R 0x17C058 1 > $CONFIG_PATH
    echo R 0x17C05C 1 > $CONFIG_PATH
    echo R 0x17C060 1 > $CONFIG_PATH
    echo R 0x17C064 1 > $CONFIG_PATH
    echo R 0x17C068 1 > $CONFIG_PATH
    echo R 0x153124    > $CONFIG_PATH
    echo R 0x156124    > $CONFIG_PATH
    echo R 0x1453A4    > $CONFIG_PATH
    echo R 0x182884    > $CONFIG_PATH
    echo R 0x145384    > $CONFIG_PATH
    echo R 0xC2A0000 15 > $CONFIG_PATH
    echo R 0xC2A1000 15 > $CONFIG_PATH
    echo R 0x17101C 1 > $CONFIG_PATH
    echo R 0x171020 1 > $CONFIG_PATH
    echo R 0x14401C 1 > $CONFIG_PATH
    echo R 0x144020 1 > $CONFIG_PATH
    echo R 0x183010 1 > $CONFIG_PATH
    echo R 0x183014 1 > $CONFIG_PATH
    echo R 0x18A160 1 > $CONFIG_PATH
    echo R 0x18A164 1 > $CONFIG_PATH
    echo R 0x18A004 1 > $CONFIG_PATH
    echo R 0x18A01C 1 > $CONFIG_PATH
    echo R 0x18A020 1 > $CONFIG_PATH
    echo R 0x18A024 1 > $CONFIG_PATH
    echo R 0x19D004 1 > $CONFIG_PATH
    echo R 0x19D008 1 > $CONFIG_PATH
    echo R 0x196100 1 > $CONFIG_PATH
    echo R 0x10003C 1 > $CONFIG_PATH
    echo R 0x10103C 1 > $CONFIG_PATH
    echo R 0x10203C 1 > $CONFIG_PATH
    echo R 0x10303C 1 > $CONFIG_PATH
    echo R 0x11303C 1 > $CONFIG_PATH
    echo R 0x11A03C 1 > $CONFIG_PATH
    echo R 0x11C03C 1 > $CONFIG_PATH
    echo R 0x17403C 1 > $CONFIG_PATH
    echo R 0x17603C 1 > $CONFIG_PATH
    echo R 0x11E03C 1 > $CONFIG_PATH
    echo R 0xBBF0004 12 > $CONFIG_PATH
    echo R 0xBBF0800 12 > $CONFIG_PATH
    echo R 0xBBF0004 12 > $CONFIG_PATH
    echo R 0xBBF0800 12 > $CONFIG_PATH
}

config_dcc_gic()
{
    CONFIG_PATH=$DCC_PATH/$1/config
    echo R 0x17A00104 29 > $CONFIG_PATH
    echo R 0x17A00204 29 > $CONFIG_PATH
}

config_dcc_limits()
{
    CONFIG_PATH=$DCC_PATH/$1/config
    echo R 0xEC80004 1 > $CONFIG_PATH
    echo R 0xEC80058 1 > $CONFIG_PATH
    echo R 0xEC80060 8 > $CONFIG_PATH
    echo R 0xEC800A0 8 > $CONFIG_PATH
    echo R 0xEC800C0 16 > $CONFIG_PATH
    echo R 0xEC80100 16 > $CONFIG_PATH
    echo R 0x634008 1 > $CONFIG_PATH
    echo R 0x634F00 8 > $CONFIG_PATH
    echo R 0x635560 1 > $CONFIG_PATH
    echo R 0x635570 1 > $CONFIG_PATH
    echo R 0x635580 1 > $CONFIG_PATH
    echo R 0x635590 1 > $CONFIG_PATH
    echo R 0x6355A0 4 > $CONFIG_PATH
    echo R 0x635600 1 > $CONFIG_PATH
    echo R 0x635610 1 > $CONFIG_PATH
    echo R 0x636008 1 > $CONFIG_PATH
    echo R 0x636F00 8 > $CONFIG_PATH
    echo R 0x637560 1 > $CONFIG_PATH
    echo R 0x637570 1 > $CONFIG_PATH
    echo R 0x637580 1 > $CONFIG_PATH
    echo R 0x637590 1 > $CONFIG_PATH
    echo R 0x6375A0 4 > $CONFIG_PATH
    echo R 0x637600 1 > $CONFIG_PATH
    echo R 0x637610 1 > $CONFIG_PATH
    echo R 0x18370220 2 > $CONFIG_PATH
    echo R 0x183702A0 2 > $CONFIG_PATH
    echo R 0x183704A0 12 > $CONFIG_PATH
    echo R 0x18370520 1 > $CONFIG_PATH
    echo R 0x18370588 1 > $CONFIG_PATH
    echo R 0x18370D10 12 > $CONFIG_PATH
    echo R 0x18370F90 10 > $CONFIG_PATH
    echo R 0x18371010 10 > $CONFIG_PATH
    echo R 0x18371A10 8 > $CONFIG_PATH
    echo R 0x183784A0 12 > $CONFIG_PATH
    echo R 0x18378520 1 > $CONFIG_PATH
    echo R 0x18378588 1 > $CONFIG_PATH
    echo R 0x18378D10 8 > $CONFIG_PATH
    echo R 0x18378F90 6 > $CONFIG_PATH
    echo R 0x18379010 6 > $CONFIG_PATH
    echo R 0x18379A10 4 > $CONFIG_PATH
    echo R 0xA310220 3 > $CONFIG_PATH
    echo R 0xA3102A0 3 > $CONFIG_PATH
    echo R 0xA3104A0 6 > $CONFIG_PATH
    echo R 0xA310520 1 > $CONFIG_PATH
    echo R 0xA310588 1 > $CONFIG_PATH
    echo R 0xA310D10 8 > $CONFIG_PATH
    echo R 0xA310F90 6 > $CONFIG_PATH
    echo R 0xA311010 6 > $CONFIG_PATH
    echo R 0xA311A10 3 > $CONFIG_PATH
}

config_dcc_tsens()
{
    CONFIG_PATH=$DCC_PATH/$1/config
    echo R 0x0C222004 1 > $CONFIG_PATH
    echo R 0x0C263014 1 > $CONFIG_PATH
    echo R 0x0C2630E0 1 > $CONFIG_PATH
    echo R 0x0C2630EC 1 > $CONFIG_PATH
    echo R 0x0C2630A0 16 > $CONFIG_PATH
    echo R 0x0C2630E8 1 > $CONFIG_PATH
    echo R 0x0C26313C 1 > $CONFIG_PATH
    echo R 0x0C223004 1 > $CONFIG_PATH
    echo R 0x0C265014 1 > $CONFIG_PATH
    echo R 0x0C2650E0 1 > $CONFIG_PATH
    echo R 0x0C2650EC 1 > $CONFIG_PATH
    echo R 0x0C2650A0 16 > $CONFIG_PATH
    echo R 0x0C2650E8 1 > $CONFIG_PATH
    echo R 0x0C26513C 1 > $CONFIG_PATH
}

config_dcc_apps_rsc()
{
    CONFIG_PATH=$DCC_PATH/$1/config
    echo R 0x0B201020 2 > $CONFIG_PATH
    echo R 0x0B200010 4 > $CONFIG_PATH
    echo R 0x0B220010 4 > $CONFIG_PATH
    echo R 0x0B200900 4 > $CONFIG_PATH
    echo R 0x0B220900 4 > $CONFIG_PATH
    echo R 0x0B201030   > $CONFIG_PATH
    echo R 0x0B201204   > $CONFIG_PATH
    echo R 0x0B201218   > $CONFIG_PATH
    echo R 0x0B20122C   > $CONFIG_PATH
    echo R 0x0B201240   > $CONFIG_PATH
    echo R 0x0B201254   > $CONFIG_PATH
    echo R 0x0B201208   > $CONFIG_PATH
    echo R 0x0B20121C   > $CONFIG_PATH
    echo R 0x0B201230   > $CONFIG_PATH
    echo R 0x0B201244   > $CONFIG_PATH
    echo R 0x0B201258   > $CONFIG_PATH
    echo R 0x0B204510   > $CONFIG_PATH
    echo R 0x0B204514   > $CONFIG_PATH
    echo R 0x0B204520   > $CONFIG_PATH
    echo R 0x0B211024 1 > $CONFIG_PATH
    echo R 0x0B221024 1 > $CONFIG_PATH
    echo R 0x0B231024 1 > $CONFIG_PATH
    echo R 0x18220010 1 > $CONFIG_PATH
    echo R 0x18220030 1 > $CONFIG_PATH
    echo R 0x182200D0 1 > $CONFIG_PATH
    echo R 0x18220408 1 > $CONFIG_PATH
    echo R 0x18230408 1 > $CONFIG_PATH
}

config_dcc_modem_rsc()
{
    CONFIG_PATH=$DCC_PATH/$1/config
    echo R 0xB2C4520   >  $CONFIG_PATH
    echo R 0xB2C1020 2 >  $CONFIG_PATH
    echo R 0xB2C1030   >  $CONFIG_PATH
    echo R 0xB2C1200   >  $CONFIG_PATH
    echo R 0xB2C1214   >  $CONFIG_PATH
    echo R 0xB2C1228   >  $CONFIG_PATH
    echo R 0xB2C123C   >  $CONFIG_PATH
    echo R 0xB2C1250   >  $CONFIG_PATH
    echo R 0xB2C1204   >  $CONFIG_PATH
    echo R 0xB2C1218   >  $CONFIG_PATH
    echo R 0xB2C122C   >  $CONFIG_PATH
    echo R 0xB2C1240   >  $CONFIG_PATH
    echo R 0xB2C1254   >  $CONFIG_PATH
    echo R 0xB2C1208   >  $CONFIG_PATH
    echo R 0xB2C121C   >  $CONFIG_PATH
    echo R 0xB2C1230   >  $CONFIG_PATH
    echo R 0xB2C1244   >  $CONFIG_PATH
    echo R 0xB2C1258   >  $CONFIG_PATH
    echo R 0xB2C4510 2 >  $CONFIG_PATH
    echo R 0xB2C0010 2 >  $CONFIG_PATH
    echo R 0xB2C0900 2 >  $CONFIG_PATH
    echo R 0x04082028  >  $CONFIG_PATH
    echo R 0x18A00C    >  $CONFIG_PATH
    echo R 0x04080044  >  $CONFIG_PATH
    echo R 0x04080304  >  $CONFIG_PATH
    echo R 0x41A802C   >  $CONFIG_PATH
    echo R 0x4200010 3 >  $CONFIG_PATH
    echo R 0x4200030   >  $CONFIG_PATH
    echo R 0x4200038   >  $CONFIG_PATH
    echo R 0x4200040   >  $CONFIG_PATH
    echo R 0x4200048   >  $CONFIG_PATH
    echo R 0x42000D0   >  $CONFIG_PATH
    echo R 0x4200210   >  $CONFIG_PATH
    echo R 0x4200230   >  $CONFIG_PATH
    echo R 0x4200250   >  $CONFIG_PATH
    echo R 0x4200270   >  $CONFIG_PATH
    echo R 0x4200290   >  $CONFIG_PATH
    echo R 0x42002B0   >  $CONFIG_PATH
    echo R 0x4200208   >  $CONFIG_PATH
    echo R 0x4200228   >  $CONFIG_PATH
    echo R 0x4200248   >  $CONFIG_PATH
    echo R 0x4200268   >  $CONFIG_PATH
    echo R 0x4200288   >  $CONFIG_PATH
    echo R 0x42002A8   >  $CONFIG_PATH
    echo R 0x420020C   >  $CONFIG_PATH
    echo R 0x420022C   >  $CONFIG_PATH
    echo R 0x420024C   >  $CONFIG_PATH
    echo R 0x420026C   >  $CONFIG_PATH
    echo R 0x420028C   >  $CONFIG_PATH
    echo R 0x42002AC   >  $CONFIG_PATH
    echo R 0x4200400 3 >  $CONFIG_PATH
    echo R 0x4200D04   >  $CONFIG_PATH
    echo R 0x4130010 3 >  $CONFIG_PATH
    echo R 0x4130210   >  $CONFIG_PATH
    echo R 0x4130230   >  $CONFIG_PATH
    echo R 0x4130250   >  $CONFIG_PATH
    echo R 0x4130270   >  $CONFIG_PATH
    echo R 0x4130290   >  $CONFIG_PATH
    echo R 0x41302B0   >  $CONFIG_PATH
    echo R 0x4130208   >  $CONFIG_PATH
    echo R 0x4130228   >  $CONFIG_PATH
    echo R 0x4130248   >  $CONFIG_PATH
    echo R 0x4130268   >  $CONFIG_PATH
    echo R 0x4130288   >  $CONFIG_PATH
    echo R 0x41302A8   >  $CONFIG_PATH
    echo R 0x413020C   >  $CONFIG_PATH
    echo R 0x413022C   >  $CONFIG_PATH
    echo R 0x413024C   >  $CONFIG_PATH
    echo R 0x413026C   >  $CONFIG_PATH
    echo R 0x413028C   >  $CONFIG_PATH
    echo R 0x41302AC   >  $CONFIG_PATH
    echo R 0x4130400 3 >  $CONFIG_PATH
}

config_dcc_lpass_rsc ()
{
    CONFIG_PATH=$DCC_PATH/$1/config
    echo R 0xB254520    > $CONFIG_PATH
    echo R 0xB251020 2  > $CONFIG_PATH
    echo R 0xB251030    > $CONFIG_PATH
    echo R 0xB251200    > $CONFIG_PATH
    echo R 0xB251214    > $CONFIG_PATH
    echo R 0xB251228    > $CONFIG_PATH
    echo R 0xB25123C    > $CONFIG_PATH
    echo R 0xB251250    > $CONFIG_PATH
    echo R 0xB251204    > $CONFIG_PATH
    echo R 0xB251218    > $CONFIG_PATH
    echo R 0xB25122C    > $CONFIG_PATH
    echo R 0xB251240    > $CONFIG_PATH
    echo R 0xB251254    > $CONFIG_PATH
    echo R 0xB251208    > $CONFIG_PATH
    echo R 0xB25121C    > $CONFIG_PATH
    echo R 0xB251230    > $CONFIG_PATH
    echo R 0xB251244    > $CONFIG_PATH
    echo R 0xB251258    > $CONFIG_PATH
    echo R 0xB254510 2  > $CONFIG_PATH
    echo R 0xB250010 2  > $CONFIG_PATH
    echo R 0xB250900 2  > $CONFIG_PATH
    echo R 0x03500010 3 > $CONFIG_PATH
    echo R 0x03500030   > $CONFIG_PATH
    echo R 0x03500038   > $CONFIG_PATH
    echo R 0x03500040   > $CONFIG_PATH
    echo R 0x03500048   > $CONFIG_PATH
    echo R 0x035000D0   > $CONFIG_PATH
    echo R 0x03500210   > $CONFIG_PATH
    echo R 0x03500230   > $CONFIG_PATH
    echo R 0x03500250   > $CONFIG_PATH
    echo R 0x03500270   > $CONFIG_PATH
    echo R 0x03500290   > $CONFIG_PATH
    echo R 0x035002B0   > $CONFIG_PATH
    echo R 0x03500208   > $CONFIG_PATH
    echo R 0x03500228   > $CONFIG_PATH
    echo R 0x03500248   > $CONFIG_PATH
    echo R 0x03500268   > $CONFIG_PATH
    echo R 0x03500288   > $CONFIG_PATH
    echo R 0x035002A8   > $CONFIG_PATH
    echo R 0x0350020C   > $CONFIG_PATH
    echo R 0x0350022C   > $CONFIG_PATH
    echo R 0x0350024C   > $CONFIG_PATH
    echo R 0x0350026C   > $CONFIG_PATH
    echo R 0x0350028C   > $CONFIG_PATH
    echo R 0x035002AC   > $CONFIG_PATH
    echo R 0x03500400 3 > $CONFIG_PATH
    echo R 0x03500D04   > $CONFIG_PATH
    echo R 0x030B0010 3 > $CONFIG_PATH
    echo R 0x030B0210   > $CONFIG_PATH
    echo R 0x030B0230   > $CONFIG_PATH
    echo R 0x030B0250   > $CONFIG_PATH
    echo R 0x030B0270   > $CONFIG_PATH
    echo R 0x030B0290   > $CONFIG_PATH
    echo R 0x030B02B0   > $CONFIG_PATH
    echo R 0x030B0208   > $CONFIG_PATH
    echo R 0x030B0228   > $CONFIG_PATH
    echo R 0x030B0248   > $CONFIG_PATH
    echo R 0x030B0268   > $CONFIG_PATH
    echo R 0x030B0288   > $CONFIG_PATH
    echo R 0x030B02A8   > $CONFIG_PATH
    echo R 0x030B020C   > $CONFIG_PATH
    echo R 0x030B022C   > $CONFIG_PATH
    echo R 0x030B024C   > $CONFIG_PATH
    echo R 0x030B026C   > $CONFIG_PATH
    echo R 0x030B028C   > $CONFIG_PATH
    echo R 0x030B02AC   > $CONFIG_PATH
    echo R 0x030B0400 3 > $CONFIG_PATH
}

config_sdi_debug ()
{
    CONFIG_PATH=$DCC_PATH/$1/config
    echo R 0x10413C > $CONFIG_PATH
    echo R 0x105024 > $CONFIG_PATH
    echo R 0x108008 > $CONFIG_PATH
    echo R 0x108004 > $CONFIG_PATH
    echo R 0x10500C > $CONFIG_PATH
    echo R 0x105658 > $CONFIG_PATH
    echo R 0x105640 > $CONFIG_PATH
    echo R 0x105644 > $CONFIG_PATH
    echo R 0x105010 > $CONFIG_PATH
    echo R 0x10500C > $CONFIG_PATH
    echo R 0x105024 > $CONFIG_PATH
}

config_dcc_nsp_rsc()
{
    CONFIG_PATH=$DCC_PATH/$1/config
    echo R 0xB2B4520   > $CONFIG_PATH
    echo R 0xB2B1020 2 > $CONFIG_PATH
    echo R 0xB2B1030   > $CONFIG_PATH
    echo R 0xB2B1200   > $CONFIG_PATH
    echo R 0xB2B1214   > $CONFIG_PATH
    echo R 0xB2B1228   > $CONFIG_PATH
    echo R 0xB2B123C   > $CONFIG_PATH
    echo R 0xB2B1250   > $CONFIG_PATH
    echo R 0xB2B1204   > $CONFIG_PATH
    echo R 0xB2B1218   > $CONFIG_PATH
    echo R 0xB2B122C   > $CONFIG_PATH
    echo R 0xB2B1240   > $CONFIG_PATH
    echo R 0xB2B1254   > $CONFIG_PATH
    echo R 0xB2B1208   > $CONFIG_PATH
    echo R 0xB2B121C   > $CONFIG_PATH
    echo R 0xB2B1230   > $CONFIG_PATH
    echo R 0xB2B1244   > $CONFIG_PATH
    echo R 0xB2B1258   > $CONFIG_PATH
    echo R 0xB2B4510 2 > $CONFIG_PATH
    echo R 0xB2B0010 1 > $CONFIG_PATH
    echo R 0xB2B0900 1 > $CONFIG_PATH
    echo R 0xA302028   > $CONFIG_PATH
    echo R 0xA0A4010 3 > $CONFIG_PATH
    echo R 0xA0A4030   > $CONFIG_PATH
    echo R 0xA0A4038   > $CONFIG_PATH
    echo R 0xA0A4040 2 > $CONFIG_PATH
    echo R 0xA0A40D0   > $CONFIG_PATH
    echo R 0xA0A4210   > $CONFIG_PATH
    echo R 0xA0A4230   > $CONFIG_PATH
    echo R 0xA0A4250   > $CONFIG_PATH
    echo R 0xA0A4270   > $CONFIG_PATH
    echo R 0xA0A4290   > $CONFIG_PATH
    echo R 0xA0A42B0   > $CONFIG_PATH
    echo R 0xA0A4208   > $CONFIG_PATH
    echo R 0xA0A4228   > $CONFIG_PATH
    echo R 0xA0A4248   > $CONFIG_PATH
    echo R 0xA0A4268   > $CONFIG_PATH
    echo R 0xA0A4288   > $CONFIG_PATH
    echo R 0xA0A42A8   > $CONFIG_PATH
    echo R 0xA0A420C   > $CONFIG_PATH
    echo R 0xA0A422C   > $CONFIG_PATH
    echo R 0xA0A424C   > $CONFIG_PATH
    echo R 0xA0A426C   > $CONFIG_PATH
    echo R 0xA0A428C   > $CONFIG_PATH
    echo R 0xA0A42AC   > $CONFIG_PATH
    echo R 0xA0A4400 2 > $CONFIG_PATH
    echo R 0xA0A4D04   > $CONFIG_PATH
    echo R 0xA3B0010 3 > $CONFIG_PATH
    echo R 0xA3B0210   > $CONFIG_PATH
    echo R 0xA3B0230   > $CONFIG_PATH
    echo R 0xA3B0250   > $CONFIG_PATH
    echo R 0xA3B0270   > $CONFIG_PATH
    echo R 0xA3B0290   > $CONFIG_PATH
    echo R 0xA3B02B0   > $CONFIG_PATH
    echo R 0xA3B0208   > $CONFIG_PATH
    echo R 0xA3B0228   > $CONFIG_PATH
    echo R 0xA3B0248   > $CONFIG_PATH
    echo R 0xA3B0268   > $CONFIG_PATH
    echo R 0xA3B0288   > $CONFIG_PATH
    echo R 0xA3B02A8   > $CONFIG_PATH
    echo R 0xA3B020C   > $CONFIG_PATH
    echo R 0xA3B022C   > $CONFIG_PATH
    echo R 0xA3B024C   > $CONFIG_PATH
    echo R 0xA3B026C   > $CONFIG_PATH
    echo R 0xA3B028C   > $CONFIG_PATH
    echo R 0xA3B02AC   > $CONFIG_PATH
    echo R 0xA3B0400 3 > $CONFIG_PATH
}

config_dcc_misc()
{
    CONFIG_PATH=$DCC_PATH/$1/config
    echo R 0x17E00434 1 > $CONFIG_PATH
    echo R 0x17E0043C 2 > $CONFIG_PATH
    echo R 0x17C00038 2 > $CONFIG_PATH
    echo R 0x17C00438 1 > $CONFIG_PATH
    echo R 0x17E0041C 1 > $CONFIG_PATH
    echo R 0x17E00420 1 > $CONFIG_PATH
    echo R 0x17E00404 1 > $CONFIG_PATH
    echo R 0xC220000 2 > $CONFIG_PATH
    echo R 0xC230000 6 > $CONFIG_PATH
    echo R 0xC260008 1 > $CONFIG_PATH
    echo R 0x18598014 1 > $CONFIG_PATH
    echo R 0x4D8634 1 > $CONFIG_PATH
    echo R 0x4D8834 1 > $CONFIG_PATH
    echo R 0x418620 1 > $CONFIG_PATH
    echo R 0x418820 1 > $CONFIG_PATH
}

config_dcc_lpass_cdsp_tunning()
{
    CONFIG_PATH=$DCC_PATH/$1/config
    echo R 0x3500D00 3 > $CONFIG_PATH
    echo R 0x3500D10 4 > $CONFIG_PATH
    echo R 0x3500FB0 4 > $CONFIG_PATH
    echo R 0x3501250 4 > $CONFIG_PATH
    echo R 0x35014F0 4 > $CONFIG_PATH
    echo R 0x3501790 4 > $CONFIG_PATH
    echo R 0x3501A30 4 > $CONFIG_PATH
    echo R 0x3503D44 4 > $CONFIG_PATH
    echo R 0x35000D0 3 > $CONFIG_PATH
    echo R 0x3500100 1 > $CONFIG_PATH
    echo R 0x3500D3C 1 > $CONFIG_PATH
    echo R 0xA0A40D0 3 > $CONFIG_PATH
    echo R 0xA0A4100 1 > $CONFIG_PATH
    echo R 0xA0A4D3C 1 > $CONFIG_PATH
    echo R 0xA0A7D44 4 > $CONFIG_PATH
    echo R 0xA0A4D00 3 > $CONFIG_PATH
}

config_dcc_wpss_rsc()
{
    CONFIG_PATH=$DCC_PATH/$1/config
    echo R 0x8A02028   > $CONFIG_PATH
    echo R 0x8b00000   > $CONFIG_PATH
    echo R 0x8b00004   > $CONFIG_PATH
    echo R 0x8b00010   > $CONFIG_PATH
    echo R 0x8b00014   > $CONFIG_PATH
    echo R 0x8b00018   > $CONFIG_PATH
    echo R 0x8b0001c   > $CONFIG_PATH
    echo R 0x8b00020   > $CONFIG_PATH
    echo R 0x8b00024   > $CONFIG_PATH
    echo R 0x8b00028   > $CONFIG_PATH
    echo R 0x8b0002c   > $CONFIG_PATH
    echo R 0x8b00030   > $CONFIG_PATH
    echo R 0x8b00034   > $CONFIG_PATH
    echo R 0x8b00038   > $CONFIG_PATH
    echo R 0x8b0003c   > $CONFIG_PATH
    echo R 0x8b00040   > $CONFIG_PATH
    echo R 0x8b00044   > $CONFIG_PATH
    echo R 0x8b00048   > $CONFIG_PATH
    echo R 0x8b000d0   > $CONFIG_PATH
    echo R 0x8b000d8   > $CONFIG_PATH
    echo R 0x8b00100   > $CONFIG_PATH
    echo R 0x8b00104   > $CONFIG_PATH
    echo R 0x8b00108   > $CONFIG_PATH
    echo R 0x8b00200   > $CONFIG_PATH
    echo R 0x8b00204   > $CONFIG_PATH
    echo R 0x8b00224   > $CONFIG_PATH
    echo R 0x8b00244   > $CONFIG_PATH
    echo R 0x8b00264   > $CONFIG_PATH
    echo R 0x8b00284   > $CONFIG_PATH
    echo R 0x8b002a4   > $CONFIG_PATH
    echo R 0x8b00208   > $CONFIG_PATH
    echo R 0x8b00228   > $CONFIG_PATH
    echo R 0x8b00248   > $CONFIG_PATH
    echo R 0x8b00268   > $CONFIG_PATH
    echo R 0x8b00288   > $CONFIG_PATH
    echo R 0x8b002a8   > $CONFIG_PATH
    echo R 0x8b0020c   > $CONFIG_PATH
    echo R 0x8b0022c   > $CONFIG_PATH
    echo R 0x8b0024c   > $CONFIG_PATH
    echo R 0x8b0026c   > $CONFIG_PATH
    echo R 0x8b0028c   > $CONFIG_PATH
    echo R 0x8b002ac   > $CONFIG_PATH
    echo R 0x8b00210   > $CONFIG_PATH
    echo R 0x8b00230   > $CONFIG_PATH
    echo R 0x8b00250   > $CONFIG_PATH
    echo R 0x8b00270   > $CONFIG_PATH
    echo R 0x8b00290   > $CONFIG_PATH
    echo R 0x8b002b0   > $CONFIG_PATH
    echo R 0x8b00400   > $CONFIG_PATH
    echo R 0x8b00404   > $CONFIG_PATH
    echo R 0x8b00408   > $CONFIG_PATH
    echo R 0x8b00460   > $CONFIG_PATH
    echo R 0x8b00464   > $CONFIG_PATH
    echo R 0x8b004a0   > $CONFIG_PATH
    echo R 0x8b004a4   > $CONFIG_PATH
    echo R 0x8b004a8   > $CONFIG_PATH
    echo R 0x8b004ac   > $CONFIG_PATH
    echo R 0x8b004b0   > $CONFIG_PATH
    echo R 0x8b004b4   > $CONFIG_PATH
    echo R 0x8b004b8   > $CONFIG_PATH
    echo R 0x8ab0000   > $CONFIG_PATH
    echo R 0x8ab0004   > $CONFIG_PATH
    echo R 0x8ab0010   > $CONFIG_PATH
    echo R 0x8ab0014   > $CONFIG_PATH
    echo R 0x8ab0018   > $CONFIG_PATH
    echo R 0x8ab00d0   > $CONFIG_PATH
    echo R 0x8ab00d8   > $CONFIG_PATH
    echo R 0x8ab0100   > $CONFIG_PATH
    echo R 0x8ab0104   > $CONFIG_PATH
    echo R 0x8ab0108   > $CONFIG_PATH
    echo R 0x8ab0200   > $CONFIG_PATH
    echo R 0x8ab0204   > $CONFIG_PATH
    echo R 0x8ab0224   > $CONFIG_PATH
    echo R 0x8ab0244   > $CONFIG_PATH
    echo R 0x8ab0264   > $CONFIG_PATH
    echo R 0x8ab0284   > $CONFIG_PATH
    echo R 0x8ab02a4   > $CONFIG_PATH
    echo R 0x8ab0208   > $CONFIG_PATH
    echo R 0x8ab0228   > $CONFIG_PATH
    echo R 0x8ab0248   > $CONFIG_PATH
    echo R 0x8ab0268   > $CONFIG_PATH
    echo R 0x8ab0288   > $CONFIG_PATH
    echo R 0x8ab02a8   > $CONFIG_PATH
    echo R 0x8ab020c   > $CONFIG_PATH
    echo R 0x8ab022c   > $CONFIG_PATH
    echo R 0x8ab024c   > $CONFIG_PATH
    echo R 0x8ab026c   > $CONFIG_PATH
    echo R 0x8ab028c   > $CONFIG_PATH
    echo R 0x8ab02ac   > $CONFIG_PATH
    echo R 0x8ab0210   > $CONFIG_PATH
    echo R 0x8ab0230   > $CONFIG_PATH
    echo R 0x8ab0250   > $CONFIG_PATH
    echo R 0x8ab0270   > $CONFIG_PATH
    echo R 0x8ab0290   > $CONFIG_PATH
    echo R 0x8ab02b0   > $CONFIG_PATH
    echo R 0x8ab0400   > $CONFIG_PATH
    echo R 0x8ab0404   > $CONFIG_PATH
    echo R 0x8ab0408   > $CONFIG_PATH
    echo R 0x8ab0460   > $CONFIG_PATH
    echo R 0x8ab0464   > $CONFIG_PATH
    echo R 0x8ab04a0   > $CONFIG_PATH
    echo R 0x8ab04a4   > $CONFIG_PATH
    echo R 0x8ab04a8   > $CONFIG_PATH
    echo R 0x8ab04ac   > $CONFIG_PATH
    echo R 0x8ab04b0   > $CONFIG_PATH
    echo R 0x8ab04b4   > $CONFIG_PATH
    echo R 0x8ab04b8   > $CONFIG_PATH
}

config_dcc_video_noc()
{
    CONFIG_PATH=$DCC_PATH/$1/config
    echo R 0xAA10504   > $CONFIG_PATH
    echo R 0xAA10508   > $CONFIG_PATH
    echo R 0xAA10510   > $CONFIG_PATH
    echo R 0xAA10520   > $CONFIG_PATH
    echo R 0xAA10524   > $CONFIG_PATH
    echo R 0xAA10528   > $CONFIG_PATH
    echo R 0xAA1052C   > $CONFIG_PATH
    echo R 0xAA10530   > $CONFIG_PATH
    echo R 0xAA10534   > $CONFIG_PATH
    echo R 0xAA10538   > $CONFIG_PATH
    echo R 0xAA1053C   > $CONFIG_PATH
    echo R 0xAA10300   > $CONFIG_PATH
    echo R 0xAA10010   > $CONFIG_PATH
    echo R 0xAA10020   > $CONFIG_PATH
}

configure_dcc()
{
    LLNUM=6
    config_dcc_pcu $LLNUM
    config_dcc_epss $LLNUM
    config_dcc_pimem $LLNUM
    config_dcc_core $LLNUM
    config_dcc_gemnoc $LLNUM
    config_dcc_tsens $LLNUM
    config_dcc_spmi $LLNUM
    config_dcc_gpu $LLNUM
    config_sysnoc $LLNUM
    config_aggrenoc $LLNUM
    config_dcc_gcc $LLNUM

    LLNUM=4
    config_confignoc $LLNUM
    config_dcc_limits $LLNUM
    config_dcc_gic $LLNUM
    config_mmssnoc $LLNUM
    config_dcc_apps_rsc $LLNUM
    config_dcc_misc $LLNUM
    config_dcc_ddr $LLNUM

    LLNUM=3
    #config_dcc_modem_rsc $LLNUM
    config_dcc_lpass_rsc $LLNUM
    config_dcc_nsp_rsc $LLNUM
    config_sdi_debug $LLNUM
    config_dcc_lpass_cdsp_tunning $LLNUM
    config_dcc_wpss_rsc $LLNUM
    config_dcc_video_noc $LLNUM
}

enable_dcc()
{
    # DCC configuration
    DCC_ROOT="/sys/kernel/debug/qcom_dcc"
    DCC_PATH="/sys/kernel/debug/qcom_dcc/117f000.dma"

    if [ ! -d $DCC_PATH ]; then
        echo "DCC does not exist on this build."
        return
    fi

    echo 1 > $DCC_ROOT/config_reset

    # List configuration
    configure_dcc

    # Enable LL's
    echo 1 > $DCC_PATH/6/enable
    echo 1 > $DCC_PATH/4/enable
    echo 1 > $DCC_PATH/3/enable
}

find_build_type()
{
    if [ $(cat /proc/config.gz | gunzip | grep "^$CONFIG_OPTION=y") ]
    then
        debug_build=true
    fi
}

config_stm_stp_policy()
{
    mkdir /sys/kernel/config/stp-policy/stm0:p_ost.policy
    mkdir -p /sys/kernel/config/stp-policy/stm0:p_ost.policy/default
    echo 0x10 > /sys/bus/coresight/devices/stm0/traceid
}

config_coresight_permission()
{
    chmod 777 /sys/bus/coresight/devices/tmc_etr0/block_size
    chmod 777 /sys/bus/coresight/devices/tmc_etr0/buffer_size
    chmod 777 /sys/bus/coresight/devices/tmc_etr0/enable_sink
    chmod 777 /sys/bus/coresight/devices/stm0/enable_source
    chmod 777 /sys/bus/coresight/devices/modem_diag/enable_source
    chmod 777 /dev/byte-cntr
    chmod 777 /sys/bus/coresight/devices/tmc_etr0/out_mode
}

#ftrace_disable=`getprop persist.debug.ftrace_events_disable
CONFIG_OPTION="CONFIG_DEBUG_LIST"
debug_build=false
enable_debug()
{
    echo "QCM6490 debug"
    find_build_type
    #if [ ${ftrace_disable} != "Yes" ]; then
    enable_ftrace_event_tracing
    #fi
    enable_dcc
    config_stm_stp_policy
    config_coresight_permission
}

enable_debug
