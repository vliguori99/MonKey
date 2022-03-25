package it.unisa.is.monkey.applicationLogic.userManager.gestioneAccountUtente;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Utente;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente.UserNotDeletedException;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente.UserNotModifiedException;
import it.unisa.is.monkey.model.MySQLUtenteDAO;


public class    AccountServiceUtente implements AccountServiceUtenteInterface{

    private MySQLUtenteDAO utenteDAO= new MySQLUtenteDAO();
    @Override
    public void modificaUtente(String id, String nome, String cognome, String username, String email,
                               String psw, String indirizzo, String numCarta) throws UserNotModifiedException {

        Utente daModificare = utenteDAO.getUtente(id);
        daModificare.setNome(nome);
        daModificare.setCognome(cognome);
        daModificare.setUsername(username);
        daModificare.setEmail(email);
        daModificare.setPsw(psw);
        daModificare.setIndirizzo(indirizzo);
        daModificare.setNumero_carta(numCarta);
        if (daModificare == null){
            throw new UserNotModifiedException("Account null. Impossibile modificare");
        }
        utenteDAO.updateUtente(daModificare);
    }

    //??
    @Override
    public void visualizzaUtente(Utente utente) {

    }

    @Override
    public void eliminaAccount(Utente utente) throws UserNotDeletedException {
        if (utente == null){
            throw new UserNotDeletedException();
        }
        utenteDAO.deleteUtente(utente.getId());
    }
}
