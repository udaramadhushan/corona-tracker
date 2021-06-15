package com.corona.tracker.services.impl;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import com.corona.tracker.io.entity.SubscriptionEntity;
import com.corona.tracker.io.repositories.SubscriptionRepository;
import com.corona.tracker.services.CoronavirusDataService;
import com.corona.tracker.services.SubscriptionService;
import com.corona.tracker.shared.EmailService;
import com.corona.tracker.shared.Utils;
import com.corona.tracker.shared.dto.SubscriptionDto;

import java.util.List;


@Service
public class SubscriptionServiceImpl implements SubscriptionService {
	@Autowired
	CoronavirusDataService coronaData;
	@Autowired
	SubscriptionRepository subscriptionRepository;
	
	@Autowired
	Utils utils;
	
	@Autowired
	EmailService emailService;
	

	
	public void createSubscription(SubscriptionDto sub) {
		
		SubscriptionEntity subscriptionEntity = new SubscriptionEntity();
		
		if(subscriptionRepository.findByEmail(sub.getEmail()) != null) {
			
			subscriptionEntity = subscriptionRepository.findByEmail(sub.getEmail());
			subscriptionEntity.setSubscribed(true);
			subscriptionEntity.setName(sub.getName());
			
		} else {
			
			
			ModelMapper modelmapper = new ModelMapper();
			subscriptionEntity = modelmapper.map(sub, SubscriptionEntity.class);
			
			String subId = utils.generateUserId(30);
			subscriptionEntity.setSubId(subId);
			
		
			
		}	
		subscriptionRepository.save(subscriptionEntity);
			
			
			emailService.sendWelcomeEmail(subscriptionEntity.getEmail(),subscriptionEntity.getSubId(),coronaData);
		
			
	}
	
	
	public void unsubscribe(String subId) {
			
		
		SubscriptionEntity subscriptionEntity = subscriptionRepository.findBySubId(subId);
		
		subscriptionEntity.setSubscribed(false);
	
	
		subscriptionRepository.save(subscriptionEntity);
		
		
	}
	
	@Scheduled(fixedRate = 86400000)
	public void sendEmails(){

		
		
		
	
		List<SubscriptionEntity> subscribersList=   subscriptionRepository.findAllBySubscribed(true);
	
	
				
		for(SubscriptionEntity subscriber:subscribersList) {
				
			emailService.sendDailyReport(subscriber.getEmail(),subscriber.getSubId(), coronaData);
		}

	
		
		
		
	}

	
	

}
