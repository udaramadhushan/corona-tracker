package com.corona.tracker.services;

import java.io.IOException;


import javax.annotation.PostConstruct;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.corona.tracker.models.Stats;


@Service
public class CoronavirusDataService {



		
		Stats newStats = new Stats();
	
		
		@PostConstruct
		@Scheduled(cron = "* * 1 * * *")
		public void fetchData () throws IOException, InterruptedException {
			
			
			
			Document webDoc = Jsoup.connect("https://www.hpb.health.gov.lk/en").get();
			Document webDoc2 = Jsoup.connect("https://www.worldometers.info/coronavirus/").get(); 
			
			String confirmedLocal = webDoc.select("p:contains(Total Confirmed Cases)")
					.first().parent()
					.selectFirst(".local").select("h4").first().attr("data-counter");
			
			String confirmedGlobal = webDoc.select("p:contains(Total Confirmed Cases)")
					.first().parent().selectFirst(".global")
					.select("h4").first().attr("data-counter");
			 String activeLocal = webDoc.select("p:contains(Active Cases)")
						.first().parent()
						.selectFirst(".local").select("h4").first().attr("data-counter");
//		
			 String dailyLocal = webDoc.select("p:contains(Daily New Cases)")
						.first().parent()
						.selectFirst(".local").select("h4").first().attr("data-counter");

			 String deathsLocal = webDoc.select("p:contains(Deaths)")
						.first().parent()
						.selectFirst(".local").select("h4").first().attr("data-counter");
			 
			 
		Element deathsGlobal =  webDoc2.select("p:contains(Deaths:)")
						.first().parent()
						.selectFirst(".maincounter-number").select("span").first();
//		
//			 String dailyGlobal = webDoc.select("p:contains(Daily New Cases)")
//				.first().parent()
//				.selectFirst(".global").select("h4").first().attr("data-counter");
			 
			 
			 
				//newStats.setDailyGlobal(dailyGlobal );
			//newStats.setDeathsGlobal(deathsGlobal );
		
		System.out.println(deathsGlobal.html());
			newStats.setConfirmedGlobal(confirmedGlobal);
			newStats.setConfirmedLocal(confirmedLocal);
			 newStats.setDailyLocal(dailyLocal);
		
			newStats.setDeathsLocal(deathsLocal );
			
			 newStats.setActiveLocal (activeLocal );
			
		 System.out.println(newStats.toString());
			
			 
	}

		

	

		public Stats getNewStats() {
			return newStats;
		}


	
}
