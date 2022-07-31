package com.radar.adjustment.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@JsonIdentityInfo(generator =  ObjectIdGenerators.PropertyGenerator.class, property = "recordId")
public class AdjustmentRecordDto {

    private Integer recordId;

    @Valid
    @NotNull
    @Size(min = 1)
//    @JsonManagedReference
    private List<AdjustmentFieldDto> adjustmentFields = new ArrayList<>();

//    @JsonBackReference
//    private AdjustmentDto adjustmentDto;
}
