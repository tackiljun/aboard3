package org.astro.aboard3.service.board;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.astro.aboard3.dto.BoardDTO;
import org.astro.aboard3.dto.PageRequestDTO;
import org.astro.aboard3.dto.PageResponseDTO;
import org.astro.aboard3.mappers.BoardMapper;
import org.astro.aboard3.mappers.FileMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {

    ////////////////////////////////////////////////////////////////////////////////
    // 의존성 주입.
    private final BoardMapper boardMapper;
    private final FileMapper fileMapper;

    ////////////////////////////////////////////////////////////////////////////////
    @Override
    public PageResponseDTO<BoardDTO> getList(PageRequestDTO pageRequestDTO) {
        
        List<BoardDTO> list = boardMapper.getList(pageRequestDTO);

        long total = boardMapper.listCount(pageRequestDTO);

        return PageResponseDTO.<BoardDTO>withAll().list(list).total(total).build();
    }

    ////////////////////////////////////////////////////////////////////////////////
    @Override
    public BoardDTO getRead(Integer bno) {
        
        return boardMapper.getRead(bno);
    }

    ////////////////////////////////////////////////////////////////////////////////
    @Override
    public void regist(BoardDTO boardDTO) {
        
        int count = boardMapper.regist(boardDTO);

        // 파일명 list로 담기.
        List<String> fileNames = boardDTO.getFileNames();

        // 게시판에서 등록성송, 파일이 등록되면 실행.
        if(count > 0 
        && boardDTO.getFileNames() != null 
        && boardDTO.getFileNames().isEmpty() == false) {

            // bno 가져오기.
            Integer bno = boardDTO.getBno();

            AtomicInteger index = new AtomicInteger();

            // 등록된 파일의 fileNames에서 추출.
            List<Map<String, String>> list = fileNames.stream().map(str -> {
                // uuid 가져오기.
                String uuid = str.substring(0, 36);
                // 실제 파일명 가져오기.
                String fileName = str.substring(37);

                // return map에 담기.
                return Map.of(
                    "uuid", uuid, 
                    "fileName", fileName, 
                    "bno", "" + bno, 
                    "ord", "" + index.getAndIncrement());
                }).collect(Collectors.toList());

            // 파일 등록 실행.
            boardMapper.registerImg(list);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////
    @Override
    public void modify(BoardDTO boardDTO) {
        
        // 수정 업데이트.
        int count = boardMapper.modify(boardDTO);

        // 기존파일 삭제.
        boardMapper.deleteImg(boardDTO.getBno());

        // 파일명 List로 가져오기.
        List<String> fileNames = boardDTO.getFileNames();

        // 게시판 등록성공과 파일이 등록되면 실행.
        if(count > 0) {
            //bno 가져오기.
            Integer bno = boardDTO.getBno();

            AtomicInteger index = new AtomicInteger();

            // 등록된 파일 fileNames에서 추출.
            List<Map<String, String>> list = fileNames.stream().map(str -> {

                log.info("str: " + str);

                // uuid 가져오기.
                String uuid = str.substring(0, 36);
                // 실제 파일명 가져오기.
                String fileName = str.substring(37);

                // return map에 담기.
                return Map.of(
                    "uuid", uuid, 
                    "fileName", fileName, 
                    "bno", "" + bno, 
                    "ord", "" + index.getAndIncrement());
            }).collect(Collectors.toList());

            // 파일등록 실행.
            boardMapper.registerImg(list);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////
    @Override
    public void delete(Integer bno) {
        
        boardMapper.delete(bno);
        boardMapper.deleteImg(bno);
    }
    
}
