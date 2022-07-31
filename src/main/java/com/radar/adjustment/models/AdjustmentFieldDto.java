package com.radar.adjustment.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AdjustmentFieldDto {

    private Integer fieldId;

    @NotNull
    private String fieldName;

    @NotNull
    private String fieldType;

    @NotNull
    private String fieldValue;

    @NotNull
    private String dataAsset;

//    @JsonBackReference
//    private AdjustmentRecordDto adjustmentRecordDto;
}
