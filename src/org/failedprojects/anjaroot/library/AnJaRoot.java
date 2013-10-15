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
import org.failedprojects.anjaroot.library.containers.Version;
import org.failedprojects.anjaroot.library.exceptions.LibraryNotLoadedException;
import org.failedprojects.anjaroot.library.internal.AnJaRootInternal;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Process;
import android.os.SystemClock;

/**
 * Core functionality provided by the library.
 * 
 * This class provides all the functionalities needed to gain root privileges
 * (linux capabilities and uid=0, gid=0). The user has to do the following steps
 * to make use of AnJaRoot:
 * 
 * <ol>
 * <li>{@link #isAccessPossible() Check if AnJaRoot is usable}</li>
 * <li>{@link #isAccessGranted() Check if access is granted by AnJaRoot}</li>
 * </ol>
 * 
 * If {@link #isAccessGranted()} returned false (access is NOT granted):
 * <ol>
 * <li>Use {@link org.failedprojects.anjaroot.library.AnJaRootRequester} to
 * request access
 * <li>If the previous step returned true, {@link #commitSuicide() kill your
 * current linux process}</li>
 * </ol>
 * 
 * If {@link #isAccessGranted()} returned true:
 * <ol>
 * <li>{@link #gainAccess() Raise privileges of the current process/thread}</li>
 * <li>Do actions which require root privileges</li>
 * <li>{@link #dropAccess() Drop access privileges back to normal}</li>
 * </ol>
 * 
 * The steps described above can be done as often as needed. There is also the
 * {@link org.failedprojects.anjaroot.library.internal.AnJaRootInternal} class
 * which provides the low level functions used by this class.
 * 
 * It's also encouraged to keep the privileges phases of an app as short as
 * possible to not disrupt any other part of the (android) system.
 */
public class AnJaRoot {

	private static final UserIds rootUids = new UserIds(0, 0, 0);
	private static final GroupIds rootGids = new GroupIds(0, 0, 0);
	private static final Capabilities rootCaps = new Capabilities(0xFFFFFFFF,
			0xFFFFFFFF, 0);
	private static UserIds originalUids = null;
	private static GroupIds originalGids = null;
	private static Capabilities originalCaps = null;
	private static final AnJaRootInternal internal = new AnJaRootInternal();

	/**
	 * Kill the current (linux) process.
	 * 
	 * Android apps live in their own linux process. To make use of newly
	 * granted AnJaRoot rights an app has to restart completely. This method
	 * invokes {@link android.os.Process#killProcess(int)} to commit suicide.
	 * 
	 * Calling this method will kill your app immediately! This means no more
	 * code is executed. Therefore the normal activity/fragment flow will NOT
	 * happen, you have to make sure that your app leaves a clean state.
	 * 
	 * @return This method will not return as the app got killed.
	 */
	public static void commitSuicide() {
		Process.killProcess(Process.myPid());
	}

