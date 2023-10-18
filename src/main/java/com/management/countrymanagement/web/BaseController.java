package com.management.countrymanagement.web;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

public interface BaseController {

    default Pageable getPageRequest(Integer inputPage, Integer inputSize){
        return PageRequest.of(getValueOrDefault(inputPage, 0), getValueOrDefault(inputSize, 10));
    }

    default Pageable getPageRequest(Integer inputPage, Integer inputSize, Sort sort){
        return PageRequest.of(getValueOrDefault(inputPage, 0), getValueOrDefault(inputSize, 10), sort);
    }

    default Integer getValueOrDefault(Integer value, Integer predefined){
        return Optional.ofNullable(value)
                .filter(n-> n > 0)
                .orElse(predefined);
    }
}
