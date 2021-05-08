package com.corona.tracker.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;


@Service
public class CoronavirusDataService {
		private static String DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_daily_reports/01-01-2021.csv";
		
		
		@PostConstruct
		public void fetchData () throws IOException, InterruptedException {
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(DATA_URL)).build();
			
			HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());   
			  
			System.out.println(httpResponse.body());
		}
		
		
}
