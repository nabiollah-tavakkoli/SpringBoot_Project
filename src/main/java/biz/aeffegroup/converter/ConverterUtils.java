package biz.aeffegroup.converter;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class ConverterUtils {

    @Bean
    ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
