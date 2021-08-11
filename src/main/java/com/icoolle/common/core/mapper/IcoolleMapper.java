package com.icoolle.common.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.icoolle.common.core.model.BaseBean;
import com.icoolle.common.core.model.BaseDTO;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @description: 基础功能mapper
 * @projectName: ColisEssMapper
 * @package: com.colis.ess.common.core.mapper
 * @version: V1.0.0
 */
public interface IcoolleMapper<T extends BaseBean> extends BaseMapper<T> {


    /**
     * 公共分页获取数据
     *
     * @param page         分页参数
     * @param searchParams 查询数据条件参数
     * @return
     */
    <P extends BaseDTO, O> IPage<O> listByPage(Page<?> page, @Param("param") P searchParams);

    /**
     * 根据id查询详情信息
     *
     * @param id  主表id
     * @param <E> 返回值类型
     * @return 返回数据
     */
    <E> E findInfDetailById(String id);
}
