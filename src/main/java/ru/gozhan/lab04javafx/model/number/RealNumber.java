package ru.gozhan.lab04javafx.model.number;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RealNumber implements Number {

    private double value;

}
