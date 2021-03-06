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
package org.failedprojects.anjaroot.library.containers;

import android.annotation.SuppressLint;

/**
 * AnJaRoot version information holder.
 * 
 * <p>
 * This class holds version information from either the AnJaRoot Library or it's
 * native counterpart. It's only useful under certain conditions (additional
 * compatibility checks or simple version printing).
 * </p>
 * 
 * <p>
 * The versioning scheme used by AnJaRoot complies with the Semantic Versioning
 * 2.0.0. The ApiLevel is constantly increased whenever a "significant" Api
 * change has been done on the native side, it's not part of the version
 * identifier itself - it's meant for AnJaRootLibrary to be able to react to
 * different native versions.
 * </p>
 * 
 * @see <a href="http://semver.org/spec/v2.0.0.html">Semantic Versioning
 *      2.0.0.</a>
 */
public class Version {
	private final int major;
	private final int minor;
	private final int patch;
	private final int apilvl;

	public Version(int major, int minor, int patch, int apilvl) {
		this.major = major;
		this.minor = minor;
		this.patch = patch;
		this.apilvl = apilvl;
	}

	/**
	 * Get Version as String for printing
	 * 
	 * @return A String with the format Major.Minor.Patch
	 */
	@Override
	@SuppressLint("DefaultLocale")
	public String toString() {
		return String.format("%d.%d.%d", major, minor, patch);
	}

	public int getMajor() {
		return major;
	}

	public int getMinor() {
		return minor;
	}

	public int getPatch() {
		return patch;
	}

	public int getApiLevel() {
		return apilvl;
	}
}
