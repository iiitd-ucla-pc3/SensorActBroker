/*
 * Name: TaskGet.java
 * Project: SensorActBroker, MUC@IIIT-Delhi
 * Version: 1.0
 * Date: 2012-05-18
 * Author: Pandarasamy Arjunan
 */
package edu.iiitd.muc.sensoract.api;

import edu.iiitd.muc.sensoract.constants.Const;

/**
 * task/get API: Gets a task
 * 
 * @author Pandarasamy Arjunan
 * @version 1.0
 */
public class TaskGet extends SensorActBrokerAPI {

	/**
	 * Services the task/get API.
	 * 
	 * @param taskGetJson
	 *            Task get request attributes in Json string
	 */
	public void doProcess(final String taskGetJson) {

		String repoResponse = processRequest(Const.API_TASK_GET, taskGetJson);
		sendRepoData(Const.API_TASK_GET, repoResponse);
	}

}
