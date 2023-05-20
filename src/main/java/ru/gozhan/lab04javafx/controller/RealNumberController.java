package ru.gozhan.lab04javafx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import ru.gozhan.lab04javafx.model.calculation.RealNumberCalculation;
import ru.gozhan.lab04javafx.model.number.RealNumber;

public class RealNumberController extends NumberController<RealNumber> {

    private RealNumberCalculation calculation;

    public RealNumberController() {
        this.calculation = new RealNumberCalculation();
    }

    @FXML
    public void processNumpad(Button button) {
        String buttonText = button.getText();
        display.appendText(buttonText);
    }

    @FXML
    public void processOperator(Button button) {
        String operator = button.getText();
        if (firstNumber == null) {
            firstNumber = new RealNumber(Double.parseDouble(display.getText()));
        } else if (secondNumber == null) {
            secondNumber = new RealNumber(Double.parseDouble(display.getText()));
            performOperation();
            firstNumber = new RealNumber(Double.parseDouble(display.getText()));
        }
        currentOperator = operator;
        display.clear();
    }

    private void performOperation() {
        if (currentOperator.equals("+")) {
            RealNumber result = calculation.add(firstNumber, secondNumber);
            display.setText(String.valueOf(result.getValue()));
        } else if (currentOperator.equals("-")) {
            RealNumber result = calculation.subtract(firstNumber, secondNumber);
            display.setText(String.valueOf(result.getValue()));
        }
        // Implement remaining operations
    }

    @FXML
    public void processEquals() {
        if (firstNumber != null && secondNumber == null) {
            secondNumber = new RealNumber(Double.parseDouble(display.getText()));
            performOperation();
            firstNumber = null;
            secondNumber = null;
        }
    }

    @FXML
    public void processSignChange() {
        if (!display.getText().isEmpty()) {
            double displayValue = Double.parseDouble(display.getText());
            display.setText(String.valueOf(-displayValue));
        }
    }

}
