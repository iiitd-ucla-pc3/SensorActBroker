/*
 * Name: DeviceTemplateGet.java
 * Project: SensorActBroker, MUC@IIIT-Delhi 
 * Version: 1.0
 * Date: 2012-05-24
 * Author: Pandarasamy Arjunan
 */
package edu.iiitd.muc.sensoract.api;

import edu.iiitd.muc.sensoract.constants.Const;

/**
 * device/template/get API: Retries a device profile from the repository.
 * 
 * @author Pandarasamy Arjunan
 * @version 1.0
 */
public class DeviceTemplateGet extends SensorActBrokerAPI {

	/**
	 * Services the device/template/get API.
	 * 
	 * @param deviceGetJson
	 *            Device get request attributes in Json string
	 */
	public void doProcess(final String deviceGetJson) {

		String repoResponse = processRequest(Const.API_DEVICE_TEMPLATE_GET,
				deviceGetJson);
		sendRepoData(Const.API_DEVICE_TEMPLATE_GET, repoResponse);
	}
}
