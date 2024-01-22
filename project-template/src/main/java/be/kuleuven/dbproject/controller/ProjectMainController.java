package be.kuleuven.dbproject.controller;

import be.kuleuven.dbproject.DatabaseManager;
import be.kuleuven.dbproject.ProjectMain;
import be.kuleuven.dbproject.User;
import be.kuleuven.dbproject.UserDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

public class ProjectMainController {

    @FXML
    private Button btnBeheerScherm1;
    @FXML
    private Button btnBeheerScherm2;
 //   @FXML
  //  private Button btnConfigAttaches;
    @FXML
    private TextField UsernameFld;

    @FXML
    private PasswordField PasswordFld;

    @FXML
    private Text statustxt;

    public static  UserDao userDao ;

    public User user;





    public ProjectMainController() {

        DatabaseManager databaseManager = new DatabaseManager("jdbc:sqlite:myDB.db");



        Jdbi jdbi = databaseManager.getJdbi();

        jdbi.installPlugin(new SqlObjectPlugin());

        userDao = jdbi.onDemand(be.kuleuven.dbproject.UserDao.class);
        try {
            jdbi.open(); // Try to open a connection
            System.out.println("Connected to the database.");
        } catch (Exception e) {
            System.err.println("Failed to connect to the database.");
            e.printStackTrace();
        }
BeheerScherm3Controller beheerScherm3Controller = new BeheerScherm3Controller(this);
    }

    public void initialize() {
        btnBeheerScherm1.setOnAction(e -> attemptLogin());
        btnBeheerScherm2.setOnAction(e -> showBeheerScherm("scherm2"));
    }

    public  void showBeheerScherm(String id) {
        var resourceName = "beheer" + id + ".fxml";
        try {
            var stage = new Stage();
            var root = (AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource(resourceName));
            var scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Admin " + id);
            stage.initOwner(ProjectMain.getRootStage());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.show();

        } catch (Exception e) {
            throw new RuntimeException("Kan beheerscherm " + resourceName + " niet vinden", e);
        }
    }
    private void attemptLogin(){
        statustxt.setText("logging in ...");
   String username = UsernameFld.getText();
   String password = PasswordFld.getText();
         user = userDao.getUserByCredentials(username, password);



        if (user != null) {
            if (user.isAdmin()){
                showBeheerScherm("scherm3");
            } else if (user.isEmployee()) {
                //employee screen
            }else{
            showBeheerScherm("scherm4");
            }
        } else {
            statustxt.setText("access denied");
        }
    }
}
