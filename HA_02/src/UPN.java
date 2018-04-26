import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Stack;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.util.ArrayList;
import java.util.List;


/**
 * Diese Klasse zeigt einen UPN-Taschenrechner, eingaben können über das Eingabenfeld getätigt werden.
 *
 * Die Ausgabe erfolgt noch über die Konsole, die Ausgabe im Stack der GUI ist momentan noch fehlerhaft und muss
 * überarbeitet werden.
 *
 * Der GUI Code wurde von Jonas Bartkowski übernommen, da ich schon eine Zulassung für das Modul habe,
 * möchte ich die Hausaufgabe nur zur eigenen Übung einreichen.
 *
 */
public class UPN extends Application {

    private static Stack<Integer> stack = new Stack<>();
    private static int result = 0;

    private int[] lastVals;

    private int cache = 0;
    private static int stackLevel = 4;

    private List<Text> stackTexts = new ArrayList<>();


    private static void calculate (char operator){

        if (stack.size() >= 2) {
            int value2 = stack.pop();
            int value1 = stack.pop();

            switch (operator) {
                case '+':
                    result = value1 + value2;
                    break;
                case '-':
                    result = value1 - value2;
                    break;
                case 'x':
                    result = value1 * value2;
                    break;
                case  '/':
                    if (value1 != 0 && value2 != 0){
                        result = value1 / value2;
                    } else {
                        System.out.println("FEHLER");
                    }
                    break;

            }
            stack.push(result);
        }
    }


    private static int[] getStackValues()
    {
        int[] vals = new int[4];
        int ssize = stack.size();
        for (int i = 0; i < stack.size(); i++)
        {
            vals[i] = stack.get(i);
        }

        for (int i = ssize-1; i < vals.length; i++)
        {
            vals[i] = 0;
        }

        return vals;
    }

    private static void fillStack(int cache){
        if (stack.size() != stackLevel)
            stack.push(cache);
    }


    //Code von Jonas Bartkowski
    @Override
    public void start(Stage primaryStage)
    {
        Scene scene = new Scene(makeFinalLayout(20, 20));
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private Parent makeFinalLayout(int padding, int centerPadding)
    {
        FlowPane fp = new FlowPane(Orientation.VERTICAL, 20, 20);
        HBox topPart = makeTopPartLayout();
        HBox middlePart = makeMiddlePartLayout(padding, 20, 20);
        HBox botPart = makeBotPartLayout();
        fp.getChildren().addAll(topPart, middlePart, botPart);
        VBox vbox = new VBox(padding, topPart, middlePart, botPart);
        return vbox;
    }

    private Parent makeNumbersLayout(int spacing, int numbersPerLine, String[] numbers)
    {
        double re = (numbers.length % numbersPerLine);
        int lines = numbers.length/numbersPerLine + (re != 0 ? 1 : 0);
        HBox[] hboxes = new HBox[lines];

        for (int l = 0; l < lines; l++)
        {
            hboxes[l] = new HBox(spacing);
            for (int r = 0; r < numbersPerLine; r++)
            {
                int index = l*numbersPerLine+r;
                if (index < numbers.length)
                {
                    Button numBut = new Button(numbers[index]);
                    numBut.setOnAction(event -> onNumberHit(Integer.valueOf(numbers[index])));
                    hboxes[l].getChildren().add(numBut);
                }
                else break;
            }
        }
        return new VBox(spacing, hboxes);
    }

    private Parent makeStackBarLayout(int levels, int spacing)
    {

        StackPane[] texts = new StackPane[levels+1];
        texts[0] = new StackPane(new Text("Stack"));
        texts[0].setAlignment(Pos.CENTER);
        for (int i = 1; i < levels+1; i++)
        {
            Text text = new Text("0");
            texts[i] = new StackPane(text);
            texts[i].setAlignment(Pos.CENTER);
            stackTexts.add(text);
        }

        return new VBox(spacing, texts);
    }

    private Parent makeSignsBarLayout(int padding)
    {
        Button[] signs = new Button[4];
        signs[0] = new Button("+");
        signs[0].setOnAction(event -> this.onSignHit('+'));
        signs[1] = new Button("-");
        signs[1].setOnAction(event -> this.onSignHit('-'));
        signs[2] = new Button("x");
        signs[2].setOnAction(event -> this.onSignHit('x'));
        signs[3] = new Button("/");
        signs[3].setOnAction(event -> this.onSignHit('/'));
        return new VBox(padding, signs);
    }

    private HBox makeTopPartLayout()
    {
        return new HBox(makeOutputText());
    }

    private HBox makeBotPartLayout()
    {
        HBox hbox = new HBox(makeEnterButton());
        hbox.setAlignment(Pos.CENTER);
        return hbox;
    }

    private HBox makeMiddlePartLayout(int padding, int paddingStack, int numbersSpacing)
    {
        return new HBox(padding, makeStackBarLayout(4, paddingStack), makeNumbersLayout(numbersSpacing, 3, new String[]{"1","2","3","4","5","6","7","8","9","0","-1"}), makeSignsBarLayout(20));
    }

    private Text makeOutputText()
    {
        Text text = new Text("");
        return text;
    }

    private Button makeEnterButton()
    {
        Button enterButton = new Button("E N T E R");
        enterButton.setOnAction(event -> onEnter());
        enterButton.setMaxWidth(Double.MAX_VALUE);
        return enterButton;
    }

    private void updateLastVersion()
    {
        lastVals = UPN.getStackValues();
    }

    private void onEnter()
    {
        System.out.println(("Enter"));
        UPN.fillStack(cache);
        cache = 0;
        updateStackTexts();
    }

    private void onSignHit(char sign)
    {
        System.out.println("On sign hit ("+sign+")");
        fillStack(cache);
        UPN.calculate(sign);
        this.updateLastVersion();
        updateStackTexts();
        cache = 0;
    }

    private void onNumberHit(int num)
    {
        System.out.println("On number hit ("+num+")");
        if (num != -1)
        {
            cache = Integer.valueOf(cache+""+num);
        }
        else
            cache = cache * -1;
    }


    private void updateStackTexts()
    {
        int[] vals = UPN.getStackValues();
        for (int i = 1; i < vals.length; i++)
        {
            stackTexts.get(i).setText(vals[i]+"");
        }
        stackTexts.get(0).setText(cache+"");
        System.out.println(stack);
    }


    public static void main (String[] args){
        launch(args);

    }
}