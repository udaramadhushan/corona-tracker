package com.corona.tracker.services;


import com.corona.tracker.shared.dto.SubscriptionDto;

public interface SubscriptionService {

	
	
	void createSubscription(SubscriptionDto sub);
	void unsubscribe(String subId);

	
}
