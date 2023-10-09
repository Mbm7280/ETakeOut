package com.echo.common;


/****************************************************
 * 创建人：@author ECHO
 * 创建时间: 2023/10/9 23:00
 * 项目名称: {EBlog}
 * 文件名称: ApiException
 * 文件描述: [Description]: 自定义API异常
 * version：1.0
 * All rights Reserved, Designed By ECHO
 *
 ********************************************************/
public class ApiException extends RuntimeException {
    private IErrorCode errorCode;

    public ApiException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }
}
