package org.astro.aboard3.mappers;

import java.util.List;
import java.util.Map;

import org.astro.aboard3.dto.BoardDTO;
import org.astro.aboard3.dto.PageRequestDTO;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface BoardMapper {

    ///////////////////////////////////////////////////////
    List<BoardDTO> getList(PageRequestDTO pageRequestDTO);

    BoardDTO getRead(Integer bno);

    int regist(BoardDTO boardDTO);

    int modify(BoardDTO boardDTO);

    int delete(Integer bno);

    long listCount(PageRequestDTO pageRequestDTO);

    ///////////////////////////////////////////////////////
    int registerImg(List<Map<String, String>> imgList);

    int deleteImg(Integer bno);
}
