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
 * Name: Application.java
 * Project: SensorAct-Broker
 * Version: 1.0
 * Date: 2013-02-05
 * Author: Pandarasamy Arjunan
 */
package controllers;

import edu.pc3.sensoract.broker.api.SensorActBrokerAPI;
import play.mvc.Controller;


/**
 * Application class, entry point for all APIs.
 * 
 * @author Pandarasamy Arjunan
 * @version  1.0
 */

public class Application extends Controller {

	public static void index() {
		renderText("Welcome to SensorActBroker!");
	}
	
	private static String getParam() {
		return request.params.get("body");
	}

	// User profile management
	public static void userLogin() {
		SensorActBrokerAPI.userLogin.doProcess(request.params.get("body"));
	}

	public static void userRegister() {
		SensorActBrokerAPI.userRegister.doProcess(request.params.get("body"));
	}

	public static void userList() {
		SensorActBrokerAPI.userList.doProcess(request.params.get("body"));
	}

	public static void vpdsRegister() {
		SensorActBrokerAPI.vpdsRegister.doProcess(request.params.get("body"));
	}

	public static void vpdsGet() {
		SensorActBrokerAPI.vpdsGet.doProcess(request.params.get("body"));
	}

	public static void vpdsList() {
		SensorActBrokerAPI.vpdsList.doProcess(request.params.get("body"));
	}
	
	public static void accesskeyGet() {
		SensorActBrokerAPI.accesskeyGet.doProcess(getParam());
	}

	public static void deviceShare() {
		SensorActBrokerAPI.deviceShare.doProcess(request.params.get("body"));
	}

	public static void deviceUserShared() {
		SensorActBrokerAPI.deviceUserShared.doProcess(request.params.get("body"));
	}

	public static void deviceOwnerShared() {
		SensorActBrokerAPI.deviceOwnerShared.doProcess(request.params.get("body"));
	}

	
}