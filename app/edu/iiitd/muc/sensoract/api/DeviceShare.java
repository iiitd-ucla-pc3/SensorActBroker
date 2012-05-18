/*
 * Name: DeviceShare.java
 * Project: SensorActBroker, MUC@IIIT-Delhi
 * Version: 1.0
 * Date: 2012-05-18
 * Author: Pandarasamy Arjunan
 */
package edu.iiitd.muc.sensoract.api;

import edu.iiitd.muc.sensoract.constants.Const;

/**
 * device/share API: Share device profile with others
 * 
 * @author Pandarasamy Arjunan
 * @version 1.0
 */
public class DeviceShare extends SensorActBrokerAPI {

	/**
	 * Services the device/share API.
	 * 
	 * @param deviceShareJson
	 *            Device share request attributes in Json string
	 */
	public void doProcess(final String deviceShareJson) {

		String repoResponse = processRequest(Const.API_DEVICE_SHARE,
				deviceShareJson);
		sendRepoData(Const.API_DEVICE_SHARE, repoResponse);

	}

}
