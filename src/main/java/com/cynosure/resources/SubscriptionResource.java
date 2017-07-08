package com.cynosure.resources;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cynosure.services.SubscriptionService;
import com.cynosure.util.BaseException;
import com.cynosure.util.CommonConstants;

import net.sf.json.JSONObject;

@RestController
@RequestMapping("/v1/subscribers")
public class SubscriptionResource {

	@Autowired
	SubscriptionService subscriptionService;

	public static Logger LOGGER = Logger.getLogger(SubscriptionResource.class);

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public ResponseEntity<JSONObject> addSubscriber(@RequestBody JSONObject jsonObject) {

		LOGGER.setLevel(Level.ERROR);
		try {
			if (null != jsonObject && jsonObject.containsKey(CommonConstants.SUBSCRIBER_EMAIL)) {
				subscriptionService.addSubscriber(jsonObject);
			} else {
				throw new BaseException("email id not found");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			JSONObject error = new JSONObject();
			error.put("error", e.getMessage());
			return new ResponseEntity<JSONObject>(error, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<JSONObject>(HttpStatus.NO_CONTENT);
	}
}