package net.codejava.javaee.bookstore.SpringSecurity;


import net.codejava.javaee.bookstore.model.Roles;
import net.codejava.javaee.bookstore.model.User;
import net.codejava.javaee.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        // с помощью нашего сервиса UserService получаем User
        User user = null;
        try {
            user = userService.find(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // указываем роли для этого пользователя
       // Roles roles111 = user.getRoles();
       /* Set<Roles> roles1;

      roles1 = user.getRoles();

        HashSet<GrantedAuthority>roles = new HashSet<>(roles1.size());

        for (Roles role : roles1)
            roles.add(new SimpleGrantedAuthority(role.getRoles()));*/



        // на основании полученных данных формируем объект UserDetails
        // который позволит проверить введенный пользователем логин и пароль
        // и уже потом аутентифицировать пользователя

        return new org.springframework.security.core.userdetails.User(Objects.requireNonNull(user).getUsername(),
                user.getPassword(),
                user.getAuthorities());
    }

}