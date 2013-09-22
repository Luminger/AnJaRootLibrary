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
		System.loadLibrary("anjaroot");
	}

	public native static long[] capget(int pid) throws NativeException, PermissionsException;
	public native static void capset(long effective, long permitted, long inheritable) throws NativeException, PermissionsException, OutOfBoundsException;
	public native static long[] getresuid() throws NativeException;
	public native static void setresuid(long ruid, long euid, long suid) throws NativeException, PermissionsException, OutOfBoundsException;
	public native static long[] getresgid() throws NativeException;
	public native static void setresgid(long rgid, long egid, long sgid) throws NativeException, PermissionsException, OutOfBoundsException;
}
