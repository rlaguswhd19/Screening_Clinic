package com.hj.clinic.common;

import javax.validation.constraints.NotEmpty;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "my-app")
@Getter @Setter
public class AppProperties {
	
	@NotEmpty
	private String csvPath;
	
}
