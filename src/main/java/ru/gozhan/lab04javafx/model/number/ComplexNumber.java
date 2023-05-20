package ru.gozhan.lab04javafx.model.number;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.numbers.complex.Complex;

@Data
@AllArgsConstructor
public class ComplexNumber implements Number {

    private Complex value;

}
