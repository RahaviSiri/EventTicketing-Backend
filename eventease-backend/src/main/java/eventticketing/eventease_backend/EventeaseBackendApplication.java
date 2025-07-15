package eventticketing.eventease_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import eventticketing.eventease_backend.filters.AuthFilter;

@SpringBootApplication
public class EventeaseBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventeaseBackendApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<AuthFilter> authFilterRegistration() {
		FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new AuthFilter());
		registrationBean.addUrlPatterns("/api/organizers/*"); 
		// Apply to your protected endpoints
		return registrationBean;
	}

}
