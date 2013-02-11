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

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

import play.modules.morphia.Model;

import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;

import edu.pc3.sensoract.broker.api.request.DeviceShareFormat;
import edu.pc3.sensoract.broker.api.request.DeviceShareFormat.Device;
import edu.pc3.sensoract.broker.api.request.DeviceShareFormat.Permission;

/**
 * Model class for user profile management.
 * 
 * @author Pandarasamy Arjunan
 * @version 1.0
 */
@Entity(value = "DeviceSharingModel", noClassnameStored = true)
public class DeviceSharingModel extends Model {

	public static class Device {
		public String devicename = null;
		public String sensorname = null;
		public String sensorid = null;
		public String actuatorname = null;
		public String actuatorid = null;
	}

	public static class Permission {
		public boolean read = false;
		public boolean write = false;
	}

	public String voname = null;
	public String vpdsname = null;
	public String username = null;

	public Device device = null;
	public Permission permission = null;

	public DeviceSharingModel(final String voname, final String vpdsname,
			final String username, final DeviceShareFormat.Device device,
			final DeviceShareFormat.Permission permission) {
		
		this.voname = voname;
		this.vpdsname = vpdsname;
		this.username = username;
		
		this.device = new Device();
		this.device.devicename = device.devicename;
		this.device.sensorname = device.sensorname;
		this.device.sensorid = device.sensorid;
		this.device.actuatorname = device.actuatorname;
		this.device.actuatorid = device.actuatorid;
		
		this.permission = new Permission();
		this.permission.read = permission.read;
		this.permission.write = permission.write;
	}

	DeviceSharingModel() {
	}
}
