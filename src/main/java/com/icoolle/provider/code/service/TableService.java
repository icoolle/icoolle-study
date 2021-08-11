package com.icoolle.provider.code.service;



import com.baomidou.mybatisplus.core.metadata.IPage;
import com.icoolle.model.code.dto.TableDTO;
import com.icoolle.model.code.dto.TableDesignSaveDTO;
import com.icoolle.model.code.po.Table;
import com.icoolle.model.code.po.TableInfo;

import java.util.List;


public interface TableService {

    /**
     * 分页查询数据
     *
     * @param tableDTO
     * @return
     */
    IPage<Table> list(TableDTO tableDTO);


    /**
     * 获取表列信息
     *
     * @param tableName
     * @return
     */
    List<TableInfo> findTableInfo(String tableName);

    /**
     * 生成代码信息
     *
     * @param tableDesignSaveDTO
     */
    void saveTableInfo(TableDesignSaveDTO tableDesignSaveDTO);
}
