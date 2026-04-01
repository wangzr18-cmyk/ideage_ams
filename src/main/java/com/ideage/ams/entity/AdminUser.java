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
public class AdminUser implements Serializable {

    private Long id;

    private String userName;

    private String displayName;

    private String password;

    private String iconUrl;

    private String email;

    private String userToken;

    private String seriesId;

    private int isSkj;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    private String role;

    @Override
    public String toString() {
        return "AdminUser{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", userToken='" + userToken + '\'' +
                ", IsSkj=" + isSkj +
                ", createTime=" + createTime +
                '}';
    }

}
