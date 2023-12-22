#!/bin/sh
# Copyright (c) 2020 Qualcomm Technologies, Inc.
# All Rights Reserved.
# Confidential and Proprietary - Qualcomm Technologies, Inc.
#

# Wait while 'ro.vendor.bt.bdaddr_path' property is empty
while [ -z $nvfile ]; do
    nvfile=$(getprop ro.vendor.bt.bdaddr_path)
done
echo "start_btnvtool.sh : nvfile = '$nvfile'"

if [ "$1" == "verbose" ]
then
    optv=" -v"
else
    optv=""
fi

if [ -e "$nvfile" ]
then
    echo "start_btnvtool.sh : Executing 'btnvtool$optv'"
    btnvtool $optv
else
    if [ -z "$nvfile" ]
    then
        # Use default location of BDADDR file path
        nvpath="/persist/factory/bluetooth"
    else
        nvpath=$(dirname "$nvfile")
    fi

    echo "start_btnvtool.sh : nvpath = '$nvpath'"

    if [ ! -d "$nvpath" ]
    then
        echo "start_btnvtool.sh : Creating directory structure..."
        mkdir -p "$nvpath"
    fi

    echo "start_btnvtool.sh : Executing 'btnvtool$optv -b'"
    btnvtool $optv -b
fi

