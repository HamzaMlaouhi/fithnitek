/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Categorie;
import java.util.List;

/**
 *
 * @author Wassim
 */
public interface ICategorieService {
   public void AjouterCategorie(Categorie ca);
   public void ModifierCategorie(Categorie ca);
   public void SupprimerCategorie(Categorie ca);
   public List<Categorie > AfficherCategorie();
}
