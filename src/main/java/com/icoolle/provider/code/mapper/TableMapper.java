package com.icoolle.provider.code.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.icoolle.model.code.dto.TableDTO;
import com.icoolle.model.code.po.Table;
import com.icoolle.model.code.po.TableInfo;

import java.util.List;


public interface TableMapper {

    /**
     * 分页获取数据
     *
     * @param tableDTO
     * @return
     */
    IPage<Table> list(Page<?> page, TableDTO tableDTO);


    /**
     * 通过名称获取表信息
     *
     * @param tableName
     * @return
     */
    Table findTable(String tableName);


    /**
     * 通过名称获取表字段的信息
     *
     * @param tableName
     * @return
     */
    List<TableInfo> findTableInfo(String tableName);
}
