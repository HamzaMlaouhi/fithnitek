/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Reclamation;
import java.sql.SQLException;

import java.util.List;

/**
 *
 * @author HP
 */
public interface IReclamationService {
    public void ajouterReclamation (Reclamation r);
    public void supprimerReclamation (int id);
    public Reclamation rechercherReclamation (Reclamation r);
    public List<Reclamation> displayReclamation();
    public int CountService(String Service);
    public List<Reclamation> trier() throws SQLException;
    public void EnableEtat(Reclamation r);
    public List<Reclamation> ListerReclamation ();
    
}
