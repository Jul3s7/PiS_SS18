import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**Diese Klasse zeigt eine grafische Oberfläche, auf der der Buchstabe "L" zu sehen ist.
 * Durch drücken des roten oder grünen Buttons wird der vorherige oder nachfolgende Buchstabe des Alphabets angezeigt.
 */
public class App extends Application{

    private char letter = 'L';
    private Button buttonUp, buttonDown;
    private Text text;
    private FlowPane buttons;

    public App(){
        this.letter = letter;
    }

    /**Methode zum hochzählen der Buchstaben*/
    private char doButtonUp() {
        if (letter != 'Z') {
            letter++;
            text.setText(String.valueOf(letter));
        }
        return letter;
    }

    /**Methode zum runterzählen der Buchstaben*/
    private char doButtonDown(){
        if (letter != 'A') {
            letter--;
            text.setText(String.valueOf(letter));
        }
        return letter;
    }


    /**Gibt aktuellen Wert von letter als String zurück*/
    private String getLetter(){
        return String.valueOf(letter);
    }

    //Startet GUI
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("App.java");

        //Textfeld für Buchstabe
        text = new Text(this.getLetter());
        text.setStyle("-fx-font-size: 54; -fx-text-alignment: left");

        //Initialisiert grünen und roten Button
        buttonUp = new Button();
        buttonDown = new Button();
        buttonUp.setPrefSize(85,50);
        buttonDown.setPrefSize(85, 50);
        buttonUp.setStyle("-fx-background-color: #008000");
        buttonDown.setStyle("-fx-background-color: #ff0000");

        //Setzt Methode für die Buttons
        buttonUp.setOnAction(e -> this.doButtonUp());
        buttonDown.setOnAction(e -> this.doButtonDown());

        //FlowPane zur leichteren Anordnung der beiden Buttons
        buttons = new FlowPane();
        buttons.setPadding(new Insets(10,10,10,10));
        buttons.setHgap(5);

        //Einfügen aller Elemente zu eigentlicher Oberfläche
        BorderPane layout = new BorderPane();
        buttons.getChildren().addAll(buttonUp,buttonDown);
        layout.setTop(text);
        layout.setBottom(buttons);
        Scene scene = new Scene(layout,200,150);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main (String[] args){
        launch(args);
    }
}
