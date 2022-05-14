package com.sajid.producer.util;

import static com.sajid.producer.constant.PatientConditionConstants.MAX_BP;
import static com.sajid.producer.constant.PatientConditionConstants.MAX_PULSE_RATE;
import static com.sajid.producer.constant.PatientConditionConstants.MAX_SP_O2;
import static com.sajid.producer.constant.PatientConditionConstants.MIN_BP;
import static com.sajid.producer.constant.PatientConditionConstants.MIN_PULSE_RATE;
import static com.sajid.producer.constant.PatientConditionConstants.MIN_SP_O2;

import com.sajid.producer.model.IotData;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataRandomizer {

	public IotData createData(String deviceId) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formatDateTime = LocalDateTime.now().format(formatter);
		IotData iotData = new IotData();
		iotData.setDeviceId(deviceId);
		iotData.setTimestamp(formatDateTime);
		iotData.setPatientCondition(randomizePatientCondition());
		return iotData;
	}

	private IotData.PatientCondition randomizePatientCondition() {
		IotData.PatientCondition patientCondition = new IotData.PatientCondition();
		patientCondition.setBloodPressure(randomizeBloodPressure());
		patientCondition.setSpO2(randomizeSpO2());
		patientCondition.setPulseRate(randomizePulse());
		return patientCondition;
	}

	private int randomizeBloodPressure() {
		return (int) ((Math.random() * (MAX_BP - MIN_BP)) + MIN_BP);
	}

	private int randomizeSpO2() {
		return (int) ((Math.random() * (MAX_SP_O2 - MIN_SP_O2)) + MIN_SP_O2);
	}

	private int randomizePulse() {
		return (int) ((Math.random() * (MAX_PULSE_RATE - MIN_PULSE_RATE)) + MIN_PULSE_RATE);
	}
}
