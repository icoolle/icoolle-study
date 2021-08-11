package com.icoolle.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static com.icoolle.tool.LogUtil.ANSI_RED;


/**
 * 通过实现ApplicationListener接口动态获取tomcat启动端口和访问路径，通过InetAddress类获取主机的ip地址，最后控制台打印项目访问地址
 */
@Component
@Slf4j
public class ServerConfig implements ApplicationListener<WebServerInitializedEvent> {

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        try {
            InetAddress inetAddress = Inet4Address.getLocalHost();
            log.info(ANSI_RED + "项目启动成功！访问地址: http://{}:{}{}", inetAddress.getHostAddress(), event.getWebServer().getPort(), event.getApplicationContext().getApplicationName());
        } catch (UnknownHostException e) {
            log.error("获取项目信息出错了", e);
        }
    }

}
