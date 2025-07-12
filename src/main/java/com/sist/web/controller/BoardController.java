package com.sist.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sist.web.dao.BoardMapper;
import com.sist.web.vo.BoardVO;

@Controller
public class BoardController {
    @Autowired
    private BoardMapper mapper;

    @GetMapping("/board/list")
    public String board_list(Model model,@RequestParam(name = "page", required = false) String page) {
        if (page == null) page = "1";
        int curpage = Integer.parseInt(page);
        int rowSize = 7;
        int start = (rowSize * curpage) - rowSize + 1;
        int end = rowSize * curpage;

        List<BoardVO> list = mapper.boardListData(start, end);

        int count = mapper.count();
        int totalpage = (int) Math.ceil(count / 7.0);
        final int BLOCK = 5;
        int startPage = ((curpage - 1) / BLOCK * BLOCK) + 1;
        int endPage = startPage + BLOCK - 1;
        if (endPage > totalpage) endPage = totalpage;

        model.addAttribute("list", list);
        model.addAttribute("curpage", curpage);
        model.addAttribute("count", count);
        model.addAttribute("totalpage", totalpage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("main_html", "board/board_list");
        return "main";
    }

    @GetMapping("/board/insert")
    public String board_insert(Model model) {
        model.addAttribute("main_html", "board/board_insert");
        return "main";
    }

    @GetMapping("/board/detail")
    public String board_detail(Model model, @RequestParam(name = "no") int no) {
        BoardVO vo = mapper.findByNo(no);
        mapper.hitIncrement(no);
        
        model.addAttribute("no", no);
        model.addAttribute("vo", vo);
        model.addAttribute("main_html", "board/board_detail");
        return "main";
    }

    @GetMapping("/board/insert_ok")
    public String board_insert_ok(BoardVO vo) {
        mapper.boardInsert(vo);
        return "redirect:/board/list";
    }

    @GetMapping("/board/update")
    public String board_update(@RequestParam("no") int no, Model model) {
        BoardVO vo = mapper.findByNo(no);
        model.addAttribute("vo", vo);
        model.addAttribute("main_html", "board/board_update");
        return "main";
    }
    
    @GetMapping("/board/delete")
    public String board_delete(@RequestParam("no") int no, Model model) {
        BoardVO vo = mapper.findByNo(no);
        model.addAttribute("vo", vo);
        model.addAttribute("main_html", "board/board_delete");
        return "main";
    }
}
