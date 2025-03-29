package com.demo.java.email.Service;

import com.demo.java.email.Entity.EmailDetails;
import org.springframework.stereotype.Service;

public interface EmailService {

    String sendMailService(EmailDetails details);
    String sendMailAttachmentService(EmailDetails details);

    String sendMailDynamicService(EmailDetails details);
}
