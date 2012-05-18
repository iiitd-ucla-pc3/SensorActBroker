/*
 * Name: RepoInfoModel.java
 * Project: SensorActBroker, MUC@IIIT-Delhi
 * Version: 1.0
 * Date: 2012-05-18
 * Author: Pandarasamy Arjunan
 */
package edu.iiitd.muc.sensoract.model.user;

import play.modules.morphia.Model;

/**
 * Model class for user profile, repo information management.
 * 
 * @author Pandarasamy Arjunan
 * @version 1.0
 */
// @Entity(value = "UserProfile", noClassnameStored = true)
public class RepoInfoModel extends Model {

	public String reponame = null;
	public String repoURL = null;
	public String repokey = null;

	public RepoInfoModel(final String name, final String URL,
			final String secretkey) {
		this.reponame = name;
		this.repoURL = URL;
		this.repokey = secretkey;
	}

	RepoInfoModel() {
	}
}
