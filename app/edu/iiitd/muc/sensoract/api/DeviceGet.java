/*
 * Name: DeviceGet.java
 * Project: SensorActBroker, MUC@IIIT-Delhi 
 * Version: 1.0
 * Date: 2012-05-18
 * Author: Pandarasamy Arjunan
 */
package edu.iiitd.muc.sensoract.api;

import edu.iiitd.muc.sensoract.constants.Const;

/**
 * device/get API: Retries a device profile from the repository.
 * 
 * @author Pandarasamy Arjunan
 * @version 1.0
 */
public class DeviceGet extends SensorActBrokerAPI {

	/**
	 * Services the device/get API.
	 * 
	 * @param deviceGetJson
	 *            Device get request attributes in Json string
	 */
	public void doProcess(final String deviceGetJson) {

		String repoResponse = processRequest(Const.API_DEVICE_GET,
				deviceGetJson);
		sendRepoData(Const.API_DEVICE_GET, repoResponse);
	}
}
