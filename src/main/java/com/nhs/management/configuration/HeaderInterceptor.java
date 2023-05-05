package com.nhs.management.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@Component
@Slf4j
public class HeaderInterceptor implements HandlerInterceptor {

    /**
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        logHeader(request, response, true);
        return true;
    }

    /**
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {

        logHeader(request, response, false);

    }

    private void logHeader(HttpServletRequest request, HttpServletResponse response, boolean isIncoming) {
        String url = request.getRequestURI();
        log.info("Request URL :: {}", url);
        boolean isNotEligibleForLog = url.contains("swagger") || url.contains("api-docs") ||
                url.contains("h2") || url.equals("/");
        if (!isNotEligibleForLog) {
            if (isIncoming) {
                log.info("http-incoming request >> url: {} ", request.getRequestURI());
                Enumeration<String> headersNames = request.getHeaderNames();
                while (headersNames.hasMoreElements()) {
                    String name = headersNames.nextElement();
                    log.info("http-incoming  >> Headers: {} : {} ", name, request.getHeader(name));
                }

                request.getParameterMap().forEach((K, V) -> {
                    log.info("http-incoming request >> Query params: {} , {} ", K, V);
                });

            } else {
                response.getHeaderNames().forEach(s -> {
                    log.info("http-outgoing  << Headers: {}:{} ", s, response.getHeader(s));
                });
                log.info("http-outgoing  << status: {} ", response.getStatus());
            }
        }
    }
}
