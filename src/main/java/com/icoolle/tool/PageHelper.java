package com.icoolle.tool;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.icoolle.common.constant.enums.SortEnum;
import com.icoolle.common.core.model.ColumnSort;
import com.icoolle.common.core.model.PageParam;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import static com.icoolle.common.constant.enums.ArgumentResponseEnum.PAGE_ERROR;
import static com.icoolle.common.constant.enums.ArgumentResponseEnum.TRANSFORM_ERROR;


/**
 * 分页工具
 */
@Slf4j
public class PageHelper {

    public static final AtomicReference<ThreadLocal<Page<?>>> LOCAL_PAGE = new AtomicReference<>(new ThreadLocal<>());

    public PageHelper() {
    }

    protected static void setLocalPage(Page<?> page) {
        LOCAL_PAGE.get().set(page);
    }

    public static Page<?> getLocalPage() {
        return LOCAL_PAGE.get().get();
    }

    public static void startPage(Object params) {
        Page<?> page = getPageFromObject(params);
        setLocalPage(page);
    }

    private static Page<?> getPageFromObject(Object params) {
        PAGE_ERROR.assertNotNull(params);
        int pageNum = 1;
        int pageSize = 10;
        PageParam pageParam = new PageParam();
        try {
            BeanUtil.copyProperties(params, pageParam);
            pageNum = Optional.ofNullable(pageParam.getPageNum()).orElse(1);
            pageSize = Optional.ofNullable(pageParam.getPageSize()).orElse(10);
        } catch (Exception var12) {
            log.error("分页参数转换异常", var12);
            TRANSFORM_ERROR.assertFail(var12);
        }
        Page<?> page = new Page<>(pageNum, pageSize);
        List<OrderItem> orders = new ArrayList<>(16);
        List<ColumnSort> sortBy = pageParam.getSortBy();
        if (null != sortBy && 0 != sortBy.size()) {
            for (ColumnSort columnSort : sortBy) {
                if (StringUtil.isNoneBlank(columnSort.getField())) {
                    String aCase = StringUtil.humpToLine2(columnSort.getField());
                    OrderItem orderItem = new OrderItem();
                    if (null == columnSort.getOrder()) {
                        orderItem.setColumn(aCase);
                    } else {
                        int compare = columnSort.getOrder().compareTo(SortEnum.ASC);
                        if (compare == 0) {
                            orderItem.setColumn(aCase);
                        } else {
                            orderItem.setColumn(aCase);
                            orderItem.setAsc(false);
                        }
                    }
                    orders.add(orderItem);
                }
            }
        }
        page.setOrders(orders);
        return page;
    }
}
