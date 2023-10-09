package com.echo.common;

/****************************************************
 * 创建人：@author ECHO
 * 创建时间: 2023/10/9 22:59
 * 项目名称: {EBlog}
 * 文件名称: IErrorCode
 * 文件描述: [Description]: API错误码
 * version：1.0
 * All rights Reserved, Designed By ECHO
 *
 ********************************************************/
public interface IErrorCode {

    long getCode();

    String getMessage();
    
}
