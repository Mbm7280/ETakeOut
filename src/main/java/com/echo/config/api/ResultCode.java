package com.echo.config.api;

public enum ResultCode implements IErrorCode {

    SUCCESS(200, "操作成功"),

    FAILED(500, "操作失败"),

    VALIDATE_FAILED(404, "参数检验失败"),

    UNAUTHORIZED(401, "暂未登录或token已经过期"),

    FORBIDDEN(403, "没有相关权限"),

    PLEASE_APPORTION_PERMISSIONS(405, "请先分配权限"),

    RETURN_VALUE_IS_NULL(990, "返回值为空，请稍后重试"),


    THE_USER_HAS_REGISTERED(1001, "该用户已注册！"),


    THE_RESOURCE_CATEGORY_QUERY_FAILED(1002, "获取所有资源分类失败，请稍后重试！"),

    THE_RESOURCE_CATEGORY_ADD_FAILED(1003, "添加资源分类失败，请稍后重试！"),


    THE_RESOURCE_ADD_FAILED(1004, "添加资源失败，请稍后重试！"),

    THE_RESOURCE_DEL_FAILED(1005, "删除资源失败，请稍后重试！"),

    THE_RESOURCE_QUERY_FAILED(1006, "查询资源失败，请稍后重试！"),


    THE_MENU_QUERY_FAILED(1007, "查询菜单失败，请稍后重试！"),

    THE_MENU_DELETE_FAILED(1008, "删除菜单失败，请稍后重试！"),

    THE_ROLE_QUERY_FAILED(1009, "查询角色失败，请稍后重试！"),

    THE_ROLE_UPDATE_FAILED(1010, "更新角色失败，请稍后重试！"),


    THE_AUTHORIZED_FAILED(1011, "验证失败，请先登录！"),

    THE_USER_IS_NOT_EXIST(1012, "用户不存在，请重新输入！"),

    THE_OLD_PASSWORD_IS_WRONG(1013, "旧密码错误，请检查后输入！"),

    THE_PASSWORD_UPDATE_FAILED(1014, "旧密码修改失败，请稍后重试！"),

    THE_ROLE_DELETE_FAILED(1015, "删除角色失败，请稍后重试！"),


    THE_MENU_HIDDEN_FAILED(1016, "隐藏菜单失败，请稍后重试！"),


    THE_RESOURCE_UPDATE_FAILED(1017, "修改资源失败，请稍后重试！"),

    THE_USER_QUERY_FAILED(1018, "查询用户失败，请稍后重试！"),

    THE_USER_DELETE_FAILED(1019, "删除用户失败，请稍后重试！"),

    THE_ADDRESS_ADD_FAILED(1020, "新增地址失败，请稍后重试！"),

    THE_ADDRESS_QUERY_FAILED(1021, "查询地址失败，请稍后重试！"),

    THE_ADDRESS_UPDATE_FAILED(1022, "修改地址失败，请稍后重试！"),

    THE_CATEGORY_ADD_FAILED(1023, "新增分类失败，请稍后重试！"),

    THE_CATEGORY_QUERY_FAILED(1024, "获取分类失败，请稍后重试！"),

    THE_CATEGORY_DELETE_FAILED(1025, "删除分类失败，请稍后重试！"),

    THE_CATEGORY_UPDATE_FAILED(1026, "修改分类失败，请稍后重试！"),

    THE_DISH_ADD_FAILED(1027, "新增菜品失败，请稍后重试！"),

    THE_DISH_QUERY_FAILED(1028, "获取菜品失败，请稍后重试！"),


    THE_USERNAME_HAS_EXISTED(1029, "该用户名已存在，请更换重试！"),


    ;

    private long code;
    private String message;

    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
