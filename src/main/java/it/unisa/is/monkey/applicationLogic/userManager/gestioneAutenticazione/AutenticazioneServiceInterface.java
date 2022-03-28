package it.unisa.is.monkey.applicationLogic.userManager.gestioneAutenticazione;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Utente;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente.LogoutFailedException;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente.UtenteNotLoggedException;

/**
 * Questa interfaccia fornisce i metodi per l'autenticazione.
 */
public interface AutenticazioneServiceInterface {

  Utente login(String username, String password, String ip) throws UtenteNotLoggedException;

  void logout(Utente utente) throws LogoutFailedException;
}
