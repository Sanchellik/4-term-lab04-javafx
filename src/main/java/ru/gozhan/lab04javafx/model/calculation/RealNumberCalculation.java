package ru.gozhan.lab04javafx.model.calculation;

import ru.gozhan.lab04javafx.model.number.RealNumber;

public class RealNumberCalculation implements Calculation<RealNumber> {

    @Override
    public RealNumber add(RealNumber a, RealNumber b) {
        return new RealNumber(a.getValue() + b.getValue());
    }

    @Override
    public RealNumber subtract(RealNumber a, RealNumber b) {
        return new RealNumber(a.getValue() - b.getValue());
    }

    @Override
    public RealNumber multiply(RealNumber a, RealNumber b) {
        return new RealNumber(a.getValue() * b.getValue());
    }

    @Override
    public RealNumber divide(RealNumber a, RealNumber b) {
        if (b.getValue() == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return new RealNumber(a.getValue() / b.getValue());
    }

}
