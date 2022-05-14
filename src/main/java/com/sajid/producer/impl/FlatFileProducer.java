package com.sajid.producer.impl;

import com.google.gson.Gson;
import com.sajid.producer.util.DataRandomizer;
import com.sajid.producer.model.IotData;
import com.sajid.producer.api.StreamProducer;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Timer;
import java.util.TimerTask;

public class FlatFileProducer implements StreamProducer {

	static Gson gson = new Gson();

	@Override
	public void produceData(String deviceId, int produceFrequency) {
		String filename = "data-stream.text";
		String workingDirectory = System.getProperty("user.dir");
		prepareFile(new File(workingDirectory, filename));
		Timer timer = new Timer();
		Path path = Paths.get(workingDirectory + File.separator + filename);
		System.out.println("Producing data...");
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				IotData iotData = new DataRandomizer().createData(deviceId);
				try {
					String jsonString = gson.toJson(iotData) + System.getProperty("line.separator");
					System.out.println(jsonString); //Not production friendly
					Files.write(path, jsonString.getBytes(), StandardOpenOption.APPEND);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Error Occurred while processing " + iotData.getDeviceId() + " " + iotData.getTimestamp());
					//TODO(Limitation for now) : Those failed events can be captured in another file, lest say data-produce-failed, which can be further produced by same/other system
				}
			}
		}, 0, produceFrequency);
	}

	private void prepareFile(File file) {
		try {
			if (file.createNewFile()) {
				System.out.println("File is created!");
			} else {
				System.out.println("File is already existed!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
