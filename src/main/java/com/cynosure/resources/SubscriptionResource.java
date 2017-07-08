package com.cynosure.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cynosure.services.SubscriptionService;
import com.cynosure.util.BaseException;
import com.cynosure.util.CommonConstants;

import net.sf.json.JSONObject;

@Path("v1/subscribers")
@Service
public class SubscriptionResource {

	@Autowired
	SubscriptionService subscriptionService;

	public static Logger LOGGER = Logger.getLogger(SubscriptionResource.class);

	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response showAccountInfo(JSONObject jsonObject) {

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
			return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
		}

		return Response.status(Response.Status.NO_CONTENT).build();
	}
}
