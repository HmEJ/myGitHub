package com.myhd.dto;

import lombok.Data;

/**
 * Description: LoginFormDto 用于注册时的表单数据
 * <br></br>
 * className: LoginFormDto
 * <br></br>
 * packageName: com.myhd.dto
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/10/22 17:00
 */
@Data
public class RegisterFormDTO {
    private String phone;
    private String userEmail;
    private String password;
}
