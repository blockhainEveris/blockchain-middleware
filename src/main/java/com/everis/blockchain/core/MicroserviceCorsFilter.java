package com.everis.blockchain.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MicroserviceCorsFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(MicroserviceCorsFilter.class);

    @Override
    public void destroy() {
        //Destroy method
    }

    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain)
            throws IOException, ServletException {
        LOGGER.debug("MicroserviceCorsFilter doFilter ...");
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, PATCH, OPTIONS, DELETE, TRACE");
        response.setHeader("Access-Control-Allow-Headers",
                "Origin, X-Requested-With, Content-Type, Accept, Authorization");
        if ("OPTIONS".equals(request.getMethod())) {
            try {
                response.getWriter().print("OK");
                response.getWriter().flush();
                response.getWriter().close();
            } catch (IOException e) {
                LOGGER.error("OPTIONS NOT OK", e);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        //Init method
    }
}
