package com.icoolle.tool;

import com.icoolle.common.constant.enums.SysBillPrefixEnum;
import com.icoolle.common.constant.enums.SysRuleDataTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.icoolle.common.constant.enums.SysBillPrefixEnum.*;
import static com.icoolle.common.constant.enums.SysRuleDataTypeEnum.*;
import static com.icoolle.tool.TimeUtil.format;


/**
 * 系统编码规则生成工具
 */
@Slf4j
@Component
public class SysCodeRuleTool {



    /**
     * 当前环境
     */
    @Value("${spring.profiles.active}")
    private String profile;

    /**
     * 库区正品默认编号
     */
    public static final String qualityReservoirAreaCode = "Z0000000001";

    /**
     * 库区次品默认编号
     */
    public static final String remnantReservoirAreaCode = "Z0000000002";

    /**
     * 巷道正品默认编号
     */
    public static final String qualityTunnelCode = "R0000000001";

    /**
     * 巷道次品默认编号
     */
    public static final String remnantTunnelCode = "R0000000002";

    /**
     * 库位正品默认编号
     */
    public static final String qualityStockLocationCode = "S0000000001";

    /**
     * 库位次品默认编号
     */
    public static final String remnantStockLocationCode = "S0000000002";

    /**
     * 获取公司编号 1000
     *
     * @return
     */
    public String getCompanyCode() {
        return this.getBaseAutoCode(COMPANY_CODE);
    }

    /**
     * 获取供应商编号 100000
     *
     * @return
     */
    public String getSupplierCode() {
        return this.getBaseAutoCode(PROVIDER_CODE);
    }

    /**
     * 获取报表编号 100000
     *
     * @return
     */
    public String getReportCode() {
        return this.getBaseAutoCode(REPORT_CODE);
    }

    /**
     * 获取单元编号 1000000
     *
     * @return
     */
    public String getUnitCode() {
        return this.getBaseAutoCode(UNIT_CODE);
    }

    /**
     * 获取属性编号
     *
     * @return
     */
    public String getPropertyCode() {
        return this.getBaseAutoCode(PROPERTY_CODE);
    }

    /**
     * 获取品牌编号
     *
     * @return
     */
    public String getBrandCode() {
        return this.getBaseAutoCode(BRAND_CODE);
    }

    /**
     * 获取物流编号
     *
     * @return
     */

    public String getLogisticsCode() {
        return this.getBaseAutoCode(LOGISTICS_CODE);
    }

    /**
     * 用户编码
     *
     * @return
     */
    public String getAdminUserCode() {
        return this.getBaseAutoCode(EMPLOYEE_CODE);
    }

    /**
     * 获取仓库编号
     *
     * @return
     */
    public String getWarehouseCode() {
        return this.getBaseAutoCode(WAREHOUSE_CODE);
    }

    /**
     * 获取科目编码
     *
     * @return
     */
    public String getSubjectCode() {
        return this.getBaseAutoCode(SUBJECT_CODE);
    }

    /**
     * 获取机型编号
     *
     * @return
     */
    public String getMachineCode() {
        return this.getBaseAutoCode(MACHINE_CODE);
    }

    /**
     * 获取机型编号
     *
     * @return
     */
    public String getMachineGroupCode() {
        return this.getBaseAutoCode(MACHINE_GROUP_CODE);
    }

    // ================================================================================================================================

    /**
     * 策略编号
     *
     * @return
     */
    public String getTacticsCode() {
        return this.getBindAutoCode(策略编号, TACTICS_CODE);
    }

    /**
     * 获取采购订单编码  日期时间 + 自增长（每天更新）
     *
     * @return
     */
    public String getPurchaseCode() {
        return this.getBindAutoCode(采购, PURCHASE_CODE);
    }

    /**
     * 获取采购退货编码
     *
     * @return
     */
    public String getReturnPurchaseCode() {
        return getBindAutoCode(采购退货, RETURN_PURCHASE_CODE);
    }

