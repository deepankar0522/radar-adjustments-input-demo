package com.radar.adjustment.data;

import com.radar.adjustment.models.AdjustmentStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "adjustment")
@NamedQuery(name = "Adjustment.findAll", query = "SELECT a FROM Adjustment a")
public class Adjustment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adjustment_id")
    private Integer adjustmentId;

    @Temporal(TemporalType.DATE)
    @Column(name="adjustment_report_date", nullable = false)
    private Date adjustmentReportedDate;

    @Column(name = "adjustment_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private AdjustmentStatusEnum adjustmentStatus;

    @Column(name = "created_by", nullable = false, length = 10)
    private String createdBy;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @Column(name = "region", nullable = false, length = 10)
    private String region;

    @Column(name = "reason_for_adjustment", nullable = false)
    private String reasonForAdjustment;

    @OneToMany(targetEntity = AdjustmentRecord.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "adjustment_id", referencedColumnName = "adjustment_id")
    private List<AdjustmentRecord> adjustmentRecords;

    @Column(name = "adjustment_comment")
    private String comment;
}
