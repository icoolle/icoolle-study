package com.icoolle.common.core.controller;

import cn.hutool.core.util.ReflectUtil;
import com.icoolle.common.constant.consts.MethodConst;
import com.icoolle.common.constant.enums.SortEnum;
import com.icoolle.common.core.model.BaseDTO;
import com.icoolle.common.core.model.ColumnSort;
import com.icoolle.common.core.model.ResultPage;
import com.icoolle.common.core.service.IcoolleService;
import com.icoolle.tool.CurrentUserTool;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.List;

import static com.icoolle.common.constant.consts.BeanTableFieldConst.CREATEDTIME;
import static com.icoolle.common.constant.enums.CommonResponseEnum.ORDER_FILED_NOT_FOUND;


/**
 * 基础功能接口管理
 */
@Slf4j
public class IcoolleController<S extends IcoolleService> {

    @Resource
    protected CurrentUserTool currentUserTool;


    @SuppressWarnings("all")
    @Autowired
    public S service;

    @ApiOperation(value = "通用自定义分页查询数据", httpMethod = MethodConst.POST, position = 2)
    @PostMapping("general_list")
    public <P extends BaseDTO, E> ResultPage<E> generalList(@RequestBody P searchParams) {
        return ResultPage.startPage(service.listByPage(searchParams));
    }


    public Pair<Long, Long> getCategoryId(List<Long> categoryIds) {
        if (ObjectUtils.isNotEmpty(categoryIds)) {
            if (categoryIds.size() == 2) {
                return Pair.of(categoryIds.get(0), categoryIds.get(1));
            }
            if (categoryIds.size() > 0) {
                return Pair.of(categoryIds.get(0), null);
            }
        }
        return Pair.of(null, null);
    }

    protected <P extends BaseDTO> void getDefaultSort(P ps, String fieldName) {
        List<ColumnSort> sortBy = ps.getSortBy();
        if (0 == sortBy.size()) {
            sortBy.add(new ColumnSort() {{
                setField(fieldName).setOrder(SortEnum.DESC);
            }});
        }
    }



    protected void validaParams(List<ColumnSort> columnSortList, Class<?> clazz) {
        if (0 != columnSortList.size()) {
            String field = columnSortList.get(0).getField();
            if (!CREATEDTIME.equals(field)) {
                ORDER_FILED_NOT_FOUND.assertNotNull(ReflectUtil.getField(clazz, field));
            }
        }
    }
}
