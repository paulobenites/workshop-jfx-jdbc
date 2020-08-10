package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Department;
import model.services.DepartamentService;

public class DepartmentListController implements Initializable {

	private DepartamentService service;
	@FXML
	private TableView<Department> tableViewDepartments;
	@FXML
	private TableColumn<Department, Integer> tableColumId;
	@FXML
	private TableColumn<Department, String> tableColumName;
	@FXML
	private Button btNew;

	private ObservableList<Department> obsList;

	@FXML
	public void onBtNewAction() {
		System.out.println("on bt");
	}

	public void setDepartmentService(DepartamentService service) {
		this.service = service;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		inicializeNode();
	}

	private void inicializeNode() {
		tableColumId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumName.setCellValueFactory(new PropertyValueFactory<>("name"));

		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewDepartments.prefHeightProperty().bind(stage.heightProperty());
	}

	public void updateTableView() {
		if (service == null) {
			throw new IllegalStateException("service was null");
		}
		List<Department> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewDepartments.setItems(obsList);
	}

}
