package be.kuleuven.dbproject.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.List;

public class BeheerScherm1Controller {

    @FXML
    private TextField txtName;
    @FXML
    private ComboBox<String> comboYear;
    @FXML
    private ComboBox<String> comboPublisher;
    @FXML
    private ComboBox<String> comboGenre;
    @FXML
    private ComboBox<String> comboConsole;
    @FXML
    private ComboBox<String> comboLocation;
    @FXML
    private CheckBox chkbx;
    @FXML
    private Button btn;
    @FXML
    private TextField price;
    @FXML
    private TextField price2;
    @FXML
    private TextField lang;


    private ProjectMainController projectMainController;

    public BeheerScherm1Controller() {
        this.projectMainController = new ProjectMainController();
    }

    public void initialize() {

        populateComboBox(comboYear, projectMainController.userDao.getAllReleaseYears());
        populateComboBox(comboPublisher, projectMainController.userDao.getAllPublishers());
        populateComboBox(comboGenre, projectMainController.userDao.getAllGenres());
        populateComboBox(comboConsole, projectMainController.userDao.getAllConsoles());
        populateComboBox(comboLocation, getAllLocations());


        btn.setOnAction(e -> handleButtonClick());
    }

    private void handleButtonClick() {


        String name = txtName.getText();
        String year = comboYear.getValue();
        String publisher = comboPublisher.getValue();
        String genre = comboGenre.getValue();
        String console = comboConsole.getValue();
        String location = comboLocation.getValue();
        String language = lang.getText();
        float price_ = Float.parseFloat(price.getText());
        boolean isChecked = chkbx.isSelected();

        boolean success = ProjectMainController.userDao.createGame( name,  year,  publisher,  genre , console,  location,  isChecked,  price_,  language);
        if(success){
            Stage stage = (Stage) txtName.getScene().getWindow();
            stage.close();}else{
            var alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Oops");
            alert.setHeaderText("Oops");
            alert.setContentText("Something went wrong!");
            alert.showAndWait();
        }
    }

    private <T> void populateComboBox(ComboBox<String> comboBox, List<T> data) {
        if (data != null) {
            comboBox.setItems(FXCollections.observableArrayList(data.toString()));
        }
    }
    private List  getAllLocations(){
      List list =  projectMainController.userDao.getAllMuseumNames();
      list.add(projectMainController.userDao.getAllWarehouseNames());
      return list;
    }
}
