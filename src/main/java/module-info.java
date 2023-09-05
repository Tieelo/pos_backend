module com.example.demo {
  requires javafx.controls;
  requires javafx.fxml;

  requires org.controlsfx.controls;
  requires org.kordamp.ikonli.javafx;
  requires org.kordamp.bootstrapfx.core;
  requires java.sql;
  requires org.xerial.sqlitejdbc;

  opens com.example.demo to javafx.fxml;
  exports com.example.demo;
  exports view.terminal;
    exports view.FXGui;
    exports model;
}
