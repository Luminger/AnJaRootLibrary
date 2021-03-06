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
 * Linux user uids holder class.
 * 
 * <p>
 * This class holds linux uids. It's not more than a helper for the underlying
 * native methods. If you want to know more about linux uids consult the
 * manpages.
 * </p>
 * 
 * @see <code>man 7 credentials</code>
 * @see <code>man 2 getresuid</code>
 */
public class UserIds {
	private long real = 0;
	private long effective = 0;
	private long saved = 0;

	public UserIds() {
	}

	public UserIds(long real, long effective, long saved) {
		this.real = real;
		this.effective = effective;
		this.saved = saved;
	}

	public long getReal() {
		return real;
	}

	public void setReal(long real) {
		this.real = real;
	}

	public long getEffective() {
		return effective;
	}

	public void setEffective(long effective) {
		this.effective = effective;
	}

	public long getSaved() {
		return saved;
	}

	public void setSaved(long saved) {
		this.saved = saved;
	}
}
