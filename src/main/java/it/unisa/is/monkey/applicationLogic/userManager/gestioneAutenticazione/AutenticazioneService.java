package it.unisa.is.monkey.applicationLogic.userManager.gestioneAutenticazione;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Utente;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente.LogoutFailedException;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente.UtenteNotLoggedException;
import it.unisa.is.monkey.model.MySQLProdottoDAO;
import it.unisa.is.monkey.model.MySQLUtenteDAO;

import java.util.List;

public class AutenticazioneService implements AutenticazioneServiceInterface {

    private MySQLUtenteDAO utenteDAO = new MySQLUtenteDAO();
    private MySQLProdottoDAO prodottoDAO = new MySQLProdottoDAO();

    @Override
    public Utente login(String username, String password, String ip) throws UtenteNotLoggedException {
        if (username == null) {
            throw new UtenteNotLoggedException("username errato");
        }
        if (password == null) {
            throw new UtenteNotLoggedException("password errata");
        }

        List<Utente> utenti = utenteDAO.getAllUtenti();
        for (Utente u : utenti) {
            if (username.equals(u.getUsername()) && password.equals(u.getPsw())) {
                if (u.getAmministratore()) {
                    return u;
                }
                prodottoDAO.updateCartOwner(u.getId(), ip);
                return u;
            }
        }
        return null;
    }

    @Override
    public void logout(Utente utente) throws LogoutFailedException {
        if (utente != null) {
            throw new LogoutFailedException();
        }
    }
}