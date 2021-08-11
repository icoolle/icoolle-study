package com.icoolle.common.core.model;


import com.icoolle.common.constant.enums.CommonResponseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

import static com.icoolle.common.constant.consts.SystemConst.NULL_DATA;

/**
 * 自定义统一返回数据
 */
@Data
@Accessors(chain = true)
@ApiModel("自定义统一返回数据实体")
public class Result<R> implements Serializable {

    @ApiModelProperty(value = "返回码", position = 1)
    private int code;

    @ApiModelProperty(value = "返回消息", position = 2)
    private String message;

    @ApiModelProperty(value = "数据", position = 3)
    private R data;

    /**
     * <p>功能说明:自定义成功返回数据 </p>
     */
    public Result() {
        this(CommonResponseEnum.SUCCESS);
    }

    /**
     * <p>功能说明:自定义返回数据 </p>
     *
     * @param code    [返回状态码]
     * @param message [返回信息]
     * @param data    [返回的数据]
     * @return Result
     */
    public Result(Integer code, String message, R data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * <p>功能说明:自定义返回数据 </p>
     *
     * @param commonResponseEnum [错误信息枚举值]
     * @return Result
     */
    public Result(CommonResponseEnum commonResponseEnum) {
        this(commonResponseEnum.getCode(), commonResponseEnum.getMessage(), (R) NULL_DATA);
    }

    /**
     * <p>功能说明:自定义返回数据 </p>
     *
     * @param commonResponseEnum [错误参数枚举值]
     * @param data               [返回信息]
     * @return Result
     */
    public Result(CommonResponseEnum commonResponseEnum, R data) {
        this.code = commonResponseEnum.getCode();
        this.message = commonResponseEnum.getMessage();
        this.data = data;
    }

    /**
     * 成功 不返回数据直接返回成功信息
     *
     * @return
     */
    public static Result<Object> success() {
        return new Result<>(CommonResponseEnum.SUCCESS, NULL_DATA);
    }

    /**
     * 成功 并且加上返回数据
     *
     * @param data
     * @return ResponseBean
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(CommonResponseEnum.SUCCESS, data);
    }

    /**
     * 单返回失败的状态码
     *
     * @return ResponseBean
     */
    public static Result<Object> failure() {
        return new Result<>(CommonResponseEnum.SERVER_BUSY, NULL_DATA);
    }

    /**
     * 返回失败的状态码和数据
     *
     * @param data
     * @return ResponseBean
     */
    public static <T> Result<T> failure(T data) {
        return new Result<T>(CommonResponseEnum.SERVER_BUSY, data);
    }
}
