package com.corona.tracker.shared;


import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.corona.tracker.services.CoronavirusDataService;



@Service
public class EmailService {
	
		
		 
		
		final String FROM = "covid.tracker.service@gmail.com";
		
		final String SUBJECT_Status = "Corona Status Update " + java.time.LocalDate.now();
		
		final String HTMLBODY = "<h1> Corona Status Update "+java.time.LocalDate.now()+" </h1>";
		final String WELCOME_TEXT = "<p> Welcome to Corona Subscription Service. We update our stats once every 24 hours and send you the updates.<p>";
		final String DAILY_REPORT = "<p>Daily report<p>";
		final String TEXTBODY = "Hello " ;
		
		
	private String createDataTable(CoronavirusDataService coronaData) {
			String statsTable = "<table style=\"width:100%\">\r\n" + 
					"  <tr>\r\n" + 
					"    <th>Description</th>\r\n" + 
					"    <th>Stats</th>\r\n" + 
					"  </tr>\r\n" +  
					"  <tr>\r\n" + 
					"    <td>Confirmed Cases (WORLD)</td>\r\n" + 
					"    <td>"+coronaData.getNewStats().getConfirmedGlobal()+"</td>\r\n" + 
					"  </tr>\r\n" + 
					
					"  <tr>\r\n" + 
					"    <td>Confirmed Cases (United States)</td>\r\n" + 
					"    <td>"+coronaData.getNewStats().getConfirmedLocal()+"</td>\r\n"+ 
					"  </tr>\r\n" +
					"  <tr>\r\n" + 
					"    <td>Active Cases (United States)</td>\r\n" + 
					"    <td>"+coronaData.getNewStats().getActiveLocal()+"</td>\r\n"+ 
					"  </tr>\r\n" +"  <tr>\r\n" + 
					"    <td>Recovered (United States)</td>\r\n" + 
					"    <td>"+coronaData.getNewStats().getDailyLocal()+"</td>\r\n"+ 
					"  </tr>\r\n" +"  <tr>\r\n" + 
					"    <td>Deaths(World)</td>\r\n" + 
					"    <td>"+coronaData.getNewStats().getDeathsGlobal()+"</td>\r\n"+ 
					"  </tr>\r\n" +"  <tr>\r\n" + 
					"    <td>Deaths(United States)</td>\r\n" + 
					"    <td>"+coronaData.getNewStats().getDeathsLocal()+"</td>\r\n"+ 

					"  </tr>\r\n" +
					"</table>";
			return statsTable;
		}
		
		public void sendWelcomeEmail(String email,String subId,CoronavirusDataService coronaData) {
			String stats = this.createDataTable(coronaData);
			
			String unsubscibe = "<p> click <a href='http://localhost:8080/Unsubscribe/"+subId+"'>Unsubscribe</a> to stop recieving emails from our service"; 
			BasicAWSCredentials awsCredentials = new BasicAWSCredentials("", "");
			AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).withRegion(Regions.AP_SOUTH_1)
					.build();
	 
			String htmlBodyWithToken = HTMLBODY + DAILY_REPORT +'\n'+ stats+'\n'+ unsubscibe;
			String textBodyWithToken = TEXTBODY;

			SendEmailRequest request = new SendEmailRequest()
					.withDestination(new Destination().withToAddresses(email))
					.withMessage(new Message()
							.withBody(new Body().withHtml(new Content().withCharset("UTF-8").withData(htmlBodyWithToken))
									.withText(new Content().withCharset("UTF-8").withData(textBodyWithToken)))
							.withSubject(new Content().withCharset("UTF-8").withData(SUBJECT_Status)))
					.withSource(FROM);

			client.sendEmail(request);

	
		}
		
		public void sendDailyReport(String email,String subId, CoronavirusDataService coronaData) {
		
			String stats = this.createDataTable(coronaData);
			
			String unsubscibe = "<p> click <a href='https://corona-tracking-application.herokuapp.com/"+subId+"'>Unsubscribe</a> to stop recieving emails from our service"; 
					
			BasicAWSCredentials awsCredentials = new BasicAWSCredentials("AKIA44Z2MKME4JQV7SVG", "BG2fUsJI66F9ypmrpswPH9qWgGuM8p66JhlZ6QDVZpl");
			AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).withRegion(Regions.AP_SOUTH_1)
					.build();
	 
			String htmlBodyWithToken = HTMLBODY + DAILY_REPORT +'\n'+ stats+'\n'+ unsubscibe;
			String textBodyWithToken = TEXTBODY;

			SendEmailRequest request = new SendEmailRequest()
					.withDestination(new Destination().withToAddresses(email))
					.withMessage(new Message()
							.withBody(new Body().withHtml(new Content().withCharset("UTF-8").withData(htmlBodyWithToken))
									.withText(new Content().withCharset("UTF-8").withData(textBodyWithToken)))
							.withSubject(new Content().withCharset("UTF-8").withData(SUBJECT_Status)))
					.withSource(FROM);

			client.sendEmail(request);


			
		}
		
		
		
		
		
}

