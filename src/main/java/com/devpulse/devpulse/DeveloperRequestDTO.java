package com.devpulse.devpulse;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class DeveloperRequestDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Email must be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Skill is required")
    private String skill;

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public String getSkill(){
        return skill;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setSkill(String skill){
        this.skill = skill;
    }

}
