package com.cynosure.services;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class CommonService {

	public static Timestamp getTimeStampFromStr(String dateStr){
		try{
			if(StringUtils.isEmpty(dateStr)){
				return null;
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		    Date parsedDate = dateFormat.parse(dateStr);
		    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
		    return timestamp;
		}catch(Exception e){
			return null;
		}
	}
	
	public static String getTimeStampStr(Timestamp timestamp){
		try{
			if(timestamp == null){
				return "";
			}
			Date date = new Date(timestamp.getTime());
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		    return dateFormat.format(date);
		}catch(Exception e){
			return "";
		}
	}
}
