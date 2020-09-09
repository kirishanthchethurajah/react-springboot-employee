package com.kira.employeespringboot.dto;

import lombok.Data;

@Data
public class MailRequest {
    private String name;
    private String from;
    private String to;
    private String subject;

}
