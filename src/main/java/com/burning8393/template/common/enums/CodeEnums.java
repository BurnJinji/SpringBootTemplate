package com.burning8393.template.common.enums;

/**
 * CodeEnums class
 *
 * @author : Pangxw
 * @date : 2019/2/21 14:45
 * @description :
 */
public enum  CodeEnums {
    /******************系统级别状态码***************/

    SUCCESS(0,"请求成功"),

    FAILED(-1,"请求失败"),

    PARAMS_ERROR(90002, "参数错误"),
    ;


    /**	提示码 */
    private int code;
    /**	提示信息 */
    private String msg;

    CodeEnums(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
