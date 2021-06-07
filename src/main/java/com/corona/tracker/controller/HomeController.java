package com.corona.tracker.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.corona.tracker.model.SubscriptionRequestModel;
import com.corona.tracker.services.CoronavirusDataService;
import com.corona.tracker.services.SubscriptionService;
import com.corona.tracker.shared.EmailService;
import com.corona.tracker.shared.dto.SubscriptionDto;

@Controller

public class HomeController {
	
	@Autowired
	CoronavirusDataService coronaVirusDataService;
	
	@Autowired
	SubscriptionService subscriptionService;
	
	@GetMapping("/")
	public String home(Model model){
	
		model.addAttribute("confirmedGlobal",coronaVirusDataService.getNewStats().getConfirmedGlobal());
		model.addAttribute("confirmedLocal", coronaVirusDataService.getNewStats().getConfirmedLocal());
		model.addAttribute("activeLocal", coronaVirusDataService.getNewStats().getActiveLocal());
		model.addAttribute("dailyLocal", coronaVirusDataService.getNewStats().getDailyLocal());
		model.addAttribute("deathsLocal", coronaVirusDataService.getNewStats().getDeathsLocal());
		model.addAttribute("deathsGlobal", coronaVirusDataService.getNewStats().getDeathsGlobal());
		model.addAttribute("subscription", new SubscriptionRequestModel());
		
	
		
		return "Home";
	}
	
	@PostMapping("/")
	public String Subscribe(@ModelAttribute("subscription")SubscriptionRequestModel subscriptionRequest,Model model) {
		
	    model.addAttribute("subscription", subscriptionRequest);
		
		model.addAttribute("confirmedGlobal",coronaVirusDataService.getNewStats().getConfirmedGlobal());
		model.addAttribute("confirmedLocal", coronaVirusDataService.getNewStats().getConfirmedLocal());
		model.addAttribute("activeLocal", coronaVirusDataService.getNewStats().getActiveLocal());
		model.addAttribute("dailyLocal", coronaVirusDataService.getNewStats().getDailyLocal());
		model.addAttribute("deathsLocal", coronaVirusDataService.getNewStats().getDeathsLocal());
		model.addAttribute("deathsGlobal", coronaVirusDataService.getNewStats().getDeathsGlobal());
		
		
		ModelMapper modelMapper = new ModelMapper();
		
		SubscriptionDto subDto = modelMapper.map(subscriptionRequest,SubscriptionDto.class);
		
		SubscriptionDto returnValue = subscriptionService.createSubscription(subDto);
		
		
		return "Home";
	
	}
	
	
}
