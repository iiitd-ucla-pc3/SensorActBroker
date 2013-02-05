/*
 * Delhi (IIIT-D) and The Regents of the University of California.
 * Copyright (c) 2012, Indraprastha Institute of Information Technology,
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
 * Project: SensorActBroker
 * Version: 1.0
 * Date: 2012-05-18
 * Author: Pandarasamy Arjunan
 */
package controllers;

import play.mvc.Controller;
import edu.iiitd.muc.sensoract.api.SensorActBrokerAPI;

/**
 * Application class, entry point for all APIs.
 * 
 * @author Pandarasamy Arjunan
 * @version 1.0
 */

public class Application extends Controller {

	public static void index() {
		renderText("Welcome to SensorActBroker!");
	}

	// User profile management
	public static void userLogin() {
		SensorActBrokerAPI.userLogin.doProcess(request.params.get("body"));
	}

	public static void userRegister() {
		SensorActBrokerAPI.userRegister.doProcess(request.params.get("body"));
	}

	public static void userRepoRegister() {
		SensorActBrokerAPI.userRepoRegister.doProcess(request.params
				.get("body"));
	}

	public static void userRepoList() {
		SensorActBrokerAPI.userRepoList.doProcess(request.params.get("body"));
	}

	// Device profile management
	public static void deviceAdd() {
		SensorActBrokerAPI.deviceAdd.doProcess(request.params.get("body"));
	}

	public static void deviceDelete() {
		SensorActBrokerAPI.deviceDelete.doProcess(request.params.get("body"));
	}

	public static void deviceGet() {
		SensorActBrokerAPI.deviceGet.doProcess(request.params.get("body"));
	}

	public static void deviceList() {
		SensorActBrokerAPI.deviceList.doProcess(request.params.get("body"));
	}

	public static void deviceShare() {
		SensorActBrokerAPI.deviceShare.doProcess(request.params.get("body"));
	}

	public static void deviceSearch() {
		SensorActBrokerAPI.deviceSearch.doProcess(request.params.get("body"));
	}

	// Device template management
	public static void deviceTemplateAdd() {
		SensorActBrokerAPI.deviceTemplateAdd.doProcess(request.params.get("body"));
	}

	public static void deviceTemplateDelete() {
		SensorActBrokerAPI.deviceTemplateDelete.doProcess(request.params.get("body"));
	}

	public static void deviceTemplateGet() {
		SensorActBrokerAPI.deviceTemplateGet.doProcess(request.params.get("body"));
	}

	public static void deviceTemplateList() {
		SensorActBrokerAPI.deviceTemplateList.doProcess(request.params.get("body"));
	}

	// Guard rule management
	public static void guardRuleAdd() {
		SensorActBrokerAPI.guardRuleAdd.doProcess(request.params.get("body"));
	}

	public static void guardRuleDelete() {
		SensorActBrokerAPI.guardRuleDelete
				.doProcess(request.params.get("body"));
	}

	public static void guardRuleGet() {
		SensorActBrokerAPI.guardRuleGet.doProcess(request.params.get("body"));
	}

	public static void guardRuleList() {
		SensorActBrokerAPI.guardRuleList.doProcess(request.params.get("body"));
	}

	// Task management
	public static void taskAdd() {
		SensorActBrokerAPI.taskAdd.doProcess(request.params.get("body"));
	}

	public static void taskDelete() {
		SensorActBrokerAPI.taskDelete.doProcess(request.params.get("body"));
	}

	public static void taskGet() {
		SensorActBrokerAPI.taskGet.doProcess(request.params.get("body"));
	}

	public static void taskList() {
		SensorActBrokerAPI.taskList.doProcess(request.params.get("body"));
	}

	// Data management
	public static void dataQuery() {
		SensorActBrokerAPI.dataQuery.doProcess(request.params.get("body"));
	}

}