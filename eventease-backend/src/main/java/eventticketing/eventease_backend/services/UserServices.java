package eventticketing.eventease_backend.services;

import java.util.regex.Pattern;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eventticketing.eventease_backend.enums.Role;
import eventticketing.eventease_backend.exceptions.EtAuthException;
import eventticketing.eventease_backend.models.User;
import eventticketing.eventease_backend.repositries.UserRepository;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(String name, String email, String password) {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        
        if (email == null || !pattern.matcher(email.toLowerCase()).matches()) {
            throw new EtAuthException("Invalid email format");
        }

        email = email.toLowerCase();

        Long count = userRepository.countByEmail(email);
        if (count != null && count > 0) {
            throw new EtAuthException("Email already exists");
        }

        if (password == null || password.length() < 8) {
            throw new EtAuthException("Password must be at least 8 characters long");
        }

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));
        User user = User.builder()
                        .name(name)
                        .email(email)
                        .password(hashedPassword)
                        .role(Role.ATTENDEE) // default role; change if needed
                        .build();

        return userRepository.save(user);
    }

    public User validateUser(String email, String password) {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        
        if (email == null || !pattern.matcher(email.toLowerCase()).matches()) {
            throw new EtAuthException("Invalid email format");
        }

        email = email.toLowerCase();
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new EtAuthException("Invalid email or password");
        }

        if (!BCrypt.checkpw(password, user.getPassword())) {
            throw new EtAuthException("Invalid email or password");
        }
        return user;
    }

    public User changeUserRole(Long userId, String newRoleStr) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Role currentRole = user.getRole();
        Role newRole;
        try {
            newRole = Role.valueOf(newRoleStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid role: " + newRoleStr);
        }
        if (currentRole != newRole) {
            if ((currentRole == Role.ATTENDEE && newRole == Role.ORGANIZER) ||
                (currentRole == Role.ORGANIZER && newRole == Role.ATTENDEE)) {
                user.setRole(Role.BOTH);
            } else if (currentRole == Role.BOTH) {
                // Already has both roles, optional log
            } else {
                user.setRole(newRole);
            }

            userRepository.save(user);
        }
        return user;
    }
}
