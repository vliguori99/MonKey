package it.unisa.is.monkey.applicationLogic.userManager.gestioneAccountUtente;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Utente;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente.UserNotDeletedException;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente.UserNotModifiedException;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente.UserNotRegisteredException;
import it.unisa.is.monkey.model.MySQLUtenteDAO;


public class AccountServiceUtente implements AccountServiceUtenteInterface{

    private MySQLUtenteDAO utenteDAO= new MySQLUtenteDAO();
    @Override
    public void modificaUtente(String id, String nome, String cognome, String username, String email,
                               String psw, String indirizzo, String numCarta) throws UserNotModifiedException {
        Utente u = utenteDAO.getUtente(id);
        if (utenteDAO.duplicateCheck(username, email)) {
            if (email.equals(u.getEmail()) || username.equals(u.getUsername())){

            }else {
                throw new UserNotModifiedException("email o username già registrati");
            }
        }

        Utente utente = new Utente(id, nome, cognome, username, email, psw, indirizzo, numCarta, false);
        utenteDAO.updateUtente(utente);
    }

    @Override
    public void eliminaAccount(Utente utente) throws UserNotDeletedException {
        if (utente == null){
            throw new UserNotDeletedException();
        }
        utenteDAO.deleteUtente(utente.getId());
    }
}
