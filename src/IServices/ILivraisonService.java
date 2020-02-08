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
   public void AjouterColis(Livraison l);
   public void ModifierColis(Livraison l);
   public void SupprimerColis(Livraison l);
   public List<Livraison> AfficherLivraison();
    
}
