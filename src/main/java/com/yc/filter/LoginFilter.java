package com.yc.filter;

import com.yc.util.SessionAttributeKey;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: 林哲达
 * @Date: 2018/3/12 15:07
 */
@WebFilter(filterName = "LoginFilter", urlPatterns = {"/*"})
public class LoginFilter implements Filter {

    private static String mainPage = "/";

    /**
     * 封装，不需要过滤的list列表
     */
    protected static List<Pattern> patterns = new ArrayList<Pattern>();


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        patterns.add(Pattern.compile("/"));
        patterns.add(Pattern.compile("/swagger-ui.html"));
        patterns.add(Pattern.compile("/login"));
        patterns.add(Pattern.compile("/toyc.action"));
        patterns.add(Pattern.compile("back/user/login.action"));
        patterns.add(Pattern.compile(".*\\.[(js)||(css)||(png)||(ico)||(jpg)||(gif)||(xls)||(pdf)||(doc)]+"));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        String url = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
        if (url.startsWith("/") && url.length() > 1) {
            url = url.substring(1);
        }

        if (isInclude(url)) {
            chain.doFilter(httpRequest, httpResponse);
            return;
        } else {
            HttpSession session = httpRequest.getSession();
            if (session.getAttribute(SessionAttributeKey.LOGIN_ADMIN) != null) {
                // session存在
                chain.doFilter(httpRequest, httpResponse);
                return;
            } else {
                // session不存在 准备跳转失败
                String path = httpRequest.getScheme() + "://" + httpRequest.getServerName() + ":" + httpRequest.getServerPort() + httpRequest.getContextPath();
                httpResponse.getWriter().print("<script>location.href='" + path + mainPage + "'</script>");
                return;
            }
        }

    }

    @Override
    public void destroy() {

    }

    /**
     * 是否需要过滤
     *
     * @param url
     * @return
     */
    private boolean isInclude(String url) {
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(url);
            if (matcher.matches()) {
                return true;
            }
        }
        return false;
    }

}
