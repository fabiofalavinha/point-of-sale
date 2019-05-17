package com.ztech.codechallenge.backend.parser;

public interface DataConverter<DTO, Model> {

    Model convertFrom(DTO data);
    DTO convertTo(Model model);
}
