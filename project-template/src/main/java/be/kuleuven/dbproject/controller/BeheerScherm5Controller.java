package be.kuleuven.dbproject.controller;

import be.kuleuven.dbproject.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class BeheerScherm5Controller {


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

    @FXML
    private CheckBox box;
    @FXML
    private CheckBox box1;







    public void initialize(){
        RegisterBtn.setOnAction(e -> attemptRegister());



    }

    private void attemptRegister(){
        String username = UsernameFld.getText();
        String password = PasswordFld.getText();
        String email = emailFld.getText();
        String lastName = LNameFld.getText();
        String firstName = FNameFld.getText();
        Boolean isemployee = box.isSelected();
        Boolean isAdmin = box1.isSelected();


        int i = 1;
        User newUser = new User(0,  username,  password,  firstName,  lastName,  email,  isAdmin, isemployee );

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
