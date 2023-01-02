package Services;


import API.EdtDAO;
import Exploit.Cours;

import java.io.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EdtService implements EdtDAO {

    //on renseigne le driver
    private String nomDriverJDBCDuSGBD = "com.mysql.cj.jdbc.Driver";

    //on renseigne les paramètres pour se connecter à la base de données
    private String urlBD = "jdbc:mysql://localhost:3306/Test_java_jdbc";
    private String user = "root";
    private String psswd = "basededonneemdp";




    public List<Cours> GetEmploiDuTemps(Date date){

        List<Cours> emploiDuTemps = new ArrayList<>();
        return emploiDuTemps;
    }

    @Override
    public void Migration() {

        //on récupere le script sur un fichier text externe
        String line = "";
        String stm = "";
        BufferedReader bufferedReader = null;

        //on charge le driver
        try {
            Class.forName(nomDriverJDBCDuSGBD);
            System.out.println("chargement du driver jdbc avec succés !");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        //on ouvre une connexion à notre base de donnée
        try (java.sql.Connection con = DriverManager.getConnection(urlBD, user, psswd)) {

            System.out.println("connexion ouverte");
            File file = new File("script_tables.txt");
            try {
                //On lit le fichier pour récupérer le script
                FileReader filereader = new FileReader(file);
                bufferedReader = new BufferedReader(filereader);

                while((line = bufferedReader.readLine()) != null ){
                    var value = line.split(";");
                    stm = value[0];
                    //on lance la migration initial pour créer nos tables en récupérant le script
                    PreparedStatement pst = con.prepareStatement(stm);
                    System.out.println(stm);
                }

            } catch (FileNotFoundException e) {
                System.out.println("fichier introuvable");
            }catch (IOException e){
                System.out.println("fichier non lus");
            }

            System.out.println("migration réussie");

        }catch(SQLException e){

            System.out.println(e);
            System.exit(-1);
        }
    }

    @Override
    public void GetDispoEnseignant(int idEnseignant) {
        String stm = "";

        //on charge le driver
        try {
            Class.forName(nomDriverJDBCDuSGBD);
            System.out.println("chargement du driver jdbc avec succés !");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        try (java.sql.Connection con = DriverManager.getConnection(urlBD, user, psswd)) {

            System.out.println("connexion ouverte");

            //on lance la migration initial pour créer nos tables
            PreparedStatement pst = con.prepareStatement(stm);
            pst.execute();
            System.out.println("migration réussie");

        }catch(SQLException e){

            System.out.println(e);
            System.exit(-1);
        }
    }

    @Override
    public void GetDispoSalle(int id) {

        String stm = "";

        //on charge le driver
        try {
            Class.forName(nomDriverJDBCDuSGBD);
            System.out.println("chargement du driver jdbc avec succés !");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        try (java.sql.Connection con = DriverManager.getConnection(urlBD, user, psswd)) {

            System.out.println("connexion ouverte");

            //on lance la migration initial pour créer nos tables
            PreparedStatement pst = con.prepareStatement(stm);
            System.out.println("migration réussie");

        }catch(SQLException e){

            System.out.println(e);
            System.exit(-1);
        }
    }

    @Override
    public List<Cours> GetHeuresCours(int idGroupe) {
        List<Cours> listeCours = new ArrayList<>();
        String stm = "";

        //on charge le driver
        try {
            Class.forName(nomDriverJDBCDuSGBD);
            System.out.println("chargement du driver jdbc avec succés !");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        try (java.sql.Connection con = DriverManager.getConnection(urlBD, user, psswd)) {

            System.out.println("connexion ouverte");

            //on lance la migration initial pour créer nos tables
            PreparedStatement pst = con.prepareStatement(stm);
            System.out.println("migration réussie");

        }catch(SQLException e){

            System.out.println(e);
            System.exit(-1);
        }
        return listeCours;
    }

    @Override
    public void AddHeureCours(Cours heure) {
        String stm = "";

        //on charge le driver
        try {
            Class.forName(nomDriverJDBCDuSGBD);
            System.out.println("chargement du driver jdbc avec succés !");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        try (java.sql.Connection con = DriverManager.getConnection(urlBD, user, psswd)) {

            System.out.println("connexion ouverte");

            //on lance la migration initial pour créer nos tables
            PreparedStatement pst = con.prepareStatement(stm);
            System.out.println("migration réussie");

        }catch(SQLException e){

            System.out.println(e);
            System.exit(-1);
        }
    }

}
