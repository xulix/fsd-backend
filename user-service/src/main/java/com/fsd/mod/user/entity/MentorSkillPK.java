package com.fsd.mod.user.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
public class MentorSkillPK implements Serializable {
    private Long mentorId;
    private Long skillId;
}
