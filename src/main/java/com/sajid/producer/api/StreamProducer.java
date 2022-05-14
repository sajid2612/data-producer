package com.sajid.producer.api;

public interface StreamProducer {
	void produceData(String deviceId, int produceFrequency);
}