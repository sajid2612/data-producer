package com.sajid.producer;

import com.sajid.producer.api.StreamProducer;
import com.sajid.producer.impl.FlatFileProducer;

public class EventProducer {
	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.printf("Usage:%n  java -jar data-producer.jar <deviceId> <produceFrequency>%n");
			System.exit(1);
		}
		String deviceId = args[0];
		int produceFrequency = Integer.parseInt(args[1]);
		startProducer(deviceId, produceFrequency);
	}

	private static void startProducer(String deviceId, int produceFrequency) {
		StreamProducer producer = new FlatFileProducer();
		producer.produceData(deviceId, produceFrequency);
	}
}
