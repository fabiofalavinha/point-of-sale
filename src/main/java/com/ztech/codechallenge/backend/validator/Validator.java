package com.ztech.codechallenge.backend.validator;

public interface Validator<T> {

    ValidationResult validate(T obj);

}
