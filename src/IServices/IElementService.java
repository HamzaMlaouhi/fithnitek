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
    public void AjouterElemnet(Element e);
   public void ModifierElement(Element e);
   public void SupprimerElement(Element e);
   public List<Element> AfficherListElementColis(int id);
   public List<Element > AfficherElement();
    
}