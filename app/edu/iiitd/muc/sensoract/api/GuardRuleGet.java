/*
 * Name: GuardRuleGet.java
 * Project: SensorActBroker, MUC@IIIT-Delhi
 * Version: 1.0
 * Date: 2012-05-18
 * Author: Pandarasamy Arjunan
 */
package edu.iiitd.muc.sensoract.api;

import edu.iiitd.muc.sensoract.constants.Const;

/**
 * guardrule/get API: Gets a guard rule
 * 
 * @author Pandarasamy Arjunan
 * @version 1.0
 */
public class GuardRuleGet extends SensorActBrokerAPI {

	/**
	 * Services the guardrule/get API.
	 * 
	 * @param guardRuleGetJson
	 *            Guard rule get request attributes in Json string
	 */
	public void doProcess(final String guardRuleGetJson) {

		String repoResponse = processRequest(Const.API_GUARDRULE_GET,
				guardRuleGetJson);
		sendRepoData(Const.API_GUARDRULE_GET, repoResponse);

	}

}
