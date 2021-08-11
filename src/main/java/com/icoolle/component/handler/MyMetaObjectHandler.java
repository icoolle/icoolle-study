package com.icoolle.component.handler;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.icoolle.tool.JwtTokenProviderTool;
import com.icoolle.tool.RequestHolderUtil;
import com.icoolle.tool.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

import static com.icoolle.common.constant.consts.BeanFieldConst.*;
import static com.icoolle.common.constant.consts.JwtTokenConst.TOKEN_HEADER;


@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        // 设置创建信息
        setFieldVal(metaObject, CREATEDBY, CREATEDTIME);
        // 设置更新信息
        setFieldVal(metaObject, UPDATEDBY, UPDATEDTIME);
        if (metaObject.hasSetter(DELFLAG)) {
            this.setFieldValByName(DELFLAG, false, metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 设置更新信息
        setFieldVal(metaObject, UPDATEDBY, UPDATEDTIME);
    }

    /**
     * 设置字段数据
     *
     * @param metaObject
     * @param by
     * @param time
     */
    private void setFieldVal(MetaObject metaObject, String by, String time) {
        // 设置操作人字段
        if (metaObject.hasSetter(by)) {
            // 获取已经设置的字段值
            Object byObj = getFieldValByName(by, metaObject);
            if (ObjectUtil.isEmpty(byObj)) {
                // 数据值为空则设置默认值
                String token = "";
                String userId;
                HttpServletRequest request = RequestHolderUtil.getHttpServletRequest();
                if (null != request) {
                    try {
                        token = request.getHeader(TOKEN_HEADER);
                    } catch (Exception e) {
                        log.error("获取请求头出错！", e);
                    }
                }
                if (StringUtil.isBlank(token)) {
                    userId = "1";
                } else {
                    try {
                        userId = JwtTokenProviderTool.getUserId(token);
                        if (StringUtil.isBlank(userId)) {
                            userId = "1";
                        }
                    } catch (Exception e) {
                        log.error("获取出错！", e);
                        userId = "1";
                    }
                }
                this.setFieldValByName(by, Long.valueOf(userId), metaObject);
            }
        }
        // 设置时间字段
        if (metaObject.hasSetter(time)) {
            // 获取已经设置的字段值
            Object dataObj = getFieldValByName(time, metaObject);
            if (ObjectUtil.isEmpty(dataObj)) {
                // 数据值为空则设置默认值
                this.setFieldValByName(time, LocalDateTime.now(), metaObject);
            }
        }
    }

}
