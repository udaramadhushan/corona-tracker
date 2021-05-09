package com.corona.tracker.services;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpEntity;
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
			 
			 
			
			
			String confirmedLocal = webDoc.select("p:contains(Total Confirmed Cases)")
					.first().parent()
					.selectFirst(".local").select("h4").first().attr("data-counter");
			
			String confirmedGlobal = webDoc.select("p:contains(Total Confirmed Cases)")
					.first().parent()
					.selectFirst(".global").select("h4").first().attr("data-counter");
			
		
			 newStats.setConfirmedCases(confirmedGlobal);
			 
	}





		public Stats getNewStats() {
			return newStats;
		}


	
}
