package com.example.enigmagui;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.geometry.*;

import java.util.Locale;
import java.util.Objects;

public class EnigmaGUI extends Application {
    private Label enter;
    private Label encrypted;
    private Label rotor1Notch;
    private Label rotor2Notch;
    private Label rotor3Notch;
    private TextField notch1;
    private TextField notch2;
    private TextField notch3;
    private TextField text;
    private TextArea output;
    private Button encrypt;

    @Override
    public void start(Stage stage) {
        GridPane pane = new GridPane();
        create();
        layout(pane);
        adjust(pane);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/enigmagui/enigma16.png"))));
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/enigmagui/enigma32.png"))));
        stage.setTitle("Enigma");
        stage.show();
    }

    /// Creates the elements within the GUI
    private void create() {
        enter = new Label("Enter Plain Text:");
        encrypted = new Label("Encrypted Text: ");
        text = new TextField("");
        output = new TextArea("");
        encrypt = new Button("Encrypt");
        rotor1Notch = new Label("Rotor 1:");
        rotor2Notch = new Label("Rotor 2:");
        rotor3Notch = new Label("Rotor 3:");
        notch1 = new TextField("");
        notch2 = new TextField("");
        notch3 = new TextField("");
    }

    /// Places the elements in their correct locations
    private void layout(GridPane pane) {
        pane.add(enter, 1, 3);
        pane.add(text, 2, 2, 2, 3);
        pane.add(encrypt, 4, 3);
        pane.add(encrypted, 1, 4);
        pane.add(output, 2, 5);
        pane.add(rotor1Notch, 1, 0);
        pane.add(notch1, 2,0);
        pane.add(rotor2Notch, 1, 1);
        pane.add(notch2, 2, 1);
        pane.add(rotor3Notch, 1, 2);
        pane.add(notch3, 2, 2);
    }

    /// Sets various attributes of elements
    private void adjust(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        encrypt.setMaxWidth(300);
        output.setPrefSize(300, 300);
        output.setWrapText(true);
        notch1.setMaxWidth(50);
        notch2.setMaxWidth(50);
        notch3.setMaxWidth(50);
        encrypt.setOnMouseClicked(this::click);

    }

    /// Encrypts the text entered into the TextField and displays the output
    private void click(MouseEvent event) {
        try {
            String input = text.textProperty().getValue().toUpperCase(Locale.ROOT);
            int n1 = Integer.parseInt(notch1.textProperty().getValue());
            int n2 = Integer.parseInt(notch2.textProperty().getValue());
            int n3 = Integer.parseInt(notch3.textProperty().getValue());

            Enigma.Rotor rotor1 = new Enigma.Rotor(0, n1, "EKMFLGDQVZNTOWYHXUSPAIBRCJ");
            Enigma.Rotor rotor2 = new Enigma.Rotor(0, n2, "AJDKSIRUXBLHWTMCQGZNPYFVOE");
            Enigma.Rotor rotor3 = new Enigma.Rotor(0, n3, "BDFHJLCPRTXVZNYEIWGAKMUSQO");
            Enigma.Reflector reflector = new Enigma.Reflector("YRUHQSLDPXNGOKMIEBFZCWVJAT");
            Enigma.Plugboard plugboard = new Enigma.Plugboard("AOBGQFSTJXZEHPILMNCVDKRUWY", "OAGBFQTSXJEZPHLINMVCDKRUWY");
            Enigma e = new Enigma(rotor1, rotor2, rotor3, reflector, plugboard, input);

            output.setText(e.encrypt());
        }
        catch (Exception e) {
            output.setText("Input Error");
        }
    }

    public static void main(String[] args) {
        launch();
    }

}