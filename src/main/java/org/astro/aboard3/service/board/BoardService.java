package org.astro.aboard3.service.board;

import org.astro.aboard3.dto.BoardDTO;
import org.astro.aboard3.dto.PageRequestDTO;
import org.astro.aboard3.dto.PageResponseDTO;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface BoardService {

    /////////////////////////////////////////////////////////////////
    PageResponseDTO<BoardDTO> getList(PageRequestDTO pageRequestDTO);

    BoardDTO getRead(Integer bno);

    void regist(BoardDTO boardDTO);

    void modify(BoardDTO boardDTO);

    void delete(Integer bno);
}
