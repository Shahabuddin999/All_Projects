package com.thymeleaf.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserControlller {

	@GetMapping("showWelcome")
	public String showWelcomePage(Model model) {
		List<String> cities = new ArrayList<String>();
		String []countryList= {"India","America","Austrailia"};
		cities.add("Mumbai");cities.add("Kolkata");cities.add("Delhi");
		model.addAttribute("firstName", "Shahabuddin");
		model.addAttribute("cityList", cities);
		model.addAttribute("countryList", countryList);
		return "welcome";
	}
}
