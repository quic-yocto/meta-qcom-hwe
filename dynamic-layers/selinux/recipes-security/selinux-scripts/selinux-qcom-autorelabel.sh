#!/bin/sh
/usr/sbin/selinuxenabled 2>/dev/null || exit 0
RESTORCON=/usr/sbin/restorecon

mkdir -p /var/rootdirs/home/root
${RESTORCON} -R  /var/rootdirs

#Will run only for the first time and once done ostree will create this file
#/etc/labeldone
if [ ! -f /etc/labeldone ]; then
       ${RESTORCON} -R  /etc/tmpfiles.d/00ostree-tmpfiles.conf 
       ${RESTORCON} -R  /var  /etc
       ${RESTORCON} -R  /media /mnt   /root  /home
       ${RESTORCON} -R  /usr/local
       ${RESTORCON} -R  /var/rootdirs
       ${RESTORCON}   /sysroot
       touch /etc/labeldone
       /sbin/reboot
fi
exit 0