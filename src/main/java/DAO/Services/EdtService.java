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
    Connexion con = new Connexion();

    @Override
    public void Migration() {

        //on récupere le script sur un fichier text externe
        String line = null;
        String stm = null;
        BufferedReader bufferedReader = null;

        //on charge le driver


        //on ouvre une connexion à notre base de donnée
        try {


            File file = new File("script_tables.txt");
            try {
                //On lit le fichier pour récupérer le script
                FileReader filereader = new FileReader(file);
                bufferedReader = new BufferedReader(filereader);

                while((line = bufferedReader.readLine()) != null ){
                 /*   var value = line.split(";");
                    stm = value[0];*/
                    //on lance la migration initial pour créer nos tables en récupérant le script
                    PreparedStatement pst = con.getConnection().prepareStatement(line);
                    pst.executeUpdate();
                    System.out.println(line);
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

    public List<Cours> GetEmploiDuTemps(Date date, int idGroupe){

        double duree;
        boolean[] horaire = new boolean[24];
        Enseignant enseignant = new Enseignant();
        Salle salle = new Salle();
        Groupe groupe = new Groupe();
        List<Cours> emploiDuTemps = new ArrayList<>();
        Cours cours = new Cours();

        String stm = "select * from cours where idGroupe = " + idGroupe + " and journee = " + date + " Order by horaire";

        //on charge le driver

        try {

            //on lance la migration initial pour créer nos tables
            PreparedStatement pst = con.getConnection().prepareStatement(stm);
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
    public Enseignant GetEnseignant(int id) {

        Enseignant enseignant =  new Enseignant();

        String stm = "Select * from Enseignant where idEnseignant = " + id ;

        try {

            PreparedStatement pst = Connexion.getInstance().getConnection().prepareStatement(stm);
            ResultSet resultSet = pst.executeQuery();
            if(resultSet.next()){
                enseignant.setIdEnseignant(resultSet.getInt("idEnseignant"));
                enseignant.setName(resultSet.getString("nom"));
                enseignant.setPrenom(resultSet.getString("prenom"));
                enseignant.setNbHeures(resultSet.getDouble("nbHeure"));
                System.out.println("opération réussie");
            } else {
                System.out.println("raté");
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

        String stm = "select * from Salle";


        try {
            System.out.println("connexion ouverte");

            //on lance la migration initial pour créer nos tables
            PreparedStatement pst = con.getConnection().prepareStatement(stm);
            ResultSet resultSet = pst.executeQuery();
            System.out.println("opération réussie");

            while(resultSet != null) {
                salle.setIdSalle(resultSet.getInt("idSalle"));
                salle.setTypeSalle(resultSet.getString("typeSalle"));
                salle.setCapacite(resultSet.getInt("capacite"));
                salle.setDisponibilité(resultSet.getBoolean("dispo"));
            }

        }catch(SQLException e){

            System.out.println(e);
            System.exit(-1);
        }
        return salle;
    }

    @Override
    public Groupe GetGroupe(int id) {

        Groupe groupe = new Groupe();
        String stm = "select * from SuitCours";


        try {

            System.out.println("connexion ouverte");

            //on lance la migration initial pour créer nos tables
            PreparedStatement pst = con.getConnection().prepareStatement(stm);
            ResultSet resultSet = pst.executeQuery();
            System.out.println("migration réussie");

            while(resultSet != null){
                groupe.setIdGroup(resultSet.getInt("idSuit"));
                groupe.setName(resultSet.getString("nomGroupe"));
                groupe.setTaille(resultSet.getInt("taille"));
                groupe.setTypeGroupe(resultSet.getString("typeGroupe"));
            }

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

        String stm = "select duree, journee, horaire from cours where idGroupe = " + idGroupe + " and journee = " + date + " and horaire = " + debutCours;


        try {

            System.out.println("connexion ouverte");

            //on lance la migration initial pour créer nos tables
            PreparedStatement pst = con.getConnection().prepareStatement(stm);
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

        Matiere matiere = new Matiere();
        String stm = "select * from Matiere";

        try {

            //on lance la migration initial pour créer nos tables
            PreparedStatement pst = con.getConnection().prepareStatement(stm);
            ResultSet resultSet = pst.executeQuery();
            System.out.println("migration réussie");

            while(resultSet != null){
                matiere.setIdMatiere(resultSet.getInt("idMatiere"));
                matiere.setName(resultSet.getString("nom"));
            }

        }catch(SQLException e){

            System.out.println(e);
            System.exit(-1);
        }

        return matiere;
    }


    @Override
    public void AddCours(Cours cours) {
        String stm = "insert into Cours (journee, horaire, duree, idEnseignant, idSuit, idMatiere, idSalle) values (?, ?, ?, ?, ?, ?, ?) ";


        try {


            //on lance la migration initial pour créer nos tables
            PreparedStatement pst = con.getConnection().prepareStatement(stm);
            pst.setDate(1, (java.sql.Date) cours.getDate());
            pst.setInt(2, cours.getDebutCours() );
            pst.setDouble(3, cours.getDuree());
            pst.setInt(4, cours.getIdEnseigant());
            pst.setInt(5, cours.getIdGoupe());
            pst.setInt(6, cours.getIdMatiere());
            pst.setInt(7,cours.getIdSalle());
            pst.executeUpdate();

            System.out.println("migration réussie");

        }catch(SQLException e){

            System.out.println(e);
            System.exit(-1);
        }
    }

}
