package com.dia.app.support.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @author tc
 * @Date 2018年5月22日
 * @Description 校验器的配置
 */
@Configuration
@EnableAutoConfiguration
public class ValidatorConfiguration {

	@Bean
	public Validator validator() {
		ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class).configure()
				.addProperty("hibernate.validator.fail_fast", "false").buildValidatorFactory();
		Validator validator = validatorFactory.getValidator();

		return validator;
	}

	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor() {
		// 默认是普通模式，会返回所有的验证不通过信息集合
		MethodValidationPostProcessor methodValidationPostProcessor = new MethodValidationPostProcessor();
		methodValidationPostProcessor.setValidator(validator());
		return methodValidationPostProcessor;
	}
}
