package com.familyapp.application.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Password {
    @Column(name = "Password")
    private String password;
}
