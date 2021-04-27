package Service.Front.Concours;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Service.Front.Concours.ProgressCircleController;
import Service.Front.Concours.ServiceConcours;
import Service.Front.Concours.ServiceQuiz;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import entities.Manager;
import static entities.Manager.getRandom;
import entities.Quiz;
import entities.Score;
import entities.questiontab;
import entities.reponsetab;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author Hiba
 */
public class AfficheQuizController implements Initializable {

    @FXML
    private Label lblTitre;
    @FXML
    private Label lblTime;
    @FXML
    private Label lblQuestion;
    @FXML
    private JFXRadioButton option1;
    @FXML
    private JFXRadioButton option2;
    @FXML
    private JFXRadioButton option3;
    @FXML
    private JFXRadioButton option4;
    @FXML
    private JFXButton suivantBtn;
    @FXML
    private JFXButton submitBtn;
    private ToggleGroup options;
    private Manager man;
    private Label txtAreaDisplay;
    int score = 0;
    String scoreFinal;
    private questiontab currentQuestion;
    ServiceQuiz sq = new ServiceQuiz();
//    private final int NUM=9;
    List<questiontab> questionsList = sq.ListQuestions();
    List<reponsetab> reponsesList = sq.ListReponses();

    private questiontab[] questions = new questiontab[500];
    private reponsetab[] reponses = new reponsetab[4];
    private int numReponses = 0;

    private int numQuestions;
    int currentIndex = 0;
    @FXML
    private AnchorPane menuId;
    @FXML
    private FlowPane progressPane;
    private char reponse;
    @FXML
    private ToggleGroup optionsGroup;
    @FXML
    private StackPane scorePane;
   int scoreFinale2;
Quiz quiz_id;
    /**
     * Initializes the controller class.
     */
    private void renderProgress() {
        for (int i = 0; i < this.questionsList.size(); i++) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/progressCircle.fxml"));
                Node node = fxmlLoader.load();
                ProgressCircleController ProgressCircleController = fxmlLoader.getController();
                ProgressCircleController.setNumber(i + 1);
                ProgressCircleController.setDefaultColor();

                progressPane.getChildren().add(node);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }

    @Override

    public void initialize(URL url, ResourceBundle rb) {

        this.showNextQuestionButton();
        this.hideEnregisterButton();
//   
        try {

            renderProgress();

            setNext();
            man = new Manager();

            lblQuestion.setText(man.getQuestion());
        } catch (SQLException ex) {
            System.out.println("cnx failed");
        }

    }

    public void addQuestion(String question, int question_id, String answer) throws SQLException {

        questions[numQuestions] = new questiontab(question, question_id, answer);
        numQuestions++;
//        setNext();

    }

    public boolean isCorrect(char letter) {
        String letters = "ABCD";
        int pos = letters.indexOf(letter + "");
        return reponses[pos].isStatu();

    }

    public char reponse(char reponse) throws SQLException {
        if (currentQuestion.isCorrect(reponse)) {
            return reponse;
        } else {
            return 0;
        }
    }

    @FXML
    private void questionSuivante(ActionEvent event) throws SQLException {
        boolean isRight = false;
//        score = 0;

        {
            //verification reponse
            JFXRadioButton selectedButton = (JFXRadioButton) optionsGroup.getSelectedToggle();
            String Useranswer = selectedButton.getText();

            String rightAnswer = this.currentQuestion.getAnswer();

            if (Useranswer.trim().equalsIgnoreCase(rightAnswer.trim())) {
                isRight = true;
            }

            Node circleNode = this.progressPane.getChildren().get(currentIndex - 1);
            ProgressCircleController controller = (ProgressCircleController) circleNode.getUserData();

            if (isRight) {

                controller.setRightAnswerColor();
                score++;
            } else {
                controller.setWrongAnswerColor();
            }
            System.out.println("score");
            System.out.println(score);
            System.out.println("******");
            System.out.println("user");
            System.out.println(Useranswer);
            System.out.println("*******");
            System.out.println("right");
            System.out.println(rightAnswer);

            this.setNext();

        }
        String nbQues = String.valueOf(this.questionsList.size());
        scoreFinal =score + "/" + nbQues;
//        scoreFinale2 = String.valueOf(scoreFinal);
      
                System.out.println(scoreFinal);

    }
    
