package com.nataliapena.seguridad;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
		@Autowired
	   private JwtUtil jwtUtil;

	    @Override
	    protected void doFilterInternal(HttpServletRequest request, 
	                                  HttpServletResponse response, 
	                                  FilterChain filterChain) throws ServletException, IOException {
	        
	        final String authHeader = request.getHeader("Authorization");
	        final String jwt;
	        final String userEmail;

	        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
	            filterChain.doFilter(request, response);
	            return;
	        }

	        jwt = authHeader.substring(7);
	        userEmail = jwtUtil.extractUsername(jwt);

	        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	            if (jwtUtil.validateToken(jwt)) {
	                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
	                    userEmail,
	                    null,
	                    null
	                );
	                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                SecurityContextHolder.getContext().setAuthentication(authToken);
	            }
	        }
	        filterChain.doFilter(request, response);
	    }
}