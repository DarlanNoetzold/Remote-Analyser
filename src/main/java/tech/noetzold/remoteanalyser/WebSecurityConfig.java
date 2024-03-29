package tech.noetzold.remoteanalyser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/css/**", "/js/**", "/resources/**", "/error").permitAll()
				.antMatchers("/home").hasRole("admin")
				.antMatchers("/home/**").hasRole("admin")
				.antMatchers("/badLanguage").hasRole("admin")
				.antMatchers("/badLanguage/**").hasRole("admin")
				.antMatchers("/maliciousPort").hasRole("admin")
				.antMatchers("/maliciousPort/**").hasRole("admin")
				.antMatchers("/maliciousProcess").hasRole("admin")
				.antMatchers("/maliciousProcess/**").hasRole("admin")
				.antMatchers("/maliciousWebsite").hasRole("admin")
				.antMatchers("/maliciousWebsite/**").hasRole("admin")
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/login").failureForwardUrl("/login-error")
				.defaultSuccessUrl("/login-success")
				.permitAll()
				.and()
				.logout()
				.permitAll();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
				.userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder());
	}

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);
	}
}