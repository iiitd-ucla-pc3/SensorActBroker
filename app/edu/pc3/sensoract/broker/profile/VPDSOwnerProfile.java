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
 * Name: VPDSOwnerProfile.java
 * Project: SensorAct-Broker
 * Version: 1.0
 * Date: 2013-02-05
 * Author: Pandarasamy Arjunan
 */

package edu.pc3.sensoract.broker.profile;

import edu.pc3.sensoract.broker.api.request.UserRegisterFormat;
import edu.pc3.sensoract.broker.api.request.VPDSRegisterFormat;
import edu.pc3.sensoract.broker.model.UserProfileModel;

public interface VPDSOwnerProfile<T> {

	/**
	 * Stores the new user profile to the repository.
	 * 
	 * @param newUser
	 *            User profile object to persist to the repository
	 */
	public boolean addVPDSOwnerProfile(final UserProfileModel userProfile,
			final VPDSRegisterFormat newVPDS);

	/**
	 * Checks the duplicate user profile. If user profile already exists in the
	 * repository, sends corresponding failure message to the caller.
	 * 
	 * @param newUser
	 *            User profile object to check duplicates
	 * @return True, if the user profile already exists in the repository,
	 *         otherwise false.
	 */
	public boolean isVPDSProfileExists(final VPDSRegisterFormat newVPDS);
	public boolean isVPDSProfileExists(final UserRegisterFormat newOwner);

	/**
	 * Retrieves the username corresponding to the given secretkey.
	 * 
	 * @param secretkey
	 *            User name
	 * @return Username of the secrectkey, if already registered, otherwise
	 *         null.
	 */
	// public String getVPDSOwnerName(final String secretkey);

	/**
	 * Retrieves the username corresponding to the given email.
	 * 
	 * @param email
	 *            email of the user
	 * @return Username, if already registered, otherwise null.
	 */
	// public String getVPDSOwnerNameByEmail(final String email);

	/**
	 * Retrieves the secretkey corresponding to the given username and password.
	 * 
	 * @param username
	 *            User name
	 * @return Secretkey of the user, if already registered, otherwise null.
	 */
	public String getSecretkey(final String username, final String password);

	/**
	 * Retrieves the secretkey corresponding to the given username.
	 * 
	 * @param username
	 *            User name
	 * @return Secretkey of the user, if already registered, otherwise null.
	 */
	// public String getSecretkey(final String username);

	/**
	 * Retrieves the email address corresponding to the given username.
	 * 
	 * @param username
	 *            User name
	 * @return email address of the user, if already registered, otherwise null.
	 */
	// public String getEmail(final String username);

	/**
	 * Helper method for other APIs to check whether the given secretkey is a
	 * registered one or not.
	 * 
	 * @param secretkey
	 *            Secretkey of the userProfile to be checked.
	 * @return True, for registered secretkey, otherwise false.
	 */
	public boolean isRegisteredSecretkey(final String secretkey);

	/**
	 * Checks the duplicate user profile. If user profile already exists in the
	 * repository, sends corresponding failure message to the caller.
	 * 
	 * @param username
	 *            Username
	 * @return True, for registered username, otherwise false.
	 */
	// public boolean isRegisteredVPDSOwner(final String username);

}