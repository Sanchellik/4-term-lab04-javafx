package ru.gozhan.lab04javafx.model.number;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.numbers.complex.Complex;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComplexNumber implements Number {

    private Complex value;

}
