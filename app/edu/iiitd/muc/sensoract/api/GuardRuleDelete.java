/*
 * Name: GuardRuleDelete.java
 * Project: SensorActBroker, MUC@IIIT-Delhi
 * Version: 1.0
 * Date: 2012-05-18
 * Author: Pandarasamy Arjunan
 */
package edu.iiitd.muc.sensoract.api;

import edu.iiitd.muc.sensoract.constants.Const;

/**
 * guardrule/delete API: Deletes a guard rule
 * 
 * @author Pandarasamy Arjunan
 * @version 1.0
 */
public class GuardRuleDelete extends SensorActBrokerAPI {

	/**
	 * Services the guardrule/delete API.
	 * 
	 * @param guardRuleDeleteJson
	 *            Guard rule delete request attributes in Json string
	 */
	public void doProcess(final String guardRuleDeleteJson) {

		String repoResponse = processRequest(Const.API_GUARDRULE_DELETE,
				guardRuleDeleteJson);
		sendRepoMessage(Const.API_GUARDRULE_DELETE, repoResponse);

	}

}
