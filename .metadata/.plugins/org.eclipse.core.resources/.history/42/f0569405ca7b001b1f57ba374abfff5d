package org.zerock.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.zerock.dao.TimeDAO;
import org.zerock.service.Restaurant;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

/**
 * Handles requests for the application home page.
 */
@NonNull
@Controller
@RequiredArgsConstructor
@Log4j
public class HomeController {

	private final Restaurant restaurant;
	private final TimeDAO timeDAO;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws Exception {
		log.info("Welcome home! The client locale is {}."+ locale);
		
		String time = timeDAO.getTime();
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("time",  time);
	
		model.addAttribute("store", restaurant);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
}
