package com.ideage.ams.config.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ideage.ams.common.ResultGenerator;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Map;

/**
 * 認証用フィルター
 */
public class AmsAuthFilter extends UsernamePasswordAuthenticationFilter {

    /**
     *
     */
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * コンストラクタ
     */
    public AmsAuthFilter() {
        super();
        // Set custom login processing URL
        setFilterProcessesUrl("/authenticate");
    }

    /**
     * @param request  from which to extract parameters and perform the authentication
     * @param response the response, which may be needed if the implementation has to do a
     *                 redirect as part of a multi-stage authentication process (such as OIDC).
     * @return 認証情報
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) {
        // Map<String, String> authRequest = new ObjectMapper().readValue(request.getInputStream(), Map.class);
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        try {
            String username = null;
            String password = null;
            if (request.getHeader("Content-Type").startsWith("application/json")) {
                // Parse JSON body for credentials
                Map<String, String> authRequest = null;
                authRequest = objectMapper.readValue(request.getInputStream(), Map.class);
                username = authRequest.get("username");
                password = authRequest.get("password");
            } else if (request.getHeader("Content-Type").startsWith("application/x-www-form-urlencoded")) {
                username = obtainUsername(request);
                password = obtainPassword(request);
            }

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);

            setDetails(request, authToken);

            Authentication authentication = this.getAuthenticationManager().authenticate(authToken);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            return authentication;
        } catch (IOException e) {
            throw new AuthenticationServiceException("Failed to parse authentication request body");
        }
    }

    /**
     * @param request
     * @param response
     * @param chain
     * @param authResult the object returned from the <tt>attemptAuthentication</tt>
     *                   method.
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {

        HttpSession session = request.getSession();
        session.setAttribute("SPRING_SECURITY_CONTEXT",
                SecurityContextHolder.getContext());

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);

        String userName = authResult.getName();

        this.getRememberMeServices().loginSuccess(request,response,authResult);
        // Continue with default behavior
       // super.successfulAuthentication(request, response, chain, authResult);

        objectMapper.writeValue(response.getWriter(), ResultGenerator.getSuccessResult());
    }

    /**
     * @param request
     * @param response
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response, AuthenticationException failed)
            throws IOException, ServletException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        // Add custom behavior on failed authentication
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        objectMapper.writeValue(response.getWriter(), ResultGenerator.getFailResult(failed.getMessage()));

        // Continue with default behavior
       // super.unsuccessfulAuthentication(request, response, failed);
    }
}