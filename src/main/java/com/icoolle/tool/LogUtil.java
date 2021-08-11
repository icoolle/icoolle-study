package com.icoolle.tool;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.vavr.CheckedConsumer;
import io.vavr.collection.List;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.Assert;

import java.io.*;
import java.lang.reflect.Method;

/**
 * 日志工具类
 *
 * @author YY
 */
@Slf4j
public class LogUtil {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";


    /***
     * 获取操作信息
     * @param joinPoint
     * @param sysLog
     * @return
     */
    public static void getControllerMethodDescription(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Class targetClass = joinPoint.getSignature().getDeclaringType();
        Api api = (Api) targetClass.getAnnotation(Api.class);
        Assert.notNull(api, "您的接口类没有添加'Api'模块注释！请为该接口类添加'Api'模块注释");
        String[] tag = api.tags();
        ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
        Assert.notNull(apiOperation, "您的接口没有添加'ApiOperation'注释！请为该接口添加'ApiOperation'注释");

    }


    /**
     * 获取堆栈信息
     *
     * @param throwable
     * @return
     */
    public static String getStackTrace(Throwable throwable) {
        if (null == throwable) {
            return "";
        }
        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw)) {
            throwable.printStackTrace(pw);
            return sw.toString();
        }
    }

    /**
     * info
     *
     * @param className
     * @param message
     */
    public static void info(String className, String message) {
        System.out.println(ANSI_GREEN + className + " : " + message + ANSI_RESET);
    }

    /**
     * error
     *
     * @param className
     * @param message
     */
    public static void error(String className, String message) {
        System.out.println(ANSI_RED + className + " : " + message + ANSI_RESET);
    }

    /**
     * debug
     *
     * @param className
     * @param message
     */
    public static void debug(String className, String message) {
        System.out.println(ANSI_BLUE + className + " : " + message + ANSI_RESET);
    }

    /**
     * warning
     *
     * @param className
     * @param message
     */
    public static void warn(String className, String message) {
        System.out.println(ANSI_YELLOW + className + " : " + message + ANSI_RESET);
    }


    public static String getErr(String message) {
        return ANSI_RED + message;
    }

    /**
     * 获取指定log文件的指定关键字的日志信息
     *
     * @param connection SSH的连接
     * @param cmd        执行语句
     * @return
     * @throws IOException
     */
    public static String readerLogInfo(Connection connection, String cmd) throws IOException {
        Session session = connection.openSession();
        //执行shell命令
        session.execCommand(cmd);
        //处理获取的shell命令的输出信息
        InputStream stdout = session.getStdout();
        InputStreamReader inputStreamReader = new InputStreamReader(stdout);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String s = bufferedReader.readLine();
        String lastLineLog = null;
        while (s != null) {
            if (s != null) {
                lastLineLog = s;
            }
            s = bufferedReader.readLine();
        }
        //最后关闭session资源
        if (session != null) {
            session.close();
        }
        return lastLineLog;
    }

    public static void main(String[] args) {
        CheckedConsumer<List<Integer>> handler = (list) -> {
            if (list.size() > 0) {
                System.out.println(list.asJava());
            }
        };
        try {
            handler.accept(List.of(1, 2, 3, 4, 5));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
