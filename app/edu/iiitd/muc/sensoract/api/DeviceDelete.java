/*
 * Name: DeviceDelete.java
 * Project: SensorActBroker, MUC@IIIT-Delhi 
 * Version: 1.0
 * Date: 2012-05-18
 * Author: Pandarasamy Arjunan
 */
package edu.iiitd.muc.sensoract.api;

import edu.iiitd.muc.sensoract.constants.Const;

/**
 * device/delete API: Deletes an existing device profile from the repository.
 * 
 * @author Pandarasamy Arjunan
 * @version 1.0
 */
public class DeviceDelete extends SensorActBrokerAPI {

	/**
	 * Services the device/delete API.
	 * 
	 * @param deviceDeleteJson
	 *            Device delete request attributes in Json string
	 */
	public void doProcess(final String deviceDeleteJson) {

		String repoResponse = processRequest(Const.API_DEVICE_DELETE,
				deviceDeleteJson);
		sendRepoMessage(Const.API_DEVICE_DELETE, repoResponse);

	}

}
