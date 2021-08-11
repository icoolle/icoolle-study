package com.icoolle.component.filter;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icoolle.tool.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
public class TrimReqParamWrapper extends HttpServletRequestWrapper {


    public TrimReqParamWrapper(HttpServletRequest request) {
        super(request);
    }


    /**
     * 重写getInputStream方法
     */
    @Override
    public ServletInputStream getInputStream() throws IOException {
        //非json类型，直接返回
        if (!super.getHeader(HttpHeaders.CONTENT_TYPE).equalsIgnoreCase(MediaType.APPLICATION_JSON_VALUE)) {
            return super.getInputStream();
        }
        //从输入流中取出body串, 如果为空，直接返回
        String reqBodyStr = IOUtils.toString(super.getInputStream(), "utf-8");
        if (StringUtils.isEmpty(reqBodyStr)) {
            return super.getInputStream();
        }
        //reqBodyStr转为Map对象
        Map<String, Object> paramMap = new ObjectMapper().readValue(reqBodyStr, new TypeReference<HashMap<String, Object>>() {
        });
        //去首尾空格
        Map<String, Object> trimedMap = recursiveTrim(paramMap);
        //重新构造一个输入流对象
        byte[] bytes = JSON.toJSONString(trimedMap).getBytes(StandardCharsets.UTF_8);
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        return new MyServletInputStream(bis);
    }

    /**
     * 只处理String, List, Map
     *
     * @param param
     * @return
     */
    @SuppressWarnings("unchecked")
    private Map<String, Object> recursiveTrim(Map<String, Object> param) {
        for (String key : param.keySet()) {
            Object vo = param.get(key);
            if (null == vo) {
                //key对应的值为空
                continue;
            }
            if (vo instanceof String) {
                String s = vo.toString().trim();
                param.put(key, StringUtil.trimToNull(s));
            } else if (vo instanceof Map) {
                param.put(key, recursiveTrim((Map<String, Object>) vo));
            } else if (vo instanceof List) {
                //key对应的值为List类型
                List<Object> keyList = (List<Object>) vo;
                for (int i = 0; i < keyList.size(); i++) {
                    //遍历list
                    Object vol = keyList.get(i);
                    if (vol instanceof String) {
                        //list里的元素为String, 去空格
                        String s = vol.toString();
                        keyList.set(i, StringUtil.trimToNull(s));
                    } else if (vol instanceof Map) {
                        //list里的元素为Map, 递归处理
                        keyList.set(i, recursiveTrim((Map<String, Object>) vol));
                    }
                }
                param.put(key, vo);
            }
        }
        return param;

    }


    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

}

