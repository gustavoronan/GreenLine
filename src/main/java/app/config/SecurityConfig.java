package app.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig  {

//trecho comentado para o uso do keycloack
//	@Autowired
//	private JwtAuthenticationFilter jwtAuthFilter;
//
//	@Autowired
//	private AuthenticationProvider authenticationProvider;
	

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http    
		.csrf(csrf -> csrf.disable())
		.oauth2ResourceServer(oauth2 -> oauth2
		.jwt(jwt -> jwt.jwtAuthenticationConverter(new JWTConverter())));

		return http.build();
//trecho comentado para o uso do keycloack
//		http    
//		.csrf(AbstractHttpConfigurer::disable)
//		.cors(AbstractHttpConfigurer::disable)
//		.authorizeHttpRequests((requests) -> requests
//				.requestMatchers("/api/usuario/login").permitAll()// pode ser acessador sem logar
//				.requestMatchers("/api/usuario/save").permitAll()// pode ser acessador sem logar
//				.requestMatchers("/api/produto/listAll").permitAll()// pode ser acessador sem logar
//				.requestMatchers("/api/categoria/listAll").permitAll()
//				.anyRequest().authenticated())
//		.authenticationProvider(authenticationProvider)
//		.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
//		.sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
//		return http.build();
	}
	

// trecho comentado para o uso do keycloack
//	@Bean
//	public FilterRegistrationBean<CorsFilter> corsFilter() {
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		CorsConfiguration config = new CorsConfiguration();
//		config.setAllowCredentials(true);
//		config.setAllowedOriginPatterns(Arrays.asList("*"));
//		config.setAllowedHeaders(Arrays.asList(HttpHeaders.AUTHORIZATION,HttpHeaders.CONTENT_TYPE,HttpHeaders.ACCEPT));
//		config.setAllowedMethods(Arrays.asList(HttpMethod.GET.name(),HttpMethod.POST.name(),HttpMethod.PUT.name(),HttpMethod.DELETE.name()));
//		config.setMaxAge(3600L);
//		source.registerCorsConfiguration("/**", config);
//		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(source));
//		bean.setOrder(-102);
//		return bean;
//	}
	

}
