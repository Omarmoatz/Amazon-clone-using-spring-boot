package com.amazon.ecommerce.config;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.amazon.ecommerce.services.users.JwtService;
import com.amazon.ecommerce.services.users.UserDetailService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;



@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    // private final ApplicationContext context;  // to prevent the circular import
    private final UserDetailService userDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Skip JWT check for login/register
        String path = request.getServletPath();
        if (path.equals("/users/login") || path.equals("/users/register")) {
            filterChain.doFilter(request, response);
            return;
        }
        
        var authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Missing or invalid Authorization header");
            return;
        }

        var token = authHeader.substring(7);

        if (!jwtService.validateToken(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid or expired token");
            return;
        }
        var userName = jwtService.extractUsername(token);
        
        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null){
            // var userDetail = context.getBean(UserDetailService.class).loadUserByUsername(userName);  // to prevent the circular import
            var userDetail = userDetailService.loadUserByUsername(userName);
            
            var authToken = new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
        filterChain.doFilter(request, response);
        
        

    }
    
}
