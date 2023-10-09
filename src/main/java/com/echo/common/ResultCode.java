package com.echo.common;

/****************************************************
 * 创建人：@author ECHO
 * 项目名称: {EBlog}
 * 文件名称: ResultCode
 * 文件描述: [Description]: API操作码
 * version：1.0
 * All rights Reserved, Designed By ECHO
 *
 ********************************************************/
public enum ResultCode implements IErrorCode {
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限"),

    PLEASE_APPORTION_PERMISSIONS(405, "请先分配权限"),

    RETURN_VALUE_IS_NULL(990, "返回值为空，请稍后重试"),

    PARAM_STRING_IS_NULL(991, "该字符串参数为空"),

    PARAM_OBJECT_IS_NULL(992, "该对象参数为空"),

    PARAM_JSON_ARRAY(993, "该字符串有误，无法转换为JSON数组"),


    PARAM_JSON_OBJECT(994, "该字符串有误，无法转换为JSON对象"),


    PARAM_VALID_INT(995, "该字符串有误，不是合法的数字串"),


    PARAM_VALID_LONG(996, "该字符串有误，不是合法的数字串"),

    THE_USER_HAS_EXISTED(997, "该用户名已存在，请更换"),

    THE_USER_ACCOUNT_OR_PWD_HAS_ERROR(997, "该用户账户名或者密码输入错误，请先仔细核查"),

    THE_USER_NOT_HAVE_RESOURCES(998, "该用户名无资源权限，请先分配"),

    REQUEST_PARAM_ILLEGAL(999, "提交参数不合法"),

    NO_SUCH_USER(1000, "找不到该用户"),

    THE_OLD_PWD_ERROR(1001, "旧密码输入错误"),

    THE_USER_REGISTER_FAILED(1002, "用户注册失败，请联系管理员"),


    THE_DATA_ALREADY_EXIST(1003, "该数据已存在，请修改后提交"),


    THE_DATA_IS_NOT_EXIST(1004, "该数据不存在，请确认后提交"),

    THE_DATA_ALREADY_DELETED(1005, "该数据已删除，请勿重复操作"),

    THE_PWD_IS_WRONG(1006, "密码错误，请勿重复操作"),

    THE_JOB_IS_IN_THIS_STATUS(1007, "任务已经处于此状态"),

    THE_ARTICLE_TAGS_NEED_TO_CHECK(1008, "文章标签需要检查"),

    THE_ARTICLE_CATEGORY_NEED_TO_CHECK(1009, "文章分类需要检查"),

    THE_ADDRESS_SAVE_FAILED(1010, "地址保存失败"),

    THE_DEFAULT_ADDRESS_UPDATE_FAILED(1010, "默认地址设置失败"),


    ;

    private long code;
    private String message;

    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
