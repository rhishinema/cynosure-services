package com.cynosure.services;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cynosure.pojo.Event;
import com.cynosure.pojo.Movie;
import com.cynosure.pojo.Product;

@Service
public class CommonService {

	@Autowired
	MovieService movieService;

	@Autowired
	EventService eventService;

	@Autowired
	ProductService productService;
	
	public static Logger LOGGER = Logger.getLogger(CommonService.class);

	public static Timestamp getTimeStampFromStr(String dateStr) {
		try {
			if (StringUtils.isEmpty(dateStr)) {
				return null;
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date parsedDate = dateFormat.parse(dateStr);
			Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
			return timestamp;
		} catch (Exception e) {
			return null;
		}
	}

	public static String getTimeStampStr(Timestamp timestamp) {
		try {
			if (timestamp == null) {
				return "";
			}
			Date date = new Date(timestamp.getTime());
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			return dateFormat.format(date);
		} catch (Exception e) {
			return "";
		}
	}

	public String buildHtmlForDailyMail() {

		Document document = null;

		try {
			document = Jsoup.parse(new File("src/main/resources/dailymail.html"), "UTF-8");
			Elements tables = document.select("table");
			for (Element element : tables) {
				if ("movieTable".equals(element.id())) {
					List<Movie> latestMovies = movieService.getLatestMovies();
					if (CollectionUtils.isNotEmpty(latestMovies)) {
						for (Movie movie : latestMovies) {
							element.appendElement("tr").appendElement("td").appendText(movie.getMovieName());
						}
					}
				}

				else if ("eventTable".equals(element.id())) {
					List<Event> upcomingEvents = eventService.getUpcomingEvents();
					if (CollectionUtils.isNotEmpty(upcomingEvents)) {
						for (Event event : upcomingEvents) {
							element.appendElement("tr").appendElement("td").appendText(event.getEventName());
						}
					}
				}

				else if ("productTable".equals(element.id())) {
					List<Product> trendingProducts = productService.getTrendingProducts();
					if (CollectionUtils.isNotEmpty(trendingProducts)) {
						for (Product product : trendingProducts) {
							element.appendElement("tr").appendElement("td").appendText(product.getProductName());
						}
					}
				}

			}
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}

		return document.html();
	}
}
