package com.icoolle.common.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.icoolle.common.core.mapper.IcoolleMapper;
import com.icoolle.common.core.model.BaseBean;
import com.icoolle.common.core.model.BaseDTO;
import com.icoolle.common.core.service.IcoolleService;
import com.icoolle.tool.CurrentUserTool;
import com.icoolle.tool.RedisServiceTool;
import com.icoolle.tool.SysCodeRuleTool;
import com.icoolle.tool.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.io.Serializable;
import java.math.BigDecimal;

import static com.icoolle.common.constant.consts.BeanTableFieldConst.*;
import static com.icoolle.common.constant.consts.SystemConst.PAGE;
import static com.icoolle.common.constant.consts.SystemConst.SQL_LAST_LIMIT;
import static com.icoolle.common.constant.enums.CommonResponseEnum.*;


/**
 * 
 * 基础功能service实现类
 */
@SuppressWarnings("unchecked")
public class IcoolleServiceImpl<T extends BaseBean, M extends IcoolleMapper<T>> extends ServiceImpl<M, T> implements IcoolleService<T, M> {

    @Autowired
    protected M mapper;

    @Resource
    protected CurrentUserTool currentUserTool;

    @Resource
    protected SysCodeRuleTool sysCodeRuleTool;

    @Resource
    protected RedisServiceTool redisServiceTool;


    protected final BigDecimal zero = BigDecimal.ZERO;

    @Override
    public M getBaseMapper() {
        return mapper;
    }

    @Override
    public <O, P extends BaseDTO> IPage<O> listByPage(P searchParams) {
        return mapper.listByPage(PAGE, searchParams);
    }


    public void generalInsert(T bean) {
        INSERT_OUT_ERROR.assertIsTrue(this.mapper.insert(bean) == 1);
    }

    public void generalUpdate(T bean) {
        UPDATE_OUT_ERROR.assertIsTrue(this.mapper.updateById(bean) == 1);
    }

    /**
     * 通用移除
     *
     * @param id
     * @return
     */
    public void generalRemove(Serializable id) {
        DELETE_NOT_FUND_ERROR.assertIsTrue(this.mapper.deleteById(id) == 1);
    }

    /**
     * 通用查询一条数据
     *
     * @param t
     * @return
     */
    protected T selectOne(T t) {
        return this.mapper.selectOne(Wrapper.m.query(t, true));
    }



    /**
     * 查询名字是否重复
     */
    public void nameExist(String column, String name, Long id) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<T>() {{
            eq(column, name);
            eq(DELFLAG, false);
            last(SQL_LAST_LIMIT);
        }};
        if (null != id) {
            queryWrapper.notIn(ID, id);
        }
        T t = mapper.selectOne(queryWrapper);
        DATA_ALREADY_EXISTS.assertIsNull(t);
    }

    @Override
    protected Class<T> currentModelClass() {
        return (Class<T>) ReflectionKit.getSuperClassGenericType(getClass(), 0);
    }

    /**
     * 如果整形为null,则赋值为0
     *
     * @param value
     * @return
     */
    public Integer convertNotNULL(Integer value) {
        Integer result = value == null ? 0 : value;
        return result;
    }

    /**
     * 如果为null,则赋值为0
     *
     * @param value
     * @return
     */
    public BigDecimal convertDecimalNotNULL(BigDecimal value) {
        BigDecimal result = value == null ? BigDecimal.ZERO : value;
        return result;
    }

    /**
     * 判断是否存在数据(支持多字段)
     * @param id
     * @param column 列字段
     * @param name 列数据
     */
    public void exist(Long id,String column, String name) {
        if(StringUtils.isBlank(column) || StringUtils.isBlank(name)){
            return;
        }
        QueryWrapper<T> queryWrapper = new QueryWrapper<T>() {{
            eq(DELFLAG, false);
            last(SQL_LAST_LIMIT);
        }};
        String[] columnStr = column.split(",");
        String[] nameStr = name.split(",");
        for(int i=0;i<columnStr.length;i++){
            queryWrapper.eq(columnStr[i], nameStr[i]);
        }
        if (null != id) {
            queryWrapper.notIn(ID, id);
        }
        T t = mapper.selectOne(queryWrapper);
        DATA_ALREADY_EXISTS.assertIsNull(t);
    }

}
