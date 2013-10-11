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
 * Not connected to AnJaRoots Granting Service
 * 
 * This exception is thrown whenever the AnJaRootRequester tried to utilize the
 * connection to the Granting Service but wasn't able to do so.
 * 
 * If you see this exception it either means that the Granting Service isn't
 * accessible at all (broken AnJaRoot installation) or a temporary connection
 * loss occurred.
 * 
 * It may also mean that you are misusing the AnJaRootRequester, if you see this
 * on a regular basis, check your implementation.
 */
public class ServiceNotConnectedException extends Exception {

	private static final long serialVersionUID = -8362510380724054770L;

	public ServiceNotConnectedException() {
		super();
	}
}
