package com.bytehonor.sdk.basic.server.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

import com.bytehonor.sdk.basic.define.constant.HeaderKey;

public class TerminalUtils {

    public static String getFromTerminal(HttpServletRequest request) {
        String from = request.getParameter(HeaderKey.X_FROM_TERMINAL);
        if (StringUtils.isEmpty(from)) {
            from = request.getHeader(HeaderKey.X_FROM_TERMINAL);
        }
        if (StringUtils.isEmpty(from)) {
            from = "browser";
        }
        return from;
    }

    public static String getFromIp(HttpServletRequest request) {
        String ip = request.getHeader(HeaderKey.X_REAL_IP);
        if (StringUtils.isEmpty(ip) == false) {
            return ip;
        }
        ip = request.getHeader(HeaderKey.X_FORWARDED_FOR);
        if (StringUtils.isEmpty(ip) == false) {
            int at = ip.indexOf(",");
            if (at < 0) {
                return ip;
            }
            return ip.substring(0, at);
        }

        return request.getRemoteAddr();
    }

    public static String getFromUuid(HttpServletRequest request) {
        String from = request.getParameter(HeaderKey.X_FROM_UUID);
        if (StringUtils.isEmpty(from)) {
            from = request.getHeader(HeaderKey.X_FROM_UUID);
        }
        return from;
    }
}
