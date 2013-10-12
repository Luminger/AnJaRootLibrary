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
import org.failedprojects.anjaroot.library.containers.Status;
import org.failedprojects.anjaroot.library.containers.UserIds;
import org.failedprojects.anjaroot.library.containers.Version;
import org.failedprojects.anjaroot.library.exceptions.LibraryNotLoadedException;
import org.failedprojects.anjaroot.library.exceptions.NativeException;
import org.failedprojects.anjaroot.library.exceptions.OutOfBoundsException;

import android.util.Log;

/**
 * Exposed internal interface of the AnJaRootLibrary.
 * 
 * This class drives the AnJaRoot high level class. You are free to use it, it
 * will change in a backward compatible way whenever possible. But keep in mind
 * that you should only do so if you really need something which can't be done
 * with the high level interface.
 */
public class AnJaRootInternal {
	private static final String LOGTAG = "AnJaRootLibraryInternal";
	private boolean loaded;
	private final Version libraryVersion = new Version(1, 0, 0, 1);
	private Version nativeVersion;
	private Status status;

	public AnJaRootInternal() {
		try {
			System.loadLibrary("anjaroot");
			nativeVersion = NativeWrapper.getVersion();
			status = NativeWrapper.getStatus();
			NativeWrapper.setCompatMode(libraryVersion);
			Log.v(LOGTAG, "Native library loaded");
			loaded = true;
		} catch (UnsatisfiedLinkError e) {
			Log.e(LOGTAG, "Couldn't load native library");
			loaded = false;
		}
	}

	public boolean isLibraryLoaded() {
		return loaded;
	}

	public boolean isReady() {
		if (isLibraryLoaded()) {
			return status.isHooked() && status.isAlreadyRun();
		}

		return false;
	}

	public boolean isGranted() {
		return isReady() && status.isGranted();
	}

	public Version getNativeVersion() throws LibraryNotLoadedException {
		if (isLibraryLoaded()) {
			return nativeVersion;
		}

		throw new LibraryNotLoadedException();
	}

	public Version getLibraryVersion() {
		return libraryVersion;
	}

	public Capabilities getCapabilities() throws NativeException,
			LibraryNotLoadedException {
		if (!isLibraryLoaded()) {
			throw new LibraryNotLoadedException();
		}

		return NativeWrapper.getCapabilities();
	}

	public void setCapabilities(Capabilities caps) throws NativeException,
			OutOfBoundsException, LibraryNotLoadedException {
		if (!isLibraryLoaded()) {
			throw new LibraryNotLoadedException();
		}

		NativeWrapper.setCapabilities(caps);
	}

	public UserIds getUserIds() throws NativeException,
			LibraryNotLoadedException {
		if (!isLibraryLoaded()) {
			throw new LibraryNotLoadedException();
		}

		return NativeWrapper.getUserIds();
	}

	public void setUserIds(UserIds uids) throws NativeException,
			OutOfBoundsException, LibraryNotLoadedException {
		if (!isLibraryLoaded()) {
			throw new LibraryNotLoadedException();
		}

		NativeWrapper.setUserIds(uids);
	}

	public GroupIds getGroupIds() throws NativeException,
			LibraryNotLoadedException {
		if (!isLibraryLoaded()) {
			throw new LibraryNotLoadedException();
		}

		return NativeWrapper.getGroupIds();
	}

	public void setGroupIds(GroupIds gids) throws NativeException,
			OutOfBoundsException, LibraryNotLoadedException {
		if (!isLibraryLoaded()) {
			throw new LibraryNotLoadedException();
		}

		NativeWrapper.setGroupIds(gids);
	}
}
