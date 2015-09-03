package fr.keyconsulting.formation.controller;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.keyconsulting.formation.model.Calcul;
import fr.keyconsulting.formation.model.Operand;
import fr.keyconsulting.formation.model.Operators;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import fr.keyconsulting.formation.facade.ILastService;
import fr.keyconsulting.formation.facade.LastService;

public class Controller implements Initializable {
	
	ApplicationContext context = new ClassPathXmlApplicationContext("/Spring-ws-client.xml");

	@FXML
	private TextField leftOperand;

	@FXML
	private ChoiceBox<String> operator;

	@FXML
	private TextField rightOperand;
	
	@FXML
	private TextField commentary;
	
	@FXML
	private TextField author;
	
	@FXML
	private Label title;

	@FXML
	private TextArea result;

	@FXML
	private TableView<Calcul> tableView;

	@FXML
	private TableColumn<Calcul, LocalDateTime> time;
	
	ILastService service;

	public void initialize(URL location, ResourceBundle resources) {
		service = new LastService();
		operator.setItems(FXCollections.observableArrayList(Operators.all()));
		Calcul calcul = service.getLastCalcul();
		if(calcul!=null){
			tableView.setItems((FXCollections.observableArrayList(calcul)));
		}
		time.setCellFactory(new DateTimeCellFactory<Calcul>());
	}

	public void run(ActionEvent event) {
		Calcul calcul = new Calcul(new Operand(leftOperand.getText()), Operators.of(operator.getValue()),
				new Operand(rightOperand.getText()), commentary.getText());
		calcul.setAuthor(author.getText());
		tableView.getItems().add(calcul);
		result.setText(calcul.execute().getValue().toPlainString());
	}

}