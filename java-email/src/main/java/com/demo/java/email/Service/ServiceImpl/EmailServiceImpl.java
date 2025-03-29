package com.demo.java.email.Service.ServiceImpl;

import com.demo.java.email.Entity.EmailDetails;
import com.demo.java.email.Service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.StringWriter;


@Service
public class EmailServiceImpl implements EmailService {

    private static String USER_NAME="Test User";

    @Value("${spring.mail.username}")
    private String sender;

    @Autowired
    private JavaMailSender javaMailSender;


    @Override
    public String sendMailService(EmailDetails details) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error while Sending Mail";
        }
    }

    @Override
    public String sendMailAttachmentService(EmailDetails details) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(sender);
            helper.setTo(details.getRecipient());
            helper.setSubject(details.getSubject());
            helper.setText(details.getMsgBody(), true);
            FileSystemResource file = new FileSystemResource(new File("/Users/dinesh/IdeaProjects/Freelancing/SendEmail/java-email/src/main/resources/test.jpg"));
            helper.addAttachment("test.jpg", file);
            helper.addAttachment("test_copy.jpg", new ClassPathResource("test.jpg"));
            javaMailSender.send(message);
            return "Mail Sent Successfully...";
        }catch (Exception e){
            e.printStackTrace();
            return "Error while Sending Mail";
        }
    }

    @Override
    public String sendMailDynamicService(EmailDetails details) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setFrom(sender);
            helper.setTo(details.getRecipient());
            helper.setSubject(details.getSubject());
            VelocityContext context = new VelocityContext();
            context.put("username", USER_NAME);
            StringWriter writer = new StringWriter();
            Velocity.evaluate(context, writer, "EvalError", details.getMsgBody());
            message.setText(writer.toString(), "UTF-8");
            javaMailSender.send(message);
            return "Mail Sent Successfully...";
        }catch (Exception e){
            e.printStackTrace();
            return "Error while Sending Mail";
        }
    }
}
