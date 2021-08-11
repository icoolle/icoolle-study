package com.icoolle.component.handler;


import com.icoolle.common.constant.enums.ArgumentResponseEnum;
import com.icoolle.common.constant.enums.CommonResponseEnum;
import com.icoolle.common.constant.enums.ServletResponseEnum;
import com.icoolle.common.core.model.Result;
import com.icoolle.common.core.service.UnifiedMessageSource;
import com.icoolle.component.exception.BaseException;
import com.icoolle.component.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.binding.BindingException;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import static com.icoolle.common.constant.consts.SystemConst.ENV_PROD;
import static com.icoolle.common.constant.consts.SystemConst.NULL_DATA;
import static com.icoolle.common.constant.enums.CommonResponseEnum.*;
import static com.icoolle.tool.LogUtil.getErr;


@Slf4j
@ControllerAdvice(basePackages = "com.icoolle")
@ConditionalOnWebApplication
public class CustomExceptionHandler {


    @Autowired
    private UnifiedMessageSource unifiedMessageSource;

    /**
     * 当前环境
     */
    @Value("${spring.profiles.active}")
    private String profile;

    /**
     * 获取国际化消息
     *
     * @param e 异常
     * @return
     */
    public String getMessage(BaseException e) {
        String code = "response." + e.getResponseEnum().toString();
        String message = unifiedMessageSource.getMessage(code, e.getArgs());
        if (message == null || message.isEmpty()) {
            return e.getMessage();
        }
        return message;
    }

    /**
     * 业务异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public Result<String> handleBusinessException(BaseException e) {
        log.error(getErr(e.getMessage()), e);
        return new Result<>(e.getResponseEnum().getCode(), getMessage(e), e.getMessage());
    }

    /**
     * 自定义异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public Result<String> handleBaseException(BaseException e) {
        log.error(getErr(e.getMessage()), e);
        return new Result<>(e.getResponseEnum().getCode(), getMessage(e), e.getMessage());
    }

    /**
     * Controller上一层相关异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler({
            NoHandlerFoundException.class,
            HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeNotSupportedException.class,
            MissingPathVariableException.class,
            MissingServletRequestParameterException.class,
            TypeMismatchException.class,
            HttpMessageNotReadableException.class,
            HttpMessageNotWritableException.class,
            HttpMediaTypeNotAcceptableException.class,
            ServletRequestBindingException.class,
            ConversionNotSupportedException.class,
            MissingServletRequestPartException.class,
            AsyncRequestTimeoutException.class
    })
    @ResponseBody
    public Result handleServletException(Exception e) {
        log.error(getErr(e.getMessage()), e);
        int code = CommonResponseEnum.SERVER_BUSY.getCode();
        try {
            ServletResponseEnum servletExceptionEnum = ServletResponseEnum.valueOf(e.getClass().getSimpleName());
            code = servletExceptionEnum.getCode();
        } catch (IllegalArgumentException e1) {
            log.error("class [{}] not defined in enum {}", e.getClass().getName(), ServletResponseEnum.class.getName());
        }
        if (e instanceof HttpMessageNotReadableException) {
            return new Result(DESERIALIZE_ERROR, e.getMessage());
        }
        return new Result(code, e.getMessage(), e.getMessage());
    }


    /**
     * 参数绑定异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public Result handleBindException(BindException e) {
        log.error(getErr("参数绑定校验异常"), e);
        return wrapperBindingResult(e.getBindingResult());
    }

    /**
     * 参数校验异常，将校验失败的所有异常组合成一条错误信息
     *
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public Result handleValidException(MethodArgumentNotValidException e) {
        log.error(getErr("参数绑定校验异常"), e);
        return wrapperBindingResult(e.getBindingResult());
    }

    /**
     * 包装绑定异常结果
     *
     * @param bindingResult 绑定结果
     * @return 异常结果
     */
    private Result wrapperBindingResult(BindingResult bindingResult) {
        StringBuilder msg = new StringBuilder();
        for (ObjectError error : bindingResult.getAllErrors()) {
            msg.append(", ");
            if (error instanceof FieldError) {
                msg.append(((FieldError) error).getField()).append(": ");
            }
            msg.append(error.getDefaultMessage() == null ? "" : error.getDefaultMessage());
        }
        return new Result(ArgumentResponseEnum.VALID_ERROR.getCode(), msg.substring(2), NULL_DATA);
    }

    /**
     * 未定义异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result<String> handleException(Exception e) {
        log.error(getErr(e.getMessage()), e);
        if (ENV_PROD.equals(profile)) {
            BaseException baseException = new BaseException(CommonResponseEnum.SERVER_BUSY);
            return new Result<>(CommonResponseEnum.SERVER_BUSY.getCode(), getMessage(baseException), e.getMessage());
        } else {
            if (e instanceof NullPointerException) {
                return new Result<>(NULL_POINTER_ERROR, e.getMessage());
            }
            if (e instanceof BindingException) {
                return new Result<>(SQL_STATEMENT_NULL_ERROR, e.getMessage());
            }
            if (e instanceof DuplicateKeyException) {
                return new Result<>(DUPLICATE_KEY_ERROR, e.getMessage());
            }
            if (e instanceof MyBatisSystemException) {
                return new Result<>(MYBATIS_RESULT_ERROR, e.getMessage());
            }
            return new Result<>(CommonResponseEnum.SERVER_ERROR.getCode(), e.getMessage(), e.getMessage());
        }
    }

}
