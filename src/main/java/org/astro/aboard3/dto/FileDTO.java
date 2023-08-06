package org.astro.aboard3.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FileDTO {

    /////////////////////////////////////////////
    private String uuid;
    private String fileName;
    private boolean img;

    /////////////////////////////////////////////
    public String getLink() {

        if(img) {
            return "s_" + uuid + "_" + fileName;
        } else {
            return "default.jpg";
        }
    }
    
}
