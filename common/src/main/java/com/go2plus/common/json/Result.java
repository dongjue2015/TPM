package com.go2plus.common.json;



import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Copyright (C) 2015 GO2.CN. All rights reserved. This computer program source
 * code file is protected by copyright law and international treaties.
 * Unauthorized distribution of source code files, programs, or portion of the
 * package, may result in severe civil and criminal penalties, and will be
 * prosecuted to the maximum extent under the law.
 * 
 * json对象模型，一般用于json返回
 * 
 * @author liyang
 * @since 2015-12-07
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Result<T>
{

    //应用状态码
    private int status;
    //错误描述
    private String message;
    //数据
    private T data;
    private Integer code;

    public Result() {
        this(EnStatus.HTTP_OK);
    }


	public Result(EnStatus status) {
        this(status, null,null);
    }

    public Result(EnStatus status, String message, Integer code) {
        this.status = status.value();
        this.message = message;
        this.code = code;
    }

    public Result(T data) {
        this.status = EnStatus.HTTP_OK.value();
        this.data = data;
    }

    public Result(T data, Integer status, String msg) {
        this.status = status;
        this.data = data;
        this.message = msg;
        this.code = null;
    }

    public Result(T data, Integer status, String msg, Integer code) {
        this.status = status;
        this.data = data;
        this.message = msg;
        this.code = code;
    }

    public Result(Integer status, String msg, Integer code) {
        this.status = status;
        this.data = null;
        this.message = msg;
        this.code = code;
    }

    public Result(T b, String s) {

        this.data = b;
        this.message = s;
        this.status = EnStatus.HTTP_OK.value();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

	public void setCode(Integer code) {
        this.code = code;
    }

    public boolean success() {
        return this.getStatus() == EnStatus.HTTP_OK.value() && this.data != null;
    }

}
