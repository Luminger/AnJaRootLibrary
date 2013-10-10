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
package org.failedprojects.anjaroot.library;

import org.failedprojects.anjaroot.IAnJaRootService;
import org.failedprojects.anjaroot.library.exceptions.ServiceNotConnectedException;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class AnJaRootRequester {
	private static final String LOGTAG = "AnJaRootLibraryRequester";
	private final Context context;
	private IAnJaRootService service;
	private final ServiceConnection serviceConnection = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.v(LOGTAG, "Disconnected from AnJaRootService");
			service = null;
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder binder) {
			Log.v(LOGTAG, "Connected to AnJaRootService");
			service = IAnJaRootService.Stub.asInterface(binder);
		}
	};

	/**
	 * 
	 * @param context
	 *            a Context provided by the calling application
	 * @throws ServiceNotConnectedException
	 *             if bindService() fails (AnJaRoot may not be installed)
	 */
	public AnJaRootRequester(Context context)
			throws ServiceNotConnectedException {
		this.context = context;
		boolean connected = connectToService();

		if (!connected) {
			throw new ServiceNotConnectedException();
		}
	}

	private boolean connectToService() {
		if (service == null) {
			final Intent intent = new Intent(
					"org.failedprojects.anjaroot.action.REQUEST_ACCESS");
			return context.bindService(intent, serviceConnection,
					Context.BIND_AUTO_CREATE);
		}
		return true;
	}

	public boolean isConnectedToService() {
		connectToService();
		return service != null;
	}

	public boolean requestAccess() throws ServiceNotConnectedException {
		if (service == null) {
			connectToService();
			throw new ServiceNotConnectedException();
		}

		try {
			return service.requestAccess();
		} catch (RemoteException e) {
			Log.e(LOGTAG, "Remote method failed", e);
		}

		return false;
	}

}
