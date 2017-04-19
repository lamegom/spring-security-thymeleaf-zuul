package com.charlyripp.spring;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ZuulDebugFilter {
    @Bean
    public ZuulFilter zuulFilter() {
        return new ZuulFilter() {
            @Override
            public String filterType() {
                return "post";
            }

            @Override
            public int filterOrder() {
                return 999999;
            }

            @Override
            public boolean shouldFilter() {
                return true;
            }

            @Override
            public Object run() {
                final List<String> routingDebug = (List<String>) RequestContext.getCurrentContext().get("routingDebug");
                routingDebug.forEach(System.out::println);
                return null;
            }
        };
    }
}
