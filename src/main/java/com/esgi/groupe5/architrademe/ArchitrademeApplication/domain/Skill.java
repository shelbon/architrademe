package com.esgi.groupe5.architrademe.ArchitrademeApplication.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Skill {
    private final SkillId skillId;

    private String name;

    private ENUMNotion notion;

    private Date createdAt;

    private Date updatedAt;

    public Skill(SkillId skillId, String name, ENUMNotion notion) {
        this.skillId = skillId;
        this.name = name;
        this.notion = notion;
    }

    public Skill(SkillId skillId, String name, ENUMNotion notion, Date createdAt, Date updatedAt) {
        this.skillId = skillId;
        this.name = name;
        this.notion = notion;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
