module ru.gozhan.lab04javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.commons.numbers.complex;
    requires lombok;

    opens ru.gozhan.lab04javafx to javafx.fxml;
    opens ru.gozhan.lab04javafx.controller to javafx.fxml;

    exports ru.gozhan.lab04javafx;
    exports ru.gozhan.lab04javafx.application to javafx.graphics;
}