package org.astro.aboard3.controller;

import java.util.Map;

import org.astro.aboard3.dto.PageRequestDTO;
import org.astro.aboard3.dto.PageResponseDTO;
import org.astro.aboard3.dto.ReplyDTO;
import org.astro.aboard3.service.reply.ReplyService;
import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@RestController
@RequiredArgsConstructor
@RequestMapping("/replies/")
@Log4j2
public class ReplyController {
    
    ///////////////////////////////////////////////////////////////////////////////
    // 의존성 주입.
    private final ReplyService replyService;

    ///////////////////////////////////////////////////////////////////////////////
    @GetMapping(value = "{bno}/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public PageResponseDTO<ReplyDTO> getReplyList(
        @PathVariable("bno") Long bno, PageRequestDTO pageRequestDTO) {

        log.info("GET ||||| ----------replyList----------");
        log.info("bno: " + bno);

        return replyService.getReplyList(bno, pageRequestDTO);
    }

    ///////////////////////////////////////////////////////////////////////////////
    @PostMapping("{bno}/new")
    public Map<String, Long> replyRegist(
        @PathVariable("bno") Long bno, @Valid @RequestBody ReplyDTO replyDTO, 
        BindingResult bindingResult) throws BindException {

        replyDTO.setBno(bno);

        if(bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        Long rno = replyService.replyRegist(replyDTO);

        log.info("POST ||||| ----------replyRegist----------");
        log.info(replyDTO);

        return Map.of("result", rno);
    }

    ///////////////////////////////////////////////////////////////////////////////
    @GetMapping("read/{rno}")
    public ReplyDTO replyRead(@PathVariable("rno") Long rno) {

        log.info("GET ||||| ----------replyRead----------");
        log.info(rno);

        return replyService.replyRead(rno);
    }

    ///////////////////////////////////////////////////////////////////////////////
    @PutMapping("modify/{rno}")
    public Map<String, Long> replyModify(@RequestBody ReplyDTO replyDTO) {

        log.info("PUT ||||| ----------replyModify----------");

        replyService.replyModify(replyDTO);

        return Map.of("result", replyDTO.getRno());
    }

    ///////////////////////////////////////////////////////////////////////////////
    @DeleteMapping("delete/{rno}")
    public Map<String, Long> replyDelete(@PathVariable("rno") Long rno) {

        log.info("DELETE ||||| ----------replyDelete----------");

        replyService.repllyDelete(rno);

        return Map.of("result", rno);
    }
}
