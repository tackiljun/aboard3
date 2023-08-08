package org.astro.aboard3.controller;

import org.astro.aboard3.dto.BoardDTO;
import org.astro.aboard3.dto.PageRequestDTO;
import org.astro.aboard3.dto.PageResponseDTO;
import org.astro.aboard3.service.board.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Controller
@RequiredArgsConstructor
@RequestMapping("/board/")
@Log4j2
public class BoardController {

    /////////////////////////////////////////////////////////////////////////////////////
    // 의존성 주입.
    private final BoardService boardService;

    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////
    // GET.
    // list.
    @GetMapping("list")
    public void getList(PageRequestDTO pageRequestDTO, Model model) {

        PageResponseDTO<BoardDTO> pageResponseDTO = boardService.getList(pageRequestDTO);

        model.addAttribute("pageResponseDTO", pageResponseDTO);

        log.info("GET ||||| ----------getList----------");
    }

    /////////////////////////////////////////////////////////////////////////////////////
    // read.
    @GetMapping("read/{bno}")
    public String getRead(@PathVariable("bno") Integer bno, Model model) {

        model.addAttribute("dto", boardService.getRead(bno));

        log.info("GET ||||| ----------getRead----------");

        // BoardDTO boardDTO = boardService.getRead(bno);

        // log.info(boardDTO);

        return "/board/read";
    }

    /////////////////////////////////////////////////////////////////////////////////////
    // regist.
    @GetMapping("regist")
    public void getRegist(BoardDTO boardDTO) {

        log.info("GET ||||| ----------getRegist----------");
    }

    /////////////////////////////////////////////////////////////////////////////////////
    // modify.
    @GetMapping("modify/{bno}")
    public String getModify(@PathVariable("bno") Integer bno, Model model) {

        model.addAttribute("dto", boardService.getRead(bno));

        log.info("GET ||||| ----------getModify----------");

        return "/board/modify";
    }

    /////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////
    // POST.
    // regist.
    @PostMapping("regist")
    public String postRegist(BoardDTO boardDTO) {

        boardService.regist(boardDTO);

        log.info("POST ||||| ----------postRegist----------");

        return "redirect:/board/list";
    }

    /////////////////////////////////////////////////////////////////////////////////////
    // modify.
    @PostMapping("modify/{bno}")
    public String postModify(@PathVariable("bno") Integer bno, BoardDTO boardDTO) {

        boardService.modify(boardDTO);

        log.info("POST ||||| ----------postModify----------");

        return "redirect:/board/read" + bno;
    }

    /////////////////////////////////////////////////////////////////////////////////////
    // delete.
    @PostMapping("delete/{bno}")
    public String PostDelete(@PathVariable("bno") Integer bno) {

        boardService.delete(bno);

        log.info("POST ||||| ----------postDelete----------");

        return "redirect:/board/list";
    }
    
}
