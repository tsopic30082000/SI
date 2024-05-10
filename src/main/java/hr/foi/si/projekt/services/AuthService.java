package hr.foi.si.projekt.services;

import hr.foi.si.projekt.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import hr.foi.si.projekt.repositories.UserRepository;
import hr.foi.si.projekt.models.User;

import java.util.List;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<User> login(String email, String password) {

        String sql = "SELECT * FROM [user] WHERE email = '" + email + "' AND password = '" + password + "'";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

}
