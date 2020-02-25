/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Colis;
import Entities.Element;
import Entities.Livraison;
import java.util.List;

/**
 *
 * @author yassine bayoudh
 */
public interface IColisService {
   public void AjouterColis(Colis c, List<Element> elements);
   public void ModifierColis(Colis c);
   public void SupprimerColis(Colis c);
   public List<Colis> AfficherColis();
   public void SendNotification(String content,int idColis , int idLiv , int idFrom , int idTo);
   public Colis getDetailColis(int idColis);
}