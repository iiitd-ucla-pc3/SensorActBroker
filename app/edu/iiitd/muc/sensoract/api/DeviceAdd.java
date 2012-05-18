/*
 * Name: DeviceAdd.java
 * Project: SensorActBroker, MUC@IIIT-Delhi
 * Version: 1.0
 * Date: 2012-05-18
 * Author: Pandarasamy Arjunan
 */
package edu.iiitd.muc.sensoract.api;

import edu.iiitd.muc.sensoract.constants.Const;

/**
 * device/add API: Adds a new device profile to the repository.
 * 
 * @author Pandarasamy Arjunan
 * @version 1.0
 */
public class DeviceAdd extends SensorActBrokerAPI {

	/**
	 * Services the device/add API.
	 * 
	 * @param deviceAddJson
	 *            Device profile in Json
	 */
	public void doProcess(final String deviceAddJson) {

		String repoResponse = processRequest(Const.API_DEVICE_ADD,
				deviceAddJson);
		sendRepoMessage(Const.API_DEVICE_ADD, repoResponse);
	}
}
