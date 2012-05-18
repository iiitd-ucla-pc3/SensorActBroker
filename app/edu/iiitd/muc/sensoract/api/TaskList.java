/*
 * Name: TaskList.java
 * Project: SensorActBroker, MUC@IIIT-Delhi
 * Version: 1.0
 * Date: 2012-05-18
 * Author: Pandarasamy Arjunan
 */
package edu.iiitd.muc.sensoract.api;

import edu.iiitd.muc.sensoract.constants.Const;

/**
 * task/list API: Lists task
 * 
 * @author Pandarasamy Arjunan
 * @version 1.0
 */
public class TaskList extends SensorActBrokerAPI {

	/**
	 * Services the task/list API.
	 * 
	 * @param taskListJson
	 *            Task list request attributes in Json string
	 */
	public void doProcess(final String taskListJson) {

		String repoResponse = processRequest(Const.API_TASK_LIST, taskListJson);
		sendRepoData(Const.API_TASK_LIST, repoResponse);

	}

}
