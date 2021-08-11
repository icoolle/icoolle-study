package com.icoolle.common.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.icoolle.common.core.mapper.IcoolleMapper;
import com.icoolle.common.core.model.BaseBean;
import com.icoolle.common.core.model.BaseDTO;

/**
 * 
 * 基础功能service
 */
public interface IcoolleService<T extends BaseBean, M extends IcoolleMapper<T>> extends IService<T> {


    /**
     * 分页数据获取
     *
     * @param searchParams 分页查询参数
     * @param <O>          返回数据类型
     * @param <P>          参数类型
     * @return
     */
    <O, P extends BaseDTO> IPage<O> listByPage(P searchParams);
}
