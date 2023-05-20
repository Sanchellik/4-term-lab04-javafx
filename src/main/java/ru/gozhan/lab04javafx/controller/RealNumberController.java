package ru.gozhan.lab04javafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import ru.gozhan.lab04javafx.model.calculation.Calculation;
import ru.gozhan.lab04javafx.model.calculation.RealNumberCalculation;
import ru.gozhan.lab04javafx.model.number.RealNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class RealNumberController extends NumberController<RealNumber> {

    private List<RealNumber> numbers;
    private List<String> operators;
    private Calculation<RealNumber> calculation;

    public RealNumberController() {
        numbers = new ArrayList<>();
        operators = new ArrayList<>();
        calculation = new RealNumberCalculation();
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

        if (!display.getText().isEmpty()) {
            double value = Double.parseDouble(display.getText());
            numbers.add(new RealNumber(value));
        }

        display.clear();
        operators.add(buttonText);
    }

    @FXML
    public void processEquals() {
        if (!display.getText().isEmpty()) {
            double value = Double.parseDouble(display.getText());
            numbers.add(new RealNumber(value));
        }

        try {
            RealNumber result = performCalculation();
            display.setText(String.valueOf(result.getValue()));
        } catch (IllegalArgumentException e) {
            display.setText(e.getMessage());
        }

        numbers.clear();
        operators.clear();
    }

    private RealNumber performCalculation() {
        if (numbers.isEmpty()) {
            return new RealNumber(0);
        }

        Stack<RealNumber> numberStack = new Stack<>();
        Stack<String> operatorStack = new Stack<>();

        for (int i = 0; i < operators.size(); i++) {
            String operator = operators.get(i);
            RealNumber number = numbers.get(i);

            while (!operatorStack.isEmpty() && hasHigherPriority(operatorStack.peek(), operator)) {
                RealNumber prevNumber = numberStack.pop();
                String prevOperator = operatorStack.pop();
                number = performOperation(prevNumber, prevOperator, number);
            }

            numberStack.push(number);
            operatorStack.push(operator);
        }

        while (!operatorStack.isEmpty()) {
            RealNumber prevNumber = numberStack.pop();
            String prevOperator = operatorStack.pop();
            RealNumber number = numbers.get(numbers.size() - 1);
            number = performOperation(prevNumber, prevOperator, number);
            numberStack.push(number);
        }

        return numberStack.pop();
    }

    private boolean hasHigherPriority(String operator1, String operator2) {
        return (operator1.equals("*") || operator1.equals("/")) && (operator2.equals("+") || operator2.equals("-"));
    }

    private RealNumber performOperation(RealNumber num1, String operator, RealNumber num2) {
        switch (operator) {
            case "+" -> {
                return calculation.add(num1, num2);
            }
            case "-" -> {
                return calculation.subtract(num1, num2);
            }
            case "*" -> {
                return calculation.multiply(num1, num2);
            }
            case "/" -> {
                if (num2.getValue() == 0) {
                    throw new IllegalArgumentException("Cannot divide by zero");
                }
                return calculation.divide(num1, num2);
            }
            default -> throw new IllegalArgumentException("Invalid operator");
        }
    }

    @FXML
    public void processSignChange(ActionEvent event) {
        double currentValue = Double.parseDouble(display.getText());
        display.setText(String.valueOf(-currentValue));
    }

}
