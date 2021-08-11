package com.icoolle.common.constant.enums;


import com.icoolle.component.assertion.CommonExceptionAssert;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 参数校验异常返回结果
 */
@Getter
@AllArgsConstructor
public enum ArgumentResponseEnum implements CommonExceptionAssert {

    //**************** 2-002- 参数通用异常 ****************

    /**
     * 绑定参数校验异常
     */
    VALID_ERROR(2002006, "参数校验异常"),

    PAGE_ERROR(2002007, "无法获取分页查询参数"),

    TRANSFORM_ERROR(2002008, "分页参数转换异常"),

    /**
     * 导出功能业务名称错误
     */
    EXCEL_KEY_NAME_ERROR(2002009, "导出功能业务名称不可为空值"),

    /**
     * 导出功能业务参数错误
     */
    EXCEL_ARGUMENT_TRANSFORM_ERROR(2002010, "参数不正确，请检查参数"),

    REQUEST_PARAM_EMPTY_ERROR(2002011, "请求参数为空值,请检查参数"),
    DATA_INTEGRITY_VIOLATION_ERROR(2002012, "参数数据不完整导致数据保存或修改出错，请检查参数"),
    IMG_TYPE_NOT_FUND_ERROR(2002013, "图片上传检测到文件中包含其他类型文件，请上传图片类型文件"),

    ;

    /**
     * 返回码
     */
    private int code;
    /**
     * 返回消息
     */
    private String message;
}
