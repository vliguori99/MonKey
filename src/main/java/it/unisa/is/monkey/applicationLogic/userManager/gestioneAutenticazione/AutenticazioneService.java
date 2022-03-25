package it.unisa.is.monkey.applicationLogic.userManager.gestioneAutenticazione;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Utente;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente.LogoutFailedException;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente.UtenteNotLoggedException;
import it.unisa.is.monkey.model.MySQLProdottoDAO;
import it.unisa.is.monkey.model.MySQLUtenteDAO;

import java.util.ArrayList;
import java.util.List;

public class AutenticazioneService implements AutenticazioneServiceInterface {

    private MySQLUtenteDAO utenteDAO = new MySQLUtenteDAO();
    private MySQLProdottoDAO prodottoDAO = new MySQLProdottoDAO();


    @Override
    public Utente login(String username, String password, String ip) throws UtenteNotLoggedException {
        if (username == "") {
            throw new UtenteNotLoggedException("Username non corretto");
        }
        if (password == "") {
            throw new UtenteNotLoggedException("Password non corretta");
        }

        List<Utente> utenti = new ArrayList<Utente>();
        int contatore = 0;

        utenti = utenteDAO.getAllUtenti();
        for (Utente u : utenti) {
            contatore++;
            if (username.equals(u.getUsername())) {
                if (password.equals(u.getPsw())) {
                    prodottoDAO.updateCartOwner(u.getId(), ip);
                    return u;
                }
            } else if(contatore == utenti.size()) {
                throw new UtenteNotLoggedException("Email o password non corretti");
            }
        }
            return null;
    }

        @Override
        public void logout (Utente utente) throws LogoutFailedException {
            if (utente != null) {
                throw new LogoutFailedException();
            }
        }
    }