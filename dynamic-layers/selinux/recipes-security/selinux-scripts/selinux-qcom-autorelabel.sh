#!/bin/sh

/usr/sbin/selinuxenabled 2>/dev/null || exit 0

RESTORCON=/usr/sbin/restorecon

#Will run only for the first time and once done ostree will create this file
# /.updated  different from upstream autorelabel and this will not trigger reboot

if [ ! -f /etc/.updated ]; then
       ${RESTORCON} -R  /etc/tmpfiles.d/00ostree-tmpfiles.conf 
       ${RESTORCON} -R  /var  /etc
       ${RESTORCON} -R  /media /mnt   /root  /home
       ${RESTORCON} -R  /usr/local
       ${RESTORCON} -R  /var/rootdirs
       ${RESTORCON}   /sysroot

fi
exit 0
