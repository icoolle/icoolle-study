package com.icoolle.common.constant.enums;


import com.icoolle.common.core.model.Result;
import com.icoolle.component.assertion.CommonExceptionAssert;
import com.icoolle.component.exception.BaseException;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @description: 返回错误结果信息提示
 * 业务级异常，使用 1-001-000 段
 * <p>
 * 一共 7 位，分成三段
 * <p>
 * 第一段，1 位，类型。
 * 1 - 业务级别异常
 * 2 - 系统级别异常
 * <p>
 * 第二段，3 位，模块。
 * 一般建议，每个系统里面，可能有多个模块，可以再去做分段。
 * <p>
 * 001 - http 模块
 * 001 - OAuth2 模块
 * 002 - User 模块
 * 003 - product 模块
 * <p>
 * 第三段，3 位，错误码。
 * 一般建议，每个模块自增。
 * 001 - 订单支付失败
 */
@Getter
@AllArgsConstructor
public enum CommonResponseEnum implements CommonExceptionAssert {

    //**************** 系统正式通用返回用户信息 ****************
    /**
     * 成功
     */
    SUCCESS(200, "SUCCESS"),
    /**
     * 服务器繁忙，请稍后重试
     */
    SERVER_BUSY(500, "服务器繁忙"),
    /**
     * 服务器异常，无法识别的异常，尽可能对通过判断减少未定义异常抛出
     */
    SERVER_ERROR(501, "网络异常"),

    LOGIN_ERROR(999, "用户未登录"),

    //**************** 2-001- 系统通用异常 ****************

    RESPONSE_OUT_ERROR(2001502, "返回结果输出数据错误"),
    INSERT_OUT_ERROR(2001503, "数据保存出错，请检查数据或SQL语句是否正确"),
    UPDATE_OUT_ERROR(2001504, "数据修改出错，请检查数据或SQL语句是否正确"),
    DELETE_NOT_FUND_ERROR(2001520, "未找到需要修改或删除的数据，请检查主键ID数据是否存在"),
    DATA_NOT_FUND_ERROR(2001521, "未找到需要修改数据，请检查参数是否正确或数据是否存在"),
    NULL_POINTER_ERROR(2001505, "空指针异常，请检查接口程序"),
    DESERIALIZE_ERROR(2001506, "请求参数反序列化失败，请检查参数是否正确，具体错误请查看'data'或系统日志"),
    SQL_STATEMENT_NULL_ERROR(2001507, "空指针异常==> sql 语句未找到，请检查接口程序"),
    DUPLICATE_KEY_ERROR(2001508, "表中设置唯一索引，字段添加重复了，请检查接口程序"),
    MYBATIS_RESULT_ERROR(2001509, "mybatis 查询映射错误，请检查接口xml配置文件语句是否正确"),
    TABLE_INFO_NOT_FIND_ERROR(2001510, "未找到实体对应===>error: can not execute. because can not find cache of TableInfo for entity!"),
    TABLE_COLUMN_NOT_FIND_ERROR(2001511, "未找到字段对应===>error: can not execute. because can not find column for id from entity!"),
    DATA_ALREADY_EXISTS(2001512, "数据已经存在，请勿重复添加"),
    IMG_UPLOAD_ERROR(2001513, "文件上传失败，请检查程序"),
    REPEATED_SUBMIT_ERROR(2001514, "请勿重复提交"),
    CURRENT_USER_ERROR(2001515, "获取当前用户信息失败！"),
    NAME_NONNULL_ERROR(2001516, "名称字段不能为空！"),
    DATA_EXISTS_CORRELATION_NOT_DELETED(2001517, "当前已存在数据关联不能随意删除此记录，请清除关联再行删除"),
    AUDIT_REPEAT_ERROR(2001518, "单据已审核或不存在，请勿重复操作审核"),
    DATA_PHONE_ALREADY_EXISTS(2001519, "号码数据已经存在，请勿重复添加"),
    DATA_NOT_FUND_EXISTS_ERROR(2001520, "数据不存在，请检查参数或程序是否正确"),
    DATA_TO_PLAY_NOT_ERROR(2001521, "状态启用中，数据不可删除"),
    DATA_AMOUNT_TO_LONG_NOT_ERROR(2001522, "金额不为零不可禁用"),
    ORDER_FILED_NOT_FOUND(2001523, "未知字段不支持排序，请检查字段是否存在"),
    //**************** 1-001-000 系统及OAuth2安全模块 ****************
    //**************** 1-002-000 用户相关模块 ****************
    ADMIN_USER_NOT_EXIST(1002001, "用户不存在"),
    PASSWORD_ERROR(1002002, "密码错误"),
    ADMIN_USER_LIMIT(1002003, "用户被禁用"),
    ADMIN_USER_EXIST(1002004, "用户名已存在"),
    ROLE_EXIST(1002005, "角色名已存在"),

    //**************** 1-999-000 工具相关模块 ****************
    DATE_NOT_NULL(1999701, "日期不能为空"),
    DATETIME_NOT_NULL(1999702, "时间不能为空"),
    TIME_NOT_NULL(1999703, "时间不能为空"),
    DATE_PATTERN_MISMATCH(1999704, "日期[%s]与格式[%s]不匹配，无法解析"),
    PATTERN_NOT_NULL(1999705, "日期格式不能为空"),
    PATTERN_INVALID(1999706, "日期格式[%s]无法识别"),
    NUMBER_FORMAT_ERROR(1999401, "string 工具加一功能数字转换异常"),
    COMMON_CODE_ERROR(1999300, "获取编码出错了"),
    GET_ORGAN_SORT_ERROR(1999301, "获取机构排序出错了"),
    GET_COORDINATE_ERROR(1999200, "解析经纬度地址出错"),

    ;

    /**
     * 返回码
     */
    private int code;
    /**
     * 返回消息
     */
    private String message;

    /**
     * 校验返回结果是否成功
     *
     * @param response 远程调用的响应
     */
    public static void assertSuccess(Result response) {
        SERVER_ERROR.assertNotNull(response);
        int code = response.getCode();
        if (CommonResponseEnum.SUCCESS.getCode() != code) {
            String msg = response.getMessage();
            throw new BaseException(code, msg);
        }
    }
}
