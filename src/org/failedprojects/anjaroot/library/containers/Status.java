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

/**
 * Native Status holder class
 * 
 * This class holds information received from the native part of AnJaRoot about
 * the current runtime status.
 */
public class Status {
	private final boolean hooked;
	private final boolean alreadyRun;
	private final boolean granted;

	public Status(boolean hooked, boolean alreadyRun, boolean granted) {
		this.hooked = hooked;
		this.alreadyRun = alreadyRun;
		this.granted = granted;
	}

	/**
	 * Get hook status
	 * 
	 * Get the status of the hook (if capset is actually hooked or not).
	 * 
	 * @return <code>true</code> if hook is placed, otherwise <code>false</code>
	 */
	public boolean isHooked() {
		return hooked;
	}

	/**
	 * Get if hook was already ran
	 * 
	 * Get the status of the hook (if it was ran in the zygote).
	 * 
	 * @return <code>true</code> if hook did ran, otherwise <code>false</code>
	 */
	public boolean isAlreadyRun() {
		return alreadyRun;
	}

	/**
	 * Get if the caller is granted
	 * 
	 * Get the current access status (if the caller is allowed to gain access)
	 * 
	 * @return <code>true</code> if granted, otherwise <code>false</code>
	 */
	public boolean isGranted() {
		return granted;
	}
}
