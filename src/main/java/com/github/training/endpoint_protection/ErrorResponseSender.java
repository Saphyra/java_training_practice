package com.github.training.endpoint_protection;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.training.error_handler.ErrorResponse;
import com.github.training.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.github.training.util.Constants.INDEX_PAGE_LOCATION;

@Component
@RequiredArgsConstructor
@Slf4j
public class ErrorResponseSender {
    private final ObjectMapper objectMapper;

    public void setErrorResponse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean isRest = Constants.REQUEST_TYPE_HEADER_VALUE.equals(request.getHeader(Constants.REQUEST_TYPE_HEADER_NAME));

        if (isRest) {
            handleJson(response);
        } else {
            handleHtml(response);
        }
    }

    private void handleHtml(HttpServletResponse response) throws IOException {
        response.sendRedirect(INDEX_PAGE_LOCATION);
    }

    private void handleJson(HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        ErrorResponse errorResponse = new ErrorResponse("Session expired.");
        String payload = objectMapper.writeValueAsString(errorResponse);
        response.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(payload);
        printWriter.flush();
        printWriter.close();
    }
}
