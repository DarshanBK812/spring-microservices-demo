package microservice.api_gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
		http.csrf().disable()
				.authorizeExchange(
						ex -> ex.pathMatchers("/actuator/health", "/actuator/info", "/actuator/gateway/**").permitAll() // ←
																														// allow
																														// gateway
																														// actuator
								.anyExchange().authenticated())
				.httpBasic();
		return http.build();
	}

	@Bean
	public MapReactiveUserDetailsService userDetailsService() {
		var user = User.withDefaultPasswordEncoder().username("user").password("user").build();
		return new MapReactiveUserDetailsService(user);
	}
}
