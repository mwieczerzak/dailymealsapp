package pl.mwieczerzak.dailymealsapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.mwieczerzak.dailymealsapp.entity.User;
import pl.mwieczerzak.dailymealsapp.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("customUserDetailsService")
@Transactional
public class CustomUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException
    {
        User user = userRepository.findUserByLogin(s);
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
                true, true, true, true,
                s.equals("mati") ? getAdminAuthorities() : getUserAuthorities());
    }

    private List<GrantedAuthority> getAdminAuthorities(){
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return authorities;
    }

    private List<GrantedAuthority> getUserAuthorities() {
        return getAdminAuthorities().stream()
                .filter(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_USER"))
                .collect(Collectors.toList());
    }
}
