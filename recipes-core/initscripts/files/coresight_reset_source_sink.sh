#!/bin/sh
#=============================================================================
# Copyright (c) 2024 Qualcomm Technologies, Inc.
# All Rights Reserved.
# Confidential and Proprietary - Qualcomm Technologies, Inc.
#=============================================================================

disable_source()
{
    read value < $1
    if [ $value == "1" ]; then
       echo 0 > $1
       disable_source $1
    fi
}

dir_name="/sys/bus/coresight/devices/"
files=$(ls -a $dir_name)
for file in $files
do
    if [ "$file" != "." ] && [ "$file" != ".." ]
    then
        if [ -e $dir_name$file"/enable_source" ]
        then
            enable_source_path=$dir_name$file"/enable_source"
            disable_source $enable_source_path
        fi

        if [ -e $dir_name$file"/enable_sink" ]
        then
            enable_sink_path=$dir_name$file"/enable_sink"
            echo 0 > $enable_sink_path
        fi
    fi
done

