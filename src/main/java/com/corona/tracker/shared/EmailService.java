package com.corona.tracker.shared;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
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
	
		
		 
		
		final String FROM = "dashudaram@gmail.com";
		
		final String SUBJECT_Status = "Corona Status Update " + java.time.LocalDate.now();
		
		final String HTMLBODY = "<h1> Corona Status Update "+java.time.LocalDate.now()+" </h1>";
		final String WELCOME_TEXT = "<p> Welcome to Corona Subscription Service. We update our stats once every 24 hours and send you the updates.<p>";
		
		final String TEXTBODY = "Hello " ;
		
		
		public void sendWelcomeEmail(String email,CoronavirusDataService coronaData) {
			
			final String STATS = "<table style=\"width:100%\">\r\n" + 
					"  <tr>\r\n" + 
					"    <th>Description</th>\r\n" + 
					"    <th>Stats</th>\r\n" + 
					"  </tr>\r\n" +  
					"  <tr>\r\n" + 
					"    <td>Confirmed Cases (WORLD)</td>\r\n" + 
					"    <td>"+coronaData.getNewStats().getConfirmedGlobal()+"</td>\r\n" + 
					"  </tr>\r\n" + 
					
					"  <tr>\r\n" + 
					"    <td>Confirmed Cases (Sri Lanka)</td>\r\n" + 
					"    <td>"+coronaData.getNewStats().getConfirmedLocal()+"</td>\r\n"+ 
					"  </tr>\r\n" +
					"  <tr>\r\n" + 
					"    <td>Active Cases (Sri Lanka)</td>\r\n" + 
					"    <td>"+coronaData.getNewStats().getActiveLocal()+"</td>\r\n"+ 
					"  </tr>\r\n" +"  <tr>\r\n" + 
					"    <td>Daily Cases (Sri Lanka)</td>\r\n" + 
					"    <td>"+coronaData.getNewStats().getDailyLocal()+"</td>\r\n"+ 
					"  </tr>\r\n" +"  <tr>\r\n" + 
					"    <td>Deaths(World)</td>\r\n" + 
					"    <td>"+coronaData.getNewStats().getDeathsGlobal()+"</td>\r\n"+ 
					"  </tr>\r\n" +"  <tr>\r\n" + 
					"    <td>Deaths(Sri Lanka)</td>\r\n" + 
					"    <td>"+coronaData.getNewStats().getDeathsLocal()+"</td>\r\n"+ 

					"  </tr>\r\n" +
					"</table>";
			
			BasicAWSCredentials awsCredentials = new BasicAWSCredentials("AKIA44Z2MKMEZAZO653V", "rK/O610IxUqlze8hNcNl23KrEdDamWqO2v/3TPoo");
			AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).withRegion(Regions.AP_SOUTH_1)
					.build();
	 
			String htmlBodyWithToken = HTMLBODY + WELCOME_TEXT+'\n'+ STATS;
			String textBodyWithToken = TEXTBODY;

			SendEmailRequest request = new SendEmailRequest()
					.withDestination(new Destination().withToAddresses(email))
					.withMessage(new Message()
							.withBody(new Body().withHtml(new Content().withCharset("UTF-8").withData(htmlBodyWithToken))
									.withText(new Content().withCharset("UTF-8").withData(textBodyWithToken)))
							.withSubject(new Content().withCharset("UTF-8").withData(SUBJECT_Status)))
					.withSource(FROM);

			client.sendEmail(request);

			System.out.println("Email sent!");
		}
}

