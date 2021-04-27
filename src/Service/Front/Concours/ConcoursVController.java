///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Service.Front.Concours;
//
//import java.net.URL;
//import java.util.ResourceBundle;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.GridPane;
//import entities.Concour;
//import javafx.scene.control.Label;
//
///**
// * FXML Controller class
// *
// * @author Hiba
// */
//public class ConcoursVController implements Initializable {
//
//    @FXML
//    private AnchorPane Competitions_list;
//    @FXML
//    private GridPane competitions;
//
//    /**
//     * Initializes the controller class.
//     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        System.out.println("test");
//                ServiceConcours cs = new ServiceConcours();
//        System.out.println("teeeeeeeeest");
//        ObservableList<Concour> tab = cs.getAll();
//        System.out.println(tab);
//        tab.forEach((c) -> {
//
//            Label start = new Label(c.getDateDebut().toString());
//            start.setTextFill(javafx.scene.paint.Color.BLACK);
//            start.setFont(Font.font("Cambria", 20));
//            Label end = new Label(c.getDateFin().toString());
//            end.setTextFill(javafx.scene.paint.Color.BLACK);
//            end.setFont(Font.font("Cambria", 20));
//            Label sub = new Label(c.getSujet());
//            sub.setFont(Font.font("Cambria", 16));
//            sub.setTextFill(javafx.scene.paint.Color.BLACK);
//            Label TalentLabel = new Label("Already a Talent!");
//            TalentLabel.setTextFill(javafx.scene.paint.Color.BLACK);
//            TalentLabel.setFont(Font.font("Cambria", 13));
//            
//            Label OverLabel = new Label("Competition is Over !");
//            OverLabel.setTextFill(javafx.scene.paint.Color.BLACK);
//            OverLabel.setFont(Font.font("Cambria", 13));
//            
//            Label ParticipatedlLabel = new Label("Participated");
//            ParticipatedlLabel.setTextFill(javafx.scene.paint.Color.BLACK);
//            ParticipatedlLabel.setFont(Font.font("Cambria", 13));
//            
//            JFXButton v = new JFXButton("View");
//            JFXButton p = new JFXButton("Participate");
//            v.resize(175, 45);
//            v.setStyle("-fx-text-fill: black;-fx-font-size:20px;");
//            v.setRipplerFill(javafx.scene.paint.Color.GREEN);
//            p.setStyle("-fx-text-fill: black;-fx-font-size:20px; -fx-background-color:#F39C12");
//            p.setRipplerFill(javafx.scene.paint.Color.BLUE);
//            p.resize(175, 45);
//            v.setOnAction(new EventHandler<ActionEvent>() {
//
//                @Override
//                public void handle(ActionEvent event) {
//                    try {
//                        Concour concours = c;
//                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Front/concourVList.fxml"));
//                        Parent root = loader.load();
//                        ConcourVListController controller = loader.<ConcourVListController>getController();
//                        controller.setConcour(concours);
//
//                        Competitions_list.getChildren().setAll(root);
//
//                    } catch (IOException ex) {
//                        Logger.getLogger(concoursVusersController.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//            });
//            p.setOnAction(new EventHandler<ActionEvent>() {
//
//                @Override
//                public void handle(ActionEvent event) {
//                    try {
//                        Concour concours = c;
//
//                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ConcourV_Participate.fxml"));
//                        Parent root = loader.load();
//                        ConcourVParticipateController controller = loader.<ConcourVParticipateController>getController();
//                        controller.setConcour(concours);
//                        Scene s = new Scene(root);
//                        Stage stage = new Stage();
//                        stage.initModality(Modality.APPLICATION_MODAL);
//                        stage.initStyle(StageStyle.TRANSPARENT);
//                        stage.setOpacity(0.8);
//                        stage.setTitle("Participate");
//                        stage.initOwner(Competitions_list.getScene().getWindow());
//                        stage.setScene(s);
//                        stage.show();
//
//                    } catch (IOException ex) {
//                        Logger.getLogger(concoursVusersController.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//
//                }
//            });
//            Label TimeleftLabel=new Label();
//            TimeleftLabel.setTextFill(javafx.scene.paint.Color.BLACK);
//            TimeleftLabel.setFont(Font.font("Cambria", 13));
//            if(c.getDateFin().before(new Timestamp(System.currentTimeMillis()))){
//           Timeline timer = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
//                Timestamp t = new Timestamp(System.currentTimeMillis());
//                long counter = getDateDiff(t, c.getDateFin(), TimeUnit.SECONDS);
//                  
//                @Override
//                public void handle(ActionEvent event) {
//
//                    long h = TimeUnit.SECONDS.toHours(counter);
//                    long m = TimeUnit.SECONDS.toMinutes(counter - h * 3600);
//                    long s = TimeUnit.SECONDS.toSeconds(counter - h * 3600 - m * 60);
//                    counter = counter - 1;
//                     TimeleftLabel.setText(String.format("%02d:%02d:%02d", h,m,s));
//                     
//                    
//
//                }
//
//               private long getDateDiff(Timestamp t, Date dateFin, TimeUnit timeUnit) {
//                   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//               }
//            }));
//            timer.setCycleCount(60);
//            timer.play();}
//            else{
//                TimeleftLabel.setText("00:00:00");
//            }
//            UserSession s = UserSession.instance;
//            ParticipationServices ps=new ParticipationServices();
////            if (s.getU().getRole().contains("ROLE_TALENTED")) {
//                competitions.addRow(tab.indexOf(c), start, end, sub, v, TalentLabel);
//            
//            if (c.getDateFin().before(new Timestamp(System.currentTimeMillis())))
//            {
//                competitions.addRow(tab.indexOf(c), start,TimeleftLabel, end, sub, v, OverLabel);
//            }
//            else if(ps.findParticipation(c, s.getU())){
//                competitions.addRow(tab.indexOf(c), start, end, sub, v, ParticipatedlLabel);
//            }
//            else 
//            competitions.addRow(tab.indexOf(c), start, end, sub, v, p);
//
//            RowConstraints row = new RowConstraints(90);
//
//            competitions.getRowConstraints().add(row);
//
//        });
//        competitions.setPadding(new Insets(10, 10, 10, 10));
//        competitions.setStyle("-fx-background-color: #0c0527");
//        competitions.setVgap(15);
//    }    
//    
//}
