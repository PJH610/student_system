package com.letopo.common.exception;

/**
 * @author jh
 * @version 1.0
 * @date 2020-02-18 10:40
 */
public class BizException extends RuntimeException {

    private int code;

    public BizException(String message) {
        super(message);
    }

    public BizException(String message, int code) {
        super(message);
    }

    public int getCode() {
        return code;
    }
}
