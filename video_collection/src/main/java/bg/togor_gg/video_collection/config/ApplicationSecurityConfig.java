package bg.togor_gg.video_collection.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests().
                // allow access to static resources to anyone
                        requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll().
                antMatchers("/js/**", "/css/**", "/img/**").permitAll().
                // allow access to index, user login and registration to anyone "/cloud","/add"
                        antMatchers("/", "/users/login", "/users/register").permitAll().
                // protect all other pages
                        antMatchers("/articles/add").hasRole("ADMIN").
                antMatchers("/**").authenticated().
                and().
                // configure login with HTML form
                        formLogin().
                // our login page will be served by the controller with mapping /users/login
                        loginPage("/users/login").
                // the name of the user name input field in OUR login form is username (optional)
                        usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY).
                // the name of the user password input field in OUR login form is password (optional)
                        passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY).
                // on login success redirect here
                        defaultSuccessUrl("/home").
                // on login failure redirect here
                        failureForwardUrl("/users/login-error").
                and().
                logout(logout -> logout.
                        // which endpoint performs logout, e.g. http://localhost:8080/logout (!this should be POST request - this is action anti CSF Attacks)
                                logoutUrl("/logout").
                        // where to land after logout
                                logoutSuccessUrl("/").
                        // remove the session from the server
                                invalidateHttpSession(true).
                        // delete the session cookie
                                deleteCookies("JSESSIONID"));


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.
//                userDetailsService().
//                passwordEncoder(passwordEncoder);
    }
}

