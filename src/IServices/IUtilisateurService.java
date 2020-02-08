/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Utilisateur;

/**
 *
 * @author Hamza Mlaouhi
 */
public interface IUtilisateurService {
    public void Sign_up(Utilisateur U);
    public void Log_in(Utilisateur U);
}
