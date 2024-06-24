package com.myhd.dto;

import lombok.Data;

import java.util.List;

/**
 * @Classname InitRecDTO
 * @Description
 * @Date 2024/4/6 下午6:31
 * @Created by HmEJ
 */
@Data
public class InitRecDTO {
    /* 用户id */
    private Integer userId;
    /* 用户初始化喜爱的领域信息 */
    private String userInitField;
    /* 用户初始化喜爱的技术信息 */
    private List<String> userInitTecList;
}