    public void AffectationScore() {
        {
                Node scoreNode;
            scoreNode = this.scorePane.getChildren().get(score);
                PseudoScoreController controller = (PseudoScoreController) scoreNode.getUserData();
                controller.setScore();
                
            }
            
    }
    

    @FXML
    private void enregistrer(ActionEvent event) throws IOException {

//         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setTitle("Merci pour votre participation");
//            alert.setHeaderText("Votre Score :");
//            String nbQues = String.valueOf(this.questionsList.size());
//
////            alert.setContentText(score+"/"+nbQues);
//            alert.setContentText(scoreFinal);
//            Optional<ButtonType> action = alert.showAndWait();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/Front/concours/PseudoScore.fxml"));
        Node node = fxmlLoader.load();
        scorePane.getChildren().add(node);
        AffectationScore();
        
           

    }

    private void hideNextQuestionButton() {
        this.suivantBtn.setVisible(false);
    }

    private void showNextQuestionButton() {
        this.suivantBtn.setVisible(true);
    }

    private void hideEnregisterButton() {
        this.submitBtn.setVisible(false);
    }

    private void showEnregisterButton() {
        this.submitBtn.setVisible(true);
    }

    public String getQuestion() {

        currentQuestion = questions[numQuestions];
        return currentQuestion.toString();

    }

    public String getAnswer() {

        String currentAnswer = questions[currentIndex].getAnswer();
        return currentAnswer.toString();

    }

    private void setNext() throws SQLException {

        String SQL = "SELECT id,questions,answer FROM questiontab WHERE quiz_id=?";
        Connection connection = MyConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement(SQL);
        ResultSet result = connection.createStatement().executeQuery(SQL);
        while (result.next()) {
            int question_id = result.getInt("id");
            String question = result.getString("questions");
            String answer = result.getString("answer");

            questions[numQuestions] = new questiontab(question, question_id, answer);
            numQuestions++;

            System.out.println(questions);
            System.out.println(reponses);

        }
        if (!(questionsList.size() <= currentIndex)) {
            System.out.println("if taadet");
            //changement couleur
            {
                Node circleNode = this.progressPane.getChildren().get(currentIndex);
                ProgressCircleController controller = (ProgressCircleController) circleNode.getUserData();
                controller.setCurrentQuestionColor();
            }
            currentQuestion = questions[currentIndex];
            String currentAnswer = questions[currentIndex].getAnswer();

            lblQuestion.setText(currentQuestion.toString());
            reponsetab[] r = currentQuestion.getReponses();

            for (int i = 0; i < 4; i++) {
                System.out.println(r[0].getReponses().toString());
                System.out.println(r[1].getReponses().toString());
                System.out.println(r[2].getReponses().toString());
                System.out.println(r[3].getReponses().toString());
                option1.setText(r[0].getReponses().toString());
                option2.setText(r[1].getReponses().toString());
                option3.setText(r[2].getReponses().toString());
                option4.setText(r[3].getReponses().toString());

//                String opt1 = r[0].toString();
//                String opt2 = r[1].toString();
//                String opt3 = r[2].toString();
//                String opt4 = r[3].toString();
            }
//                
            System.out.println(currentQuestion.toString());
            System.out.println(currentAnswer);

        } else {
            hideNextQuestionButton();
            showEnregisterButton();
        }

        currentIndex++;
    }

    @FXML
    private void minAction(MouseEvent event) {
        Stage stage = new Stage();
        stage = (Stage) lblQuestion.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void closeAction(MouseEvent event) {
//        Stage stage = new Stage();
//        stage = (Stage) lblTitre.getScene().getWindow();
//        stage.close();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/displayConcoursPage.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void choice1(ActionEvent event) throws SQLException {
        reponse('A');

    }

    @FXML
    private void choice2(ActionEvent event) throws SQLException {
        reponse('B');

    }

    @FXML
    private void choice3(ActionEvent event) throws SQLException {
        reponse('C');

    }

    @FXML
    private void choice4(ActionEvent event) throws SQLException {
        reponse('D');

    }
}
