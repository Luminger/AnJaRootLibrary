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
 * Linux capabilities holder class.
 * 
 * <p>
 * This class holds informations about the capabilities a linux process has.
 * It's not more than a helper for the underlying native methods. If you want to
 * know more about linux capabilities consult the manpages.
 * </p>
 * 
 * @see <code>man 7 capabilities</code>
 * @see <code>man 2 capget</code>
 */
public class Capabilities {
	private long effective = 0;
	private long permitted = 0;
	private long inheritable = 0;

	public Capabilities() {
	}

	public Capabilities(long effective, long permitted, long inheritable) {
		this.effective = effective;
		this.permitted = permitted;
		this.inheritable = inheritable;
	}

	public long getEffective() {
		return effective;
	}

	public void setEffective(long effective) {
		this.effective = effective;
	}

	public long getPermitted() {
		return permitted;
	}

	public void setPermitted(long permitted) {
		this.permitted = permitted;
	}

	public long getInheritable() {
		return inheritable;
	}

	public void setInheritable(long inheritable) {
		this.inheritable = inheritable;
	}
}
