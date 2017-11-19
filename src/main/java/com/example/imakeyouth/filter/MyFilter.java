package com.example.imakeyouth.filter;

import com.example.imakeyouth.common.Constants;
import com.example.imakeyouth.common.utils.SessionUtils;
import com.example.imakeyouth.model.User;
import com.example.imakeyouth.service.IUserService;
import com.example.imakeyouth.exception.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URLDecoder;
import java.util.*;

@Component
@Slf4j
@WebFilter(filterName = "myFilter", urlPatterns = "/blog/makeyouth/*")
public class MyFilter implements Filter {

    @Autowired
    private IUserService userService;

    // 排除拦截
    private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList("/makeyouth/user/login","/makeyouth/user/login-swagger","/makeyouth/user/forget/send-email","/makeyouth/user/forget/set-password")));

    private static final PathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        System.out.println("我是myFilter的init,我应该最先运行");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String path = httpRequest.getServletPath();
        addHeaders(httpResponse);
        log.info("start request path {}", path);
        if (!httpRequest.getMethod().equalsIgnoreCase("options")) {
            if (!this.matchExPath(path)) {
                try {
                    String userToken = this.getToken(httpRequest);
                    if (StringUtils.isNotBlank(userToken)) {
                        User user = userService.queryBySessionId(userToken);

                        if (user == null) {
                            throw new ApplicationException("无法查询到用户信息");
                        }
                        SessionUtils.setUser(user);
                    } else {
                        throw new ApplicationException("无法获取到token");
                    }
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                    httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
                    response.setCharacterEncoding("UTF-8");
                    Writer writer = httpResponse.getWriter();
                    writer.write("请先登录");
                    return;
                }
            }
        }
        chain.doFilter(request, response);
        log.info("end request path {}", path);
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
        System.out.println("我是myFilter的destroy,我应该最后运行");
    }

    private String getToken(HttpServletRequest httpRequest) throws UnsupportedEncodingException {
        String userToken = null;
        //1、从cookie中获取Security-Token(swagger调用方式，需要登录)；
        Cookie[] cookies = httpRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(Constants.SECURITY_TOKEN_USER)) {
                    userToken = URLDecoder.decode(cookie.getValue(), "utf-8");
                    break;
                }
            }
        }
        if (StringUtils.isBlank(userToken)) {
            //2、再从header中的Security-Token（前端页面调用方式，需要登录）。
            Map<String, String> map = new HashMap<String, String>();
            Enumeration headerNames = httpRequest.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String key = (String) headerNames.nextElement();
                String value = httpRequest.getHeader(key);
                map.put(key, value);
            }
            log.info("headers:" + map.toString());
            userToken = httpRequest.getHeader(Constants.SECURITY_TOKEN_USER);
        }
        if (StringUtils.isBlank(userToken)) {
            //3、从请求参数中获取的Security-Token。
            userToken = httpRequest.getParameter(Constants.SECURITY_TOKEN_USER);
        }
        return userToken;
    }

    private boolean matchExPath(String path) {
        if (ALLOWED_PATHS != null) {
            for (String ignore : ALLOWED_PATHS) {
                if (pathMatcher.match(ignore, path)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void addHeaders(HttpServletResponse httpResponse) {
        httpResponse.setHeader("Cache-Control", "no-cache");
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
        httpResponse.setHeader("Access-Control-Max-Age", "1728000");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Headers",
                "Origin, X-Requested-With, Content-Type, Accept," + Constants.SECURITY_TOKEN_USER);
        httpResponse.setHeader("Access-Control-Expose-Headers", "Location");
        httpResponse.setCharacterEncoding(Constants.CHARSET_NAME);
        httpResponse.setContentType(MediaType.TEXT_PLAIN_VALUE);
    }

}
