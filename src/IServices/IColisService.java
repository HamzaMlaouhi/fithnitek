/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Colis;
import java.util.List;

/**
 *
 * @author yassine bayoudh
 */
public interface IColisService {
   public void AjouterColis(Colis c);
   public void ModifierColis(Colis c);
   public void SupprimerColis(Colis c);
   public List<Colis> AfficherColis();
}
