package com.stlouiscatclinic.room_status_api.services;

import com.stlouiscatclinic.room_status_api.controllers.AuthenticationController;
import com.stlouiscatclinic.room_status_api.models.Staff;
import com.stlouiscatclinic.room_status_api.repositories.StaffRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Trevor Gruber
 */
@Component
public class AuthenticationFilter implements HandlerInterceptor {
    
    /* fields */
    @Autowired
    StaffRepository staffRepository;
    
    @Autowired
    AuthenticationService authenticationService;
    
    private static final List<String> whitelist = Arrays.asList("/public", "/css");
    
    /* Custom methods */
    
    private static boolean isWhitelisted(String path) {
        for (String pathRoot : whitelist) {
            if (path.contains(pathRoot)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws IOException {
        // Don't require sign-in for whitelisted pages
        if (isWhitelisted(request.getRequestURI())) {
            // returning true indicates that the request may proceed
            return true;
        }
        HttpSession session = request.getSession();
        Staff staff = authenticationService.getStaffFromSession(session);
        
        //The user is logged in
        if (staff != null){
            return true;
        }
        
        //The user is not logged in
        response.sendError(401);
        return false;
    }
    
    /*@Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                    @Nullable ModelAndView modelAndView) throws IOException {
        
        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);
        
        //The user is logged in
        if (user != null){
            authenticationController.setLoggedInUser(user);
        } else {
            //The user is not logged in
            authenticationController.setLoggedInUser(null);
        }
    }*/
}