	/**
	 * Kill the current (linux) process and restart.
	 * 
	 * This method works exactly like {@link #commitSuicide()} but also accepts
	 * a pending intent which will be fired by the @ AlarmManager} a second
	 * after the current process is killed. This enables you to restart your app
	 * in a user friendly way as it (should) gets soon restarted. It's up to you
	 * to provide a valid Intent here, no further checking is done.
	 * 
	 * @param context
	 *            context used to get a reference to {@link AlarmManager}
	 * @param intent
	 *            intent which will be fired by the {@link AlarmManager} shortly
	 *            after the app committed suicide
	 */
	public static void commitSuicideAndRestart(Context context,
			PendingIntent intent) {
		if (context == null) {
			throw new NullPointerException("context can't be null");
		}

		if (intent == null) {
			throw new NullPointerException("intent can't be null");
		}

		AlarmManager am = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
				SystemClock.elapsedRealtime() + 1000, intent);
		commitSuicide();
	}

	/**
	 * Check whether AnJaRoot may be used or not.
	 * 
	 * This method checks if AnJaRoot is installed on this system and if it's
	 * usable. A negative return value may either indicate that AnJaRoot is not
	 * installed at all or the current installation is broken.
	 * 
	 * @return <code>true</true> if AnJaRoot is ready, <code>false</code> if
	 *         AnJaRoot is not usable on this system.
	 */
	public static boolean isAccessPossible() {
		return internal.isLibraryLoaded();
	}

	/**
	 * Check whether access is granted or not.
	 * 
	 * This method checks if access is already granted for the calling app. The
	 * user may proceed to {@link #gainAccess()} if <code>true</code> is
	 * returnd, otherwise {@link #requestAccess()} needs to be called.
	 * 
	 * @return <code>true</code> if the user allowed the usage of AnJaRoot for
	 *         this app, <code>false</code> if request must be requested
	 */
	public static boolean isAccessGranted() {
		try {
			return internal.isGranted();
		} catch (Exception ignored) {
			return false;
		}
	}

	/**
	 * Get the version identifier for AnJaRootLibrary.
	 * 
	 * @return The version identifier for AnJaRootLibrary.
	 */
	public static Version getLibraryVersion() {
		return internal.getLibraryVersion();
	}

	/**
	 * Get the version identifier for the native part of AnJaRoot
	 * 
	 * @return The version identifier for the native parts of AnJaRoot.
	 * @throws LibraryNotLoadedException
	 *             if AnJaRoot isn't installed
	 */
	public static Version getNativeVersion() throws LibraryNotLoadedException {
		return internal.getNativeVersion();
	}

	/**
	 * Check the current access level (active of inactive).
	 * 
	 * This method may be used to check whether the calling all is currently
	 * able to use root capabilities.
	 * 
	 * @return <code>true</code> if the privileges for this app are currently
	 *         raised, otherwise <code>false</code>
	 */
	public static boolean isAccessActive() {
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

	/**
	 * Gain root access
	 * 
	 * This method raises the calling app from an unprivileged state to a
	 * privileged state where root capabilities are granted and uid and gid are
	 * set both to 0 (root).
	 * 
	 * @return <code>true</code> if gain was successful, otherwise
	 *         <code>false</code>
	 */
	public static boolean gainAccess() {
		if (!isAccessGranted()) {
			return false;
		}

		if (isAccessActive()) {
			return true;
		}

		try {
			originalUids = internal.getUserIds();
			originalGids = internal.getGroupIds();
			originalCaps = internal.getCapabilities();
		} catch (Exception e) {
			return false;
		}

		try {
			internal.setCapabilities(rootCaps);
			internal.setUserIds(rootUids);
			internal.setGroupIds(rootGids);
		} catch (Exception e) {
			emergencyAccessDrop();
			return false;
		}

		return true;
	}

	/**
	 * Drop privileged state after usage.
	 * 
	 * This method drops the (possibly) acquired privileged state and restores
	 * the unprivileged state back. It's highly encouraged to do so because the
	 * Android system is very sensitive to uid and gid changed of a process
	 * (access is checked, for example, by referring to the callers uid and
	 * gid).
	 * 
	 * @return <code>true</code> is returned in case privileges have been
	 *         restored, <code>false</code> otherwise.
	 */
	public static boolean dropAccess() {
		if (!isAccessActive()) {
			return true;
		}

		try {
			internal.setGroupIds(originalGids);
		} catch (Exception e) {
			emergencyAccessDrop();
			return false;
		}

		try {
			internal.setUserIds(originalUids);
		} catch (Exception e) {
			emergencyAccessDrop();
			return false;
		}

		try {
			internal.setCapabilities(originalCaps);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	private static void emergencyAccessDrop() {
		try {
			internal.setGroupIds(originalGids);
		} catch (Exception ignored) {
		}

		try {
			internal.setUserIds(originalUids);
		} catch (Exception ignored) {
		}

		try {
			internal.setCapabilities(originalCaps);
		} catch (Exception ignored) {
		}
	}
}
