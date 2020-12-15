package com.example.Zing.filter;

import com.example.Zing.FacebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.HttpClientErrorException;

import javax.naming.AuthenticationException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
public class FacebookFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        FacebookService facebookService = new FacebookService();
        System.out.println("Fb auth");
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String token = httpRequest.getHeader("Authorization").substring(7);
        try {
            String uid = facebookService.validate(token);
            httpRequest.setAttribute("user_id", uid);
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (AuthenticationException e) {
            System.out.println(e.getMessage());
            HttpServletResponse resp = ((HttpServletResponse) servletResponse);
            resp.setStatus(401);
            resp.sendError(401, e.getMessage());
        }
    }
}
