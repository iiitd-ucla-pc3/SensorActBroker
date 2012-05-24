/*
 * Name: Application.java
 * Project: SensorActBroker, MUC@IIIT-Delhi
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