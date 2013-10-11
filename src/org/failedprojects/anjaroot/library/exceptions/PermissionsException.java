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

/**
 * Insufficient permissions.
 * 
 * This exception is thrown whenever a native method wasn't able to fulfill a
 * request because the caller had insufficient permissions.
 * 
 * If you see this exception if you don't use AnJaRootInternal, something is
 * wrong with the provided wrappers. If you are using AnJaRootInternal it's up
 * to you to interpret the returned errno.
 */
public class PermissionsException extends Exception {
	private static final long serialVersionUID = -2302160964432316936L;

	public PermissionsException(String msg) {
		super(msg);
	}
}
