/*
 * Name: SensorActBrokerAPI.java
 * Project: SensorAct, MUC@IIIT-Delhi
 * Version: 1.0
 * Date: 2012-04-14
 * Author: Pandarasamy Arjunan
 */
package edu.iiitd.muc.sensoract.api;

import play.libs.WS;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import controllers.Application;
import edu.iiitd.muc.sensoract.api.response.ResponseFormat;
import edu.iiitd.muc.sensoract.constants.Const;
import edu.iiitd.muc.sensoract.enums.ErrorType;
import edu.iiitd.muc.sensoract.exceptions.InvalidJsonException;
import edu.iiitd.muc.sensoract.model.user.RepoInfoModel;
import edu.iiitd.muc.sensoract.profile.UserProfile;
import edu.iiitd.muc.sensoract.util.ParamValidator;
import edu.iiitd.muc.sensoract.util.SensorActLogger;

/**
 * Super class for all SensorActBroker Apis.
 * 
 * @author Pandarasamy Arjunan
 * @version 1.0
 */
public class SensorActBrokerAPI extends Application {

	/*
	 * API references
	 */
	public static UserRegister userRegister = new UserRegister();
	// public static UserRemove userRemove = new UserRemove();
	public static UserLogin userLogin = new UserLogin();

	public static UserRepoRegister userRepoRegister = new UserRepoRegister();
	public static UserRepoList userRepoList = new UserRepoList();

	public static DeviceAdd deviceAdd = new DeviceAdd();
	public static DeviceDelete deviceDelete = new DeviceDelete();
	public static DeviceGet deviceGet = new DeviceGet();
	public static DeviceList deviceList = new DeviceList();
	public static DeviceShare deviceShare = new DeviceShare();
	public static DeviceSearch deviceSearch = new DeviceSearch();

	public static DeviceTemplateAdd deviceTemplateAdd = new DeviceTemplateAdd();
	public static DeviceTemplateDelete deviceTemplateDelete = new DeviceTemplateDelete();
	public static DeviceTemplateGet deviceTemplateGet = new DeviceTemplateGet();
	public static DeviceTemplateList deviceTemplateList = new DeviceTemplateList();

	public static GuardRuleAdd guardRuleAdd = new GuardRuleAdd();
	public static GuardRuleDelete guardRuleDelete = new GuardRuleDelete();
	public static GuardRuleGet guardRuleGet = new GuardRuleGet();
	public static GuardRuleList guardRuleList = new GuardRuleList();

	public static TaskAdd taskAdd = new TaskAdd();
	public static TaskDelete taskDelete = new TaskDelete();
	public static TaskGet taskGet = new TaskGet();
	public static TaskList taskList = new TaskList();

	public static DataQuery dataQuery = new DataQuery();

	/*
	 * API helper references
	 */
	public static ResponseFormat response = new ResponseFormat();
	public static ParamValidator validator = new ParamValidator();
	public static Gson gson = new Gson();
	public static SensorActLogger log = new SensorActLogger();

	/**
	 * 
	 * @param strJson
	 * @return
	 * @throws InvalidJsonException
	 */
	protected JsonObject parseJson(final String strJson)
			throws InvalidJsonException {

		JsonElement jsonElement = null;
		try {
			jsonElement = new JsonParser().parse(strJson);
		} catch (Exception e) {
			throw new InvalidJsonException(e.getMessage());
		}

		if (null == jsonElement) {
			throw new InvalidJsonException(Const.EMPTY_JSON);
		}

		if (!jsonElement.isJsonObject()) {
			throw new InvalidJsonException(Const.INVALID_JSON);
		}

		return (JsonObject) jsonElement;
	}

	/**
	 * 
	 * @param jsonObj
	 * @param property
	 * @return
	 */
	protected String getJsonProperty(final JsonObject jsonObj,
			final String property) {

		if (null != jsonObj && jsonObj.has(property)) {
			return jsonObj.get(property).getAsString();
		}
		return null;
	}

	/**
	 * 
	 * @param apiname
	 * @param secretkey
	 * @param reponame
	 */
	protected void validateSecretkeyAndReponame(final String apiname,
			final String secretkey, final String reponame) {

		validator.validateSecretKey(secretkey);
		validator.validateRepoName(reponame);

		if (validator.hasErrors()) {
			response.sendFailure(apiname, ErrorType.VALIDATION_FAILED,
					validator.getErrorMessages());
		}
	}

