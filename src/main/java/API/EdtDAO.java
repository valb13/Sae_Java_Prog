package API;

import Exploit.Cours;

import java.util.*;

public interface EdtDAO {



    void Migration();
    void GetDispoEnseignant(int idEnseignant);
    void GetDispoSalle(int idSalle);
    List<Cours> GetHeuresCours(int idGroupe);

    List<Cours> GetEmploiDuTemps(Date date);
    void AddHeureCours(Cours heure);





}
