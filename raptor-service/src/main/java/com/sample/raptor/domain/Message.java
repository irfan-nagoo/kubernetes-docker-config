package com.sample.raptor.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

/**
 * @author irfan.nagoo
 */

@AllArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Message {

    @NotEmpty
    private final String phoneNumber;

    @NotEmpty
    private final String text;
}
