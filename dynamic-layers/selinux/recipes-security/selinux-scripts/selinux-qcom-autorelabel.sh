#!/bin/sh

# Copyright (c) 2023-2024 Qualcomm Innovation Center, Inc. All rights reserved.
# SPDX-License-Identifier: BSD-3-Clause-Clear

/usr/sbin/selinuxenabled 2>/dev/null || exit 0

SETFILES=/usr/sbin/setfiles
SETENFORCE=/usr/sbin/setenforce
FIXFILES=/usr/sbin/fixfiles
RESTORCON=/usr/sbin/restorecon

for i in ${FIXFILES} ${SETENFORCE}; do
	test -x $i && continue
	echo "$i is missing in the system."
	echo "Please add \"selinux=0\" in the kernel command line to disable SELinux."
	exit 1
done

# If /.autorelabel placed, the whole file system should be relabeled

if [ -f /.autorelabel ]; then
        echo "SELinux: /.autorelabel placed, filesystem will be relabeled..."
        start_time=$(date +%s)
        ${SETENFORCE} 0
        source /usr/etc/selinux/config  > /dev/null
#       ${SETFILES}  -v -i -F -r  /usr /usr/etc/selinux/${SELINUXTYPE}/contexts/files/file_contexts  /usr
        sed -i 's|/bin/sh|/bin/bash|1' /usr/sbin/fixfiles
#       ${FIXFILES} -F -f relabel
####  OSTree expected  not to touch  /sysroot  /   so
      ${RESTORCON}  -v -R   /var  /etc
      ${RESTORCON}  -v -R /usr/local
      ${RESTORCON}  -v -R /var/rootdirs/media
      ${RESTORCON}  -v -R /var/rootdirs/mnt
      ${RESTORCON}  -v -R /var/rootdirs/media
      ${RESTORCON}  -v -R /var/rootdirs/opt

        /bin/rm -f /.autorelabel
        end_time=$(date +%s)
        execution_time=$(expr $end_time - $start_time)
        echo " * Time taken :  $execution_time Relabel done, rebooting the system."
        /sbin/reboot
fi

exit 0
