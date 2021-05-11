package com.corona.tracker.services;

import java.io.IOException;


import javax.annotation.PostConstruct;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.corona.tracker.models.Stats;


@Service
public class CoronavirusDataService {

	@Value(value = "web.url1")
	String webUrl1;
	
	@Value(value = "web.url2")
	String webUrl2;

	Stats newStats = new Stats();


	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	public void fetchData () throws IOException, InterruptedException {

		
		Document webDoc = Jsoup.connect(webUrl1).get();
		Document webDoc2 = Jsoup.connect(webUrl2).get(); 

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


		String deathsGlobal =  webDoc2.select("#maincounter-wrap").eq(1).select("span").text();


		newStats.setConfirmedGlobal(confirmedGlobal);
		newStats.setConfirmedLocal(confirmedLocal);
		newStats.setDailyLocal(dailyLocal);

		newStats.setDeathsLocal(deathsLocal );

		newStats.setActiveLocal (activeLocal );




	}





	public Stats getNewStats() {
		return newStats;
	}



}
