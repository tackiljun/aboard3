package org.astro.aboard3.mappers;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.astro.aboard3.dto.BoardDTO;
import org.astro.aboard3.dto.PageRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;


@SpringBootTest
@Log4j2
public class BoardMapperTests {

    /////////////////////////////////////////////////////////////////////
    @Autowired
    BoardMapper boardMapper;

    /////////////////////////////////////////////////////////////////////
    @Test
    public void testGetList() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();

        List<BoardDTO> result = boardMapper.getList(pageRequestDTO);

        log.info(result);
    }

    /////////////////////////////////////////////////////////////////////

    @Test
    @Commit
    @Transactional
    public void testRegist() {
        
        BoardDTO dto = new BoardDTO();

        dto.setTitle("테스트제목");
        dto.setContent("테스트내용");
        dto.setWriter("테스트작성자");
        dto.setFileNames(List.of(UUID.randomUUID() + "_t1.jpg", UUID.randomUUID() + "_t2.jpg"));

        List<String> fileNames = dto.getFileNames();

        int count = boardMapper.regist(dto);

        log.info("INSERT PRODUCT COUNT: " + count);

        Integer bno = dto.getBno();

        log.info("bno: " + bno);

        AtomicInteger index = new AtomicInteger();

        List<Map<String, String>> list = fileNames.stream().map(str -> {
            
            String uuid = str.substring(0, 36);
            String fileName = str.substring(37);

            log.info(uuid);
            log.info(fileName);

            return Map.of("uuid", uuid, "fileName", fileName, "bno", "" + bno, "ord", "" + index.getAndIncrement());
        }).collect(Collectors.toList());

        log.info(list);

        int countImgs = boardMapper.registerImg(list);

        log.info("countImgs: " + countImgs);

    }
}
