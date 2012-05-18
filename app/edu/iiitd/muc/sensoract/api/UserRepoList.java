/*
 * Name: UserRepoList.java
 * Project: SensorActBroker, MUC@IIIT-Delhi
 * Version: 1.0
 * Date: 2012-05-18
 * Author: Pandarasamy Arjunan
 */
package edu.iiitd.muc.sensoract.api;

import java.util.Iterator;
import java.util.List;

import edu.iiitd.muc.sensoract.api.request.UserRepoListFormat;
import edu.iiitd.muc.sensoract.api.response.RepoListResponseFormat;
import edu.iiitd.muc.sensoract.constants.Const;
import edu.iiitd.muc.sensoract.enums.ErrorType;
import edu.iiitd.muc.sensoract.exceptions.InvalidJsonException;
import edu.iiitd.muc.sensoract.model.user.RepoInfoModel;
import edu.iiitd.muc.sensoract.profile.UserProfile;

/**
 * user/repo/list API: Returns a list of all registered repo profile
 * 
 * @author Pandarasamy Arjunan
 * @version 1.0
 */

public class UserRepoList extends SensorActBrokerAPI {

	/**
	 * Converts the user device profile request in Json string to object.
	 * 
	 * @param userJson
	 *            User profile in Json string
	 * @return Converted user profile object
	 * @throws InvalidJsonException
	 *             If the Json string is not valid or not in the required
	 *             request format
	 * @see UserRepoListFormat
	 */
	private UserRepoListFormat convertToRepoListFormat(final String repoJson)
			throws InvalidJsonException {
		UserRepoListFormat newRepo = null;
		try {
			newRepo = gson.fromJson(repoJson, UserRepoListFormat.class);
		} catch (Exception e) {
			throw new InvalidJsonException(e.getMessage());
		}

		if (null == newRepo) {
			throw new InvalidJsonException(Const.EMPTY_JSON);
		}
		return newRepo;
	}

	/**
	 * Validates new user profile attributes. If validation fails, sends
	 * corresponding failure message to the caller.
	 * 
	 * @param newRepo
	 *            User profile object to validate
	 */
	private void validateRepoProfile(final UserRepoListFormat newRepo) {

		// TODO: Add validation
		// validator.validateRepoOwner(newRepo.owner);
		// validator.validateRepoName(newRepo.name);
		// validator.validateRepoURL(newRepo.URL);

		if (validator.hasErrors()) {
			response.sendFailure(Const.API_USER_REPO_LIST,
					ErrorType.VALIDATION_FAILED, validator.getErrorMessages());
		}
	}

	/**
	 * Sends the list of requested device profile object to caller in Json
	 * array.
	 * 
	 * @param repoInfoList
	 *            List of device profile objects to send.
	 */
	private void sendRepoInfoList(final List<RepoInfoModel> repoInfoList) {

		RepoListResponseFormat outList = new RepoListResponseFormat();

		Iterator<RepoInfoModel> repoInfoListIterator = repoInfoList.iterator();

		while (repoInfoListIterator.hasNext()) {
			RepoInfoModel repo = repoInfoListIterator.next();
			repo.repokey = null;
			outList.repolist.add(repo);
		}

		System.out.println(gson.toJson(outList));

		// TODO: log the request
		response.sendJSON(outList);

	}

	/**
	 * Services the registeruser API (POST format request, user profile in
	 * Json).
	 * 
	 * New user profile will be created in the repository using the request
	 * parameters.
	 * 
	 * @param userJson
	 *            User profile (username, password, email and secretkey) in Json
	 */

	public final void doProcess(final String repoJson) {

		try {
			UserRepoListFormat newRepo = convertToRepoListFormat(repoJson);
			validateRepoProfile(newRepo);

			if (!UserProfile.isRegisteredSecretkey(newRepo.secretkey)) {
				response.sendFailure(Const.API_USER_REPO_LIST,
						ErrorType.UNREGISTERED_SECRETKEY, newRepo.secretkey);
			}

			// TODO: Change the error type
			List<RepoInfoModel> repoList = UserProfile
					.getRepoList(newRepo.secretkey);
			if (null == repoList || 0 == repoList.size()) {
				response.sendFailure(Const.API_USER_REPO_LIST,
						ErrorType.DEVICE_NODEVICE_FOUND, Const.MSG_NONE);
			}

			sendRepoInfoList(repoList);

		} catch (InvalidJsonException e) {
			response.sendFailure(Const.API_USER_REPO_LIST,
					ErrorType.INVALID_JSON, e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			response.sendFailure(Const.API_USER_REPO_LIST,
					ErrorType.SYSTEM_ERROR, e.getMessage());
		}
	}

}
