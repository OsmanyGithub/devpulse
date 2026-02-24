package com.devpulse.devpulse;

public class DeveloperResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String skill;

    public DeveloperResponseDTO(Long id, String name, String email, String skill){
        this.id = id;
        this.name = name;
        this.email = email;
        this.skill = skill;
    }

    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public String getSkill(){
        return skill;
    }
}
