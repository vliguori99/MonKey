package it.unisa.is.monkey.applicationLogic.usermanager;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Utente;
import java.util.Properties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;


/**
 * Classe per l'invio della mail.
 */
public class MailSingletonSender {

  private JavaMailSender javaMailSender;

  /**
   * Metodo per l'invio della mail alla creazione dell'account.
   *
   * @param utente Un Utente
   */
  public void sendEmailCreazioneAccount(Utente utente) {
    javaMailSender = getJavaMailSender();
    SimpleMailMessage msg = new SimpleMailMessage();
    msg.setTo(utente.getEmail());
    msg.setSubject("Nuovo Account");
    String messaggio = ("Salve " + utente.getUsername() + ",\n\n"
            + "Creazione account avvenuta con successo.");
    msg.setText(messaggio);
    javaMailSender.send(msg);
  }

  /**
   * Metodo per prendere un JavaMailSender.
   *
   * @return un JavaMailSender
   */
  public static JavaMailSender getJavaMailSender() {
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    mailSender.setHost("smtp.gmail.com");
    mailSender.setUsername("MonKeyStoreSalerno@gmail.com");
    mailSender.setPassword("VincenzoEmanueleGianmarcoIs2022");

    Properties properties = mailSender.getJavaMailProperties();
    properties.put("mail.transport.protocol", "smtp");
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.debug", "true");

    return mailSender;
  }
}
