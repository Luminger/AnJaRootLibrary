/*
 * Copyright 2013 Simon Brakhane
 *
 * This file is part of AnJaRoot.
 *
 * AnJaRoot is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * AnJaRoot is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * AnJaRoot. If not, see http://www.gnu.org/licenses/.
 */

/* Well, I would normally all of the operations performed by this tool in a
 * simple shell script. But all the Android devices out there may or may not
 * have a certain tool (cp is sometimes not there, people use cat to emulate
 * it, how ugly is that...) it seems to be the safest way to just code this in
 * c++ as the bionic libc provides all the stuff we need (copy, mv, chmod,
 * chown and more)
 *
 * Another good thing is that we can use this installer binary also to perform
 * a recovery install as liblog.so and libc.so are also present there. And even
 * if that's not the case (for liblog.so) we could link it statically, all
 * problems solved at once.
 */

/* How it would look like as a shellscript (without error handling):
 *
 * #!/bin/sh
 *
 * # pre install
 * mount -o rw,remount /system/
 *
 * # install library
 * cp libanjaroothook.so /system/lib/
 * # would be better to get perms and owner from an library like libc.so
 * chown 0:0 /system/lib/libanjaroothook.so
 * chmod 644 /system/lib/libanjaroothook.so
 *
 * # install app_process wrapper
 * cp app_process_wrapper.sh /system/bin/app_process_wrapper
 * # would be better to just copy perms and owner from orig binary
 * chown 0:0 /system/bin/app_process_wrapper
 * chmod 755 /system/bin/app_process_wrapper
 * mv /system/bin/app_process /system/bin/app_process.orig
 * mv /system/bin/app_process_wrapper /system/bin/app_process
 *
 * # cleanup
 * mount -o ro,remount /system/
 * sync
 */

/* Assumptions:
 *  - The caller takes care of calling "mount -o rw,remount /system"
 *    and its inversion after we have finished here
 *  - The caller takes care of granting us enough rights, we don't
 *    check for root or any elected state, we just do our business.
 */

int main(int argc, char** argv)
{
    return 0;
}
