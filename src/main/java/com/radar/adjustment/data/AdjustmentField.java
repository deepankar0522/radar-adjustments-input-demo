package com.radar.adjustment.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "adjustment_field")
public class AdjustmentField implements Serializable {
    @Id
    @Column(name = "field_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fieldId;

    @Column(name = "field_name", nullable = false, length = 50)
    private String fieldName;

    @Column(name = "field_type", nullable = false, length = 10)
    private String fieldType;

    @Column(name = "field_value", nullable = false)
    private String fieldValue;

    @Column(name = "data_asset", nullable = false)
    private String dataAsset;

//    @ManyToOne(cascade = CascadeType.ALL)
//    private AdjustmentRecord adjustmentRecord;
}
