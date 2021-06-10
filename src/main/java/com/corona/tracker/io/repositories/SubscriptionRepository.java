package com.corona.tracker.io.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.corona.tracker.io.entity.SubscriptionEntity;

public interface SubscriptionRepository extends CrudRepository<SubscriptionEntity, Long > {

			SubscriptionEntity findByEmail(String email);
			
			SubscriptionEntity findBySubId(String subId);
			
			List<SubscriptionEntity> findAllBySubscribed(Boolean subscribed);
}
