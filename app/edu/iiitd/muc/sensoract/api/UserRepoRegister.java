/*
 * Name: UserRepoRegister.java
 * Project: SensorActBroker, MUC@IIIT-Delhi 
 * Version: 1.0
 * Date: 2012-05-18
 * Author: Pandarasamy Arjunan
 */
package edu.iiitd.muc.sensoract.api;

import edu.iiitd.muc.sensoract.api.request.UserRepoRegisterFormat;
import edu.iiitd.muc.sensoract.constants.Const;
import edu.iiitd.muc.sensoract.enums.ErrorType;
import edu.iiitd.muc.sensoract.exceptions.InvalidJsonException;
import edu.iiitd.muc.sensoract.profile.UserProfile;

/**
 * user/repo/register API: Register a new repository to the broker
 * 
 * @author Pandarasamy Arjunan
 * @version 1.0
 */
public class UserRepoRegister extends SensorActBrokerAPI {

	/**
	 * Converts the user device profile request in Json string to object.
	 * 
	 * @param userJson
	 *            User profile in Json string
	 * @return Converted user profile object
	 * @throws InvalidJsonException
	 *             If the Json string is not valid or not in the required
	 *             request format
	 * @see UserRepoRegisterFormat
	 */
	private UserRepoRegisterFormat convertToRegisterRepoFormat(final String repoJson)
			throws InvalidJsonException {
		UserRepoRegisterFormat newRepo = null;
		try {
			newRepo = gson.fromJson(repoJson, UserRepoRegisterFormat.class);
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
	private void validateRepoProfile(final UserRepoRegisterFormat newRepo) {

		validator.validateSecretKey(newRepo.secretkey);
		validator.validateRepoName(newRepo.reponame);
		validator.validateRepoURL(newRepo.repoURL);
		validator.validateRepoKey(newRepo.repokey);

		if (validator.hasErrors()) {
			response.sendFailure(Const.API_USER_REPO_REGISTER,
					ErrorType.VALIDATION_FAILED, validator.getErrorMessages());
		}
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
			UserRepoRegisterFormat newRepo = convertToRegisterRepoFormat(repoJson);
			validateRepoProfile(newRepo);

			if (!UserProfile.isRegisteredSecretkey(newRepo.secretkey)) {
				response.sendFailure(Const.API_USER_REPO_REGISTER,
						ErrorType.UNREGISTERED_SECRETKEY, newRepo.secretkey);
			}

			// TODO: find duplicate repo before adding

			// TODO: Change the error type
			if (!UserProfile.addRepo(newRepo)) {
				response.sendFailure(Const.API_USER_REPO_REGISTER,
						ErrorType.UNREGISTERED_SECRETKEY, newRepo.secretkey);
			}

			// TODO: Change the const name REGISTERREPO_CREATED
			response.SendSuccess(Const.API_USER_REPO_REGISTER,
					Const.REGISTERREPO_CREATED, newRepo.reponame);

		} catch (InvalidJsonException e) {
			response.sendFailure(Const.API_USER_REPO_REGISTER,
					ErrorType.INVALID_JSON, e.getMessage());
		} catch (Exception e) {
			response.sendFailure(Const.API_USER_REPO_REGISTER,
					ErrorType.SYSTEM_ERROR, e.getMessage());
		}
	}
}
