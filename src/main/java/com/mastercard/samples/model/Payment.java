package com.mastercard.samples.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class Payment {

    private Long id;
    private Card card;
    private String item;
    private Boolean success;
}
