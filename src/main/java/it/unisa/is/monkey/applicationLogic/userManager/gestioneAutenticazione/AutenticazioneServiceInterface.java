package it.unisa.is.monkey.applicationLogic.userManager.gestioneAutenticazione;

import it.unisa.is.monkey.applicationLogic.monkeyEntita.Utente;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente.LogoutFailedException;
import it.unisa.is.monkey.applicationLogic.monkeyErrore.erroreUtente.UtenteNotLoggedException;

public interface AutenticazioneServiceInterface {

    Utente login(String username, String password) throws UtenteNotLoggedException;

    void logout(Utente utente) throws LogoutFailedException;
}
