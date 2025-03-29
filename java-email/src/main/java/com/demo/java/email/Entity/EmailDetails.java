package com.demo.java.email.Entity;

import lombok.Data;

import java.io.File;

@Data
public class EmailDetails {
    private String recipient;
    private String msgBody;
    private String subject;
    private File attachment;
}
