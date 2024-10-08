package com.sboard.intercepter;

import com.sboard.config.AppInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
public class AppInfoIntercepter implements HandlerInterceptor {
    /*

        Intercepter
        - 클라이언트의 요청과 컨트롤러 사이에서 특정 작업을 수행하기 위한 컴포넌트
        - HTTP 요청을 가로채고, 요청이 컨트롤러 도달 전과 후에 추가 작업 수행
     */
    private final AppInfo appInfo;

    //컨트롤러 (요청 메서드)를 실행한 전 실행
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    //컨트롤러 (요청 메서드)를 실행한 후 실행
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if(modelAndView != null){
            modelAndView.addObject("appInfo", appInfo);
        }
    }
}
