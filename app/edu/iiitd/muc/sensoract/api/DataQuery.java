/*
 * Name: DataQuery.java
 * Project: SensorActBroker, MUC@IIIT-Delhi 
 * Version: 1.0
 * Date: 2012-04-18
 * Author: Pandarasamy Arjunan
 */
package edu.iiitd.muc.sensoract.api;

import edu.iiitd.muc.sensoract.constants.Const;

/**
 * data/query API: Retrieves wavesegmetns from the repository based upong the
 * given query.
 * 
 * @author Pandarasamy Arjunan
 * @version 1.0
 */
public class DataQuery extends SensorActBrokerAPI {

	/**
	 * Services the data/query API. Retrieves data from the repository as per the
	 * request query and sends back to the caller.
	 * 
	 * @param queryJson
	 *            Request query in Json string
	 */
	public void doProcess(final String queryJson) {

		String repoResponse = processRequest(Const.API_DATA_QUERY, queryJson);
		sendRepoData(Const.API_DATA_QUERY, repoResponse);
	}

}
