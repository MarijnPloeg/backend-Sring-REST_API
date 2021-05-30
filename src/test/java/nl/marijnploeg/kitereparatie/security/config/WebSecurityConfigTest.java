package nl.marijnploeg.kitereparatie.security.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.List;

import nl.marijnploeg.kitereparatie.filter.JwtRequestFilter;
import nl.marijnploeg.kitereparatie.service.AppUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AppUserService.class, JwtRequestFilter.class, WebSecurityConfig.class,
        BCryptPasswordEncoder.class, AuthenticationManagerBuilder.class})
@ExtendWith(SpringExtension.class)
public class WebSecurityConfigTest {
    @Autowired
    private ObjectPostProcessor<Object> objectPostProcessor;

    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Test
    public void testConfigure() throws Exception {
        WebSecurityConfig webSecurityConfig = new WebSecurityConfig(this.jwtRequestFilter, this.appUserService,
                this.appUserService, this.bCryptPasswordEncoder);
        webSecurityConfig.configure(this.authenticationManagerBuilder);
        assertTrue(this.authenticationManagerBuilder.getDefaultUserDetailsService() instanceof AppUserService);
        AuthenticationManager orBuild = this.authenticationManagerBuilder.getOrBuild();
        assertTrue(orBuild instanceof ProviderManager);
        List<AuthenticationProvider> providers = ((ProviderManager) orBuild).getProviders();
        assertEquals(70, providers.size());
        assertFalse(((DaoAuthenticationProvider) providers.get(69)).isForcePrincipalAsString());
        assertTrue(((DaoAuthenticationProvider) providers.get(69))
                .getUserCache() instanceof org.springframework.security.core.userdetails.cache.NullUserCache);
        assertTrue(((DaoAuthenticationProvider) providers.get(69)).isHideUserNotFoundExceptions());
    }

    @Test
    public void testConfigure2() throws Exception {
        WebSecurityConfig webSecurityConfig = new WebSecurityConfig(this.jwtRequestFilter, this.appUserService,
                this.appUserService, this.bCryptPasswordEncoder);
        HttpSecurity httpSecurity = new HttpSecurity(this.objectPostProcessor, this.authenticationManagerBuilder,
                new HashMap<Class<?>, Object>(1));
        webSecurityConfig.configure(httpSecurity);
        assertSame(httpSecurity, httpSecurity.cors().and());
    }

    @Test
    public void testDaoAuthenticationProvider() {
        DaoAuthenticationProvider actualDaoAuthenticationProviderResult = (new WebSecurityConfig(this.jwtRequestFilter,
                this.appUserService, this.appUserService, this.bCryptPasswordEncoder)).daoAuthenticationProvider();
        assertTrue(actualDaoAuthenticationProviderResult
                .getUserCache() instanceof org.springframework.security.core.userdetails.cache.NullUserCache);
        assertTrue(actualDaoAuthenticationProviderResult.isHideUserNotFoundExceptions());
        assertFalse(actualDaoAuthenticationProviderResult.isForcePrincipalAsString());
    }

    @Test
    public void testAuthenticationManagerBean() throws Exception {
//        TODO: Write this test
    }
}

