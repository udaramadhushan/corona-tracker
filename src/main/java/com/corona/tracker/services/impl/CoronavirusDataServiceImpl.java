package com.corona.tracker.services.impl;

import java.io.IOException;
import java.text.NumberFormat;

import javax.annotation.PostConstruct;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.corona.tracker.model.Stats;
import com.corona.tracker.services.CoronavirusDataService;

@Service
public class CoronavirusDataServiceImpl implements CoronavirusDataService{
	



		@Value(value = "${web.url1}")
		String webUrl1;
		
		@Value(value = "${web.url2}")
		String webUrl2;
		
		@Value(value = "${web.url3}")
		String webUrl3;
		
		
		
		
		Stats newStats = new Stats();


		@PostConstruct
		@Scheduled(cron = "* * 1 * * *")
		public void fetchData () throws IOException, InterruptedException {

			
			
			Document webDoc = Jsoup.connect(webUrl1).get();
			Document webDoc2 = Jsoup.connect(webUrl2).get(); 
			Document webDoc3 = Jsoup.connect(webUrl3).get();
			
			

			String confirmedLocal = webDoc3.select("#maincounter-wrap").eq(0).select("span").text();

			String confirmedGlobal = webDoc.select("p:contains(Total Confirmed Cases)")
					.first().parent().selectFirst(".global")
					.select("h4").first().attr("data-counter");

			String activeLocal =  webDoc3.select("#usa_table_countries_today").select("tbody").eq(0).select("tr").eq(0).select("td").eq(7).text();
					
			String dailyLocal = webDoc3.select("#maincounter-wrap").eq(2).select("span").text();

			String deathsLocal =  webDoc3.select("#maincounter-wrap").eq(1).select("span").text();


			String deathsGlobal =  webDoc2.select("#maincounter-wrap").eq(1).select("span").text();

			
			//*[@id="usa_table_countries_today"]/tbody[1]/tr[1]/td[8]
			
			
			newStats.setConfirmedGlobal(confirmedGlobal);
			newStats.setConfirmedLocal(confirmedLocal);
			newStats.setDailyLocal(dailyLocal);

			

			newStats.setActiveLocal (activeLocal );
			newStats.setDeathsGlobal(deathsGlobal);
			newStats.setDeathsLocal(deathsLocal);

		

		}





		public Stats getNewStats() {
			return newStats;
		}



	}