    /**
     * 获取入库通知单编号
     *
     * @return
     */
    public String getReceiptNoticeCode() {
        return this.getBindAutoCode(入库通知单, RECEIPT_NOTICE_CODE);
    }

    /**
     * 获取配送收货单编号
     *
     * @return
     */
    public String getDistributionNoticeCode() {
        return this.getBindAutoCode(配送收货单号, DISTRIBUTION_RECEIPT_CODE);
    }

    /**
     * 获取应收款编号(RA+年月日时分秒+四位编码)
     *
     * @return
     */
    public String getReceivablesCode() {
        return this.getBindAutoCode(收款, RECEIVABLES_CODE);
    }

    /**
     * 获取对账单编号(AR+年月日+四位编码)
     *
     * @return
     */
    public String getBillVerifyCode() {
        return this.getBindAutoCode(对账单号, VR_CODE);
    }


    /**
     * 获取应收单编号(AR+年月日+四位编码)
     *
     * @return
     */
    public String getBillArCode() {
        return this.getBindAutoCode(应收, BILL_AR_CODE);
    }

    /**
     * 获取应付款编号(PA+年月日时分秒+四位编码)
     *
     * @return
     */
    public String getPaymentCode() {
        return this.getBindAutoCode(付款, PAYMENT_CODE);
    }

    /**
     * 获取销售编码
     *
     * @return
     */
    public String getOrderCode() {
        return this.getBindAutoCode(销售, ORDER_CODE);
    }

    /**
     * 销售退货
     *
     * @return
     */
    public String getOrderReturnCode() {
        return this.getBindAutoCode(销售退货, ORDER_RETURN_CODE);
    }

    /**
     * 出库通知单
     *
     * @return
     */
    public String getWarehouseOutNoticeCode() {
        return this.getBindAutoCode(出库通知单, WAREHOUSE_NOTICE_OUT_CODE);
    }

    /**
     * 出库单
     *
     * @return
     */
    public String getWarehouseOutCode() {
        return this.getBindAutoCode(出库编号, WAREHOUSE_OUT_CODE);
    }


    /**
     * 获取应付单编号(AP+年月日+四位编码)
     *
     * @return
     */
    public String getBillApCode() {
        return this.getBindAutoCode(应付, BILL_AP_CODE);
    }

    /**
     * 获取银行卡流水单编号(BC-20200801-1000)
     *
     * @return
     */
    public String getContinualCode() {
        return this.getBindAutoCode(银行卡流水, BANK_CONTINUAL_CODE);
    }

    public String getTransferOrderCode() {
        return this.getBindAutoCode(调货单, TRANSFER_ORDER_CODE);
    }

    public String getProcessCode() {
        return this.getBindAutoCode(加工单, PROCESS_CODE);
    }

    public String getFundTransferOrderCode() {
        return this.getBindAutoCode(资金调拨, FUND_TRANSFER_CODE);
    }

    /**
     * 获取任务单编号
     *
     * @return 单号
     */
    public String getTaskCode() {
        return this.getBindAutoCode(任务单号, TASK_CODE);
    }

    /**
     * 库工编号
     *
     * @return
     */
    public String getJobCode() {
        return 库工.getCode() + getStringCode(STORE_KEEPER_CODE, false);
    }

    /**
     * 获取上架单编号
     *
     * @return 单号
     */
    public String getShelevesCode() {
        return this.getBindAutoCode(上架单号, SHELVES_CODE);
    }

    /**
     * 获取盘点单编号
     *
     * @return 单号
     */
    public String getStockTakCode() {
        return this.getBindAutoCode(盘点单号, STOCK_TAK_CODE);
    }

    /**
     * 获取请货单编号
     *
     * @return 单号
     */
    public String getApplyAllocationCode() {
        return this.getBindAutoCode(请货单号, APPLY_ALLOCATION_CODE);
    }

    /**
     * 获取差异单编号
     *
     * @return 单号
     */
    public String getDistributionDiffCode() {
        return this.getBindAutoCode(差异单号, DISTRIBUTION_DIFF_CODE);
    }

