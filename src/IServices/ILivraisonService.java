/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Livraison;
import java.util.List;

/**
 *
 * @author HP
 */
public interface ILivraisonService {
   public void AjouterLivraison(Livraison l);
   public void ModifierLivraison(Livraison l);
   public void SupprimerLivraison(Livraison l);
   public List<Livraison> AfficherLivraison();
   public List<Livraison> AfficherLivraisonPerson(int idP);
   public List<Livraison> RechercherLivraison(String depart, String dest);
   public List<Livraison> RechercherLivraisonId(int id);
    
}
