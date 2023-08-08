package org.astro.aboard3.service.reply;

import org.astro.aboard3.dto.PageRequestDTO;
import org.astro.aboard3.dto.PageResponseDTO;
import org.astro.aboard3.dto.ReplyDTO;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface ReplyService {
    
    PageResponseDTO<ReplyDTO> getReplyList(Long bno, PageRequestDTO pageRequestDTO);

    Long replyRegist(ReplyDTO replyDTO);

    ReplyDTO replyRead(Long rno);

    void replyModify(ReplyDTO replyDTO);

    void replyDelete(Long rno);
    
}
