package ru.gozhan.lab04javafx.model.calculation;

import ru.gozhan.lab04javafx.model.number.ComplexNumber;

public class ComplexNumberCalculation implements Calculation<ComplexNumber> {

    @Override
    public ComplexNumber add(ComplexNumber a, ComplexNumber b) {
        return new ComplexNumber(a.getValue().add(b.getValue()));
    }

    @Override
    public ComplexNumber subtract(ComplexNumber a, ComplexNumber b) {
        return new ComplexNumber(a.getValue().subtract(b.getValue()));
    }

    @Override
    public ComplexNumber multiply(ComplexNumber a, ComplexNumber b) {
        return new ComplexNumber(a.getValue().multiply(b.getValue()));
    }

    @Override
    public ComplexNumber divide(ComplexNumber a, ComplexNumber b) {
        return new ComplexNumber(a.getValue().divide(b.getValue()));
    }

}
