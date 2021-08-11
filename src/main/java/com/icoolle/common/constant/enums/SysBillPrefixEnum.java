package com.icoolle.common.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 系统单据前缀
 */
@Getter
@AllArgsConstructor
public enum SysBillPrefixEnum {

    /**
     * 系统单据前缀
     */
    应收("AR"),

    应付("AP"),

    银行卡流水("BC"),

    收款("RA"),

    付款("PA"),

    调货单("TO"),

    资金调拨("ZJDB"),

    销售("SO"),

    出库通知单("DO"),

    出库编号("CK"),

    销售退货("SR"),

    任务单号("RW"),

    库工("KG"),

    上架单号("SJ"),

    盘点单号("CC"),

    盘点计划单号("SP"),

    盘入单号("SI"),

    移库单号("ML"),

    采购("PE"),

    采购退货("PR"),

    入库通知单("IN"),

    容器单号("RQ"),

    策略编号("TS"),

    报价("BJ"),

    加工单("BM"),

    费用("FY"),

    请货单号("RQ"),

    对账单号("VR"),

    日结营收("DY"),

    日结支付("DP"),

    日结统计("DE"),

    差异单号("DA"),

    差异调整单号("DF"),

    配送收货单号("DR"),

    配送单("DL"),

    ;

    private final String code;
}
