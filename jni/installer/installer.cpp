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

#include <iostream>
#include <getopt.h>
#include <stdlib.h>
#include "installer.h"
#include "util.h"

const char* shortopts = "s:icuh";
const struct option longopts[] = {
    {"srclibpath",   required_argument, 0, 's'},
    {"install",      no_argument,       0, 'i'},
    {"checkinstall", no_argument,       0, 'c'},
    {"uninstall",    no_argument,       0, 'u'},
    {"help",         no_argument,       0, 'h'},
    {0, 0, 0, 0},
};


ModeSpec processArguments(int argc, char** argv)
{
    std::string sourcelib;
    OperationMode mode = InvalidMode;

    int c, option_index = 0;
    while(c != -1)
    {
        c = getopt_long (argc, argv, shortopts, longopts, &option_index);
        switch(c)
        {
            case 's':
                util::logVerbose("Opt: -s set to '%s'", optarg);
                sourcelib = optarg;
                break;
            case 'i':
                util::logVerbose("Opt: -i");
                mode = InstallMode;
                break;
            case 'c':
                util::logVerbose("Opt: -c");
                mode = CheckMode;
                break;
            case 'u':
                util::logVerbose("Opt: -u");
                mode = UninstallMode;
                break;
            case 'h':
            default:
                return std::make_pair("", HelpMode);
        }
    }

    return std::make_pair(sourcelib, mode);
}

int main(int argc, char** argv)
{
    ModeSpec spec = processArguments(argc, argv);
    if(spec.second == HelpMode)
    {
        std::cout << "Usage: " << argv[0] << " [OPTIONS] [MODE]" << std::endl;
        std::cout << std::endl << "Valid Options:" << std::endl;
        std::cout << "\t-h, --help\t\t\tprint this usage message" << std::endl;
        std::cout << "\t-s, --srclibpath [PATH] \tset source lib path" << std::endl;
        std::cout << std::endl << "Valid Modes:" << std::endl;
        std::cout << "\t-i\t\t\t\tdo install (needs -s to be set)" << std::endl;
        std::cout << "\t-u\t\t\t\tdo uninstall" << std::endl;
        std::cout << "\t-c\t\t\t\tdo an installation ckeck" << std::endl;
        return -1;
    }

    return 0;
}
