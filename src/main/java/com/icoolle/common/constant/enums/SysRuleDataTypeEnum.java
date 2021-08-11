package com.icoolle.common.constant.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.icoolle.common.constant.consts.SystemConst.*;


/**
 * 编码
 */
@Getter
@AllArgsConstructor
public enum SysRuleDataTypeEnum {

    /**
     * 商品编号
     */
    PRODUCT_CODE(1, "商品编号", INIT_NUM),

    TACTICS_CODE(2, "策略编号", INIT_NUM1),

    PROVIDER_CODE(3, "供应商编号", INIT_NUM1),

    SHOP_CODE(4, "门店编号", INIT_NUM),

    COMPANY_CODE(5, "公司编号", INIT_NUM),

    EMPLOYEE_CODE(6, "员工工号", INIT_NUM2),

    SEATO_CODE(7, "部门编号", INIT_NUM),

    INCOME_CODE(8, "收支项编号", INIT_NUM),

    UNIT_CODE(9, "单元编号", INIT_NUM2),

    PROPERTY_CODE(10, "属性编号", INIT_NUM2),

    BRAND_CODE(11, "品牌编号", INIT_NUM2),

    LOGISTICS_CODE(12, "物流公司编码", INIT_NUM2),

    WAREHOUSE_CODE(13, "仓库编码", INIT_NUM1),

    PURCHASE_CODE(14, "采购编码", INIT_NUM),

    ORDER_CODE(15, "订单编号", INIT_NUM),

    SUBJECT_CODE(16, "科目编号", INIT_NUM1),

    MACHINE_CODE(17, "机型编号", INIT_NUM1),

    MACHINE_GROUP_CODE(18, "机型组编号", INIT_NUM1),

    BILL_AR_CODE(19, "应收单编号", INIT_NUM),

    BILL_AP_CODE(20, "应付单编号", INIT_NUM),

    RECEIVABLES_CODE(21, "收款单编号", INIT_NUM),

    PAYMENT_CODE(22, "付款单编号", INIT_NUM),

    RETURN_PURCHASE_CODE(23, "采购退货编码", INIT_NUM),

    BANK_CONTINUAL_CODE(24, "银行卡流水单编号", INIT_NUM),

    RECEIPT_NOTICE_CODE(25, "入库通知单编号", INIT_NUM),

    TRANSFER_ORDER_CODE(26, "调货通知单编号", INIT_NUM),

    WAREHOUSE_NOTICE_OUT_CODE(27, "出库通知编号", INIT_NUM),

    FUND_TRANSFER_CODE(28, "资金调拨编号", INIT_NUM),

    ORDER_RETURN_CODE(29, "销售退货", INIT_NUM),

    TASK_CODE(30, "任务单号", INIT_NUM),

    SHELVES_CODE(31, "上架单号", INIT_NUM),

    STOCK_TAK_CODE(32, "盘点单号", INIT_NUM),

    TRANSFER_CODE(33, "移库单号", INIT_NUM),

    WAREHOUSE_OUT_CODE(34, "出库编号", INIT_NUM),

    STORE_KEEPER_CODE(35, "库工编号", INIT_NUM1),

    CONTAINER_CODE(36, "容器单号", INIT_NUM),

    OFFER_CODE(37, "报价单号", INIT_NUM),

    BUDGET_CODE(38, "费用单号", INIT_NUM),

    PROCESS_CODE(39, "加工单号", INIT_NUM),

    STOCK_TAKE_PLAN(40, "盘点计划单号", INIT_NUM),

    STOCK_TAKE_IN(41, "盘入单号", INIT_NUM),

    APPLY_ALLOCATION_CODE(42, "请货单号", INIT_NUM),

    DAILY_STATISTICS(43, "日结营收", INIT_NUM),

    DAILY_PAY(44, "日结支付", INIT_NUM),

    VR_CODE(45, "对账单号", INIT_NUM),

    DISTRIBUTION_DIFF_CODE(46, "差异单号", INIT_NUM),

    ADJUST_CODE(47, "差异调整单号", INIT_NUM),

    DISTRIBUTION_RECEIPT_CODE(48, "配送收货单号", INIT_NUM),

    DAILY_EIM(49, "日结统计", INIT_NUM),

    BILL_CODE(50, "出库通知编号", INIT_NUM),

    REPORT_CODE(51, "报表编号", INIT_NUM),

    ;

    @EnumValue
    @JsonValue
    private final int code;

    private final String label;

    private final String startCode;
}
