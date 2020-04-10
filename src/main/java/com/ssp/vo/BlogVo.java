package com.ssp.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class BlogVo {

    public String title;
    private Long typeId;
    private boolean recommend;

}
