/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Personne;
import Entities.Utilisateur;

/**
 *
 * @author Hamza Mlaouhi
 */
public interface IUtilisateurService {
   public void ModifierUtilisateur(Utilisateur u, Personne p);
   public void SupprimerUtilisateur();
   public void Sign_up(Utilisateur U , Personne p);
   public boolean Log_in(String username, String password);
}
