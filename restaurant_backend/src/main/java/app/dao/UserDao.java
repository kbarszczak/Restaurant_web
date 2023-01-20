package app.dao;

import model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends MongoRepository<User, String>, UserDetailsService {

    Optional<User> findByEmailEquals(String email);

    @Override
    default UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = findByEmailEquals(username);
        if(user.isEmpty()) throw new UsernameNotFoundException("The user with email '" + username + "' does not exist");

        User u = user.get();
        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        for(String role : u.getRoles()){
            roles.add(new SimpleGrantedAuthority(role));
        }
        return new org.springframework.security.core.userdetails.User(u.getEmail(), u.getPassword(), roles);
    }
}
