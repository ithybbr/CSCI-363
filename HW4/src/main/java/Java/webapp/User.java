package Java.webapp;

import jakarta.persistence.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;

@Entity
@Table(name = "users")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;
    private String salt;

    public User(String username, String password) {
        this.username = username;
        this.salt = RandomStringUtils.random(31);
        this.password = DigestUtils.sha1Hex(password + salt);
    }

    public User() {

    }

    public Boolean checkPassword(String password) {
        return DigestUtils.sha1Hex(password + salt).equals(this.password);
    }

    public String getUsername() { return username; }
    public Long getId() { return id; }
}
