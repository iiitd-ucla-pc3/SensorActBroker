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
 * Name: UserProfileModel.java
 * Project: SensorAct-Broker
 * Version: 1.0
 * Date: 2013-02-05
 * Author: Pandarasamy Arjunan
 */
package edu.pc3.sensoract.broker.model;

import java.util.ArrayList;
import java.util.List;

import play.modules.morphia.Model;

import com.google.code.morphia.annotations.Entity;

import edu.pc3.sensoract.broker.api.request.DeviceShareFormat;

/**
 * Model class for user profile management.
 * 
 * @author Pandarasamy Arjunan
 * @version 1.0
 */
@Entity(value = "DeviceSharingModel", noClassnameStored = true)
public class DeviceSharingModel extends Model {

	public static class Sensor {
		public String sensorname = null;
		public String sensorid = null;
		public boolean read = false;
		public boolean write = false;
	}

	public static class Actuator {
		public String actuatorname = null;
		public String actuatorid = null;
		public boolean read = false;
		public boolean write = false;
	}

	public static class SharedDevice {
		public String devicename = null;
		public List<Sensor> sensors = new ArrayList<Sensor>();
		public List<Actuator> actuators = new ArrayList<Actuator>();
	}

	public String voname = null;
	public String vpdsname = null;
	public String username = null;
	public List<SharedDevice> shared = new ArrayList<SharedDevice>();

	private DeviceSharingModel(final String voname,
			final DeviceShareFormat shareReq) {

		this.voname = voname;
		this.vpdsname = shareReq.vpdsname;
		this.username = shareReq.username;

		addSharedDeviceInfo(shareReq.share);
	}

	private void addSharedDeviceInfo(final DeviceShareFormat.Share req) {

		SharedDevice newSharedDevice = new SharedDevice();
		newSharedDevice.devicename = req.devicename;

		if (req.sensorname != null && req.sensorid != null) {
			Sensor s = new Sensor();
			s.sensorname = req.sensorname;
			s.sensorid = req.sensorid;
			s.read = req.read;
			s.write = req.write;

			newSharedDevice.sensors = new ArrayList<Sensor>();
			newSharedDevice.sensors.add(s);
		}

		if (req.actuatorname != null && req.actuatorid != null) {
			Actuator a = new Actuator();
			a.actuatorname = req.actuatorname;
			a.actuatorid = req.actuatorid;
			a.read = req.read;
			a.write = req.write;

			newSharedDevice.actuators = new ArrayList<Actuator>();
			newSharedDevice.actuators.add(a);
		}

		if (null == this.shared) {
			this.shared = new ArrayList<SharedDevice>();
		}
		this.shared.add(newSharedDevice);
	}

	private void updateSharedDevice(final DeviceShareFormat.Share req) {

		SharedDevice sharedDevice = null;

		// locate the device
		for (SharedDevice s : this.shared) {
			if (s.devicename.equalsIgnoreCase(req.devicename)) {
				sharedDevice = s;
				break;
			}
		}

		// if no device found, its a new device to share.. so add new device
		// share info and return
		if (null == sharedDevice) {
			System.out.println("sharedShared...  is null ");
			addSharedDeviceInfo(req);
			return;
		}

		if (req.sensorname != null && req.sensorid != null) {

			System.out.println("sharedShared... locating sensor"
					+ sharedDevice.devicename);

			// locate the sensor and update its read and write attributes
			Sensor nSensor = null;
			for (Sensor s : sharedDevice.sensors) {
				if (s.sensorname.equalsIgnoreCase(req.sensorname)
						&& s.sensorid.equalsIgnoreCase(req.sensorid)) {
					nSensor = s;
					s.read = req.read;
					s.write = req.write;
					break;
				}
			}
			// not able to locate the sensor.. means request is for new sensor
			if (null == nSensor) {
				nSensor = new Sensor();
				nSensor.sensorname = req.sensorname;
				nSensor.sensorid = req.sensorid;
				nSensor.read = req.read;
				nSensor.write = req.write;

				if (null == sharedDevice.sensors) {
					sharedDevice.sensors = new ArrayList<Sensor>();
				}
				sharedDevice.sensors.add(nSensor);
				return;
			}
		}

		if (req.actuatorname != null && req.actuatorid != null) {

			System.out.println("sharedShared... locating actt..."
					+ sharedDevice.devicename);

			// locate the Actuator and update its read and write attributes
			Actuator nActuator = null;
			for (Actuator a : sharedDevice.actuators) {
				if (a.actuatorname.equalsIgnoreCase(req.actuatorname)
						&& a.actuatorid.equalsIgnoreCase(req.actuatorid)) {
					nActuator = a;
					a.read = req.read;
					a.write = req.write;
					break;
				}
			}

			// not able to locate the sensor.. means request is for new sensor
			if (null == nActuator) {
				nActuator = new Actuator();
				nActuator.actuatorname = req.actuatorname;
				nActuator.actuatorid = req.actuatorid;
				nActuator.read = req.read;
				nActuator.write = req.write;

				if (null == sharedDevice.actuators) {
					sharedDevice.actuators = new ArrayList<Actuator>();
				}
				sharedDevice.actuators.add(nActuator);
			}
		}
	}

	private static boolean checkDuplicateSensors(List<Sensor> sensorList,
			DeviceShareFormat.Share newShare) {
		for (Sensor s : sensorList) {
			if (s.sensorname.equals(newShare.sensorname)
					&& s.sensorid.equalsIgnoreCase(newShare.sensorid)) {
				if (s.read == newShare.read && s.write && newShare.write) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean checkDuplicateActuators(List<Actuator> actuatorList,
			DeviceShareFormat.Share newShare) {
		for (Actuator a : actuatorList) {
			if (a.actuatorname.equals(newShare.actuatorname)
					&& a.actuatorid.equalsIgnoreCase(newShare.actuatorid)) {
				if (a.read == newShare.read && a.write && newShare.write) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isDuplicateShare(final String voname,
			final DeviceShareFormat req) {

		List<DeviceSharingModel> sharedList = DeviceSharingModel.q()
				.filter("voname", voname).filter("vpdsname", req.vpdsname)
				.filter("username", req.username).fetchAll();

		if (null == sharedList || sharedList.isEmpty()) {
			return false;
		}

		if (sharedList.size() > 1) {
			// TODO: something went wrong
			System.out.println("Something went wrong");
		}

		// check for duplicate share
		for (SharedDevice shared : sharedList.get(0).shared) {
			if (shared.devicename.equalsIgnoreCase(req.share.devicename)) {
				if (checkDuplicateSensors(shared.sensors, req.share)
						|| checkDuplicateActuators(shared.actuators, req.share)) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean updateShare(final String voname,
			final DeviceShareFormat reqShare) {

		List<DeviceSharingModel> sharedList = DeviceSharingModel.q()
				.filter("voname", voname).filter("vpdsname", reqShare.vpdsname)
				.filter("username", reqShare.username).fetchAll();

		DeviceSharingModel newShare = null;
		if (null == sharedList || sharedList.isEmpty()) {
			newShare = new DeviceSharingModel(voname, reqShare);
		} else {
			newShare = sharedList.get(0);
			System.out.println("newShare = sharedList.get(0);");
			newShare.updateSharedDevice(reqShare.share);
		}
		newShare.save();
		return true;
	}

	DeviceSharingModel() {
	}
}
