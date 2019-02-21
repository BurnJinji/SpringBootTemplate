package com.burning8393.template.common.handler;

import com.burning8393.template.common.enums.CodeEnums;
import com.burning8393.template.common.utils.ResultHelper;
import com.burning8393.template.model.ParamValidationResult;
import com.burning8393.template.model.ResultWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CommonExceptionHandler class
 *
 * @author : Pangxw
 * @date : 2019/2/21 14:20
 * @description :
 */
@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResultWrapper methodArgumentNotValidHandler(MethodArgumentNotValidException e){
        return ResultHelper.result(CodeEnums.PARAMS_ERROR, fillValidationResult(e.getBindingResult().getFieldErrors()));
    }

    @ExceptionHandler(BindException.class)
    public ResultWrapper handleBindException(BindException e) {
        return ResultHelper.result(CodeEnums.PARAMS_ERROR, fillValidationResult(e.getBindingResult().getFieldErrors()));
    }

    @ExceptionHandler(Exception.class)
    public Map<String, Object> exceptionHandler(Exception e) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", "123");
        result.put("msg", e.getMessage());
        return result;
    }

    private List<ParamValidationResult> fillValidationResult(List<FieldError> fieldErrors) {
        List<ParamValidationResult> paramValidationResults = new ArrayList<>();
        //解析原错误信息，封装后返回，此处返回非法的字段名称，错误信息
        for (FieldError error : fieldErrors) {
            ParamValidationResult validationResult = new ParamValidationResult();
            validationResult.setMessage(error.getDefaultMessage());
            validationResult.setParam(error.getField());
            paramValidationResults.add(validationResult);
        }
        return paramValidationResults;
    }
}
