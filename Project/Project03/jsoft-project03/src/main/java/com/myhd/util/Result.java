package com.myhd.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description: Result
 * <br></br>
 * className: Result
 * <br></br>
 * packageName: com.myhd.util
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/10/22 10:39
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Integer code;
    private Object object;
    private String msg;

    public static Result ok(String msg){
        return new Result(Code.OK, true, msg);
    }

    public static Result ok(Integer code, Object object, String msg){
        return new Result(code, object, msg);
    }

    public static Result fail(String msg){
        return new Result(Code.FAIL, false, msg);
    }

    public static Result fail(Integer code, Object object, String msg){
        return new Result(code, object, msg);
    }
}
