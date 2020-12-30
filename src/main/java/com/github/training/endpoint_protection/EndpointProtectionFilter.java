package com.github.training.endpoint_protection;

import com.github.training.service.LoginSessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static com.github.training.util.Constants.SESSION_ID_COOKIE_NAME;
import static org.apache.logging.log4j.util.Strings.isBlank;

@Component
@RequiredArgsConstructor
@Slf4j
public class EndpointProtectionFilter extends OncePerRequestFilter {
    private final EndpointProtectionProperties protectionProperties;
    private final AntPathMatcher antPathMatcher;
    private final LoginSessionService loginSessionService;
    private final ErrorResponseSender errorResponseSender;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (isProtectedEndpoint(request)) {
            if (!userHasValidSession(request)) {
                errorResponseSender.setErrorResponse(request, response);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private boolean userHasValidSession(HttpServletRequest request) {
        Optional<UUID> cookieValue = getSessionCookie(request);

        if (cookieValue.isPresent()) {
            return loginSessionService.findBySessionId(cookieValue.get())
                    .isPresent();
        } else {
            return false;
        }
    }

    private Optional<UUID> getSessionCookie(HttpServletRequest request) {
        return Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals(SESSION_ID_COOKIE_NAME))
                .filter(cookie -> !isBlank(cookie.getValue()))
                .filter(cookie -> isUuid(cookie.getValue()))
                .map(cookie -> UUID.fromString(cookie.getValue()))
                .findFirst();
    }

    private boolean isUuid(String value) {
        try {
            UUID.fromString(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isProtectedEndpoint(HttpServletRequest request) {
        return protectionProperties.getProtectedEndpoints()
                .stream()
                .filter(endpoint -> antPathMatcher.match(endpoint.getPathPattern(), request.getRequestURI()))
                .anyMatch(endpoint -> endpoint.getMethod().equalsIgnoreCase(request.getMethod()));
    }
}
