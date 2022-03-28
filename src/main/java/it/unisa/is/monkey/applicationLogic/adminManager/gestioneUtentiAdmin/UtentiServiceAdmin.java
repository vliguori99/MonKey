package it.unisa.is.monkey.applicationLogic.adminManager.gestioneUtentiAdmin;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Utente;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente.UserNotDeletedException;
import it.unisa.is.monkey.model.MySqlUtenteDao;

public class UtentiServiceAdmin implements UtentiServiceAdminInterface{

    private MySqlUtenteDao utenteDAO = new MySqlUtenteDao();

    @Override
    public void rimozioneUtente(String utente) throws UserNotDeletedException {
        if (utente == null){
            throw new UserNotDeletedException("Utente non valido");
        }
        utenteDAO.deleteUtente(utente);
    }

    @Override
    public Utente modificaUtenteAdmin(String idUtente, boolean admin) {

        Utente daModificare = utenteDAO.getUtente(idUtente);

        if(daModificare.getAmministratore() != admin){
            daModificare.setAmministratore(admin);
            utenteDAO.updateUtente(daModificare);
        }
        return daModificare;
    }


}


