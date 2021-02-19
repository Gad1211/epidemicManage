package com.gad.epidemicmanage.pojo.vo;

import com.gad.epidemicmanage.common.GlobalConstant;
import lombok.Data;

/**
 * @Description 通用返回结果
 */
@Data
public class Result {


    /**
     * 返回代码
     */
    private Integer code;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 返回数据
     */
    private Object data;

    /**
     * 快捷构造函数
     *
     * @param isSuccess 返回是否成功true或false
     * @param message   返回消息
     */
    public Result(Boolean isSuccess, String message) {
        if (isSuccess) {
            this.code = GlobalConstant.REQUEST_SUCCESS_STATUS_CODE;
        } else {
            this.code = GlobalConstant.REQUEST_ERROR_STATUS_CODE;
        }
        this.message = message;
    }

    /**
     * 全参构造函数
     *
     * @param code    返回状态码
     * @param message 返回消息
     * @param data    返回数据
     */
    public Result(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

}
