package DAO.API;

import DAO.Exploit.*;

import java.util.*;

public interface EdtDAO {



    void Migration();

    Enseignant GetEnseignant(int id);
    Salle GetSalle(int id);
    Cours GetCours(Date date, int idGroupe, int horaire);
    Matiere GetMatiere(int id);
    Groupe GetGroupe(int id);
    List<Cours> GetEmploiDuTemps(Date date, int idGroupe);
    void AddCours(Cours cours);





}
