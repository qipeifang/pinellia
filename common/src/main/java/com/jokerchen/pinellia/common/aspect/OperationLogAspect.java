package com.jokerchen.pinellia.common.aspect;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONArray;
import com.jokerchen.pinellia.common.annotation.OperationLogger;
import com.jokerchen.pinellia.common.constant.Constant;
import com.jokerchen.pinellia.common.log.entity.OperationLog;
import com.jokerchen.pinellia.common.log.service.OperationLogService;

import lombok.extern.slf4j.Slf4j;

/**   
 * @description: 操作日志切点类
 * @author Joker Chen 
 * @date 2019-03-21 16:53:10  
 */
@Slf4j
@Aspect
@Component
public class OperationLogAspect {

	@Autowired
	private OperationLogService operationLogService;
	
    /**
     * Controller层切点
     */
    //@Pointcut("execution (* com.jokerchen.pinellia.*..controller.*Controller.*(..))")
    public void controllerAspect() {}

    /**
     * 前置通知
     * @param joinPoint
     */
    //@Before("controllerAspect()")
    public void before(JoinPoint joinPoint) {
        log.info("======= 前置通知 =======");
    }

    /**
     * 环绕通知
     * @param joinPoint
     */
    /**@Around("controllerAspect()")**/
    public void around(JoinPoint joinPoint) {
    	log.debug("======= 环绕通知 =======");
    }

    /**
     * 后置通知
     * @param joinPoint
     */
    /**@After("controllerAspect()")**/
    public void after(JoinPoint joinPoint) {
    	log.debug("======= 后置通知 =======");
    }

    /**
     * 后置返回通知
     * 用于记录操作日志
     * @param joinPoint
     */
    @AfterReturning("execution (* com.jokerchen.pinellia.*..controller.*Controller.*(..)) && @annotation(operationLogger)")
    public void afterReturning(JoinPoint joinPoint, OperationLogger operationLogger) {
    	OperationLog operationLog = new OperationLog();
        //操作结果
        operationLog.setState(Constant.OPERATION_LOG_SUCCESS);
        this.createOperationLog(joinPoint,operationLogger, operationLog);
    }

    /**
     * 异常通知 用于拦截记录异常日志
     * @param joinPoint
     */
    @AfterThrowing(value="execution (* com.jokerchen.pinellia.*..controller.*Controller.*(..)) && @annotation(operationLogger)",
    		throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, OperationLogger operationLogger, Throwable ex) {
    	OperationLog operationLog = new OperationLog();
        //操作结果
        operationLog.setState(Constant.OPERATION_LOG_FAILURE);
        // 保存异常信息
        operationLog.setException(ex.getMessage());
        this.createOperationLog(joinPoint, operationLogger,operationLog);
    }

    /**
     * 保存操作日志
     *
     * @param joinPoint
     * @param operationLog
     */
    private void createOperationLog(JoinPoint joinPoint,
    		OperationLogger operationLogger, OperationLog operationLog) {
        try {
            HttpServletRequest request = ((ServletRequestAttributes)
                    RequestContextHolder.getRequestAttributes()).getRequest();
            //远程访问的客户端的IP地址
            String ip = request.getRemoteAddr();
            //被操作的类
            String className = joinPoint.getTarget().getClass().getName();
            log.error(className);
            //被调用的方法
            String methodName = joinPoint.getSignature().getName();
            //传入的参数
            String paramsStr = "";
            Object[] arguments = joinPoint.getArgs();
            if (arguments != null && arguments.length > 0) {
                StringBuffer params = new StringBuffer();
                for (int i = 0; i < arguments.length; i++) {
                    if (arguments[i] == null) {
                        params.append("null,");
                    } else {
                        params.append(arguments[i].getClass().getSimpleName() + ", ");
                    }
                }
                paramsStr = params.toString().substring(0, params.length() - 2);
            }
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    		if (!(authentication instanceof AnonymousAuthenticationToken)) {
    		    String currentUserName = authentication.getName();
    		    //当前登录用户
    		    operationLog.setOperator(currentUserName);
    		}
    		// 访问的主机地址
    		operationLog.setRemoteAddr(ip);
            // 调用的方法
            operationLog.setMethod(className + "." + methodName + "(" + paramsStr + ")");
            //传入的参数值
            operationLog.setArgs(JSONArray.toJSONString(request.getParameterMap()));
            // 操作时间
            operationLog.setOperateTime(new Date());
            // 操作类型
            operationLog.setOperateType(operationLogger.operationType());
            // 操作描述
            operationLog.setOperateDesc(operationLogger.operationDesc());
            //保存数据
            operationLogService.saveOperationLog(operationLog);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
