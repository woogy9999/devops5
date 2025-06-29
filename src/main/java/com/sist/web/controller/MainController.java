package com.sist.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sist.web.service.CampService;
import com.sist.web.vo.*;
import java.util.*;

@Controller
public class MainController {
	@Autowired
	private CampService cService;
	
	@GetMapping("/main")
	public String main_main(Model model)
	{
		
		CampVO vo1= cService.campMainList1();
		List<CampVO> list1=cService.campMainList2();
		 
		
		model.addAttribute("vo1",vo1);
		model.addAttribute("list1",list1);
		model.addAttribute("main_html","main/home");
		return "main";
	}
	
	
	
}
