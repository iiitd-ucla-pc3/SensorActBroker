/*
 * Name: GuardRuleAdd.java
 * Project: SensorActBroker, MUC@IIIT-Delhi
 * Version: 1.0
 * Date: 2012-05-18
 * Author: Pandarasamy Arjunan
 */
package edu.iiitd.muc.sensoract.api;

import edu.iiitd.muc.sensoract.constants.Const;

/**
 * guardrule/add API: Adds a guard rule to a device
 * 
 * @author Pandarasamy Arjunan
 * @version 1.0
 */
public class GuardRuleAdd extends SensorActBrokerAPI {

	/**
	 * Services the guardrule/add API.
	 * 
	 * @param guardRuleAddJson
	 *            Guard rule add request attributes in Json string
	 */
	public void doProcess(final String guardRuleAddJson) {

		String repoResponse = processRequest(Const.API_GUARDRULE_ADD,
				guardRuleAddJson);
		sendRepoMessage(Const.API_GUARDRULE_ADD, repoResponse);

	}
}
