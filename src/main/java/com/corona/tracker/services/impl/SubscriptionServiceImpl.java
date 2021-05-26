package com.corona.tracker.services.impl;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corona.tracker.io.entity.SubscriptionEntity;
import com.corona.tracker.io.repositories.SubscriptionRepository;
import com.corona.tracker.services.SubscriptionService;
import com.corona.tracker.shared.dto.SubscriptionDto;


@Service
public class SubscriptionServiceImpl implements SubscriptionService {

	
	@Autowired
	SubscriptionRepository subscriptionRepository;
	
	
	
	@Override
	public SubscriptionDto createSubscription(SubscriptionDto sub) {
		//if(subscriptionRepository.findByEmail(sub.getEmail()) != null) 
		
			
			ModelMapper modelmapper = new ModelMapper();
			SubscriptionEntity subscriptionEntity = modelmapper.map(sub, SubscriptionEntity.class);
			
			//String subId = utils.generateUserId(30);
			//subscriptionEntity.setSubId(subId);
			
			SubscriptionEntity storedSubscription = subscriptionRepository.save(subscriptionEntity);
			
			SubscriptionDto returnValue = modelmapper.map(storedSubscription,SubscriptionDto.class );
			
			
			return returnValue;
			
	}

}
