/*
 * Name: DeviceTemplateDelete.java
 * Project: SensorActBroker, MUC@IIIT-Delhi 
 * Version: 1.0
 * Date: 2012-05-24
 * Author: Pandarasamy Arjunan
 */
package edu.iiitd.muc.sensoract.api;

import edu.iiitd.muc.sensoract.constants.Const;

/**
 * device/template/delete API: Deletes an existing device template from the repository.
 * 
 * @author Pandarasamy Arjunan
 * @version 1.0
 */
public class DeviceTemplateDelete extends SensorActBrokerAPI {

	/**
	 * Services the device/template/delete API.
	 * 
	 * @param deviceDeleteJson
	 *            Device delete request attributes in Json string
	 */
	public void doProcess(final String deviceDeleteJson) {

		String repoResponse = processRequest(Const.API_DEVICE_TEMPLATE_DELETE,
				deviceDeleteJson);
		sendRepoMessage(Const.API_DEVICE_TEMPLATE_DELETE, repoResponse);

	}

}
