package com.radar.adjustment.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "adjustment_record")
public class AdjustmentRecord  {

    @Id
    @Column(name = "record_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recordId;

    @OneToMany(targetEntity = AdjustmentField.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "record_id", referencedColumnName = "record_id")
    private List<AdjustmentField> adjustmentFields;

//    @ManyToOne(cascade = CascadeType.ALL)
//    private Adjustment adjustment;

}

