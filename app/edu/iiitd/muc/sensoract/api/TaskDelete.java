/*
 * Name: TaskDelete.java
 * Project: SensorActBroker, MUC@IIIT-Delhi
 * Version: 1.0
 * Date: 2012-05-18
 * Author: Pandarasamy Arjunan
 */
package edu.iiitd.muc.sensoract.api;

import edu.iiitd.muc.sensoract.constants.Const;

/**
 * task/delete API: Deletes a task
 * 
 * @author Pandarasamy Arjunan
 * @version 1.0
 */
public class TaskDelete extends SensorActBrokerAPI {

	/**
	 * Services the task/delete API.
	 * 
	 * @param taskDeleteJson
	 *            Task delete request attributes in Json string
	 */
	public void doProcess(final String taskDeleteJson) {

		String repoResponse = processRequest(Const.API_TASK_DELETE,
				taskDeleteJson);
		sendRepoMessage(Const.API_TASK_DELETE, repoResponse);
	}

}
