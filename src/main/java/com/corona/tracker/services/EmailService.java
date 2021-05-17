package com.corona.tracker.services;

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

public class EmailService {
		final String FROM = "dashudaram@gmail.com";
		
		final String SUBJECT = "CORONA UPDATE" + java.time.LocalDate.now();
		
		final String HTMLBODY = "<h1> corona update </h1>";
		
		final String TEXTBODY = "test body";
		
		
		public void sendEmail(String email) {
			
			BasicAWSCredentials awsCredentials = new BasicAWSCredentials("AKIA44Z2MKMEZAZO653V", "rK/O610IxUqlze8hNcNl23KrEdDamWqO2v/3TPoo");
			AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).withRegion(Regions.AP_SOUTH_1)
					.build();
	 
			String htmlBodyWithToken = HTMLBODY;
			String textBodyWithToken = TEXTBODY;

			SendEmailRequest request = new SendEmailRequest()
					.withDestination(new Destination().withToAddresses(email))
					.withMessage(new Message()
							.withBody(new Body().withHtml(new Content().withCharset("UTF-8").withData(htmlBodyWithToken))
									.withText(new Content().withCharset("UTF-8").withData(textBodyWithToken)))
							.withSubject(new Content().withCharset("UTF-8").withData(SUBJECT)))
					.withSource(FROM);

			client.sendEmail(request);

			System.out.println("Email sent!");
		}
}

