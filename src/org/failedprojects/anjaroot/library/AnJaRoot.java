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

import org.failedprojects.anjaroot.library.containers.GroupIds;
import org.failedprojects.anjaroot.library.containers.UserIds;
import org.failedprojects.anjaroot.library.containers.Version;
import org.failedprojects.anjaroot.library.exceptions.LibraryNotLoadedException;
import org.failedprojects.anjaroot.library.internal.AnJaRootInternal;

import android.os.Process;
import android.util.Log;

public class AnJaRoot {
	private static final UserIds rootUids = new UserIds(0, 0, 0);
	private static final GroupIds rootGids = new GroupIds(0, 0, 0);
	private static UserIds originalUids = null;
	private static GroupIds originalGids = null;
	private static final AnJaRootInternal internal = new AnJaRootInternal();

	public static void commitSuicide() {
		Log.v("XXX", "My PID: " + Process.myPid());
		Process.killProcess(Process.myPid());
	}

	public static boolean isAccessGranted() {
		return internal.isReady();
	}

	public static Version getLibraryVersion() {
		return internal.getLibraryVersion();
	}

	public static Version getNativeVersion() throws LibraryNotLoadedException {
		return internal.getNativeVersion();
	}

	public static boolean hasAccess() {
		if (!isAccessGranted()) {
			return false;
		}

		try {
			UserIds uids = internal.getUserIds();
			return uids.getReal() == 0;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean gainAccess() {
		if (!isAccessGranted()) {
			return false;
		}

		try {
			originalUids = internal.getUserIds();
			originalGids = internal.getGroupIds();
		} catch (Exception e) {
			return false;
		}

		try {
			internal.setUserIds(rootUids);
			internal.setGroupIds(rootGids);
		} catch (Exception e) {
			try {
				internal.setGroupIds(originalGids);
			} catch (Exception ignored) {
			}

			try {
				internal.setUserIds(originalUids);
			} catch (Exception ignored) {
			}
			return false;
		}

		return true;
	}

	public static boolean dropAccess() {
		if (!isAccessGranted()) {
			return true;
		}

		try {
			internal.setGroupIds(originalGids);
		} catch (Exception e) {
			return false;
		}

		try {
			internal.setUserIds(originalUids);
		} catch (Exception e) {
			return false;
		}

		return true;
	}
}
