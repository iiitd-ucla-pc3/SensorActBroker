/*
 * Name: RepoListResponseFormat.java
 * Project: SensorActBroker, MUC@IIIT-Delhi
 * Version: 1.0
 * Date: 2012-05-18
 * Author: Pandarasamy Arjunan
 */
package edu.iiitd.muc.sensoract.api.response;

import java.util.ArrayList;
import java.util.List;

import edu.iiitd.muc.sensoract.model.user.RepoInfoModel;

/**
 * Defines the response format for user/repo/list API.
 * 
 * @author Pandarasamy Arjunan
 * @version 1.0
 */
public class RepoListResponseFormat {

	public List<RepoInfoModel> repolist = null;

	public RepoListResponseFormat() {
		repolist = new ArrayList<RepoInfoModel>();
	}
}
