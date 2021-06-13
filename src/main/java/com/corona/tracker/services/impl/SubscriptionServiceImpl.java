package com.corona.tracker.services.impl;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.corona.tracker.exceptions.SubscriptionServiceException;
import com.corona.tracker.io.entity.SubscriptionEntity;
import com.corona.tracker.io.repositories.SubscriptionRepository;
import com.corona.tracker.services.CoronavirusDataService;
import com.corona.tracker.services.SubscriptionService;
import com.corona.tracker.shared.EmailService;
import com.corona.tracker.shared.Utils;
import com.corona.tracker.shared.dto.SubscriptionDto;
import java.util.ArrayList;
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
	

	@Override
	public void createSubscription(SubscriptionDto sub) {
		if(subscriptionRepository.findByEmail(sub.getEmail()) != null) throw new SubscriptionServiceException("record already exsists");
		
			
			ModelMapper modelmapper = new ModelMapper();
			SubscriptionEntity subscriptionEntity = modelmapper.map(sub, SubscriptionEntity.class);
			
			String subId = utils.generateUserId(30);
			subscriptionEntity.setSubId(subId);
			
			subscriptionRepository.save(subscriptionEntity);
			
			
			
			
			
			emailService.sendWelcomeEmail(subscriptionEntity.getEmail(),subscriptionEntity.getSubId(),coronaData);
		
			
	}
	
	@Override
	public void unsubscribe(String subId) {
			
		
		SubscriptionEntity subscriptionEntity = subscriptionRepository.findBySubId(subId);
		
		subscriptionEntity.setSubscribed(false);
	
	
		subscriptionRepository.save(subscriptionEntity);
		
		
	}
	
	//@Scheduled(fixedRate = 5000)
	public List<String> sendEmails(){
		List<String> emailList= new ArrayList<>();
		
		
		
	
		List<SubscriptionEntity> subscribers=   subscriptionRepository.findAllBySubscribed(true);
	
		
	
		
	
				
		for(String email:emailList) {
				
				
		}

	
		
		
		return emailList;
	}

	
	

}
