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
 * Name: VPDSRegister.java
 * Project: SensorAct-Broker
 * Version: 1.0
 * Date: 2013-02-05
 * Author: Pandarasamy Arjunan
 */
package edu.pc3.sensoract.broker.api;

import java.util.List;

import edu.pc3.sensoract.broker.api.request.AccesskeyGetFormat;
import edu.pc3.sensoract.broker.api.request.VPDSRegisterFormat;
import edu.pc3.sensoract.broker.api.response.AccesskeyGetResponseFormat;
import edu.pc3.sensoract.broker.api.response.VPDSListResponseFormat;
import edu.pc3.sensoract.broker.constants.Const;
import edu.pc3.sensoract.broker.enums.ErrorType;
import edu.pc3.sensoract.broker.exceptions.InvalidJsonException;
import edu.pc3.sensoract.broker.model.DeviceSharingModel;
import edu.pc3.sensoract.broker.model.VPDSProfileModel;

/**
 * vpds/list API: Register a VPDS to the broker
 * 
 * @author Pandarasamy Arjunan
 * @version 1.0
 */

public class AccesskeyGet extends SensorActBrokerAPI {

	/**
	 * Validates new user profile attributes. If validation fails, sends
	 * corresponding failure message to the caller.
	 * 
	 * @param vpdsProfile
	 *            User profile object to validate
	 */
	private void validateVPDSProfile(final VPDSRegisterFormat vpdsProfile) {

		// TODO: add validatations
		// validator.validateUserName(vpdsProfile.username);

		if (validator.hasErrors()) {
			response.sendFailure(Const.API_ACCESSKEY_GET,
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
			AccesskeyGetFormat req = convertToRequestFormat(userProfileJson,
					AccesskeyGetFormat.class);
			// validateVPDSProfile(newVPDS);

			String username = userProfile.getUsername(req.secretkey);

			if (null == username) {
				response.sendFailure(Const.API_ACCESSKEY_GET,
						ErrorType.UNREGISTERED_SECRETKEY, req.secretkey);
			}

			if (!userProfile.isRegisteredVPDS(req.vpdsname)) {				
				response.sendFailure(Const.API_ACCESSKEY_GET,
						ErrorType.VPDS_NOTFOUND, req.vpdsname);
			}

			System.out.println(req.vpdsname);
			System.out.println(username);
			
			String email = userProfile.getEmail(username);
			List<DeviceSharingModel> deviceList = DeviceSharingModel.find(
					"byVpdsnameAndUsername", req.vpdsname, username).fetchAll();
						
			if(null == deviceList || 0 == deviceList.size()) {
				response.sendFailure(Const.API_ACCESSKEY_GET,
						ErrorType.DEVICE_NODEVICE_SHARED, req.vpdsname);
			}
								
			// TODO: broker name
			String msg = Const.SENSORACT_BROKER +  username + email;			
			String accesskey = userProfile.getHashCode(msg);
			
			VPDSProfileModel vpds = userProfile.getVPDSProfile(req.vpdsname);
			
			AccesskeyGetResponseFormat out = new AccesskeyGetResponseFormat();
			
			out.accesskey = accesskey;
			out.vpdsurl = vpds.vpdsURL;
			
			response.sendJSON(out);
			
			//System.out.println(deviceList.size());
			//System.out.println(msg + " " + accesskey);
						
			//response.SendSuccess(Const.API_ACCESSKEY_GET, accesskey);			

		} catch (InvalidJsonException e) {
			response.sendFailure(Const.API_ACCESSKEY_GET,
					ErrorType.INVALID_JSON, e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			response.sendFailure(Const.API_ACCESSKEY_GET,
					ErrorType.SYSTEM_ERROR, e.getMessage());
		}
	}
}