package com.zstiu.IoTService.config;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

/**
 * Created by Administrator on 2018/1/18.
 */
public class SessionInitializer extends AbstractHttpSessionApplicationInitializer {
    public SessionInitializer() {
        super(SessionConfig.class);
    }
}
