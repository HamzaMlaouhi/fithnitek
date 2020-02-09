/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Personne;
import java.util.List;

/**
 *
 * @author Wassim
 */
public interface IPersonneService {
    public void AjouterPersonne(Personne p);
   public void ModifierPersonne(Personne p);
   public void SupprimerPersonne(Personne p);
   public List<Personne> AfficherPersonne();
    
}
