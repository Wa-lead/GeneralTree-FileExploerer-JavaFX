package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.net.URL;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;

public class WALEED {
    
        @FXML
        private Pane mainPage;

        @FXML
        private ImageView Search;

        @FXML
        void browseFiles(MouseEvent event) throws IOException {
            final DirectoryChooser dir = new DirectoryChooser();
            Stage stage = (Stage) mainPage.getScene().getWindow();
            File file1 = dir.showDialog(stage);
            Data.path = file1.getAbsolutePath();
            Pane pane1 = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
            mainPage.getChildren().setAll(pane1);
        }

    @FXML
    void ReadMeShow(MouseEvent event) {
        AlertBox("ReadMe","Hi Iam waleed");
    }
   

    public static void enter(File file, GeneralTree<String> tree, String parent) {
        for (File fileEntry : file.listFiles()) {
            if (fileEntry.isDirectory()) {
                tree.insert(parent, fileEntry.getName());
                enter(fileEntry, tree, fileEntry.getName());
            } else {
                tree.insert(parent, fileEntry.getName());
            }
        }
    }

    public void AlertBox(String title, String massege) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(450);

        Label msg = new Label(massege);

        JFXButton closeJFXButton = new JFXButton("close");
        closeJFXButton.setOnAction(e -> window.close());

        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(8, 8, 8, 8));
        vbox.getChildren().addAll(msg, closeJFXButton);

        Scene scene = new Scene(vbox);
        window.setScene(scene);
        window.showAndWait();
    }

}
