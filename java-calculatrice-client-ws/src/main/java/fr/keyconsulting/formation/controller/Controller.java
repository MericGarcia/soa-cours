package fr.keyconsulting.formation.controller;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.keyconsulting.formation.model.Calcul;
import fr.keyconsulting.formation.model.Operand;
import fr.keyconsulting.formation.model.Operators;
import fr.keyconsulting.formation.service.IHelloService;
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
	
	IHelloService service;

	public void initialize(URL location, ResourceBundle resources) {
		operator.setItems(FXCollections.observableArrayList(Operators.all()));
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