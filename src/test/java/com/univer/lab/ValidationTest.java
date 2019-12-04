package com.univer.lab;

import com.univer.lab.model.Provider;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.Assert.assertFalse;

public class ValidationTest {

    private Validator validator;

    @Before
    public void setUp(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void createProvider(){
        Provider provider = new Provider();

        provider.setProviderId(1L);
        provider.setProviderName("Ze");

        Set<ConstraintViolation<Provider>> violations = validator.validate(provider);
        assertFalse(violations.isEmpty());
    }
}
