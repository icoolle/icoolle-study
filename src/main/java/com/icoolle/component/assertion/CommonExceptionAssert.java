package com.icoolle.component.assertion;

import cn.hutool.core.util.ArrayUtil;
import com.icoolle.component.exception.ArgumentException;
import com.icoolle.component.exception.BaseException;
import com.icoolle.component.exception.IResponseEnum;

import java.text.MessageFormat;


public interface CommonExceptionAssert extends IResponseEnum, Assert {

    @Override
    default BaseException newException(Object... args) {
        String msg = this.getMessage();
        if (ArrayUtil.isNotEmpty(args)) {
            msg = MessageFormat.format(this.getMessage(), args);
        }
        return new ArgumentException(this, args, msg);
    }

    @Override
    default BaseException newException(Throwable t, Object... args) {
        String msg = this.getMessage();
        if (ArrayUtil.isNotEmpty(args)) {
            msg = MessageFormat.format(this.getMessage(), args);
        }
        return new ArgumentException(this, args, msg, t);
    }

}
