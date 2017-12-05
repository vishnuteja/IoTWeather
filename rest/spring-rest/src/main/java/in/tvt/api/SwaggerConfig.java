package in.tvt.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api()
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build()
				.apiInfo(apiInfo())
				.useDefaultResponseMessages(false);
	}
	
	public ApiInfo apiInfo()
	{
		//Contact contact = new Contact(, "google.com", "vishnutejat@outlook.com");
		ApiInfo info = new ApiInfo("Spring-REST", 
				"API Documentation for Spring based RESTful API", 
				"1.0.0", 
				"http://google.com", "Vishnu", "MIT", ".edu");
		
		return info;
	}
}
