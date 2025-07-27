package eventticketing.eventease_backend.Configurations;

import eventticketing.eventease_backend.enums.Role;
import eventticketing.eventease_backend.models.User;
import eventticketing.eventease_backend.repositries.UserRepository;
import eventticketing.eventease_backend.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        User userOpt = userRepository.findByEmail(email);

        User user;
        if (userOpt == null) {
            user = User.builder()
                    .email(email)
                    .name(name)
                    .password("") // Google login doesn't need password
                    .role(Role.ATTENDEE)
                    .build();
            userRepository.save(user);
        } else {
            user = userOpt;
        }
        System.out.println("Email for JWT: " + user.getEmail()); // Debugging
        if (user.getEmail() == null) {
            throw new RuntimeException("Email is null, cannot generate JWT.");
        }
        String token = jwtUtil.generateToken(user.getEmail());

        // Redirect to frontend with token (could be via cookie, header, or query param)
        response.sendRedirect("http://localhost:5173/oauth-success?token=" + token);
    }
}
