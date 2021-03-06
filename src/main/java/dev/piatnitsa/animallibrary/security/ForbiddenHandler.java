package dev.piatnitsa.animallibrary.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ForbiddenHandler implements AccessDeniedHandler {
    private static final String UTF_8 = "UTF-8";

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(UTF_8);
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString("Access denied"));
    }
}
