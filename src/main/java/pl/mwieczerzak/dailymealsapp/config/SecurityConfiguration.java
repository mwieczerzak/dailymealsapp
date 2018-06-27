package pl.mwieczerzak.dailymealsapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("mati").password("mateusz").roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password("admin123").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("user").password("user123").roles("ADMIN","DBA");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/main").permitAll()
                .antMatchers("/meals/**").access("hasRole('USER')")
                .antMatchers("/admin/**").access("hasRole('ADMIN') and hasRole('DBA')")
                .and().formLogin()
                .and().logout()
                .and().exceptionHandling().accessDeniedPage("/error");

    }

}
