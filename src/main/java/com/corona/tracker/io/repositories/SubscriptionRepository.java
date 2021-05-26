package com.corona.tracker.io.repositories;

import org.springframework.data.repository.CrudRepository;

import com.corona.tracker.io.entity.SubscriptionEntity;

public interface SubscriptionRepository extends CrudRepository<SubscriptionEntity, Long > {

			SubscriptionEntity findByEmail(String email);
			
}
