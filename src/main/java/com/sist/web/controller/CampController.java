package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

import com.sist.web.service.CampService;
import com.sist.web.vo.*;
@Controller
public class CampController {

	@Autowired
	private CampService cService;
	
	@GetMapping("/camp/list")
	public String camp_list(@RequestParam(name="page",required = false) String page, Model model)
	{
		if(page==null)
			page="1";
		
		int curpage=Integer.parseInt(page);
		int rowSize=12;
		int start=(rowSize*curpage)-(rowSize-1);
		int end =(rowSize*curpage);
		
		
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		List<CampVO> list=cService.campListData(map); 
		int totalpage=cService.campTotalPage();
		 
		final int BLOCK=10; 
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		
		model.addAttribute("list",list);
		model.addAttribute("curpage",curpage);
		model.addAttribute("startPage",startPage);
		model.addAttribute("endPage",endPage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("main_html","camp/list");
		
		return "main";
	}
	
	
    @GetMapping("/camp/detail/{cno}")
    public String camp_detail(@PathVariable("cno") int cno, Model model) {
        CampVO vo = cService.campDetailData(cno);
        model.addAttribute("vo", vo);
        model.addAttribute("main_html", "camp/detail");
        return "main";
    }
    
    
    @GetMapping("/camp/find")
    public String camp_list(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                            @RequestParam(name = "keyword", required = false) String keyword,
                            Model model) {

        int rowSize = 12;
        int start = (rowSize * page) - (rowSize - 1);
        int end = rowSize * page;

        Map map = new HashMap();
        map.put("start", start);
        map.put("end", end);
        if (keyword != null && !keyword.isEmpty()) {
            map.put("keyword", "%" + keyword + "%");
        }

        List<CampVO> list = cService.campFindData(map);
        int totalpage = cService.campFindPage(map);  

        final int BLOCK = 10;
        int startPage = ((page - 1) / BLOCK * BLOCK) + 1;
        int endPage = startPage + BLOCK - 1;
        if (endPage > totalpage)
            endPage = totalpage;

        model.addAttribute("list", list);
        model.addAttribute("curpage", page);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("totalpage", totalpage);
        model.addAttribute("keyword", keyword); 
        model.addAttribute("main_html", "camp/find");

        return "main";
    }
}
