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

import java.util.List;

import edu.pc3.sensoract.broker.api.request.DeviceOwnerSharedFormat;
import edu.pc3.sensoract.broker.constants.Const;
import edu.pc3.sensoract.broker.enums.ErrorType;
import edu.pc3.sensoract.broker.exceptions.InvalidJsonException;
import edu.pc3.sensoract.broker.model.DeviceSharingModel;

/**
 * user/login API: Verifies user credentials and sends the secretkey to the
 * caller.
 * 
 * @author Pandarasamy Arjunan
 * @version 1.0
 */

public class DeviceOwnerShared extends SensorActBrokerAPI {

	public List<DeviceSharingModel> devicelist = null;

	/**
	 * Validates login credentials. If validation fails, sends corresponding
	 * failure message to the caller.
	 * 
	 * @param newLogin
	 *            Login credentials object to validate
	 */
	private void validateRequest(final DeviceOwnerSharedFormat req) {

		validator.validateSecretKey(req.secretkey);
		if (validator.hasErrors()) {
			response.sendFailure(Const.API_DEVICE_OWNER_SHARED,
					ErrorType.VALIDATION_FAILED, validator.getErrorMessages());
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
			DeviceOwnerSharedFormat req = convertToRequestFormat(loginJson,
					DeviceOwnerSharedFormat.class);
			validateRequest(req);

			String ownername = userProfile.getUsername(req.secretkey);
			if (null == ownername) {
				response.sendFailure(Const.API_DEVICE_OWNER_SHARED,
						ErrorType.UNREGISTERED_SECRETKEY, req.secretkey);
			}

			if (!userProfile.isOwner(req.secretkey)) {
				response.sendFailure(Const.API_DEVICE_OWNER_SHARED,
						ErrorType.VPDS_NO_VPDS_REGISTERED, Const.EMPTY);
			}

			DeviceUserShared out = new DeviceUserShared();
			out.devicelist = DeviceSharingModel.find("voname", ownername).fetchAll();
			response.sendJSON(out);

			/*
			 * if (!userProfile.isOwner(req.secretkey)) {
			 * response.sendFailure(Const.API_DEVICE_USER_SHARED,
			 * ErrorType.VPDS_NO_VPDS_REGISTERED, Const.EMPTY); }
			 */

		} catch (InvalidJsonException e) {
			response.sendFailure(Const.API_DEVICE_OWNER_SHARED,
					ErrorType.INVALID_JSON, e.getMessage());
		} catch (Exception e) {
			response.sendFailure(Const.API_DEVICE_OWNER_SHARED,
					ErrorType.SYSTEM_ERROR, e.getMessage());
		}
	}
}
