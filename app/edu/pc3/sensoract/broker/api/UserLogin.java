/*
 * Copyright (c) 2012, Indraprastha Institute of Information Technology,
 * Delhi (IIIT-D) and The Regents of the University of California.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above
 *    copyright notice, this list of conditions and the following
 *    disclaimer in the documentation and/or other materials provided
 *    with the distribution.
 * 3. Neither the names of the Indraprastha Institute of Information
 *    Technology, Delhi and the University of California nor the names
 *    of their contributors may be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE IIIT-D, THE REGENTS, AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE IIITD-D, THE REGENTS
 * OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *
 */
/*
 * Name: UserLogin.java
 * Project: SensorAct-Broker
 * Version: 1.0
 * Date: 2013-02-05
 * Author: Pandarasamy Arjunan
 */
package edu.pc3.sensoract.broker.api;

import edu.pc3.sensoract.broker.api.request.UserLoginFormat;
import edu.pc3.sensoract.broker.constants.Const;
import edu.pc3.sensoract.broker.enums.ErrorType;
import edu.pc3.sensoract.broker.exceptions.InvalidJsonException;

/**
 * user/login API: Verifies user credentials and sends the secretkey to the
 * caller.
 * 
 * @author Pandarasamy Arjunan
 * @version 1.0
 */

public class UserLogin extends SensorActBrokerAPI {

	/**
	 * Validates login credentials. If validation fails, sends corresponding
	 * failure message to the caller.
	 * 
	 * @param newLogin
	 *            Login credentials object to validate
	 */
	private void validateRequest(final UserLoginFormat newLogin) {

		validator.validateUserName(newLogin.username);
		validator.validatePassword(newLogin.password);

		if (validator.hasErrors()) {

			String errMsg = validator.getErrorMessages();
			if (errMsg.contains(Const.MSG_REQUIRED)) {
				response.sendFailure(Const.API_USER_LOGIN,
						ErrorType.VALIDATION_FAILED, errMsg);
			} else {
				// for min/max length error
				response.sendFailure(Const.API_USER_LOGIN,
						ErrorType.LOGIN_FAILED, newLogin.username);
			}
		}
	}

	/**
	 * Services the user/login API.
	 * 
	 * @param loginJson
	 *            Login credentials in json format
	 */
	public final void doProcess(final String loginJson) {

		try {
			UserLoginFormat newLogin = convertToRequestFormat(loginJson,
					UserLoginFormat.class);
			validateRequest(newLogin);

			newLogin.password = userProfile.getHashCode(newLogin.password);
			String secretkey = userProfile.getSecretkey(newLogin.username,
					newLogin.password);

			if (null != secretkey) {
				response.SendSuccess(Const.API_USER_LOGIN, Const.PARAM_OWNER, secretkey);
			} else {
				response.sendFailure(Const.API_USER_LOGIN,
						ErrorType.LOGIN_FAILED, newLogin.username);
			}

		} catch (InvalidJsonException e) {
			response.sendFailure(Const.API_USER_LOGIN, ErrorType.INVALID_JSON,
					e.getMessage());
		} catch (Exception e) {
			response.sendFailure(Const.API_USER_LOGIN, ErrorType.SYSTEM_ERROR,
					e.getMessage());
		}
	}
}
