package gr.hua.dit.ds.divorce.it22047_it22113_it22047.service.email;

import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.Divorce;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.Faculty;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.exceptions.user.UserWithWrongRoleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(String toEmail,
                                String subject,
                                String body
    ) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("Divorce Service<divorceservicehua@gmail.com>");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);
        System.out.println("Mail Send...");
    }

    public void emailParty(Divorce divorce, Faculty faculty, EmailOption emailOption){
        String toEmail = null;
        try {
            toEmail = divorce.getUser(faculty).getEmail();
        } catch (UserWithWrongRoleException e) {
            return;
        }
        String subject;
        String body;

        if (emailOption.equals(EmailOption.NEW_DIVORCE)){
            subject = "New Divorce";
            body = "You have a new divorce request. Please check your account.";
        } else if (emailOption.equals(EmailOption.STATUS_CHANGED)){
            subject = "Divorce Status Changed";
            body = "The status of your divorce has changed. Please check your account.";
        } else if (emailOption.equals(EmailOption.READY_FOR_NOTARY)){
            subject = "Divorce Ready for Notary";
            body = "Your divorce is ready for notary. Please check your account.";
        } else {
            subject = "Divorce Service";
            body = "You have a new message from the divorce service. Please check your account.";
        }

        sendSimpleEmail(toEmail, subject, body);
    }
}

