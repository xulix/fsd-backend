package com.fsd.mod.training.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class TrainingDto {
    private Long id;
    private String status;
    private Integer progress;
    private Float commissionAmount;
    private Integer rating;
    private Float avgRating = 0.0f;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String endDate;
    @JsonFormat(pattern = "HH:mm:ss")
    private String startTime;
    @JsonFormat(pattern = "HH:mm:ss")
    private String endTime;
    private Float amountReceived;
    private Long userId;
    private String userName;
    private Long mentorId;
    private String mentorName;
    private Long skillId;
    private String skillName;
    private Float fees;
}
