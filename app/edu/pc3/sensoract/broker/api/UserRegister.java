/*
 * Name: UserRegister.java
 * Project: SensorAct-VPDS
 * Version: 1.0
 * Date: 2012-04-14
 * Author: Pandarasamy Arjunan
 */
package edu.pc3.sensoract.broker.api;

import edu.pc3.sensoract.broker.api.request.UserRegisterFormat;
import edu.pc3.sensoract.broker.constants.Const;
import edu.pc3.sensoract.broker.enums.ErrorType;
import edu.pc3.sensoract.broker.exceptions.InvalidJsonException;

/**
 * user/register API: Creates a new user profile in the repository.
 * 
 * @author Pandarasamy Arjunan
 * @version 1.0
 */

public class UserRegister extends SensorActBrokerAPI {

	/**
	 * Validates new user profile attributes. If validation fails, sends
	 * corresponding failure message to the caller.
	 * 
	 * @param userProfile
	 *            User profile object to validate
	 */
	private void validateUserProfile(final UserRegisterFormat userProfile) {

		validator.validateUserName(userProfile.username);
		validator.validatePassword(userProfile.password);
		validator.validateEmail(userProfile.email);

		if (validator.hasErrors()) {
			response.sendFailure(Const.API_USER_REGISTER,
					ErrorType.VALIDATION_FAILED, validator.getErrorMessages());
		}
	}

	/**
	 * Services the user/register API.
	 * 
	 * New user profile will be created in the repository using the request
	 * parameters.
	 * 
	 * @param userProfileJson
	 *            User profile attributes in Json format
	 */
	public final void doProcess(final String userProfileJson) {

		try {
			UserRegisterFormat newUser = convertToRequestFormat(
					userProfileJson, UserRegisterFormat.class);
			validateUserProfile(newUser);

			if (userProfile.isUserProfileExists(newUser)
					|| vpdsOwnerProfile.isVPDSProfileExists(newUser)) {
				response.sendFailure(Const.API_USER_REGISTER,
						ErrorType.USER_ALREADYEXISTS, newUser.username);
			}

			userProfile.addUserProfile(newUser);
			response.SendSuccess(Const.API_USER_REGISTER,
					Const.USER_REGISTERED, newUser.username);

		} catch (InvalidJsonException e) {
			response.sendFailure(Const.API_USER_REGISTER,
					ErrorType.INVALID_JSON, e.getMessage());
		} catch (Exception e) {
			response.sendFailure(Const.API_USER_REGISTER,
					ErrorType.SYSTEM_ERROR, e.getMessage());
		}
	}
}