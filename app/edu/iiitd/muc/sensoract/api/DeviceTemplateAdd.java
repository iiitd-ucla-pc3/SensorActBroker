/*
 * Name: DeviceTemplateAdd.java
 * Project: SensorActBroker, MUC@IIIT-Delhi
 * Version: 1.0
 * Date: 2012-05-24
 * Author: Pandarasamy Arjunan
 */
package edu.iiitd.muc.sensoract.api;

import edu.iiitd.muc.sensoract.constants.Const;

/**
 * device/template/add API: Adds a new device template to the repository.
 * 
 * @author Pandarasamy Arjunan
 * @version 1.0
 */
public class DeviceTemplateAdd extends SensorActBrokerAPI {

	/**
	 * Services the device/template/add API.
	 * 
	 * @param deviceAddJson
	 *            Device profile in Json
	 */
	public void doProcess(final String deviceAddJson) {

		String repoResponse = processRequest(Const.API_DEVICE_TEMPLATE_ADD,
				deviceAddJson);
		sendRepoMessage(Const.API_DEVICE_TEMPLATE_ADD, repoResponse);
	}
}
