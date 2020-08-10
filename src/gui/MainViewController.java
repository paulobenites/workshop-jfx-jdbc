package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.DepartamentService;

public class MainViewController implements Initializable{
	@FXML
	private MenuItem menuItemSeller;
	@FXML
	private MenuItem menuItemDepartment;
	@FXML
	private MenuItem menuItemAbout;
	
	@FXML
	public void onMenuItemSellerAtion() {
		System.out.println("onSeller");
	}
	@FXML
	public void onMenuItemDepartmentAtion() {
		loadView("/gui/DepartmentList.fxml",(DepartmentListController controller)->{
			controller.setDepartmentService(new DepartamentService());
			controller.updateTableView();
		});
	}
	@FXML
	public void onMenuItemAboutAtion() {
		loadView("/gui/About.fxml", x ->{});
	}
	@Override
	public void initialize(URL uri, ResourceBundle rb) {
	}
	private synchronized <T> void loadView(String absolutName, Consumer <T> inicializingAtion) {
		try {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(absolutName));
		VBox newVbox = loader.load();
		
		Scene mainScene = Main.getMainScene();
		VBox mainVbox = (VBox)((ScrollPane)mainScene.getRoot()).getContent();
		Node mainMenu = mainVbox.getChildren().get(0);
		mainVbox.getChildren().clear();
		mainVbox.getChildren().add(mainMenu);
		mainVbox.getChildren().addAll(newVbox.getChildren());
		
		T controller = loader.getController();
		inicializingAtion.accept(controller);
		}
		catch(IOException e){
			Alerts.showAlert("IO exception", "Error loding view", e.getMessage(), AlertType.ERROR);
		}
	}
}
