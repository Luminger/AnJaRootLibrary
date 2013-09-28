/*    Copyright 2013 Simon Brakhane
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.failedprojects.anjaroot.library.internal;

import org.failedprojects.anjaroot.library.exceptions.NativeException;
import org.failedprojects.anjaroot.library.exceptions.OutOfBoundsException;
import org.failedprojects.anjaroot.library.exceptions.PermissionsException;


class NativeMethods {
	static {
		// TODO we may get serious problems when the installed Version (in /system/lib)
		// is older/newer than the now loaded one. I would guess this could result in major
		// problems as we already have injected the library via LD_PRELOAD and a possible
		// newer variant shipped with a program will never get called. Maybe the libraries
		// should be splitted.
		
		// TODO My idea is to load it directly into the app_process load, I just have to figure out
		//      how to bind this native methods here...
		System.loadLibrary("anjaroot");
	}

	public native static long[] capget(int pid) throws NativeException, PermissionsException;
	public native static void capset(long effective, long permitted, long inheritable) throws NativeException, PermissionsException, OutOfBoundsException;
	public native static long[] getresuid() throws NativeException;
	public native static void setresuid(long ruid, long euid, long suid) throws NativeException, PermissionsException, OutOfBoundsException;
	public native static long[] getresgid() throws NativeException;
	public native static void setresgid(long rgid, long egid, long sgid) throws NativeException, PermissionsException, OutOfBoundsException;
	public native static int[] getversion();
	public native static boolean[] getstatus();
}
