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
 * Name: DeviceShare.java
 * Project: SensorAct-VPDS
 * Version: 1.0
 * Date: 2012-05-13
 * Author: Pandarasamy Arjunan
 */
package edu.pc3.sensoract.broker.api;

import play.libs.WS;
import play.libs.WS.HttpResponse;
import edu.pc3.sensoract.broker.api.request.DeviceShareFormat;
import edu.pc3.sensoract.broker.api.response.ResponseFormat;
import edu.pc3.sensoract.broker.constants.Const;
import edu.pc3.sensoract.broker.enums.ErrorType;
import edu.pc3.sensoract.broker.exceptions.InvalidJsonException;
import edu.pc3.sensoract.broker.model.DeviceSharingModel;
import edu.pc3.sensoract.broker.model.VPDSProfileModel;

/**
 * device/share API: Share device profile with others
 * 
 * @author Pandarasamy Arjunan
 * @version 1.0
 */
public class DeviceShare extends SensorActBrokerAPI {

	/**
	 * Validates the device share request format attributes. If validation
	 * fails, sends corresponding failure message to the caller.
	 * 
	 * @param deviceShareRequest
	 *            Device share request format object
	 */
	private void validateRequest(final DeviceShareFormat deviceShareRequest) {

		validator.validateSecretKey(deviceShareRequest.secretkey);
		// TODO: add validation for other parameters

		if (validator.hasErrors()) {
			response.sendFailure(Const.API_DEVICE_SHARE,
					ErrorType.VALIDATION_FAILED, validator.getErrorMessages());
		}
	}

	private void shareDevice(final DeviceShareFormat req) {

		// Step 1: verify the vpdsname
		VPDSProfileModel vpds = userProfile.getVPDSProfile(req.secretkey,
				req.vpdsname);
		if (null == vpds) {
			response.sendFailure(Const.API_DEVICE_SHARE,
					ErrorType.VPDS_NO_VPDS_REGISTERED, Const.EMPTY);
		}

		// TODO: am I really mad?
		req.email = userProfile.getEmail(userProfile.getUsername(req.secretkey));
		
		String param = json.toJson(req);
		System.out.println(param);
		System.out.println(json.toJson(vpds));

		// Step 2: invoke the VPDS device/share API
		HttpResponse httpResponse = null;
		ResponseFormat vpdsResponse = null;
		try {
			httpResponse = WS.url(vpds.vpdsURL + "/device/share").body(param).post();

			vpdsResponse = convertToRequestFormat(httpResponse.getString(),
					ResponseFormat.class);

		} catch (Exception e) {
			response.sendFailure(Const.API_DEVICE_SHARE,
					ErrorType.VPDS_CONNECTION_FAILED, e.getMessage());
		}

		if (Const.SUCCESS != vpdsResponse.statuscode) {
			//response.sendFailure(Const.API_DEVICE_SHARE,
				//	ErrorType.VPDS_CONNECTION_FAILED, vpdsResponse.message);
		}

		System.out.println(httpResponse.getString());

		// Step 3: Update the tables
		String owner = userProfile.getUsername(req.secretkey);
		DeviceSharingModel share = new DeviceSharingModel(owner, req.vpdsname,
				req.username, req.device, req.permission);
		share.save();
	}

	/**
	 * Services the device/share API.
	 * 
	 * @param deviceShareJson
	 *            Device share request attributes in Json string
	 */
	public void doProcess(final String deviceShareJson) {

		try {

			DeviceShareFormat deviceShareRequest = convertToRequestFormat(
					deviceShareJson, DeviceShareFormat.class);
			validateRequest(deviceShareRequest);

			if (!userProfile
					.isRegisteredSecretkey(deviceShareRequest.secretkey)) {
				response.sendFailure(Const.API_DEVICE_SHARE,
						ErrorType.UNREGISTERED_SECRETKEY,
						deviceShareRequest.secretkey);
			}

			if (!userProfile.isOwner(deviceShareRequest.secretkey)) {
				response.sendFailure(Const.API_DEVICE_SHARE,
						ErrorType.VPDS_NO_VPDS_REGISTERED, Const.EMPTY);
			}

			shareDevice(deviceShareRequest);

			response.SendSuccess(Const.API_DEVICE_SHARE, Const.DEVICE_SHARED);

		} catch (InvalidJsonException e) {
			response.sendFailure(Const.API_DEVICE_SHARE,
					ErrorType.INVALID_JSON, e.getMessage());
		} catch (Exception e) {
			response.sendFailure(Const.API_DEVICE_SHARE,
					ErrorType.SYSTEM_ERROR, e.getMessage());
		}
	}

}
