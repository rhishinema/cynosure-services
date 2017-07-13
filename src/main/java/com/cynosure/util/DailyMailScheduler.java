package com.cynosure.util;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.cynosure.services.ImailService;

public class DailyMailScheduler {
	
	@Autowired
	ImailService mailJetMailService;
	
	public static Logger LOGGER = Logger.getLogger(DailyMailScheduler.class);
	
	@Scheduled(cron="0 0 * * *")
    public void printMessage() {
        try {
			mailJetMailService.sendDailyMail();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
    }

}
