package org.failedprojects.anjaroot.library.containers;

public class Status {
	private boolean hooked;
	private boolean alreadyRun;

	public Status(boolean hooked, boolean alreadyRun) {
		this.hooked = hooked;
		this.alreadyRun = alreadyRun;
	}

	public boolean isHooked() {
		return hooked;
	}

	public boolean isAlreadyRun() {
		return alreadyRun;
	}

}
