package ru.gozhan.lab04javafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import ru.gozhan.lab04javafx.model.calculation.Calculation;
import ru.gozhan.lab04javafx.model.calculation.RealNumberCalculation;
import ru.gozhan.lab04javafx.model.number.RealNumber;

import java.util.ArrayList;
import java.util.List;

public class RealNumberController extends NumberController<RealNumber> {

    private List<RealNumber> numbers;

    public RealNumberController() {
        numbers = new ArrayList<>();
    }

    @FXML
    public void processNumpad(ActionEvent event) {
        Button button = (Button) event.getSource();
        String buttonText = button.getText();

        if (buttonText.equals(".") && display.getText().contains(".")) {
            return;
        }

        display.setText(display.getText() + buttonText);
    }

    @FXML
    public void processOperator(ActionEvent event) {
        Button button = (Button) event.getSource();
        String buttonText = button.getText();

        if (!display.getText().isEmpty()){
            double value = Double.parseDouble(display.getText());
            numbers.add(new RealNumber(value));
        }

        display.clear();

        if (buttonText.equals("sqrt") || buttonText.equals("%")) {
            try {
                RealNumber result = buttonText.equals("sqrt") ? performSqrt(numbers) : performPercent(numbers);
                display.setText(String.valueOf(result.getValue()));
            } catch (IllegalArgumentException e) {
                display.setText(e.getMessage());
            }
        } else {
            currentOperator = buttonText;
        }
    }

    private RealNumber performSqrt(List<RealNumber> numbers) {
        if (numbers.isEmpty()) {
            return new RealNumber(0);
        }

        RealNumber number = numbers.get(0);
        numbers.clear();
        RealNumberCalculation calculation = new RealNumberCalculation();
        return calculation.sqrt(number);
    }

    private RealNumber performPercent(List<RealNumber> numbers) {
        if (numbers.isEmpty()) {
            return new RealNumber(0);
        }

        RealNumber number = numbers.get(0);
        numbers.clear();
        RealNumberCalculation calculation = new RealNumberCalculation();
        return calculation.percent(number);
    }

    @FXML
    public void processEquals() {
        if (!display.getText().isEmpty()){
            double value = Double.parseDouble(display.getText());
            numbers.add(new RealNumber(value));
        }

        try {
            RealNumber result = performCalculation(numbers);
            display.setText(String.valueOf(result.getValue()));
        } catch (IllegalArgumentException e) {
            display.setText(e.getMessage());
        }

        numbers.clear();
        currentOperator = null;
    }

    private RealNumber performCalculation(List<RealNumber> numbers) {
        if (numbers.isEmpty()) {
            return new RealNumber(0);
        }

        RealNumber result = numbers.get(0);
        RealNumberCalculation calculation = new RealNumberCalculation();

        for (int i = 1; i < numbers.size(); i++) {
            RealNumber number = numbers.get(i);

            result = switch (currentOperator) {
                case "+" -> calculation.add(result, number);
                case "-" -> calculation.subtract(result, number);
                case "*" -> calculation.multiply(result, number);
                case "/" -> calculation.divide(result, number);
                case "mod" -> calculation.mod(result, number);
                case "^" -> calculation.pow(result, number);
                default -> throw new IllegalArgumentException("Invalid operator");
            };
        }
        return result;
    }

}
