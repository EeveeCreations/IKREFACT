package nl.hsleiden.svdj8.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Random;

@Service
public class VerificationService {

    @Autowired
    private JavaMailSender javaMailSender;

    public String createVerificationCode() {
        String codeNumber = String.valueOf(new Random().nextInt(89999999) + 10000000);
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < codeNumber.length(); i++) {
            if (i == 4) {
                code.append(" ");
            }
            code.append(codeNumber.charAt(i));
        }
        return code.toString();
    }

    public void sendEmail(String email) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("noreply@svdj.nl");
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("Verificatie code SVDJ admin");
        String code = createVerificationCode();
        simpleMailMessage.setText("Gebruik deze code om u identiteit te bevestigen voor de SVDJ admin portal \n" + code + "\n \n- SVDJ");
        javaMailSender.send(simpleMailMessage);
    }

}
