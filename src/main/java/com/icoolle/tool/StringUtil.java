package com.icoolle.tool;


import ch.qos.logback.core.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.icoolle.common.constant.consts.SystemConst.DF_TIME_ONE;
import static com.icoolle.common.constant.enums.CommonResponseEnum.NUMBER_FORMAT_ERROR;


@Slf4j
public class StringUtil extends StringUtils {

    private static Pattern linePattern = Pattern.compile("_(\\w)");

    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    public static String addOne(String testStr) {
        String[] strs = testStr.split("[^0-9]");
        String numStr = strs[strs.length - 1];
        if (numStr != null && numStr.length() > 0) {
            int n = numStr.length();
            int num = Integer.parseInt(numStr) + 1;
            String added = String.valueOf(num);
            n = Math.min(n, added.length());
            return testStr.subSequence(0, testStr.length() - n) + added;
        } else {
            log.error("数字转换异常", new NumberFormatException());
            NUMBER_FORMAT_ERROR.assertFail();
        }
        return testStr;
    }

    /**
     * 下划线转驼峰
     *
     * @param str
     * @return
     */
    public static String lineToHump(String str) {
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 驼峰转下划线(简单写法，效率低于{@link #humpToLine2(String)})
     *
     * @param str
     * @return
     */
    public static String humpToLine(String str) {
        int index = str.indexOf("_");
        if (-1 == index) {
            return str.replaceAll("[A-Z]", "_$0").toLowerCase();
        }
        return str;
    }

    /**
     * 驼峰转下划线,效率比上面高
     *
     * @param str
     * @return
     */
    public static String humpToLine2(String str) {
        int index = str.indexOf("_");
        if (-1 == index) {
            Matcher matcher = humpPattern.matcher(str);
            StringBuffer sb = new StringBuffer();
            while (matcher.find()) {
                matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
            }
            matcher.appendTail(sb);
            return sb.toString();
        }
        return str;
    }

    /**
     * 把一个字符串的第一个字母大写、效率是最高的、
     *
     * @param filedName
     * @return
     */
    public static String getMethodName(String filedName) {
        byte[] items = filedName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }

    /**
     * 把一个字符串的第一个字母小写、效率是最高的、
     *
     * @param filedName 字符串
     * @return
     */
    public static String getAttrName(String filedName) {
        byte[] items = filedName.getBytes();
        items[0] = (byte) ((char) items[0] - 'A' + 'a');
        return new String(items);
    }

    /**
     * 字符串参数转换成map
     *
     * @param str 字符串参数
     * @return map
     */
    public static Map<String, String> str2Map(String str) {
        Map<String, String> result = new HashMap<>();
        String[] results = str.split("&");
        if (results.length > 0) {
            for (int var = 0; var < results.length; ++var) {
                String pair = results[var];
                String[] kv = pair.split("=", 2);
                if (2 == kv.length) {
                    result.put(kv[0], kv[1]);
                }
            }
        }
        return result;
    }


}
