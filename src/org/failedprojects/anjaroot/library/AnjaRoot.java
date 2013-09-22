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
