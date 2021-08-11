package com.icoolle.common.constant.consts;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public interface SystemConst {

    /**
     * 生产环境
     */
    String ENV_PROD = "prod";
    /**
     * 本地环境
     */
    String ENV_LOCAL = "local";

    /**
     * 系统树形数据顶级ID值
     */
    Long TREEPID = 0L;

    /**
     * 系统空数据
     */
    Object NULL_DATA = new Object();

    /**
     * 分页参数
     */
    Page<?> PAGE = new Page<>();


    /**
     * 时间格式化
     */
    String DF_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    /**
     * 时间格式化
     */
    String DF_DATE_TIME1 = "yyyyMMddHHmmss";

    /**
     * 时间格式化
     */
    String DF_DATE = "yyyy-MM-dd";

    /**
     * 时间格式化
     */
    String DF_TIME = "HH:mm:ss";
    /**
     * 时间格式化
     */
    String DF_TIME_ONE = "HHmmss";

    /**
     * 时间
     */
    String LOCAL_DATE_TIME = "LocalDateTime";

    /**
     * 时间
     */
    String BIG_DECIMAL = "BigDecimal";

    /**
     * 查询一条
     */
    String SQL_LAST_LIMIT = "limit 1";

    /**
     * 机构初始code
     */
    String TEN = "10";

    String INIT_NUM = "1001";
    String INIT_NUM1 = "100001";
    String INIT_NUM2 = "1000001";
    /**
     * sku 长度
     */
    int SKU_LEN = 14;

    String A = "a";
    String B = "b";
    String C = "c";
    String D = "d";
    String E = "e";
    String F = "f";
    String G = "g";
    String H = "h";
}
