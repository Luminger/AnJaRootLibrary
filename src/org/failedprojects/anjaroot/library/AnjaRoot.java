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
package org.failedprojects.anjaroot.library;

import org.failedprojects.anjaroot.library.containers.Capabilities;
import org.failedprojects.anjaroot.library.containers.GroupIds;
import org.failedprojects.anjaroot.library.containers.UserIds;
import org.failedprojects.anjaroot.library.internal.NativeWrapper;

public class AnjaRoot {
	private static final UserIds rootUids = new UserIds(0, 0, 0);
	private static final GroupIds rootGids = new GroupIds(0, 0, 0);
	private static UserIds originalUids = null;
	private static GroupIds originalGids = null;
	
	public static boolean isAccessPossible() {
		try {
			Capabilities caps = NativeWrapper.getCapabilities();
			return caps.getEffective() == 0xFFFFFFFF;
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public static boolean isAccessGranted() {
		if(!isAccessPossible()) {
			return false;
		}
		
		try {
			UserIds uids = NativeWrapper.getUserIds();
			return uids.getReal() == 0;
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public static boolean gainAccess() {
		if(isAccessPossible()) {
			try {
				originalUids = NativeWrapper.getUserIds();
				originalGids = NativeWrapper.getGroupIds();
			}
			catch(Exception e) {
				return false;
			}
				
			try {
				NativeWrapper.setUserIds(rootUids);
				NativeWrapper.setGroupIds(rootGids);
			}
			catch(Exception e) {
				// TODO if one fails the other has to be reverted
				try {
					NativeWrapper.setGroupIds(originalGids);
				}
				catch(Exception ignored) {
				}
				
				try {
					NativeWrapper.setUserIds(originalUids);
				}
				catch(Exception ignored) {
				}
				return false;
			}
		}
		return true;
	}
	
	public static boolean abandonAccess() {
		if(!isAccessGranted()) {
			return true;
		}
		
		try {
			NativeWrapper.setGroupIds(originalGids);
		}
		catch(Exception e) {
			return false;
		}
		
		try {
			NativeWrapper.setUserIds(originalUids);
		}
		catch(Exception e) {
			return false;
		}
		
		return true;
	}
}
