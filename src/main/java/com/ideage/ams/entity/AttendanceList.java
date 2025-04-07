package com.ideage.ams.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;


@Setter
@Getter
@Data
public class AttendanceList implements Serializable {

    private Integer id;

    private String articleTitle;

    private String articleContent;

    private String addName;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date updateTime;
}
