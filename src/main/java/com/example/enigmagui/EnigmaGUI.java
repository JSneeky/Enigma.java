package com.example.enigmagui;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.geometry.*;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.Objects;

public class EnigmaGUI extends Application {
    private Label enter;
    private Label encrypted;
    private Label rotor1Pos;
    private Label rotor2Pos;
    private Label rotor3Pos;
    private TextField pos1;
    private TextField pos2;
    private TextField pos3;
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
    public void start(@NotNull Stage stage) {
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
        rotor1Pos = new Label("Rotor 1 Position:");
        rotor2Pos = new Label("Rotor 2 Position:");
        rotor3Pos = new Label("Rotor 3 Position:");
        pos1 = new TextField("");
        pos2 = new TextField("");
        pos3 = new TextField("");
        rotor1Notch = new Label("Rotor 1 Notch:");
        rotor2Notch = new Label("Rotor 2 Notch:");
        rotor3Notch = new Label("Rotor 3 Notch:");
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
        pane.add(rotor1Notch, 3, 0);
        pane.add(notch1, 4,0);
        pane.add(rotor1Pos, 1, 0);
        pane.add(pos1, 2, 0);
        pane.add(rotor2Notch, 3, 1);
        pane.add(notch2, 4, 1);
        pane.add(rotor2Pos, 1, 1);
        pane.add(pos2, 2, 1);
        pane.add(rotor3Notch, 3, 2);
        pane.add(notch3, 4, 2);
        pane.add(rotor3Pos, 1, 2);
        pane.add(pos3, 2, 2);
    }

    /// Sets various attributes of elements
    private void adjust(@NotNull GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        encrypt.setMaxWidth(300);
        output.setPrefSize(300, 300);
        output.setWrapText(true);
        notch1.setMaxWidth(50);
        notch2.setMaxWidth(50);
        notch3.setMaxWidth(50);
        pos1.setMaxWidth(50);
        pos2.setMaxWidth(50);
        pos3.setMaxWidth(50);
        encrypt.setOnMouseClicked(this::click);

    }

    /// Encrypts the text entered into the TextField and displays the output
    private void click(MouseEvent event) {
        try {
            String input = text.textProperty().getValue().toUpperCase(Locale.ROOT);
            int n1 = Integer.parseInt(notch1.textProperty().getValue());
            int n2 = Integer.parseInt(notch2.textProperty().getValue());
            int n3 = Integer.parseInt(notch3.textProperty().getValue());
            int p1 = Integer.parseInt(pos1.textProperty().getValue());
            int p2 = Integer.parseInt(pos2.textProperty().getValue());
            int p3 = Integer.parseInt(pos3.textProperty().getValue());

            Enigma.Rotor rotor1 = new Enigma.Rotor(p1, n1, "EKMFLGDQVZNTOWYHXUSPAIBRCJ");
            Enigma.Rotor rotor2 = new Enigma.Rotor(p2, n2, "AJDKSIRUXBLHWTMCQGZNPYFVOE");
            Enigma.Rotor rotor3 = new Enigma.Rotor(p3, n3, "BDFHJLCPRTXVZNYEIWGAKMUSQO");
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