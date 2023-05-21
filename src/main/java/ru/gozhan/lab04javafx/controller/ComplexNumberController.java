package ru.gozhan.lab04javafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ru.gozhan.lab04javafx.model.number.ComplexNumber;
import ru.gozhan.lab04javafx.model.calculation.ComplexNumberCalculation;
import org.apache.commons.numbers.complex.Complex;

public class ComplexNumberController extends NumberController<ComplexNumber> {

    @FXML
    private TextField imaginaryDisplay;

    private ComplexNumberCalculation calculation;
    private boolean isRealPart = true;

    public ComplexNumberController() {
        calculation = new ComplexNumberCalculation();
    }

    public void initialize() {
        realDisplay.setText("real");
        imaginaryDisplay.setText("imaginary");
    }

    @Override
    public void processNumpad(ActionEvent event) {
        String digit = ((Button) event.getSource()).getText();
        if (isRealPart) {
            removePlaceholder(realDisplay);
            realDisplay.appendText(digit);
        } else {
            removePlaceholder(imaginaryDisplay);
            imaginaryDisplay.appendText(digit);
        }
    }

    private void removePlaceholder(TextField display) {
        if (display.getText().equals("real") || display.getText().equals("imaginary")) {
            display.clear();
        }
    }

    @Override
    public void processOperator(ActionEvent event) {
        String operator = ((Button) event.getSource()).getText();
        if (operator.equals("switch")) {
            isRealPart = !isRealPart;
            return;
        }
        if (operator.equals("mod")) {
            double modulus = calculation.mod(new ComplexNumber(
                    Complex.ofCartesian(
                            parseDisplayValue(realDisplay),
                            parseDisplayValue(imaginaryDisplay)
                    )
            ));
            realDisplay.setText(Double.toString(modulus));
            imaginaryDisplay.clear();
            return;
        }
        currentOperator = operator;
        firstNumber = new ComplexNumber(
                Complex.ofCartesian(
                        parseDisplayValue(realDisplay),
                        parseDisplayValue(imaginaryDisplay)
                )
        );
        initialize();
        isRealPart = true;
    }

    private double parseDisplayValue(TextField display) {
        if (display.getText().isEmpty() || display.getText().equals("real") || display.getText().equals("imaginary")) {
            return 0.0;
        }
        return Double.parseDouble(display.getText());
    }

    @Override
    public void processEquals(ActionEvent event) {
        secondNumber = new ComplexNumber(
                Complex.ofCartesian(
                        parseDisplayValue(realDisplay),
                        parseDisplayValue(imaginaryDisplay)
                )
        );
        ComplexNumber result = switch (currentOperator) {
            case "+" -> calculation.add(firstNumber, secondNumber);
            case "-" -> calculation.subtract(firstNumber, secondNumber);
            case "*" -> calculation.multiply(firstNumber, secondNumber);
            case "/" -> calculation.divide(firstNumber, secondNumber);
            case "^" -> calculation.pow(firstNumber, secondNumber);
            default -> throw new UnsupportedOperationException("Unsupported operator: " + currentOperator);
        };
        realDisplay.setText(Double.toString(result.getValue().getReal()));
        imaginaryDisplay.setText(Double.toString(result.getValue().getImaginary()));
    }

    @Override
    public void processSignChange() {
        if (!isRealPart) {
            if (!imaginaryDisplay.getText().isEmpty() && !imaginaryDisplay.getText().equals("imaginary")) {
                double currentValue = Double.parseDouble(imaginaryDisplay.getText());
                imaginaryDisplay.setText(String.valueOf(-currentValue));
            }
        } else {
            super.processSignChange();
        }
    }

    @Override
    public void processClear(ActionEvent event) {
        realDisplay.setText("real");
        imaginaryDisplay.setText("imaginary");
        isRealPart = true;
    }

    @Override
    public void processBackspace(ActionEvent event) {
        if (isRealPart) {
            if (!realDisplay.getText().isEmpty()) {
                String newText = realDisplay.getText().substring(0, realDisplay.getText().length() - 1);
                realDisplay.setText(newText);
            }
        } else {
            if (!imaginaryDisplay.getText().isEmpty()) {
                String newText = imaginaryDisplay.getText().substring(0, imaginaryDisplay.getText().length() - 1);
                imaginaryDisplay.setText(newText);
            }
        }
    }

}
