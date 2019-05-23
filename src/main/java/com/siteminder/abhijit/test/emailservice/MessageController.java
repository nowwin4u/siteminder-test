package com.siteminder.abhijit.test.emailservice;

import javax.xml.bind.ValidationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController {
    private  EmailConfig emailConfig;

    public MessageController(EmailConfig emailConfig){
      this.emailConfig= emailConfig;

    }


    @PostMapping
    public void sendEmail(@RequestBody EmailMessage emailMessage, BindingResult bindingResult) throws Exception{
      if(bindingResult.hasErrors()){
        throw new ValidationException("Invalid email message");
      }

      //Mail sending
      JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
      mailSender.setPassword(this.emailConfig.getPassword());
      mailSender.setUsername(this.emailConfig.getUserName());
      mailSender.setPort(this.emailConfig.getPort());
      mailSender.setHost(this.emailConfig.getHost());

      //Create Email
      SimpleMailMessage mailMessage = new SimpleMailMessage();
      mailMessage.setFrom(emailMessage.getEmail());
      //TODO
      //mailMessage.setTo(emailMessage.get);

      //TODO
      mailMessage.setText(emailMessage.getMessage());

      //Send mail
      mailSender.send(mailMessage);
    }


}
