/*
 * Name: GuardRuleList.java
 * Project: SensorActBroker, MUC@IIIT-Delhi
 * Version: 1.0
 * Date: 2012-05-18
 * Author: Pandarasamy Arjunan
 */
package edu.iiitd.muc.sensoract.api;

import edu.iiitd.muc.sensoract.constants.Const;

/**
 * guardrule/list API: Gets a guard rule
 * 
 * @author Pandarasamy Arjunan
 * @version 1.0
 */
public class GuardRuleList extends SensorActBrokerAPI {

	/**
	 * Services the guardrule/list API.
	 * 
	 * @param guardRuleListJson
	 *            Guard rule list request attributes in Json string
	 */
	public void doProcess(final String guardRuleListJson) {

		String repoResponse = processRequest(Const.API_GUARDRULE_LIST,
				guardRuleListJson);
		sendRepoData(Const.API_GUARDRULE_LIST, repoResponse);
	}

}
