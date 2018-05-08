package com.zstiu.IoTService.interceptor;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class AccessLoggingInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(HandlerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String ip = httpServletRequest.getRemoteAddr();
        String path = httpServletRequest.getRequestURI();
        StringBuilder stringBuilder = new StringBuilder();

        String query = httpServletRequest.getQueryString();
        if(!StringUtils.isEmpty(query)){
            path += "?" + query;
        }

        stringBuilder.append(ip).append("--");
        stringBuilder.append(httpServletRequest.getMethod()).append("  ").append(path);

        Map<String, String[]> parameters = httpServletRequest.getParameterMap();
        if(!CollectionUtils.isEmpty(parameters)){
            stringBuilder.append(": 参数为：").append(JSON.toJSONString(parameters));
        }

        log.info(String.valueOf(stringBuilder));

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
