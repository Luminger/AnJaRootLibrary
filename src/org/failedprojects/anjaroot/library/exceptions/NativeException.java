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
package org.failedprojects.anjaroot.library.exceptions;

public class NativeException extends Exception {
	private static final long serialVersionUID = 5613972860865993387L;

	public NativeException(int errno_, String err) {
		super(err);
		errno = errno_;
	}
	
	public int getErrno() {
		return errno;
	}

	private int errno;
}