    /**
     * 获取差异调整单编号
     *
     * @return 单号
     */
    public String getAdjustCode() {
        return this.getBindAutoCode(差异调整单号, ADJUST_CODE);
    }

    public String getStockTakPlanCode() {
        return this.getBindAutoCode(盘点计划单号, STOCK_TAKE_PLAN);
    }

    public String getStockTakPlanInCode() {
        return this.getBindAutoCode(盘入单号, STOCK_TAKE_IN);
    }

    public String getDailyStatisticsCode() {
        return this.getBindAutoCode(日结营收, DAILY_STATISTICS);
    }

    public String getDailyPayCode() {
        return this.getBindAutoCode(日结支付, DAILY_PAY);
    }

    public String getDailyEimCode() {
        return this.getBindAutoCode(日结统计, DAILY_EIM);
    }

    /**
     * 获取移库单编号
     *
     * @return 单号
     */
    public String getTransferCode() {
        return this.getBindAutoCode(移库单号, TRANSFER_CODE);
    }

    /**
     * 获取容器号编号 RQ+年月日+六位编号
     *
     * @return 单号
     */
    public String getContainerCode() {
        return this.getBindAutoCode(容器单号, CONTAINER_CODE);
    }


    /**
     * 获取基础code
     *
     * @param typeEnum 枚举类型
     * @return 结果
     */
    public String getBaseAutoCode(SysRuleDataTypeEnum typeEnum) {
        return getStringCode(typeEnum, false);
    }

    /**
     * 获取报价编码
     *
     * @return
     */
    public String getOfferCode() {
        return this.getBindAutoCode(报价, OFFER_CODE);
    }

    /**
     * 获取费用编号
     *
     * @return
     */
    public String getBudgetCode() {
        return this.getBindAutoCode(费用, BUDGET_CODE);
    }

    /**
     * 配送单
     *
     * @return
     */
    public String getbillCode() {
        return this.getbillCode(配送单, BILL_CODE);
    }

    /**
     * 获取基础code
     *
     * @param typeEnum 枚举类型
     * @param b
     * @return 结果
     */
    public String getBaseAutoCode(SysRuleDataTypeEnum typeEnum, boolean b) {
        return getStringCode(typeEnum, b);
    }

    private synchronized String getStringCode(SysRuleDataTypeEnum typeEnum, boolean b) {
        return "10001";
//        String name = getName(typeEnum::name);
//        DataRule dataRule = b ? this.dataRuleMapper.selectByTwoDataName(name) : this.dataRuleMapper.selectByDataName(name);
//        return Match(dataRule).of(
//                Case($(isNotNull()), v -> {
//                    update(v.setDataValue(add(v.getDataValue())));
//                    return v;
//                }),
//                Case($(Objects::isNull), v -> {
//                    v = new DataRule() {{
//                        setDataName(name);
//                        setDataType(typeEnum);
//                        if ("test".equals(profile)) {
//                            setDataValue("1" + typeEnum.getStartCode());
//                        } else {
//                            setDataValue(typeEnum.getStartCode());
//                        }
//                        setEnable(b);
//                    }};
//                    insert(v);
//                    return v;
//                })
//        ).getDataValue();
    }


    private String getBindAutoCode(SysBillPrefixEnum prefixEnum, SysRuleDataTypeEnum typeEnum) {
        return prefixEnum.getCode() + format(LocalDate.now(), "yyyyMMdd") + getBaseAutoCode(typeEnum, true);
    }

    private String getbillCode(SysBillPrefixEnum prefixEnum, SysRuleDataTypeEnum typeEnum) {
        return prefixEnum.getCode() + format(LocalDate.now(), "yyyyMMdd") + getBaseAutoCode(typeEnum, true);
    }





    public String getName(Supplier<String> nameFunc) {
        return nameFunc.get();
    }

    public String add(String str) {
        Function<String, String> func = StringUtil::addOne;
        return func.apply(str);
    }

    public void update(String code, SysRuleDataTypeEnum typeEnum, boolean b) {

    }
}
