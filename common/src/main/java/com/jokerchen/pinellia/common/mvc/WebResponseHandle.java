package com.jokerchen.pinellia.common.mvc;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.alibaba.fastjson.JSONObject;
import com.jokerchen.pinellia.common.constant.ResponseEnum;
import com.jokerchen.pinellia.common.model.ResponseMessage;

import lombok.extern.slf4j.Slf4j;

/**
 * @description: 统一的返回数据处理
 * @author Joker Chen
 * @date 2019-03-13 11:32:51
 */
@Slf4j
@ControllerAdvice
public class WebResponseHandle implements ResponseBodyAdvice<Object> {

	/**
	 * 全局统一的异常处理
	 * @param exception	抛出的异常
	 * @param request	请求	
	 * @return
	 */
    @ExceptionHandler(value = {Exception.class})  
    public ResponseEntity<ResponseMessage> handleExceptions(final Exception exception, final WebRequest request) {  
    	//将异常信息输出
    	log.error(exception.toString(),exception);
    	//对异常信息进行封装，给前端使用
		ResponseMessage responseMessage = new ResponseMessage();
		responseMessage.setCode(exception.getClass().getSimpleName());
    	if(exception instanceof BindException) {
    		BindException bindException = (BindException) exception;
    		List<ObjectError> list = bindException.getAllErrors();
    		StringBuilder stringBuilder = new StringBuilder();
    		list.forEach(item->{
    			stringBuilder.append(item.getCodes()[0]+":"+item.getDefaultMessage()+"\n");
    		});
    		//返回信息
    		responseMessage.setMessage(stringBuilder.toString());
    	}else {
    		//返回信息
    		responseMessage.setMessage(exception.getClass().getSimpleName()+":"+exception.getMessage());
    	}
    	//为了对自定义异常的统一处理，对于一些业务异常让前端接收异常信息进行处理，而不显示请求异常。
    	//仍旧这里返回的http状态仍然使用成功，具体的交由web端处理。
    	return new ResponseEntity<ResponseMessage>(responseMessage,HttpStatus.OK);  
    }
	
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        if (methodParameter.getMethodAnnotation(RequestMapping.class) != null) {
            String typeName = methodParameter.getGenericParameterType().getTypeName();
            // 只处理非 ResponseMessage 的返回值类型，包括void类型也会被处理
            if(typeName != null && typeName.equals(ResponseMessage.class.getTypeName())){
                return false;
            } else if (typeName != null && typeName.equals("org.springframework.http.ResponseEntity<byte[]>")) {
                return false;
            }
        }
        return true;
    }

    
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter,
                                  MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        //返回数据的类型名称
        String typeName = methodParameter.getGenericParameterType().getTypeName();
        // 只处理非 ResponseVo 的返回值类型，包括void类型也会被处理
        ResponseMessage vo = new ResponseMessage(ResponseEnum.SUCCESS.code(),ResponseEnum.SUCCESS.message());
        if(typeName != null && typeName.equals(ModelAndView.class.getTypeName())){
            ((ModelAndView)body).addObject(vo);
            return body;
        }else if(body instanceof ResponseMessage) {
        	//如果是进这里来，说明返回的信息已经是ResponseMessage对象的数据了。不需要再进行处理
        	return body;
        }else if(typeName.equals(byte[].class.getTypeName())){
            return body;
        }else{
        	if(body instanceof LinkedHashMap) {
        		//因为security权限验证时的异常，在handleExceptions中无法捕获处理，所以在这里增加处理
            	@SuppressWarnings("unchecked")
				LinkedHashMap<String,Object> map = (LinkedHashMap<String,Object>) body;
            	Object status = map.get("status");
            	Object error = map.get("error");
            	if(status != null && !status.equals(HttpStatus.OK.value()+"")
            			&& error != null/* && error.equals("Internal Server Error")*/) {
            		vo.setCode(status.toString());
            		vo.setMessage(error.toString());
            	}
            }
            vo.setData(body);
            if(typeName != null && typeName.equals(String.class.getTypeName())){
                return JSONObject.toJSONString(vo);
            }
            return vo;
        }
    }
}
