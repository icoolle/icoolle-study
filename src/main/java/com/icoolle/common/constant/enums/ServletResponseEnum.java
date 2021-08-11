package com.icoolle.common.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.servlet.http.HttpServletResponse;

/**
 * 异常类与http status对照关系
 */
@Getter
@AllArgsConstructor
public enum ServletResponseEnum {

    MethodArgumentNotValidException(1001400, "", HttpServletResponse.SC_BAD_REQUEST),
    MethodArgumentTypeMismatchException(1001400, "", HttpServletResponse.SC_BAD_REQUEST),
    MissingServletRequestPartException(1001400, "", HttpServletResponse.SC_BAD_REQUEST),
    MissingPathVariableException(1001400, "", HttpServletResponse.SC_BAD_REQUEST),
    BindException(1001400, "", HttpServletResponse.SC_BAD_REQUEST),
    MissingServletRequestParameterException(1001400, "", HttpServletResponse.SC_BAD_REQUEST),
    TypeMismatchException(1001400, "", HttpServletResponse.SC_BAD_REQUEST),
    ServletRequestBindingException(1001400, "", HttpServletResponse.SC_BAD_REQUEST),
    HttpMessageNotReadableException(1001400, "", HttpServletResponse.SC_BAD_REQUEST),
    NoHandlerFoundException(1001404, "", HttpServletResponse.SC_NOT_FOUND),
    NoSuchRequestHandlingMethodException(1001404, "", HttpServletResponse.SC_NOT_FOUND),
    HttpRequestMethodNotSupportedException(1001405, "", HttpServletResponse.SC_METHOD_NOT_ALLOWED),
    HttpMediaTypeNotAcceptableException(1001406, "", HttpServletResponse.SC_NOT_ACCEPTABLE),
    HttpMediaTypeNotSupportedException(1001415, "", HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE),
    ConversionNotSupportedException(1001500, "", HttpServletResponse.SC_INTERNAL_SERVER_ERROR),
    HttpMessageNotWritableException(1001500, "", HttpServletResponse.SC_INTERNAL_SERVER_ERROR),
    AsyncRequestTimeoutException(1001503, "", HttpServletResponse.SC_SERVICE_UNAVAILABLE);

    /**
     * 返回码，目前与{@link #statusCode}相同
     */
    private int code;
    /**
     * 返回信息，直接读取异常的message
     */
    private String message;
    /**
     * HTTP状态码
     */
    private int statusCode;
}
