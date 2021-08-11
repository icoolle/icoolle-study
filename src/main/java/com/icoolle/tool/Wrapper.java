package com.icoolle.tool;

import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.experimental.Accessors;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static com.icoolle.common.constant.consts.SystemConst.SQL_LAST_LIMIT;


public class Wrapper {

    public static Wrapper m;

    public static List<WrapperObj> wrapperObjList;

    static {
        m = new Wrapper();
        wrapperObjList = Lists.newArrayList();
    }

    public <T> QueryWrapper<T> query(T obj, boolean oneFlag) {
        QueryWrapper<T> wrapper = new QueryWrapper<T>().eq("del_flag", false);
        if (oneFlag) {
            wrapper.last(SQL_LAST_LIMIT);
        }
        Field[] fields = ReflectUtil.getFields(obj.getClass());
        eqFmat(obj, fields, wrapper);
        return wrapper;
    }

    public <T> UpdateWrapper<T> update(T obj) {
        Field[] fields = ReflectUtil.getFields(obj.getClass());
        UpdateWrapper<T> wrapper = new UpdateWrapper<>();
        eqFmat(obj, fields, wrapper);
        return wrapper;
    }

    public Wrapper in(String fieldName, Collection<?> coll) {
        add(fieldName, coll, KeyEnum.IN);
        return m;
    }

    public Wrapper eq(String fieldName, Object o) {
        add(fieldName, 0, KeyEnum.EQ);
        return m;
    }

    public Wrapper notIn(String fieldName, Collection<?> coll) {
        add(fieldName, coll, KeyEnum.NOT_IN);
        return m;
    }

    private void add(String fieldName, Object coll, KeyEnum keyEnum) {
        wrapperObjList.add(new WrapperObj().setName(fieldName).setValue(coll).setKeyEnum(keyEnum));
    }

    private <T, W extends AbstractWrapper> void eqFmat(T obj, Field[] fields, W wrapper) {
        for (Field field : fields) {
            Object value = ReflectUtil.getFieldValue(obj, field);
            if (Objects.nonNull(value)) {
                String name = StringUtil.humpToLine2(field.getName());
                wrapper.eq(name, value);
            }
        }
        wrapperObjList.forEach(s -> {
            switch (s.getKeyEnum()) {
                case EQ:
                    wrapper.eq(s.getName(), s.getValue());
                    break;
                case IN:
                    wrapper.in(s.getName(), s.getValue());
                    break;
                case NOT_IN:
                    wrapper.notIn(s.getName(), s.getValue());
                    break;
                case LIKE:
                    wrapper.like(s.getName(), s.getValue());
                    break;
                case NOT_LIKE:
                    wrapper.notLike(s.getName(), s.getValue());
                    break;
                default:
            }
        });
        wrapperObjList = Lists.newArrayList();
    }

    @Data
    @Accessors(chain = true)
    static class WrapperObj {

        private String name;

        private Object value;

        private KeyEnum keyEnum;

        public String getName() {
            return StringUtil.humpToLine2(this.name);
        }
    }

    protected enum KeyEnum {
        /**
         * 关键链接
         */
        IN, EQ, NOT_IN, LIKE, NOT_LIKE
    }



}
