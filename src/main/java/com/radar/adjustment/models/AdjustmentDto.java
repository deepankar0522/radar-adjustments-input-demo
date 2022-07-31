package com.radar.adjustment.models;

import com.fasterxml.jackson.annotation.*;
import com.radar.adjustment.data.Adjustment;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@JsonIdentityInfo(generator =  ObjectIdGenerators.PropertyGenerator.class, property = "adjustmentId")
public class AdjustmentDto {
    private Integer adjustmentId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull
    private Date adjustmentReportedDate;

    @Valid
    @NotNull
    private AdjustmentStatusEnum adjustmentStatus;

    @Valid
    private User user;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd")
    private Date createdDate;

    @NotNull
    private String region;

    @NotNull
    private String reasonForAdjustment;

    @NotNull
    @Valid
    @Size(min = 1)
    private List<AdjustmentRecordDto> adjustmentRecords = new ArrayList<>();

    private String comment;

}
