package kh.petmily.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("LoginCheckInterceptor 실행");

//        String uri = request.getRequestURI();
//        log.info("uri = {}", uri);

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("authUser") == null) {
            log.info("미인증 사용자");
//            response.sendRedirect("/login?redirectURL=" + URLEncoder.encode(uri));
            response.sendRedirect("/login");

            return false;
        }

        return true;
    }
}
