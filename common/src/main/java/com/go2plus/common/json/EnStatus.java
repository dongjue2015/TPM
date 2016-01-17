package com.go2plus.common.json;

/**
 * Copyright (C) 2015 GO2.CN. All rights reserved. This computer program source
 * code file is protected by copyright law and international treaties.
 * Unauthorized distribution of source code files, programs, or portion of the
 * package, may result in severe civil and criminal penalties, and will be
 * prosecuted to the maximum extent under the law.
 * @author liyang
 * @since 2015-12-07
 */
public enum EnStatus {
    INVALID(-1),
    HTTP_NONE(0),

    HTTP_OK(200),

    HTTP_MOVED_PERMANENTLY(301), // 永久重定向
    HTTP_FOUND(302), // 临时重定向

    HTTP_BAD_REQUEST(400), //参数错误


    // 参数错误
    HTTP_UNAUTHORIZED(401), // 身份验证失败，无token信息
    HTTP_FORBIDDEN(403), // 无权限
    HTTP_NOT_FOUND(404),
    HTTP_METHOD_NOT_ALLOWED(405), // 资源被禁止，例如：Http Method 不正确

    HTTP_INTERNAL_SERVER_ERROR(500); // 程序代码抛出异常

    // 定义私有变量
    private int val;

    // 构造函数，枚举类型只能为私有
    private EnStatus(int val) {
        this.val = val;
    }

    public int value() {
        return this.val;
    }

    @Override
    public String toString() {
        return String.valueOf(this.val);
    }
}
