package org.astro.aboard3.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.astro.aboard3.dto.PageRequestDTO;
import org.astro.aboard3.dto.ReplyDTO;


public interface ReplyMapper {

    // 등록.
    int replyRegist(ReplyDTO replyDTO);

    List<ReplyDTO> selectList(@Param("bno") Long bno, @Param("page") PageRequestDTO pageRequestDTO);

    // 보기.
    ReplyDTO replyRead(Long rno);

    // 수정.
    int replyModify(ReplyDTO replyDTO);

    // 삭제.
    int replyDelete(Long rno);
    
}
