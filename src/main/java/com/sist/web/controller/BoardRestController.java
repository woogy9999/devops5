package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.dao.BoardMapper;
import com.sist.web.vo.BoardVO;

@RestController
public class BoardRestController {
    @Autowired
    private BoardMapper mapper;

    @GetMapping("/board/delete_vue")
    public String board_delete(@RequestParam("no") int no,@RequestParam("pwd") String pwd) {
        String result = "";
        BoardVO vo = mapper.findByNo(no);
        if (vo.getPwd().equals(pwd)) {
            result = "yes";
            mapper.boardDelete(no);
        } else {
            result = "no";
        }
        return result;
    }

    @PostMapping("/board/update_vue")
    public String board_update(BoardVO vo) {
        String result = "";
        BoardVO db_vo = mapper.findByNo(vo.getNo());
        if (vo.getPwd().equals(db_vo.getPwd())) {
            result = "yes";
            vo.setHit(db_vo.getHit());
            mapper.boardUpdate(vo);
        } else {
            result = "no";
        }
        return result;
    }
}
