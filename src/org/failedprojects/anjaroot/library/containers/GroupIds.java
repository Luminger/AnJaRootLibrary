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

public class GroupIds {
	private long real = 0;
	private long effective = 0;
	private long saved = 0;
	
	public GroupIds() {
	}
	
	public GroupIds(long real, long effective, long saved) {
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
