package DAO.Main;

import DAO.API.EdtDAO;
import DAO.Services.EdtService;
import DAO.Exploit.*;

import java.util.Date;

//On ne passera à l'étape n+1 que si l'étape n aboutie
//si non on abandonne le programme


public class ProgrammePrinciapl {

    public static void main(String[] args) {



       /* EdtService exploitation = new EdtService();
        exploitation.Migration();*/

       /* Date date = new Date();

        boolean[] horaire = new boolean[24];
        Cours cours = new Cours(date, 1 ,2.5, horaire,1,1,1,1);
        service.AddCours(cours);
        cours.toString();*/
        EdtService service = new EdtService();
        Enseignant enseignant = new Enseignant();
        enseignant = service.GetEnseignant(1);
        System.out.println(enseignant.toString());





    }

}
