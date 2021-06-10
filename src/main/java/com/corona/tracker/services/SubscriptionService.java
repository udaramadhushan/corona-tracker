package com.corona.tracker.services;

import java.util.List;

import com.corona.tracker.shared.dto.SubscriptionDto;

public interface SubscriptionService {

	
	
	void createSubscription(SubscriptionDto sub);
	void unsubscribe(String subId);

	
}
