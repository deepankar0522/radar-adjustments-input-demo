package com.radar.adjustment.models;

import com.fasterxml.jackson.annotation.JsonValue;

public enum AdjustmentStatusEnum {

    DRAFT(("DRAFT")),
    SUBMITTED(("SUBMITTED")),
    APPROVED(("APPROVED")),
    REJECTED(("REJECTED"));
    private String value;

    private AdjustmentStatusEnum(String value) {
        this.value = value;
    }

    @JsonValue
    public String value(){ return value; }
}
