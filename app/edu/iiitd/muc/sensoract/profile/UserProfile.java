/*
 * Name: UserProfile.java
 * Project: SensorActBroker, MUC@IIIT-Delhi
 * Version: 1.0
 * Date: 2012-05-18
 * Author: Pandarasamy Arjunan
 */
package edu.iiitd.muc.sensoract.profile;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import edu.iiitd.muc.sensoract.api.request.UserRegisterFormat;
import edu.iiitd.muc.sensoract.api.request.UserRepoRegisterFormat;
import edu.iiitd.muc.sensoract.model.user.RepoInfoModel;
import edu.iiitd.muc.sensoract.model.user.UserProfileModel;

/**
 * User profile management, provides methods for managing user profiles.
 * 
 * @author Pandarasamy Arjunan
 * @version 1.0
 */

public class UserProfile {

	/**
	 * Generates unique ids to create secret keys.
	 * 
	 * @return Unique Id
	 */
	public static String generateSecretKey() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * Generates MD5 hash key for the given message.
	 * 
	 * @param message
	 *            Message for which hash key needs to be generated
	 * @return MD5 hash key
	 * @throws Exception
	 */
	public static String getHashCode(final String message) throws Exception {

		MessageDigest md = null;

		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw e;
		}
		md.update(message.getBytes());

		byte bytes[] = md.digest();
		StringBuffer sb = new StringBuffer();
		for (byte b : bytes) {
			sb.append(String.format("%02x", b));
		}

		return sb.toString();
	}

	/**
	 * Stores the new user profile to the repository.
	 * 
	 * @param newUser
	 *            User profile object to persist to the repository
	 */
	public static boolean addUserProfile(final UserRegisterFormat newUser,
			final String secretkey) {

		UserProfileModel newUserProfile = new UserProfileModel(
				newUser.username, newUser.password, newUser.email, secretkey);
		newUserProfile.save();
		return true;
	}

	/**
	 * Retrieves the secretkey corresponding to the given username.
	 * 
	 * @param username
	 *            User name
	 * @return Secretkey of the user, if already registered, otherwise null.
	 */
	public static String getSecretkey(final String username,
			final String password) {

		List<UserProfileModel> userList = UserProfileModel.find(
				"byUsernameAndPassword", username, password).fetchAll();
		if (null != userList && userList.size() > 0) {
			return userList.get(0).secretkey;
		}
		return null;
	}

	/**
	 * Helper method for other APIs to check whether the given secretkey is a
	 * registered one or not.
	 * 
	 * @param secretkey
	 *            Secretkey of the userProfile to be checked.
	 * @return True, for registered secretkey, otherwise false.
	 */
	public static boolean isRegisteredSecretkey(final String secretkey) {
		return !(0 == UserProfileModel.count("bySecretkey", secretkey));
	}

	/**
	 * Checks the duplicate user profile. If user profile already exists in the
	 * repository, sends corresponding failure message to the caller.
	 * 
	 * @param username
	 *            Username
	 * @return True, for registered username, otherwise false.
	 */
	public static boolean isRegisteredUser(final String username) {
		return !(0 == UserProfileModel.count("byUsername", username));
	}

	/**
	 * Checks the duplicate user profile. If user profile already exists in the
	 * repository, sends corresponding failure message to the caller.
	 * 
	 * @param newUser
	 *            User profile object to check duplicates
	 * @return True, if the user profile already exists in the repository,
	 *         otherwise false.
	 */
	public static boolean isUserProfileExists(final UserRegisterFormat newUser) {
		return !(0 == UserProfileModel.count("byUsername", newUser.username));
	}

	/**
	 * Updates the broker key list of a user profile.
	 * 
	 * @param username
	 *            User name
	 * @param repokey
	 *            Secretkey of the user or broker (generated)
	 * @return True, if the broker key list is successfully updated, otherwise
	 *         false.
	 */
	public static boolean addRepo(final UserRepoRegisterFormat repoInfo) {

		List<UserProfileModel> userList = UserProfileModel.find("bySecretkey",
				repoInfo.secretkey).fetchAll();

		if (null == userList || 0 == userList.size()) {
			return false;
		}

		List<RepoInfoModel> repoList = userList.get(0).repolist;

		if (null == repoList) {
			repoList = new ArrayList<RepoInfoModel>();
		}

		// TODO: duplicate repo info
		RepoInfoModel newRepo = new RepoInfoModel(repoInfo.reponame,
				repoInfo.repoURL, repoInfo.repokey);
		repoList.add(newRepo);

		userList.get(0).repolist = repoList;
		userList.get(0).save();

		return true;
	}

	public static List<RepoInfoModel> getRepoList(final String secretkey) {

		// TODO: Inconsistent way with play's jpa Model
		List<UserProfileModel> userList = UserProfileModel.find("bySecretkey",
				secretkey).fetchAll();

		if (null == userList) {
			return null;
		}

		if (null == userList.get(0).repolist
				|| 0 == userList.get(0).repolist.size()) {
			return null;
		}

		return userList.get(0).repolist;
	}

	public static RepoInfoModel getRepoInfo(final String secretkey,
			final String reponame) {

		// List<UserProfileModel> userList = UserProfileModel.q()
		// .filter("secretkey", secretkey)
		// .filter("repolist.name", reponame).asList();

		// TODO: repolist.name filter is not working??
		List<UserProfileModel> userList = UserProfileModel.find(
				"secretkey repolist.reponame", secretkey, reponame).asList();

		if (null == userList || 0 == userList.size()) {
			return null;
		}

		// TODO: This has to be incorporated with the query
		Iterator<RepoInfoModel> itr = userList.get(0).repolist.iterator();
		while (itr.hasNext()) {
			RepoInfoModel repo = itr.next();
			if (null != repo.reponame
					&& repo.reponame.equalsIgnoreCase(reponame)) {
				return repo;
			}
		}

		return null;
	}

}
