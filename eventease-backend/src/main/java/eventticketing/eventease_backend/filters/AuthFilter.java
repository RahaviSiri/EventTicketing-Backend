package eventticketing.eventease_backend.filters;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import eventticketing.eventease_backend.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthFilter extends GenericFilterBean {

    // GenericFilterBean is a base class provided by Spring for creating custom filters.

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        String authHeader = httpRequest.getHeader("Authorization");
        // This extracts the Authorization header from the request. It should look like : Authorization: Bearer <token>

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // Skip "Bearer "
            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(Constants.API_SECRET_KEY.getBytes()) // Ensure byte[]
                        .build()
                        .parseClaimsJws(token) // validate token
                        .getBody(); // get the claims (payload)

                httpRequest.setAttribute("userId", Long.parseLong(claims.get("userId").toString()));
            } catch (Exception e) {
                httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "Invalid/Expired Token");
                return;
            }
        } else {
            httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "Token must be provided with Bearer prefix");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
        // This is the core mechanism in a servlet filter that passes the request along the filter chain to the next filter or eventually to your controller.
    }
}

