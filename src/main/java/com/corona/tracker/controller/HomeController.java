package com.corona.tracker.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.corona.tracker.exceptions.SubscriptionServiceException;

import com.corona.tracker.model.SubscriptionRequestModel;
import com.corona.tracker.services.CoronavirusDataService;
import com.corona.tracker.services.SubscriptionService;

import com.corona.tracker.shared.dto.SubscriptionDto;
import java.time.format.DateTimeFormatter;

import java.time.LocalDateTime;    
@Controller

public class HomeController {
	
	@Autowired
	CoronavirusDataService coronaVirusDataService;
	
	@Autowired
	SubscriptionService subscriptionService;
	
	 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E yyyy.MM.dd ");  
	 LocalDateTime now = LocalDateTime.now();  
	
	@GetMapping("/")
	public String home(Model model){
	
		
		model.addAttribute("confirmedGlobal",coronaVirusDataService.getNewStats().getConfirmedGlobal());
		model.addAttribute("confirmedLocal", coronaVirusDataService.getNewStats().getConfirmedLocal());
		model.addAttribute("activeLocal", coronaVirusDataService.getNewStats().getActiveLocal());
		model.addAttribute("dailyLocal", coronaVirusDataService.getNewStats().getDailyLocal());
		model.addAttribute("deathsLocal", coronaVirusDataService.getNewStats().getDeathsLocal());
		model.addAttribute("deathsGlobal", coronaVirusDataService.getNewStats().getDeathsGlobal());
		model.addAttribute("currentDate", dtf.format(now));
		model.addAttribute("subscription", new SubscriptionRequestModel());
		
		
	
		
		return "Home";
	}
	
	
	@GetMapping("/Unsubscribe/{subId}")
	public String Unsubscribe(Model model, @PathVariable String subId){
		model.addAttribute("currentDate", dtf.format(now));
		 model.addAttribute("subId", subId);
	
		return "Unsubscribe";
	}
	
	@PostMapping("/Unsubscribe/**")
	public String UnsubsribeUser(@RequestParam(value = "subId")String subId,Model model) {
	
		subscriptionService.unsubscribe(subId);
		return "SubscriptionCancelled";
		
	}
	
	
	@PostMapping("/")
	public String Subscribe(@ModelAttribute("subscription")SubscriptionRequestModel subscriptionRequest,Model model) throws Exception {
		
	    model.addAttribute("subscription", subscriptionRequest);
		
		model.addAttribute("confirmedGlobal",coronaVirusDataService.getNewStats().getConfirmedGlobal());
		model.addAttribute("confirmedLocal", coronaVirusDataService.getNewStats().getConfirmedLocal());
		model.addAttribute("activeLocal", coronaVirusDataService.getNewStats().getActiveLocal());
		model.addAttribute("dailyLocal", coronaVirusDataService.getNewStats().getDailyLocal());
		model.addAttribute("deathsLocal", coronaVirusDataService.getNewStats().getDeathsLocal());
		model.addAttribute("deathsGlobal", coronaVirusDataService.getNewStats().getDeathsGlobal());
		
		
		ModelMapper modelMapper = new ModelMapper();
		
		SubscriptionDto subDto = modelMapper.map(subscriptionRequest,SubscriptionDto.class);
		
		try {
				subscriptionService.createSubscription(subDto);
		} catch (SubscriptionServiceException sub){
			
			
			System.out.println("invalid username");
		}
		
		return "Home";
	
	}
	
	
	
	
}
