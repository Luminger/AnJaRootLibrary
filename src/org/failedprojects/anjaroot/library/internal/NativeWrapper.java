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

import org.failedprojects.anjaroot.library.containers.Capabilities;
import org.failedprojects.anjaroot.library.containers.GroupIds;
import org.failedprojects.anjaroot.library.containers.UserIds;
import org.failedprojects.anjaroot.library.exceptions.NativeException;
import org.failedprojects.anjaroot.library.exceptions.OutOfBoundsException;
import org.failedprojects.anjaroot.library.exceptions.PermissionsException;

public class NativeWrapper {
	public static Capabilities getCapabilities() throws NativeException, PermissionsException {
		long[] perms = NativeMethods.capget(0);
		return new Capabilities(perms[0], perms[1], perms[2]);
	}
	
	public static void setCapabilities(Capabilities caps) throws NativeException, PermissionsException, OutOfBoundsException {
		NativeMethods.capset(caps.getEffective(), caps.getPermitted(), caps.getInheritable());
	}
	
	public static UserIds getUserIds() throws NativeException {
		long[] uids = NativeMethods.getresuid();
		return new UserIds(uids[0], uids[1], uids[2]);
	}
	
	public static void setUserIds(UserIds uids) throws NativeException, PermissionsException, OutOfBoundsException {
		NativeMethods.setresuid(uids.getReal(), uids.getEffective(), uids.getSaved());
	}
	
	public static GroupIds getGroupIds() throws NativeException {
		long[] gids = NativeMethods.getresgid();
		return new GroupIds(gids[0], gids[1], gids[2]);
	}
	
	public static void setGroupIds(GroupIds gids) throws NativeException, PermissionsException, OutOfBoundsException {
		NativeMethods.setresgid(gids.getReal(), gids.getEffective(), gids.getSaved());
	}
}
