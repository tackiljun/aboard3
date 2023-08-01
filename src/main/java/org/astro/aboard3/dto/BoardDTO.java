package org.astro.aboard3.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BoardDTO {
    
    ////////////////////////////////
    private Integer bno;

    private String title;

    private String content;

    private String writer;

    private String dueDate;

    private String fileName;

    ////////////////////////////////
    // 파일리스트.
    private List<String> fileNames;
}
