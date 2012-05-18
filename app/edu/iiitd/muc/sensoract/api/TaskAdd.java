/*
 * Name: TaskAdd.java
 * Project: SensorActBroker, MUC@IIIT-Delhi
 * Version: 1.0
 * Date: 2012-05-18
 * Author: Pandarasamy Arjunan
 */
package edu.iiitd.muc.sensoract.api;

import edu.iiitd.muc.sensoract.constants.Const;

/**
 * task/add API: Adds a task
 * 
 * @author Pandarasamy Arjunan
 * @version 1.0
 */
public class TaskAdd extends SensorActBrokerAPI {

	/**
	 * Services the task/add API.
	 * 
	 * @param taskAddJson
	 *            Task add request attributes in Json string
	 */
	public void doProcess(final String taskAddJson) {

		String repoResponse = processRequest(Const.API_TASK_ADD, taskAddJson);
		sendRepoMessage(Const.API_TASK_ADD, repoResponse);
	}

}
