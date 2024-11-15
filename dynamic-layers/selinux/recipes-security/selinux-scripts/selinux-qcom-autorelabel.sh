#!/bin/sh
/usr/sbin/selinuxenabled 2>/dev/null || exit 0
RESTORCON=/usr/sbin/restorecon

mkdir -p /var/rootdirs/home/root
${RESTORCON} -R  /var/rootdirs

current_deployment=$(ostree admin status | awk '/^\*/ {print $3}')
if [ -n "$current_deployment" ]; then
       restore_cookie="/etc/restore-$current_deployment"
fi

#Will run only for the first time and once done it will create a cookie
#of the form restore-hash specific to each deployment
if [ ! -f "$restore_cookie" ]; then
       ${RESTORCON} -R  /etc/tmpfiles.d/00ostree-tmpfiles.conf
       ${RESTORCON} -R  /var  /etc
       ${RESTORCON} -R  /media /mnt   /root  /home
       ${RESTORCON} -R  /usr/local
       ${RESTORCON} -R  /var/rootdirs
       ${RESTORCON}   /sysroot
       touch "$restore_cookie"
       /sbin/reboot
fi
exit 0
