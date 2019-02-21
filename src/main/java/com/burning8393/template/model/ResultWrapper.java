package com.burning8393.template.model;

import lombok.Getter;
import lombok.Setter;

/**
 * ResultWrapper class
 *
 * @author : Pangxw
 * @date : 2019/2/21 14:44
 * @description :
 */
public class ResultWrapper {
    /**
     * 返回码
     */
    @Getter
    @Setter
    private Integer code;

    /**
     * 返回信息
     */
    @Getter
    @Setter
    private String msg;

    /**
     * 结果集
     */
    private Object data;

    public ResultWrapper(Integer code, String msg) {
        this(code, msg, null);
    }

    public ResultWrapper(Integer code, String msg, Object result) {
        this.code = code;
        this.msg = msg;
        this.data = result;
    }
}
