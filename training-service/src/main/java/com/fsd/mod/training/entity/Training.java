package com.fsd.mod.training.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "tbl_training")
public class Training extends BaseEntity {

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "progress")
    private Integer progress = 0;

    @Column(name = "fees")
    private Float fees = 0.0f;

    @Column(name = "commission_amount")
    private Float commissionAmount = 0.0f;

    @Column(name = "rating")
    private Integer rating = 0;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "start_date")
    private String startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "end_date")
    private String endDate;

    @JsonFormat(pattern = "HH:mm:ss")
    @Column(name = "start_time")
    private String startTime;

    @JsonFormat(pattern = "HH:mm:ss")
    @Column(name = "end_time")
    private String endTime;

    @Column(name = "amount_received")
    private Float amountReceived = 0.0f;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "mentor_id")
    private Long mentorId;

    @Column(name = "skill_id")
    private Long skillId;
}
