package application;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Stage{
	
	private VBox vbox;
	private GridPane gridpane;
	private Button btn;
	private Button btnOk;
	private TextField textfield;
	private Label label;
	private Label answer;
	private TextField [][] textfields;
	private int size;
	
	public GUI(){
		vbox = new VBox(10);
		vbox.setAlignment(Pos.CENTER);
		
		label = new Label("Determinantenberechnung einer quadratischen Matrix im Bereich von 2 bis 10");
		answer = new Label();

		textfield = new TextField();
		textfield.setPromptText("Größe der Matrix (zwischen 2 und 10)");
		textfield.setMaxWidth(100);

		gridpane = new GridPane();
		gridpane.setAlignment(Pos.CENTER);
		
		btn = new Button("Klicken");
		btnOk = new Button("Berechnen");
		
		vbox.getChildren().addAll(label, textfield, btn, gridpane);
		
		
		btn.setOnAction(e -> {
		    try {
		        size = Integer.valueOf(textfield.getText());
		        if (size >= 2 && size <= 10) {
		            initialisieren();
		        } else {
		            Alert alert = new Alert(AlertType.ERROR);
		            alert.setContentText("Die Größe der Matrix muss im Bereich von 2 bis 10 liegen.");
		            alert.showAndWait();
		        }
		    } catch (NumberFormatException e2) {
		        Alert alert = new Alert(AlertType.ERROR);
		        alert.setContentText("Bitte geben Sie eine gültige Zahl ein (zwischen 2 und 10).");
		        alert.showAndWait();
		    }
		});
		
		btnOk.setOnAction(e->{
			Determinant det = new Determinant(matrix());
			if (det != null) {
				answer.setText(det.toString()); 
				vbox.getChildren().add(answer);
			}
		});
	}

	private double[][] matrix() {
		double [][] matrix = new double [size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				try {
					matrix[i][j] = Double.valueOf(textfields[i][j].getText());
				} catch (NumberFormatException e) {
					Alert alert = new Alert(AlertType.ERROR);
				    alert.setContentText("Bitte geben Sie für alle Felder gültige Zahlen ein.");
				    alert.showAndWait();
				    return null;  
				}
			}
		}
		return matrix;
	}

	private void initialisieren() {
		gridpane.getChildren().clear();
		textfields = new TextField[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				TextField field = new TextField();
				textfields[i][j] = field;
				gridpane.setConstraints(field, j, i); 
				gridpane.getChildren().add(field);
			}
		}
		vbox.getChildren().add(btnOk);
	}

	public VBox getVbox() {
		return vbox;
	}
}
