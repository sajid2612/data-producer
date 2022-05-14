package com.sajid.producer.model;

import lombok.Data;

@Data
public class IotData {
	private String deviceId;
	private String patientId;
	private String timestamp;
	private PatientCondition patientCondition;

	@Data
	public static class PatientCondition {
		private int spO2;
		private int pulseRate;
		private int bloodPressure;
	}
}