package com.burning8393.template.common.exception;

/**
 * CustomAsyncRequestTimeoutException class
 *
 * @author : Pangxw
 * @date : 2019/2/27 11:10
 * @description :
 */
public class CustomAsyncRequestTimeoutException extends RuntimeException {
    private static final long serialVersionUID = 5493268435511705295L;

    public CustomAsyncRequestTimeoutException(String uri) {
        super(uri);
    }
}
