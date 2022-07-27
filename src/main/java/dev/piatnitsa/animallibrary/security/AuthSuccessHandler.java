package dev.piatnitsa.animallibrary.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.piatnitsa.animallibrary.model.dto.UserCredentialsDto;
import dev.piatnitsa.animallibrary.security.jwt.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class represents the successful authorization handler.
 * @author Vlad Piatnitsa
 * @version 1.0
 */
@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {
    private static final String UTF_8 = "UTF-8";
    private final TokenProvider tokenProvider;

    @Autowired
    public AuthSuccessHandler(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        String token = tokenProvider.generateToken(principal.getUsername());
        UserCredentialsDto dto = new UserCredentialsDto(principal.getUsername(), token);
        response.getWriter().write(objectMapper.writeValueAsString(dto));
    }
}
