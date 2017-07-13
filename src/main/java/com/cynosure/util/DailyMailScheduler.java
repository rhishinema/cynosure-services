package com.cynosure.util;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cynosure.services.ImailService;

@Component
public class DailyMailScheduler {
	
	@Autowired
	ImailService mailJetMailService;
	
	public static Logger LOGGER = Logger.getLogger(DailyMailScheduler.class);
	
	@Scheduled(cron="0 0 * * *")
    public void sendDailyMail() {
        try {
        	LOGGER.info("Sending Daily Mail");
			mailJetMailService.sendDailyMail();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
    }

}
