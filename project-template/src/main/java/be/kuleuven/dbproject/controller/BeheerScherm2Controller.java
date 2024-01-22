package be.kuleuven.dbproject.controller;

import be.kuleuven.dbproject.User;
import be.kuleuven.dbproject.UserDao;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class BeheerScherm2Controller {



    @FXML
    private TextField LNameFld;

    @FXML
    private TextField FNameFld;

    @FXML
    private TextField emailFld;

    @FXML
    private TextField PasswordFld;

    @FXML
    private TextField UsernameFld;

    @FXML
    private Button RegisterBtn;

    @FXML
    private Text resultText;








    public void initialize(){
        RegisterBtn.setOnAction(e -> attemptRegister());



    }

    private void attemptRegister(){
        String username = UsernameFld.getText();
        String password = PasswordFld.getText();
        String email = emailFld.getText();
        String lastName = LNameFld.getText();
        String firstName = FNameFld.getText();


int i = 1;
        User newUser = new User(0,  username,  password,  firstName,  lastName,  email,  false, false );

        try {


            ProjectMainController.userDao.registerUser(newUser);
        } catch (Exception e){
            resultText.setText("Registration failed!");
            resultText.setFill(javafx.scene.paint.Color.RED);
            i=0;

        }
        if (i==1) {
            resultText.setText("Registration successful!");
            resultText.setFill(javafx.scene.paint.Color.GREEN);
        }


    }


}
