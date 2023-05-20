package ru.gozhan.lab04javafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ru.gozhan.lab04javafx.model.number.Number;

public abstract class NumberController<T extends Number> {

    @FXML
    protected TextField display;

    protected T firstNumber;
    protected T secondNumber;
    protected String currentOperator;

    public void processNumpad() {
        // To be overridden in child classes
    }

    public void processOperator() {
        // To be overridden in child classes
    }

    public void processEquals() {
        // To be overridden in child classes
    }

    public void processClear() {
        firstNumber = null;
        secondNumber = null;
        currentOperator = null;
        display.clear();
    }

    public void processSignChange() {
        if (!display.getText().isEmpty()) {
            double currentValue = Double.parseDouble(display.getText());
            display.setText(String.valueOf(-currentValue));
        }
    }

    public void processBackspace(ActionEvent event) {
        String currentText = display.getText();
        if (!currentText.isEmpty()) {
            String newText = currentText.substring(0, currentText.length() - 1);
            display.setText(newText);
        }
    }

}
