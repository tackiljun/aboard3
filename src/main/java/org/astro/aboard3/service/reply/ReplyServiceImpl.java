package org.astro.aboard3.service.reply;

import java.util.List;

import org.astro.aboard3.dto.PageRequestDTO;
import org.astro.aboard3.dto.PageResponseDTO;
import org.astro.aboard3.dto.ReplyDTO;
import org.astro.aboard3.mappers.ReplyMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    /////////////////////////////////////////////////////////////////////////////////////////
    private final ReplyMapper replyMapper;

    /////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public PageResponseDTO<ReplyDTO> getReplyList(Long bno, PageRequestDTO pageRequestDTO) {
        
        List<ReplyDTO> list = replyMapper.selectList(bno, pageRequestDTO);

        return PageResponseDTO.<ReplyDTO>withAll().list(list).build();
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public Long replyRegist(ReplyDTO replyDTO) {
        
        replyMapper.replyRegist(replyDTO);

        Long newRno = replyDTO.getRno();

        return newRno;
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public ReplyDTO replyRead(Long rno) {
        
        return replyMapper.replyRead(rno);
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void replyModify(ReplyDTO replyDTO) {
        
        replyMapper.replyModify(replyDTO);
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void repllyDelete(Long rno) {
        
        replyMapper.replyDelete(rno);
    }

    

    

    

    

    
    
}
