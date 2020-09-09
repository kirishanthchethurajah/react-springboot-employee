package com.kira.employeespringboot.model;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
@Data
public class FeedBack {
    @NotNull
    private String name;
    @NotNull
    @Email
    private String email;
    @NotNull
    @Min(10)
    private String feedback;

}
