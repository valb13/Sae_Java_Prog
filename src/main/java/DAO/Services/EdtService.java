package DAO.Services;
import DAO.Exploit.*;

import DAO.API.EdtDAO;

import java.io.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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




    public List<Cours> GetEmploiDuTemps(Date date, int idGroupe){

        double duree;
        boolean[] horaire = new boolean[24];
        Enseignant enseignant = new Enseignant();
        Salle salle = new Salle();
        Groupe groupe = new Groupe();
        List<Cours> emploiDuTemps = new ArrayList<>();
        Cours cours = new Cours();

        String stm = "select * from cours where idGroupe = " + idGroupe + " and journee = " + date + " Order by horaire;";

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
            ResultSet resultSet = pst.executeQuery();
            System.out.println("migration réussie");

            while(resultSet.next()){
                cours.setDate(date);
                cours.setDebutCours(resultSet.getInt("horaire"));
                duree = resultSet.getDouble("duree");
                for(int i = (cours.getDebutCours()-8)*2; i <= duree*2; i++ ){
                    horaire[i] = true;
                }
                cours.setHoraire(horaire);
                cours.setIdEnseigant(resultSet.getInt("idEnseignant"));
                cours.setIdGoupe(resultSet.getInt("idSuit"));
                cours.setIdMatiere(resultSet.getInt("idMatiere"));
                emploiDuTemps.add(cours);
            }

        }catch(SQLException e){

            System.out.println(e);
            System.exit(-1);
        }
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
    public Enseignant GetEnseignant(int id) {

        Enseignant enseignant =  new Enseignant();

        String stm = " Select * from Enseignant";

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
            ResultSet resultSet = pst.executeQuery();
            System.out.println("opération réussie");

            while(resultSet != null){
                enseignant.setIdEnseignant(resultSet.getInt("idEnseignant "));
                enseignant.setName(resultSet.getString("nom"));
                enseignant.setPrenom(resultSet.getString("prenom"));
                enseignant.setNbHeures(resultSet.getDouble("nbHeure"));
            }

        }catch(SQLException e){

            System.out.println(e);
            System.exit(-1);
        }

        return enseignant;
    }

    @Override
    public Salle GetSalle(int id) {

        Salle salle = new Salle();

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
        return salle;
    }

    @Override
    public Groupe GetGroupe(int id) {

        Groupe groupe = new Groupe();
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
        return groupe;
    }

    @Override
    public Cours GetCours(Date date, int idGroupe, int debutCours) {
        Cours cours = new Cours();
        double duree;
        boolean[] horaire = new boolean[24];
        Enseignant enseignant = new Enseignant();
        Salle salle = new Salle();
        Groupe groupe = new Groupe();

        String stm = "select duree, journee, horaire from cours where idGroupe = " + idGroupe + " and journee = " + date + " and horaire = " + debutCours +";";

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
            ResultSet resultSet = pst.executeQuery();
            System.out.println("migration réussie");

            while(resultSet.next()){
                cours.setDate(date);
                cours.setDebutCours(resultSet.getInt("horaire"));
                duree = resultSet.getDouble("duree");
                for(int i = (cours.getDebutCours()-8)*2; i <= duree*2; i++ ){
                    horaire[i] = true;
                }
                cours.setHoraire(horaire);
                cours.setIdEnseigant(resultSet.getInt("idEnseignant"));
                cours.setIdGoupe(resultSet.getInt("idSuit"));
                cours.setIdMatiere(resultSet.getInt("idMatiere"));
            }

        }catch(SQLException e){

            System.out.println(e);
            System.exit(-1);
        }
        return cours;
    }

    @Override
    public Matiere GetMatiere(int id) {
        return null;
    }


    @Override
    public void AddCours(Cours cours) {
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
