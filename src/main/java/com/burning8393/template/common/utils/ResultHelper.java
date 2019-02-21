package com.burning8393.template.common.utils;

import com.burning8393.template.common.enums.CodeEnums;
import com.burning8393.template.model.ResultWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * ResultHelper class
 *
 * @author : Pangxw
 * @date : 2019/2/21 14:42
 * @description :
 */
public class ResultHelper {

    /**
     * @param codeEnums --返回枚举
     * @param obj------数据源
     * @return
     */
    public static ResultWrapper result(CodeEnums codeEnums, Object obj) {
        return new ResultWrapper(codeEnums.getCode(), codeEnums.getMsg(), obj);
    }

    /**
     * @param codeEnums--返回码
     * @return
     */
    public static ResultWrapper result(CodeEnums codeEnums) {
        return new ResultWrapper(codeEnums.getCode(), codeEnums.getMsg());
    }

    /**
     * 成功返回数据
     * @param obj
     * @return
     */
    public static ResultWrapper success(Object obj) {
        return result(CodeEnums.SUCCESS,obj);
    }

    /**
     * 成功修改数据
     * @param num
     * @return
     */
    public static ResultWrapper takeEffect(Integer num) {
        Map<String, Object> data = new HashMap<>();
        Boolean flag = false;
        if (num > 0) {
            flag = true;
        }
        data.put("isChanged", flag);
        data.put("rows", num);
        return result(CodeEnums.SUCCESS,data);
    }

    /**
     * 请求失败
     * @return
     */
    public static ResultWrapper failed() {
        return result(CodeEnums.FAILED);
    }

}
