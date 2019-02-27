package com.burning8393.template.common.aspect;

import com.burning8393.template.common.annotation.Log;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Date;

/**
 * LogAspect class
 *
 * @author : Pangxw
 * @date : 2019/2/27 15:53
 * @description :
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    /**
     * 设置切入点， 这里直接拦截被@RestController注解的类
     */
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // 1. 开始时间
        long beginTime = System.currentTimeMillis();
        // 利用RequestContextHolder 获取request对象
        ServletRequestAttributes requestAttrs = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        String uri = requestAttrs.getRequest().getRequestURI();
        log.info("开始计时： {}， URI：{}", new Date(), uri);
        // 访问目标方法的参数， 可动态改变参数值
        Object[] args = joinPoint.getArgs();
        // 方法名获取
        String methodName = joinPoint.getSignature().getName();
        log.info("请求方法：{}， 请求参数： {}", methodName, Arrays.toString(args));
        // 可能在反向代理请求进来时， 获取的ip存在不正确，
        log.info("请求IP：{}", getIpAddr(requestAttrs.getRequest()));

        Signature signature = joinPoint.getSignature();
        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException("暂不支持非方法注解");
        }
        // 调用实际方法
        Object object = joinPoint.proceed();
        // 获取执行的方法
        MethodSignature methodSign = (MethodSignature) signature;
        Method method = methodSign.getMethod();
        Log logAnno = AnnotationUtils.getAnnotation(method, Log.class);
        if (logAnno != null && logAnno.ignore()) {
            return object;
        }
        log.info("log注解描述： {}", logAnno.desc());
        long endTime = System.currentTimeMillis();
        log.info("结束计时：{}， URI: {}, 耗时： {}", new Date(), uri, endTime - beginTime);
        return object;
    }

    @AfterThrowing(pointcut = "pointCut()", throwing = "e")
    public void afterThrowing(Throwable e) {
        log.error("切面发生了异常", e);
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        log.error("获取ip异常：{}" ,e.getMessage());
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        }  catch (Exception e) {
            ipAddress = "";
        }
        // ipAddress = this.getRequest().getRemoteAddr();

        return ipAddress;
    }
}
