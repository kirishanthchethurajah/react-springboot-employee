package com.kira.employeespringboot.model;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Indexed;

import javax.persistence.Id;


@RequiredArgsConstructor
@Getter
public class Verification {
    @Id
    private String id;
    private final String userName;

}
