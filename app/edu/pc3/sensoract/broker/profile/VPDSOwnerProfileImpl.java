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
 * Name: VPDSOwnerProfileImpl.java
 * Project: SensorAct-Broker
 * Version: 1.0
 * Date: 2012-04-14
 * Author: Pandarasamy Arjunan
 */
package edu.pc3.sensoract.broker.profile;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import edu.pc3.sensoract.broker.api.request.UserRegisterFormat;
import edu.pc3.sensoract.broker.api.request.VPDSRegisterFormat;
import edu.pc3.sensoract.broker.model.UserProfileModel;
import edu.pc3.sensoract.broker.model.VPDSOwnerProfileModel;

/**
 * User profile management, provides methods for managing user profiles.
 * 
 * @author Pandarasamy Arjunan
 * @version 1.0
 */

public class VPDSOwnerProfileImpl implements
		VPDSOwnerProfile<VPDSOwnerProfileModel> {

	/**
	 * Stores the new user profile to the repository.
	 * 
	 * @param newUser
	 *            User profile object to persist to the repository
	 */
	@Override
	public boolean addVPDSOwnerProfile(final UserProfileModel userProfile,
			final VPDSRegisterFormat newVPDS) {

		VPDSOwnerProfileModel ownerProfileModel = new VPDSOwnerProfileModel(
				userProfile.secretkey, userProfile.username,
				userProfile.password, userProfile.email, newVPDS.vpdsname,
				newVPDS.vpdsURL, newVPDS.ownerkey);
		ownerProfileModel.save();

		return true;
	}

	@Override
	public boolean isVPDSProfileExists(final VPDSRegisterFormat newVPDS) {
		return !(0 == VPDSOwnerProfileModel.count("byvpdsURL", newVPDS.vpdsURL));
	}

	@Override
	public boolean isRegisteredSecretkey(String secretkey) {
		return !(0 == VPDSOwnerProfileModel.count("bySecretkey", secretkey));
	}

}
