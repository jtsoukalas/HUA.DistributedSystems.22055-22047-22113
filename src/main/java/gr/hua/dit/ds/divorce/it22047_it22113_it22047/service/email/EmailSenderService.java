package gr.hua.dit.ds.divorce.it22047_it22113_it22047.service.email;

import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.Divorce;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.DivorceStatementChoice;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.Faculty;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.exceptions.user.UserWithWrongRoleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

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

    public void emailParties(Divorce divorce, EmailOption emailOption){
        List<Faculty> faculties = Faculty.getFaculties();
        faculties.remove(Faculty.LAWYER_LEAD);
        faculties.forEach(f->{
            emailParty(divorce, f, emailOption);
        });
    }

    public void emailParty(Divorce divorce, Faculty faculty, EmailOption emailOption){
        String toEmail = null;
        try {
            toEmail = divorce.getUserFromStatements(faculty).getEmail();
            if(!divorce.getStatement(faculty).equals(DivorceStatementChoice.PENDING)){
                return;
            }
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
        } else if (emailOption.equals(EmailOption.DIVORCE_DELETED)){
            subject = "Divorce Service";
            body = "You have a new message from the divorce service. Please check your account.";
        } else if (emailOption.equals(EmailOption.DIVORCE_READY_FOR_NOTARIAL_ACT)){
            subject = "Divorce Ready for Notarial Act";
            body = "Your divorce is ready for notarial act. Please check your account.";
        } else {
            return;
        }

        sendSimpleEmail(toEmail, subject, body);
    }
}

