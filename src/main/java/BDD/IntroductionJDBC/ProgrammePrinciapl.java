package BDD.IntroductionJDBC;

import Exploit.Exploitation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

//On ne passera à l'étape n+1 que si l'étape n aboutie
//si non on abandonne le programme


public class ProgrammePrinciapl {



    public static void main(String[] args) {


        Exploitation exploitation = new Exploitation();
        exploitation.Migration();



    }

}
