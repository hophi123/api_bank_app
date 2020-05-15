/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.vnext.phinh.api_bank_app.security;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * [OVERVIEW] XXXXX.
 *
 * @author: (VNEXT) PhiNH
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/23      (VNEXT) PhiNH       Create new
*/
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
    private static final Log log = LogFactory.getLog(JwtAuthenticationEntryPoint.class);

    private static final long serialVersionUID = -7858869558953243875L;

    /**
     * @author (VNEXT) PhiNH
     * @param req, res, authExeprion
     * Từ chối truy cập chưa được xác thực và gửi về lỗi code 401
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, org.springframework.security.core.AuthenticationException authException)
            throws IOException, ServletException {
        log.debug("### commence START ###");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        log.debug("### commence END ###");
    }
}
