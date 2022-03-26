package it.unisa.is.monkey.applicationLogic.userManager;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Prodotto;
import it.unisa.is.monkey.applicationLogic.monkeyEntita.Utente;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;

public class MailSingletonSender {

    private JavaMailSender javaMailSender;

    public void sendEmailCreazioneAccount(Utente utente) {
        javaMailSender = getJavaMailSender();
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(utente.getEmail());
        msg.setSubject("Nuovo Account");
        String messaggio = ("Salve " + utente.getUsername() + ",\n\n" +
                "Creazione account avvenuta con successo.");
        msg.setText(messaggio);
        javaMailSender.send(msg);
    }

    public void sendEmailEliminazioneAccount(Utente utente) {
        javaMailSender = getJavaMailSender();
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(utente.getEmail());
        msg.setSubject("Account Eliminato");
        String messaggio = ("Salve " + utente.getUsername() + ",\n\n" +
                "Eliminazione account avvenuta con successo.");
        msg.setText(messaggio);
        javaMailSender.send(msg);
    }

    public void sendAcquistoAvvenuto(Utente utente, Prodotto prodotto) {
        String key = RandomKeyProduct.getAlphaNumericString(16);
        javaMailSender = getJavaMailSender();
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(utente.getEmail());
        msg.setSubject("Acquisto Avvenuto");
        String messaggio = ("Salve " + utente.getUsername() + ",\n\n" +
                "L'acquisto di: " + prodotto.getTitolo() + "(" + prodotto.getPiattaforma() + ")\n" +
                "è avvenuto con successo.\n" +
                "Codice prodotto: "+ key);
        msg.setText(messaggio);
        javaMailSender.send(msg);
    }


    public static JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setUsername("MonKeyStoreSalerno@gmail.com");
        mailSender.setPassword("VincenzoEmanueleGianmarcoIs");

        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.debug", "true");

        return mailSender;
    }
}
