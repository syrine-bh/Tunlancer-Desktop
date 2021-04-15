/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlancer;

import Models.Publication;
import Services.ServicePublication;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Cyrina
 */
public class TunlancerDesktop extends Application {

    @Override
    public void start(Stage primaryStage) throws SQLException, IOException {


        //displayPublications();
        //addPublication();
        UpdatePublication();
        //DetailsPublications(5);
        //DeletePublication(26);
        
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/DisplayPublication.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("/GUI/AddPublication.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        //primaryStage.setFullScreen(true);


    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public void displayPublications() throws SQLException {
        ServicePublication SP = new ServicePublication();
        List<Publication> publications = new ArrayList<>();

        publications = SP.afficherPublications();
        for (int i = 0; i < publications.size(); i++) {
            System.out.println(publications.get(i).toString());
            System.out.println("liste des reactions :");
            for (int j = 0; j < publications.get(i).getReactions().size(); j++) {
                System.out.println(publications.get(i).getReactions().get(j).toString());
            }
            System.out.println("liste des commentaires :");
            for (int k = 0; k < publications.get(i).getCommentaires().size(); k++) {
                System.out.println(publications.get(i).getCommentaires().get(k).toString());
            }
            System.out.println("liste des vues :");
            for (int l = 0; l < publications.get(i).getVues().size(); l++) {
                System.out.println(publications.get(i).getVues().get(l).toString());
            }
            System.out.println("\n");
        }

    }

    public void addPublication() throws SQLException {
        ServicePublication SP = new ServicePublication();
        Publication publication = new Publication(
                0,//id
                1,//idUser
                "add from java",//description
                0,//type
                0,//archive
                "test.png",//image_name
                "Tunis"//localisation
        );
        SP.ajouterPublication(publication);
    }

    public void UpdatePublication() throws SQLException {
        ServicePublication SP = new ServicePublication();
        Publication publication = new Publication(
                26,//id
                1,//idUser
                "akko mofidier",//description
                0,//type
                0,//archive
                "1.jpg",//image_name
                "Tunis"//localisation
        );
        SP.modifierPublication(publication);
        System.out.println("publication modifiée");
    }

    public void DeletePublication(int id) throws SQLException {
        ServicePublication SP = new ServicePublication();
        SP.supprimerPublication(24);
        System.out.println("publication supprimée");
    }

    public void DetailsPublications(int id) throws SQLException {
        ServicePublication SP = new ServicePublication();
        Publication publication = SP.getPublicationById(id);
        System.out.println(publication);
        System.out.println("liste des reactions :");
        for (int j = 0; j < publication.getReactions().size(); j++) {
            System.out.println(publication.getReactions().get(j).toString());
        }
        System.out.println("liste des commentaires :");
        for (int k = 0; k < publication.getCommentaires().size(); k++) {
            System.out.println(publication.getCommentaires().get(k).toString());
        }
        System.out.println("liste des vues :");
        for (int l = 0; l < publication.getVues().size(); l++) {
            System.out.println(publication.getVues().get(l).toString());
        }
    }

}
