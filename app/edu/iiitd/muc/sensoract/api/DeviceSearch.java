/*
 * Name: DeviceSearch.java
 * Project: SensorActBroker, MUC@IIIT-Delhi
 * Version: 1.0
 * Date: 2012-05-18
 * Author: Pandarasamy Arjunan
 */
package edu.iiitd.muc.sensoract.api;

import edu.iiitd.muc.sensoract.constants.Const;

/**
 * device/search API: Searches device profiles in the repository
 * 
 * @author Pandarasamy Arjunan
 * @version 1.0
 */
public class DeviceSearch extends SensorActBrokerAPI {


	/**
	 * Services the device/search API.
	 * 
	 * @param deviceSearchJson
	 *            Device search request attributes in Json string
	 */
	public void doProcess(final String deviceSearchJson) {

		String repoResponse = processRequest(Const.API_DEVICE_SEARCH,
				deviceSearchJson);
		sendRepoData(Const.API_DEVICE_SEARCH, repoResponse);

	}

}
