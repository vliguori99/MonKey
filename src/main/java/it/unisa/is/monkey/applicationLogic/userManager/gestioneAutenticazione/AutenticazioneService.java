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

        List<Utente> utenti = utenteDAO.getAllUtenti();
        for (Utente u : utenti) {
            if (username.equals(u.getUsername())) {
                if (password.equals(u.getPsw())) {
                    if (u.getAmministratore()) {
                        return u;
                    }
                    prodottoDAO.updateCartOwner(u.getId(), ip);
                    return u;
                }
                throw new UtenteNotLoggedException("Password errata");
            }
            throw new UtenteNotLoggedException("Username errato");
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