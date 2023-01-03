package DAO.Main;

import DAO.API.EdtDAO;
import DAO.Services.EdtService;
import DAO.Exploit.*;

//On ne passera à l'étape n+1 que si l'étape n aboutie
//si non on abandonne le programme


public class ProgrammePrinciapl {

    public static void main(String[] args) {



        EdtService exploitation = new EdtService();
        exploitation.Migration();






    }

}
