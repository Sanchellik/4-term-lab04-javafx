package ru.gozhan.lab04javafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ru.gozhan.lab04javafx.model.number.Number;

public abstract class NumberController<T extends Number> {

    @FXML
    protected TextField realDisplay;

    protected T firstNumber;
    protected T secondNumber;
    protected String currentOperator;

    public void processNumpad(ActionEvent event) {
        // To be overridden in child classes
    }

    public void processOperator(ActionEvent event) {
        // To be overridden in child classes
    }

    public void processEquals(ActionEvent event) {
        // To be overridden in child classes
    }

    public void processClear(ActionEvent event) {
        firstNumber = null;
        secondNumber = null;
        currentOperator = null;
        realDisplay.clear();
    }

    public void processSignChange() {
        if (!realDisplay.getText().isEmpty()) {
            double currentValue = Double.parseDouble(realDisplay.getText());
            realDisplay.setText(String.valueOf(-currentValue));
        }
    }

    public void processBackspace(ActionEvent event) {
        String currentText = realDisplay.getText();
        if (!currentText.isEmpty()) {
            String newText = currentText.substring(0, currentText.length() - 1);
            realDisplay.setText(newText);
        }
    }

}
