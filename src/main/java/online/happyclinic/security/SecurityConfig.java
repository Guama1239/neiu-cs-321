package online.happyclinic.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("userRepositoryUserDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(encoder());
    }

    @Bean // the instantiation creates a singleton
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/registerfacility/**", "/orders/**", "/primarytableview/**", "/orders/current/**", "/modify/**", "/h2-console/")
                .hasRole("USER")
                .and()
            .authorizeRequests()
                .antMatchers("/h2-console/**", "/primarytableview")
                .permitAll()
            .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/primarytableview")
                //.formLogin().loginPage("/login").defaultSuccessUrl("/registerfacility")
            .and()
                .logout().logoutSuccessUrl("/");

        http.csrf().disable(); // enable in production
        http.headers().frameOptions().disable(); // enable in production
}
}
