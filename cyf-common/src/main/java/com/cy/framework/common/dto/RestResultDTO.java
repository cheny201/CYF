package com.cy.framework.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Data
public class RestResultDTO<T> implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(RestResultDTO.class);
    public static final Integer RESULT_SUCCESS = 0;
    public static final Integer RESULT_FAIL = 1;
    /**
     * 返回调用结果，0-成功，1-失败
     */
    private Integer result;
    /**
     * 返回前台的信息
     */
    private String msg;
    /**
     * 返回的结果数据
     */
    private T data;
    /**
     * 异常信息
     */
    private String exception;
    /**
     * 时间戳
     */
    @JsonFormat(
            shape = JsonFormat.Shape.NUMBER
    )
    private Date timestamp = new Date();

    public RestResultDTO(Integer result, String msg, T data, String exception) {
        if (StringUtils.isBlank(msg)) {
            if (Objects.equals(result, RESULT_SUCCESS)) {
                msg = "操作成功";
            } else {
                msg = "操作失败";
            }
        }

        this.result = result;
        this.msg = msg;
        this.data = data;
        this.exception = exception;
    }

    public static <T> RestResultDTO<T> newSuccess() {
        return new RestResultDTO(RESULT_SUCCESS, (String)null, (Object)null, (String)null);
    }

    public static <T> RestResultDTO<T> newSuccess(T data) {
        return new RestResultDTO(RESULT_SUCCESS, (String)null, data, (String)null);
    }

    public static <T> RestResultDTO<T> newSuccess(T data, String msg) {
        return new RestResultDTO(RESULT_SUCCESS, msg, data, (String)null);
    }

    public static <T> RestResultDTO<T> newFail(String msg) {
        return new RestResultDTO(RESULT_FAIL, msg, (Object)null, (String)null);
    }

    public static <T> RestResultDTO<T> newFail(String msg, String exception) {
        return new RestResultDTO(RESULT_FAIL, msg, (Object)null, exception);
    }

    @JsonFormat(
            shape = JsonFormat.Shape.NUMBER
    )
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

}
