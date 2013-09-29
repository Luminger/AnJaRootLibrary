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

	@Override
	@SuppressLint("DefaultLocale")
	public String toString() {
		return String.format("%d.%d.%d-%d", major, minor, patch, apilvl);
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
