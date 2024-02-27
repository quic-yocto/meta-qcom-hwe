#!/bin/sh
# Copyright (c) 2023 Qualcomm Innovation Center, Inc. All rights reserved.
# SPDX-License-Identifier: BSD-3-Clause-Clear

rproc_path="/sys/class/remoteproc/"

cmd=${1}

if [ "${cmd}" == "start" ] || [ "${cmd}" == "stop" ]; then
    for remoteproc_dir in $(ls -1 ${rproc_path}); do
        fwname_node=$(cat ${rproc_path}/${remoteproc_dir}/firmware)
        fw=$(echo ${fwname_node} | awk -F/ '{print $NF}')
        if [ "${fw}" == "modem.mdt" ]; then
            echo ${cmd} > ${rproc_path}/${remoteproc_dir}/state
            break
        fi
    done
fi
