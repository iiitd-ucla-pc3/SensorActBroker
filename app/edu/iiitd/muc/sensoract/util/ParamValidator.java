/*
 * Name: ParamValidator.java
 * Project: SensorActBroker, MUC@IIIT-Delhi
 * Version: 1.0
 * Date: 2012-05-18
 * Author: Pandarasamy Arjunan
 */
package edu.iiitd.muc.sensoract.util;

import play.data.validation.Error;
import edu.iiitd.muc.sensoract.api.SensorActBrokerAPI;
import edu.iiitd.muc.sensoract.constants.Const;

/**
 * API helper class to validate various request parameters. This is a wrapper
 * for play's internal <validation> object.
 * 
 * @author Pandarasamy Arjunan
 * @version 1.0
 */
public class ParamValidator extends SensorActBrokerAPI {

	/**
	 * Validates a string parameter and sets the corresponding error messages on
	 * failure.
	 * 
	 * @param string
	 *            String parameter value
	 * @param name
	 *            Name of the parameter
	 * @param minSize
	 *            Parameter's valid minimum length
	 * @param maxSize
	 *            Parameter's valid maximum length
	 */
	private void validateString(final String string, final String name,
			final int minSize, final int maxSize) {
		validation.required(string).message(name + Const.MSG_REQUIRED);
		validation.minSize(string, minSize).message(
				name + Const.MSG_MIN_LENGTH + minSize);
		validation.maxSize(string, maxSize).message(
				name + Const.MSG_MAX_LENGTH + maxSize);
	}

	public boolean hasErrors() {
		return validation.hasErrors();
	}

	/**
	 * Returns validation error messages, if any previous validation has failed.
	 * Otherwise returns null.
	 * 
	 * @return Validation error messages separated by ;
	 */
	public String getErrorMessages() {

		if (!validation.hasErrors()) {
			return null;
		}

		StringBuffer errMsg = new StringBuffer();
		for (Error error : validation.errors()) {
			errMsg.append(error.message()).append(";");
		}
		return errMsg.toString();
	}

	/**
	 * Adds an error message to the validation object
	 * 
	 * @param field
	 *            Name of the attribute caused the error
	 * @param message
	 *            Error message
	 */
	public void addError(final String field, final String message) {
		validation.addError(field, message);
	}

	/**
	 * Validates username parameter and sets corresponding error message, if
	 * validation fails.
	 * 
	 * @param username
	 *            User name to validate
	 */
	public void validateUserName(final String username) {
		validateString(username, Const.PARAM_USERNAME,
				Const.USERNAME_MIN_LENGTH, Const.USERNAME_MAX_LENGTH);
	}

	/**
	 * Validates password parameter and sets the corresponding error message, if
	 * validation fails.
	 * 
	 * @param password
	 *            Password Password to validate
	 */
	public void validatePassword(final String password) {
		validateString(password, Const.PARAM_PASSWORD,
				Const.PASSWORD_MIN_LENGTH, Const.PASSWORD_MAX_LENGTH);
	}

	/**
	 * Validates email parameter and sets the corresponding error message, if
	 * validation fails.
	 * 
	 * @param email
	 *            E-mail address to validate
	 */
	public void validateEmail(final String email) {
		validation.required(email).message(
				Const.PARAM_EMAIL + Const.MSG_REQUIRED);
		validation.email(email).message(Const.PARAM_EMAIL + Const.MSG_INVALID);
		validation.minSize(email, Const.EMAIL_MIN_LENGTH).message(
				Const.PARAM_EMAIL + Const.MSG_MIN_LENGTH
						+ Const.EMAIL_MIN_LENGTH);
		validation.maxSize(email, Const.EMAIL_MAX_LENGTH).message(
				Const.PARAM_EMAIL + Const.MSG_MAX_LENGTH
						+ Const.EMAIL_MAX_LENGTH);
	}

	/**
	 * Validates secretkey parameter and sets the corresponding error message,
	 * if validation fails.
	 * 
	 * @param secretkey
	 *            Secret key to validate
	 */
	public void validateSecretKey(final String secretkey) {
		validateString(secretkey, Const.PARAM_SECRETKEY,
				Const.SECRETKEY_MIN_LENGTH, Const.SECRETKEY_MAX_LENGTH);
	}

	/**
	 * 
	 * @param repoName
	 */
	public void validateRepoName(final String repoName) {
		validateString(repoName, Const.PARAM_REPONAME,
				Const.REPONAME_MIN_LENGTH, Const.REPONAME_MAX_LENGTH);
	}

	/**
	 * 
	 * @param repoURL
	 */
	public void validateRepoURL(final String repoURL) {
		validateString(repoURL, Const.PARAM_REPOURL, Const.REPOURL_MIN_LENGTH,
				Const.REPOURL_MAX_LENGTH);
	}

	/**
	 * Validates secretkey parameter and sets the corresponding error message,
	 * if validation fails.
	 * 
	 * @param secretkey
	 *            Secret key to validate
	 */
	public void validateRepoKey(final String repoKey) {
		validateString(repoKey, Const.PARAM_REPOKEY, Const.REPOKEY_MIN_LENGTH,
				Const.REPOKEY_MAX_LENGTH);
	}

}
