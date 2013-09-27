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

import org.failedprojects.anjaroot.library.containers.Capabilities;
import org.failedprojects.anjaroot.library.containers.GroupIds;
import org.failedprojects.anjaroot.library.containers.UserIds;
import org.failedprojects.anjaroot.library.containers.Version;
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
	
	public static Version getVersion()
	{
		int[] version = NativeMethods.getversion();
		return new Version(version[0], version[1], version[2]);
	}
}
