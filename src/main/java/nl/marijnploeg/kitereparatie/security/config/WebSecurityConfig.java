package nl.marijnploeg.kitereparatie.security.config;

import lombok.AllArgsConstructor;
import nl.marijnploeg.kitereparatie.filter.JwtRequestFilter;
import nl.marijnploeg.kitereparatie.security.rolesAndPermissions.AppUserRole;
import nl.marijnploeg.kitereparatie.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    private final AppUserService appUserService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/authenticated").authenticated()
                .antMatchers("/authenticate").permitAll()
                .antMatchers("/api/v*/registration/**").permitAll()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(appUserService);
        return provider;
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails testUser1 = User.builder()
                .username("Marijn")
                .password(bCryptPasswordEncoder.encode("password"))
                .roles(String.valueOf(AppUserRole.EMPLOYEE))
                .build();

        UserDetails testUser2 = User.builder()
                .username("Hans")
                .password(bCryptPasswordEncoder.encode("password"))
                .roles(String.valueOf(AppUserRole.MANAGER))
                .build();

        UserDetails testUser3 = User.builder()
                .username("Michiel")
                .password(bCryptPasswordEncoder.encode("password"))
                .roles(String.valueOf(AppUserRole.CUSTOMER))
                .build();

        return new InMemoryUserDetailsManager(
                testUser1,
                testUser2,
                testUser3
        );
    }
}

