package it.unisa.is.monkey.applicationLogic.adminmanager.gestioneutentiadmin;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Utente;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente.UserNotDeletedException;
import it.unisa.is.monkey.model.MySqlUtenteDao;

/**
 * Classe che gestisce servizi admin.
 */
public class UtentiServiceAdmin implements UtentiServiceAdminInterface {
  private MySqlUtenteDao utenteDao = new MySqlUtenteDao();

  /**
   * metodo che rimuove l'utente dal db.
   *
   * @param utente identifica l'utente
   * @throws UserNotDeletedException eccezione nel caso in cui utente non valido
   */
  @Override
  public void rimozioneUtente(String utente) throws UserNotDeletedException {
    if (utente == null) {
      throw new UserNotDeletedException("Utente non valido");
    }
    utenteDao.deleteUtente(utente);
  }

  @Override
  public Utente modificaUtenteAdmin(String idUtente, boolean admin) {
    Utente daModificare = utenteDao.getUtente(idUtente);
    if (daModificare.getAmministratore() != admin) {
      daModificare.setAmministratore(admin);
      utenteDao.updateUtente(daModificare);
    }
    return daModificare;
  }
}


