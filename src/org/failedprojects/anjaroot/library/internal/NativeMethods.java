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

class NativeMethods {
	public native static long[] capget(int pid) throws NativeException;

	public native static void capset(long effective, long permitted,
			long inheritable) throws NativeException, OutOfBoundsException;

	public native static long[] getresuid() throws NativeException;

	public native static void setresuid(long ruid, long euid, long suid)
			throws NativeException, OutOfBoundsException;

	public native static long[] getresgid() throws NativeException;

	public native static void setresgid(long rgid, long egid, long sgid)
			throws NativeException, OutOfBoundsException;

	public native static int[] getversion();

	public native static void setcompatmode(int apilvl);
}
