package com.corona.tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.corona.tracker.services.CoronavirusDataService;

@Controller

public class HomeController {
	
	@Autowired
	CoronavirusDataService coronaVirusDataService;
	
	@GetMapping("/")
	public String home(Model model){
		
		model.addAttribute("confirmedGlobal",coronaVirusDataService.getNewStats().getConfirmedGlobal());
		model.addAttribute("confirmedLocal", coronaVirusDataService.getNewStats().getConfirmedLocal());
		model.addAttribute("activeLocal", coronaVirusDataService.getNewStats().getActiveLocal());
		model.addAttribute("dailyLocal", coronaVirusDataService.getNewStats().getDailyLocal());
		model.addAttribute("deathsLocal", coronaVirusDataService.getNewStats().getDeathsLocal());
		model.addAttribute("deathsGlobal", coronaVirusDataService.getNewStats().getDeathsGlobal());
		
		
	
		
		return "Home";
	}

}
