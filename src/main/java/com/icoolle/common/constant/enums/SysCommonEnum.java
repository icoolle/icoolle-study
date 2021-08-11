package com.icoolle.common.constant.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import com.icoolle.tool.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static io.vavr.API.*;

/**
 * 通用枚举
 */
@Getter
@AllArgsConstructor
public enum SysCommonEnum {

    /**
     * 通用类型
     */
    a("a"),
    b("b"),
    c("c"),
    d("d"),
    e("e"),
    f("f"),
    g("g"),
    h("h");

    @EnumValue
    @JsonValue
    public final String code;


    public static SysCommonEnum valueOfName(String name) {
        if (StringUtil.isBlank(name)) {
            return null;
        }
        return Match(name).of(
                Case($("a"), () -> a),
                Case($("b"), () -> b),
                Case($("c"), () -> c),
                Case($("d"), () -> d),
                Case($("e"), () -> e),
                Case($("f"), () -> f),
                Case($("g"), () -> g),
                Case($("h"), () -> h)
        );
    }


}
