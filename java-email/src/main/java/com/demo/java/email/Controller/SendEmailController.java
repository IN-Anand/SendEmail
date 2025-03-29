package com.demo.java.email.Controller;

import com.demo.java.email.Entity.EmailDetails;
import com.demo.java.email.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class SendEmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/without-attachment")
    public String sendMailHandler(@RequestBody EmailDetails details){
        return emailService.sendMailService(details);
    }

    @PostMapping("/with-attachment")
    public String sendMailAttachmentHandler(@RequestBody EmailDetails details){
        return emailService.sendMailAttachmentService(details);
    }


    @PostMapping("/dynamic")
    public String sendMailDynamicHandler(@RequestBody EmailDetails details){
        return emailService.sendMailDynamicService(details);
    }

}
