/*
 * Name: Const.java
 * Project: SensorActBroker, MUC@IIIT-Delhi
 * Version: 1.0
 * Date: 2012-05-18
 * Author: Pandarasamy Arjunan
 */
package edu.iiitd.muc.sensoract.constants;

/**
 * Defines various constants.
 * 
 * @author Pandarasamy Arjunan
 * @version 1.0
 */

public class Const {

	/*
	 * General
	 */

	public static final String repoName = "SensorActRepo";
	public static final String repoURL = "http://localhost:9000/";

	public static final String SENSORACT = "SensorAct";
	public static final String APPLICATION_NAME = SENSORACT;

	public static final String TODO = "Yet to implement. Please stay tuned!";

	public static final String MSG_SUCCESS = "Success";
	public static final String MSG_FAILED = "Failed";
	public static final String MSG_ERROR = "Error ";
	public static final String MSG_NONE = "None";
	public static final String DELIM1 = "-> ";
	public static final String DELIM2 = ": ";

	public static final String MSG_CONNECTING = "Connecting to ";
	public static final String MSG_DATASENT = "Sending data to caller";

	public static final int SUCCESS = 0;

	/*
	 * API names
	 */
	public static final String API_USER_REPO_REGISTER = "user/repo/register";
	public static final String API_USER_REPO_LIST = "user/repo/list";

	public static final String API_USER_LOGIN = "user/login";
	public static final String API_USER_REGISTER = "user/register";

	public static final String API_DEVICE_ADD = "device/add";
	public static final String API_DEVICE_DELETE = "device/delete";
	public static final String API_DEVICE_GET = "device/get";
	public static final String API_DEVICE_LIST = "device/list";
	public static final String API_DEVICE_SEARCH = "device/search";
	public static final String API_DEVICE_SHARE = "device/share";

	public static final String API_GUARDRULE_ADD = "guardrule/add";
	public static final String API_GUARDRULE_DELETE = "guardrule/delete";
	public static final String API_GUARDRULE_GET = "guardrule/get";
	public static final String API_GUARDRULE_LIST = "guardrule/list";

	public static final String API_TASK_ADD = "task/add";
	public static final String API_TASK_DELETE = "task/delete";
	public static final String API_TASK_GET = "task/get";
	public static final String API_TASK_LIST = "task/list";

	public static final String API_DATA_UPLOAD_WAVESEGMENT = "data/upload/wavesegment";
	public static final String API_DATA_QUERY = "data/query";

	/*
	 * API parameter names
	 */
	public static final String PARAM_SECRETKEY = "secretkey";

	public static final String PARAM_USERNAME = "username";
	public static final String PARAM_PASSWORD = "password";
	public static final String PARAM_EMAIL = "email";

	public static final String PARAM_REPONAME = "reponame";
	public static final String PARAM_REPOURL = "repoURL";
	public static final String PARAM_REPOKEY = "repokey";

	/*
	 * API parameter validation limits
	 */
	public static final int SECRETKEY_MIN_LENGTH = 32; // UUID length
	public static final int SECRETKEY_MAX_LENGTH = 32;

	public static final int USERNAME_MIN_LENGTH = 8;
	public static final int USERNAME_MAX_LENGTH = 20;

	public static final int PASSWORD_MIN_LENGTH = 8;
	public static final int PASSWORD_MAX_LENGTH = 20;

	public static final int EMAIL_MIN_LENGTH = 8;
	public static final int EMAIL_MAX_LENGTH = 40;

	public static final int REPONAME_MIN_LENGTH = 4;
	public static final int REPONAME_MAX_LENGTH = 20;

	public static final int REPOURL_MIN_LENGTH = 10;
	public static final int REPOURL_MAX_LENGTH = 50;

	public static final int REPOKEY_MIN_LENGTH = SECRETKEY_MIN_LENGTH;
	public static final int REPOKEY_MAX_LENGTH = SECRETKEY_MAX_LENGTH;

	/*
	 * API parameter validation error messages
	 */
	public static final String MSG_REQUIRED = " is required";
	public static final String MSG_LENGTH = " length must be ";
	public static final String MSG_MIN_LENGTH = " minimum length is ";
	public static final String MSG_MAX_LENGTH = " maximum length is ";
	public static final String MSG_MIN_VALUE = " minimum value is ";
	public static final String MSG_MAX_VALUE = " maximum value is ";
	public static final String MSG_INVALID = " is invalid";
	public static final String MSG_EMPTY = " is empty";

	/*
	 * API Error messages
	 */
	public static final String SYSTEM_ERROR = "System error";
	public static final String VALIDATION_FAILED = "Validation failed";
	public static final String INVALID_JSON = "Invalid JSON object";
	public static final String EMPTY_JSON = "Empty JSON object";

	/*
	 * API success/failure messages
	 */

	// TODO: rearrange them
	public static final String ERROR_INVALID_RESPONSE = "Invalid response";
	public static final String ERROR_CONNECTION_REFUSED = "Connection refused";

	public static final String REGISTERREPO_CREATED = "New repository registered";
	public static final String REGISTERREPO_ALREADYEXISTS = "Repository name or URL already registered";

	public static final String LISTREPOS_NOTFOUND = "No repository found";

	public static final String LOGIN_FAILED = "Login failed";
	public static final String USER_REGISTERED = "New Userprofile registered";
	public static final String USER_ALREADYEXISTS = "Userprofile already exists";

	public static final String DEVICE_ADDED = "New device added";
	public static final String DEVICE_ALREADYEXISTS = "Device already exists";
	public static final String DEVICE_DELETED = "Device successfully deleted";
	public static final String DEVICE_NOTFOUND = "Device not found";
	public static final String DEVICE_NODEVICE_FOUND = "No device found";

	public static final String UNREGISTERED_SECRETKEY = "Unregistered secretkey";
	public static final String UNREGISTERED_USERNAME = "Unregistered username";

	public static final String UPLOAD_WAVESEGMENT_SUCCESS = "Wavesegment stored successfully";

	private Const() {
	}
}
