/*
 * Copyright 2013 Simon Brakhane
 *
 * This file is part of AnJaRoot.
 *
 * AnJaRoot is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * AnJaRoot is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * AnJaRoot. If not, see http://www.gnu.org/licenses/.
 */
package org.failedprojects.anjaroot.library.containers;

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
