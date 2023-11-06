package com.myhd.dto;

import lombok.Data;

/**
 * Description: ToEmail
 * <br></br>
 * className: ToEmail
 * <br></br>
 * packageName: com.myhd.dto
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/10/25 21:48
 */
@Data
public class ToEmail {
    private String toUser;
    private String subject;
    private String content;
}
