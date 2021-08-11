package com.icoolle.common.core.model;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 分页数据统一返回结果实体
 */
@Data
@ApiModel("分页数据统一返回结果实体")
@Accessors(chain = true)
public class ResultPage<R> implements Serializable {

    @ApiModelProperty(value = "分页数据", position = 5)
    private List<R> dataList;

    @ApiModelProperty(value = "总记录数", position = 1)
    private Long total;

    @ApiModelProperty(value = "总页数", position = 2)
    private Long totalPage;

    @ApiModelProperty(value = "当前记录数", position = 4)
    private Long pageSize;

    @ApiModelProperty(value = "当前页数", position = 3)
    private Long currentPage;

    @ApiModelProperty(value = "额外数据")
    private Object data;

    public ResultPage(List<R> dataList, Long total, Long totalPage, Long pageSize, Long currentPage) {
        this.dataList = dataList;
        this.total = total;
        this.totalPage = totalPage;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
    }

    /**
     * 分页工具
     *
     * @param iPage
     * @param <T>
     * @return
     */
    public static <T> ResultPage<T> startPage(IPage<T> iPage) {
        return new ResultPage<>(iPage.getRecords(), iPage.getTotal(), iPage.getPages(), iPage.getSize(), iPage.getCurrent());
    }

    /**
     * 分页工具
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> ResultPage<T> startPage(List<T> list) {
        //分页结果
        PageInfo<T> pageInfo = new PageInfo<>(list);
        return new ResultPage<>(pageInfo.getList(), pageInfo.getTotal(), Integer.valueOf(pageInfo.getPages()).longValue(),
                Integer.valueOf(pageInfo.getSize()).longValue(), Integer.valueOf(pageInfo.getPageNum()).longValue());
    }

    /**
     * 条件过滤分页
     *
     * @param page
     * @param mapper
     * @param <E>
     */
    public <E> ResultPage(IPage<E> page, Function<E, R> mapper) {
        this.setCurrentPage(page.getCurrent());
        this.setPageSize(page.getSize());
        this.setTotal(page.getTotal());
        this.setTotalPage(page.getPages());
        if (CollUtil.isEmpty(page.getRecords())) {
            this.setDataList(Collections.emptyList());
        } else {
            this.setDataList(page.getRecords().stream().map(mapper).collect(Collectors.toList()));
        }
    }

    public <E> ResultPage(IPage<E> page, List<R> records) {
        this.dataList = records;
        this.setCurrentPage(page.getCurrent());
        this.setPageSize(page.getSize());
        this.setTotal(page.getTotal());
        this.setTotalPage(page.getPages());
    }
}
