package model;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class ButtonCreation {

    public static HBox createItemRow(int start, int end) {
        HBox itemRow = new HBox();
        itemRow.setAlignment(Pos.TOP_CENTER);
        itemRow.setSpacing(10);

        for (int i = start; i <= end; i++) {
            Button itemButton = new Button("Item" + i);
            itemButton.setId("itembutton");
            itemButton.setMinWidth(100);
            itemButton.setMinHeight(50);
            itemRow.getChildren().add(itemButton);
        }

        return itemRow;
    }

    public static HBox createGroupRow(int start, int end) {
        HBox groupRow = new HBox();
        groupRow.setAlignment(Pos.TOP_CENTER);
        groupRow.setSpacing(10);

        for (int i = start; i <= end; i++) {
            Button groupButton = new Button("Group" + i);
            groupButton.setId("groupbutton");
            groupButton.setMinWidth(100);
            groupButton.setMinHeight(50);
            groupRow.getChildren().add(groupButton);
        }

        return groupRow;
    }
}
