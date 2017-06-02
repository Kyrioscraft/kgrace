package cn.kyrioscraft.web.util;



import cn.kyrioscraft.data.model.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Web常用工具集，用于获取当前登录用户，请求信息等
 * create by kyrioscraft
 */
public class WebUtil {

    /**
     * 尝试获取当前请求的HttpServletRequest实例
     *
     * @return HttpServletRequest
     */
    public static HttpServletRequest getHttpServletRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 尝试获取当前登录的用户（基于ThreadLocal）
     *
     * @return 当前登录的用户
     */
    public static User getLoginUser() {
        return getLoginUser(getHttpServletRequest());
    }

    /**
     * 在HttpSession中获取当前登录的用户
     *
     * @param session HttpSession
     * @return 当前登录的用户
     */
    public static User getLoginUser(HttpSession session) {
        try {
            return (User) session.getAttribute("user");
        } catch (Exception e) {
        }
        return null;
    }

    public static User setCurrentUser(User user) {
        ThreadLocalUtils.put("current-user", user);
        return user;
    }

    public static void removeCurrentUser() {
        ThreadLocalUtils.remove("current-user");
    }


    /**
     * 在HttpServletRequest中获取当前登录的用户
     *
     * @param request HttpServletRequest
     * @return 当前登录的用户
     */
    public static User getLoginUser(HttpServletRequest request) {
        if (request == null) return ThreadLocalUtils.get("current-user");
        HttpSession session = request.getSession(false);
        if (session == null) {
            return null;
        }
        return getLoginUser(session);
    }

    public static Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> map = new LinkedHashMap<>();
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }

    /**
     * 解析参数列表为map
     *
     * @param request request请求对象
     * @return 参数集合
     */
    public static Map<String, String> getParams(HttpServletRequest request) {
        Map<String, String[]> map = request.getParameterMap();
        Map<String, String> param = new LinkedHashMap<>();
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            String key = entry.getKey();
            String[] varr = entry.getValue();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < varr.length; i++) {
                String var = varr[i];
                if (i != 0) builder.append(",");
                builder.append(var);
            }
            param.put(key, builder.toString());
        }
        return param;
    }

    /**
     * 获取请求客户端的真实ip地址
     *
     * @param request 请求对象
     * @return ip地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader(" x-forwarded-for ");
        if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
            ip = request.getHeader(" Proxy-Client-IP ");
        }
        if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
            ip = request.getHeader(" WL-Proxy-Client-IP ");
        }
        if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * web应用绝对路径
     *
     * @param request 请求对象
     * @return 绝对路径
     */
    public static String getBasePath(HttpServletRequest request) {
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
        return basePath;
    }

    //获取request请求的ip
    public static String getIpAddress(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     *
     * UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
     * 但我在实际运用中发现获得的Authentication为null。仔细看了下源代码发现，如果想用上面的代码获得当前用户，必须在spring
     * security过滤器执行中执行，否则在过滤链执行完时org.springframework.security.web.context.SecurityContextPersistenceFilter类会
     * 调用SecurityContextHolder.clearContext();而把SecurityContextHolder清空，所以会得到null。    经过spring security认证后，
     * security会把一个SecurityContextImpl对象存储到session中，此对象中有当前用户的各种资料
     *
     */
    public static org.springframework.security.core.userdetails.User getCurrentUser(HttpServletRequest request){
        SecurityContextImpl ssctx = (SecurityContextImpl) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        if(ssctx==null)
            return null;
        Authentication authentication = ssctx.getAuthentication();
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        return principal;
    }
    public static String getCurrentUserLoginIP(HttpServletRequest request){
        SecurityContextImpl ssctx = (SecurityContextImpl) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        if(ssctx==null)
            return null;
        Authentication authentication = ssctx.getAuthentication();
        WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
        String remoteAddress = details.getRemoteAddress();
        return remoteAddress;
    }
    public static String getCurrentSessionId(HttpServletRequest request){
        SecurityContextImpl ssctx = (SecurityContextImpl) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        if(ssctx==null)
            return null;
        Authentication authentication = ssctx.getAuthentication();
        WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
        String sessionId = details.getSessionId();
        return sessionId;
    }
}
