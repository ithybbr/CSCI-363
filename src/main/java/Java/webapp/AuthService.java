package Java.webapp;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@Service
public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public void authenticate(String Username, String Password, HttpSession session) throws AuthException {
        User user = userRepository.getByUsername(Username);
        if (user == null || !user.checkPassword(Password)) {
            throw new AuthException();
        }
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
        // Manually create an authenticated session
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user,
                null,
                authorities
        );
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
        session.setAttribute("SPRING_SECURITY_CONTEXT", context);
    }
    public boolean UsernameAvailable(String Username){
        User user = userRepository.getByUsername(Username);
        return user == null;
    }
    public void registerUser(String username, String password) {
        User user = new User(username, password);
        userRepository.save(user);
    }
    public static class AuthException extends Exception {}
}
