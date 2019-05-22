package it.polito.tdp.porto;

import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;

import it.polito.tdp.porto.model.Adiacenza;
import it.polito.tdp.porto.model.Author;
import it.polito.tdp.porto.model.Model;
import it.polito.tdp.porto.model.Paper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class PortoController {

	Model model;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Author> boxPrimo;

    @FXML
    private ComboBox<Author> boxSecondo;

    @FXML
    private TextArea txtResult;
    @FXML
    void handleCoautori(ActionEvent event) {
    	this.model.creaGrafo();
        List<Author> nonCoautori=new ArrayList<Author>();

    	List<Author> coautori=this.model.getCoautori(this.boxPrimo.getSelectionModel().getSelectedItem());
		
    	
    	this.txtResult.appendText("COAUTORI DI "+this.boxPrimo.getSelectionModel().getSelectedItem().getLastname()+":\n");
    	Collections.sort(coautori);

    	for(Author a:coautori)
    		this.txtResult.appendText(a.toString()+"\n");
    	
    	
    	nonCoautori=this.model.getAllAuthors();
    	nonCoautori.removeAll(coautori);
    	nonCoautori.remove(this.boxPrimo.getSelectionModel().getSelectedItem());
    	Collections.sort(nonCoautori);
    	this.boxSecondo.getItems().addAll(nonCoautori);
    	this.boxSecondo.setDisable(false);
    	
    	}

    @FXML
    void handleSequenza(ActionEvent event) {
    	
    	List<Adiacenza> path=this.model.trovaCamminoMinimo(this.boxPrimo.getSelectionModel().getSelectedItem(), this.boxSecondo.getSelectionModel().getSelectedItem());
		this.txtResult.appendText("CAMMINO MINIMO:\n");

    	for(Adiacenza a:path)
    		this.txtResult.appendText(a.toString()+"\n");

    }

    @FXML
    void initialize() {
        assert boxPrimo != null : "fx:id=\"boxPrimo\" was not injected: check your FXML file 'Porto.fxml'.";
        assert boxSecondo != null : "fx:id=\"boxSecondo\" was not injected: check your FXML file 'Porto.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Porto.fxml'.";

    }
    public void setModel(Model model) {
    	this.model=model;
    	this.boxPrimo.getItems().addAll(this.model.getAllAuthors());
    }
}
