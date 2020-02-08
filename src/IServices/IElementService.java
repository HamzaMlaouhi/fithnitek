/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Categorie;
import Entities.Element;
import java.util.List;

/**
 *
 * @author abder
 */
public interface IElementService {
    public void AjouterElemnet(Categorie ca);
   public void ModifierElement(Categorie ca);
   public void SupprimerElement(Categorie ca);
   public List<Element > AfficherElement();
    
}
