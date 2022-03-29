package it.unisa.is.monkey.applicationLogic.usermanager.gestioneaccountutente;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Utente;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente.UserNotDeletedException;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente.UserNotModifiedException;
import it.unisa.is.monkey.model.MySqlUtenteDao;

/**
 * La classe fornisce i metodi per la logica di business della gestione dell'utente.
 *
 * @author Emanuele zini
 */
public class AccountServiceUtente implements AccountServiceUtenteInterface {

  private MySqlUtenteDao utenteDao = new MySqlUtenteDao();

  /**
   * Permette la modifica di un utente.
   *
   * @param id identifica l'id dell'utente
   * @param nome identifica il nome dell'utente
   * @param cognome identifica il cognome dell'utente
   * @param username identifica l'username dell'utente
   * @param email identifica l'email dell'utente
   * @param psw identifica la password dell'utente
   * @param indirizzo identifica l'indirizzo dell'utente
   * @param numCarta identifica il numero di carta di credito dell'utente.
   * @throws UserNotModifiedException Eccezione nel caso in cui l'utente non viene modificato
   */

  @Override
public void modificaUtente(String id, String nome, String cognome, String username, String email,
      String psw, String indirizzo, String numCarta) throws UserNotModifiedException {

    Utente u = utenteDao.getUtente(id);
    if (utenteDao.duplicateCheck(username, email)) {
      if (!email.equals(u.getEmail()) || !username.equals(u.getUsername())) {
        throw new UserNotModifiedException("email o username già utilizzati");
      }
    }

    Utente utente = new Utente(id, nome, cognome, username, email, psw, indirizzo, numCarta, false);
    utenteDao.updateUtente(utente);
  }

  /**
   *Permette l'eliminazione di un account.
   *
   * @param utente Identifica l'utente
   * @throws UserNotDeletedException Eccezione nel caso in cui l'utente non sia eliminato
   */
  @Override
public void eliminaAccount(Utente utente) throws UserNotDeletedException {
    if (utente == null) {
      throw new UserNotDeletedException();
    }
    utenteDao.deleteUtente(utente.getId());
  }
}
