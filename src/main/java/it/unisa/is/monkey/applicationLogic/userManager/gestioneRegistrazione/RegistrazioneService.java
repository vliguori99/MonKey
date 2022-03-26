package it.unisa.is.monkey.applicationLogic.userManager.gestioneRegistrazione;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Utente;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente.UserNotRegisteredException;
import it.unisa.is.monkey.applicationLogic.userManager.MailSingletonSender;
import it.unisa.is.monkey.model.MySQLUtenteDAO;
import org.springframework.mail.javamail.JavaMailSender;

public class RegistrazioneService implements RegistrazioneServiceInterface {

    private MailSingletonSender mailSingletonSender = new MailSingletonSender();
    private MySQLUtenteDAO utenteDAO = new MySQLUtenteDAO();
    @Override
    public Utente registrazione(String nome, String cognome, String username, String email, String psw,
                                String indirizzo, String numCarta, boolean amministratore)
            throws UserNotRegisteredException {

        if (utenteDAO.duplicateCheck(username, email)) {
                throw new UserNotRegisteredException("email o username già registrati");
        }

        String id = utenteDAO.codUserGenerator();
        Utente utente = new Utente(id, nome, cognome, username, email, psw, indirizzo, numCarta, amministratore);
        utenteDAO.createUtente(utente);
        mailSingletonSender.sendEmailCreazioneAccount(utente);

        return utenteDAO.getUtente(id);
    }
}
