/*
 * Name: DeviceList.java
 * Project: SensorActBroker, MUC@IIIT-Delhi
 * Version: 1.0
 * Date: 2012-05-18
 * Author: Pandarasamy Arjunan
 */
package edu.iiitd.muc.sensoract.api;

import edu.iiitd.muc.sensoract.constants.Const;

/**
 * device/list API: Retries all device profiles associated to an user from the
 * repository.
 * 
 * @author Pandarasamy Arjunan
 * @version 1.0
 */
public class DeviceList extends SensorActBrokerAPI {

	/**
	 * Services the device/list API.
	 * 
	 * @param deviceListJson
	 *            Device list request attributes in Json string
	 */
	public void doProcess(final String deviceListJson) {

		String repoResponse = processRequest(Const.API_DEVICE_LIST,
				deviceListJson);
		sendRepoData(Const.API_DEVICE_LIST, repoResponse);

	}

}
