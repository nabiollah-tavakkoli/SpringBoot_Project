package biz.aeffegroup.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import biz.aeffegroup.service.UsersDetailsService;




@EnableWebSecurity
@Configuration
public class UserSecurityConfiguration{
	
	@Autowired
	private UsersDetailsService usersDetailsService;
	
//	@Bean
//	InMemoryUserDetailsManager userDetailService(PasswordEncoder passwordEncoder) throws Exception{
//		UserDetails user = User
//				.withUsername("user")
//				.password(passwordEncoder.encode("users"))
//				.roles("USER")
//				.build();
//		UserDetails admin = User
//				.withUsername("admin")
//				.password(passwordEncoder.encode("admin"))
//				.roles("USER", "ADMIN")
//				.build();
//		return new InMemoryUserDetailsManager(user, admin);
//	}
	
	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(usersDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		
		return provider;
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests(requests -> requests
                		.antMatchers("/h2-console/**").permitAll()
                		.antMatchers("/member/**").permitAll()
                		.antMatchers(
                				HttpMethod.GET
//                				"/info/requestedread/**",
//                				"/client/read/**",
//                				"/developer/read/**",
//                				"/office/read/**",
//                				"/responsible/read/**"
                				).hasAuthority("user")
                		.antMatchers(
                				HttpMethod.DELETE
//                				"/client/create/**",
//                				"/client/update/**",
//                				"/client/delete/**",
//                				"/developer/create/**",
//                				"/developer/update/**",
//                				"/developer/delete/**",
//                				"/office/create/**",
//                				"/office/update/**",
//                				"/office/delete/**",
//                				"/responsible/create/**",
//                				"/responsible/update/**",
//                				"/responsible/delete/**"
                				).hasAuthority("admin")
                		.antMatchers(
                				HttpMethod.POST).hasAnyAuthority("admin")
                		.antMatchers(
                				HttpMethod.PUT).hasAnyAuthority("admin"))
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
				.httpBasic(Customizer.withDefaults());

        return http.build();

	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);
				//PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
    
}
