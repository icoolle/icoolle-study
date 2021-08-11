package com.icoolle.provider.code.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.icoolle.annotation.TransactionalService;
import com.icoolle.model.code.dto.TableDTO;
import com.icoolle.model.code.dto.TableDesignSaveDTO;
import com.icoolle.model.code.po.Table;
import com.icoolle.model.code.po.TableInfo;
import com.icoolle.provider.code.mapper.TableMapper;
import com.icoolle.provider.code.service.TableService;
import com.icoolle.tool.GeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.icoolle.common.constant.consts.SystemConst.PAGE;


@TransactionalService
public class TableServiceImpl implements TableService {

    @Autowired
    private TableMapper tableMapper;

    @Override
    public IPage<Table> list(TableDTO tableDTO) {
        return tableMapper.list(PAGE, tableDTO);
    }

    @Override
    public List<TableInfo> findTableInfo(String tableName) {
        return tableMapper.findTableInfo(tableName);
    }

    @Override
    public void saveTableInfo(TableDesignSaveDTO tableDesignSaveDTO) {
        GeneratorUtil.generatorCode(tableDesignSaveDTO);
    }
}