	/**
	 * 
	 * @param apiname
	 * @param repoAPIURL
	 * @param jsonRequest
	 * @return
	 */
	protected String sendToRepo(final String apiname, final String repoAPIURL,
			final String jsonRequest) {

		WS.HttpResponse wsResponse = null;

		try {
			log.info(apiname, Const.MSG_CONNECTING + repoAPIURL);
			wsResponse = WS.url(repoAPIURL).body(jsonRequest).post();
		} catch (Exception e) {
			response.sendFailure(apiname, ErrorType.ERROR_CONNECTION_REFUSED,
					e.getMessage());
		}

		return wsResponse.getString();
	}

	
	protected String processRequest(final String apiname,
			final String requestJson) {

		String repoResponse = null;

		try {
			JsonObject jsonObject = parseJson(requestJson);

			String secretkey = getJsonProperty(jsonObject,
					Const.PARAM_SECRETKEY);
			String reponame = getJsonProperty(jsonObject, Const.PARAM_REPONAME);

			validateSecretkeyAndReponame(apiname, secretkey, reponame);

			if (!UserProfile.isRegisteredSecretkey(secretkey)) {
				response.sendFailure(apiname, ErrorType.UNREGISTERED_SECRETKEY,
						secretkey);
			}

			RepoInfoModel repoInfo = UserProfile.getRepoInfo(secretkey,
					reponame);
			String repoURL = repoInfo.repoURL;
			String repoKey = repoInfo.repokey;
			String apiURL = repoURL + "/" + apiname;

			// if (null == repoURL || null == repoKey) {
			// // TODO: change the error type
			// response.sendFailure(apiname, ErrorType.UNREGISTERED_SECRETKEY,
			// secretkey);
			// }

			jsonObject.remove(Const.PARAM_SECRETKEY);
			jsonObject.remove(Const.PARAM_REPONAME);
			jsonObject.addProperty(Const.PARAM_SECRETKEY, repoKey);

			repoResponse = sendToRepo(apiname, apiURL, jsonObject.toString());

		} catch (InvalidJsonException e) {
			response.sendFailure(apiname, ErrorType.INVALID_JSON,
					e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			response.sendFailure(apiname, ErrorType.SYSTEM_ERROR,
					e.getMessage());
		}

		return repoResponse;
	}

	/**
	 * 
	 * @param apiname
	 * @param repoResponse
	 * @return
	 */
	protected ResponseFormat convertToResponseFormat(final String apiname, final String repoResponse) {

		// JsonObject repoJson = parseJson(repoResponse);
		// String apiname = getJsonProperty(repoJson, "apiname");
		// String statuscode = getJsonProperty(repoJson, "statuscode");
		// String message = getJsonProperty(repoJson, "message");

		ResponseFormat resJson = null;
		try {
			resJson = gson.fromJson(repoResponse, ResponseFormat.class);
		} catch (Exception e) {
			response.sendFailure(apiname, ErrorType.ERROR_INVALID_RESPONSE,
					e.getMessage());
		}

		if (null == resJson) {
			response.sendFailure(apiname, ErrorType.ERROR_INVALID_RESPONSE,
					Const.MSG_NONE);
		}
		
		return resJson;

	}
	/**
	 * 
	 * @param apiname
	 * @param repoResponse
	 */
	protected void sendRepoMessage(final String apiname, final String repoResponse) {

		ResponseFormat resJson = convertToResponseFormat(apiname,repoResponse);
		
		if (0 == resJson.statuscode) {
			log.info(apiname, resJson.message);
		} else {
			log.error(apiname, "[" + resJson.statuscode + "]" + resJson.message);
		}

		response.sendJSON(resJson);
	}

	/**
	 * 
	 * @param apiname
	 * @param repoResponse
	 */
	protected void sendRepoData(final String apiname, final String repoResponse) {

		ResponseFormat resJson = convertToResponseFormat(apiname,repoResponse);
		
		if (null == resJson.apiname && null == resJson.message ) { // data
			log.info(apiname, Const.MSG_SUCCESS + Const.DELIM2 + Const.MSG_DATASENT);
			response.sendJSON(repoResponse);
		} else {
			log.error(apiname, "[" + resJson.statuscode + "]" + resJson.message);
			response.sendJSON(resJson);
		}
	}

}
