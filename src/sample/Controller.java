















package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.sql.Time;

public class Controller {
    private File file = new File(Data.path);
    private GeneralTree<String> Tree = new GeneralTree<String>(file,file.getName());
    
    @FXML
    
    private ImageView printButton;
    
    @FXML
    private ImageView insertButton;

    @FXML
    private ImageView sortButton;

    @FXML
    private ImageView deleteButton;

    @FXML
    private ImageView pathButton;

    @FXML
    private ImageView TravelsalsButton;

    @FXML
    private JFXTextArea BreadthFirstText;

    @FXML
    private JFXTextArea PostOrderText;

    @FXML
    private JFXTextArea PreOrderText;



    @FXML
    void printTravelsals(MouseEvent event) {
        
        BreadthFirstText.setText(Tree.BreadthFirst());
        BreadthFirstText.setFont(Font.font("Courier New", 14));

        PostOrderText.setText(Tree.Postorder());
        PostOrderText.setFont(Font.font("Courier New", 14));

        PreOrderText.setText(Tree.Preorder());
        PreOrderText.setFont(Font.font("Courier New", 14));

    }

    @FXML
    void deleteAndPrint(MouseEvent event) {
        deleteBox("Delete File", "Enter the file name to delete");
        Tree.delete(Data.path);
        TextArea.setText(Tree.printTree());
        TextArea.setFont(Font.font("Courier New", 14));
    }

    @FXML
    void insertAndPrint(MouseEvent event) {
        InsertBox("Insertion","Enter parent and child respectively");
        Tree.insert(Data.parent,Data.insertedFile);
        TextArea.setText(Tree.printTree());
        TextArea.setFont(Font.font("Courier New", 14));
    }

    @FXML
    void printPath(MouseEvent event) {
        SearchPathBox("Path","Enter the file name to track path");
            TextArea.setText(Tree.search(Data.path));
            Data.path="";
    }

    @FXML
    private JFXTextArea TextArea;

    @FXML
    void sortAndPrint(MouseEvent event) {
        Tree.sortByLevel();
        TextArea.setText(Tree.printTree());
        TextArea.setFont(Font.font("Courier New", 14));
    }


    @FXML
    void printTree(MouseEvent event) throws InterruptedException {
        TextArea.setText(Tree.printTree());
        TextArea.setFont(Font.font("Courier New", 14));
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

    public void InsertBox(String title, String massege) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(450);

        Label msg = new Label(massege);

        JFXButton closeJFXButton = new JFXButton("Insert");

        TextField parent = new TextField();
        TextField added = new TextField();

        System.out.println(parent.getText());
        System.out.println(added.getText());
        

        closeJFXButton.setOnAction(e -> {
            Data.parent = parent.getText();
            Data.insertedFile = added.getText();
            window.close();
        });

        Data.parent = parent.getText();
        Data.insertedFile = added.getText();
        System.out.println(Data.parent+" "+Data.insertedFile);

        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(8, 8, 8, 8));
        vbox.getChildren().addAll(msg,parent,added,closeJFXButton);
        
       

        Scene scene = new Scene(vbox);
        window.setScene(scene);
        window.showAndWait();
    }

    public void SearchPathBox(String title, String massege) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(450);

        Label msg = new Label(massege);

        JFXButton closeJFXButton = new JFXButton("Print path");

        TextField path = new TextField();

        closeJFXButton.setOnAction(e -> {
            Data.path = path.getText();
            window.close();
        });

        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(8, 8, 8, 8));
        vbox.getChildren().addAll(msg,path,closeJFXButton);



        Scene scene = new Scene(vbox);
        window.setScene(scene);
        window.showAndWait();
    }
    
    public void deleteBox(String title, String massege) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(450);

        Label msg = new Label(massege);

        JFXButton closeJFXButton = new JFXButton("Delete file");

        TextField path = new TextField();

        closeJFXButton.setOnAction(e -> {
            Data.path = path.getText();
            window.close();
        });

        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(8, 8, 8, 8));
        vbox.getChildren().addAll(msg,path,closeJFXButton);



        Scene scene = new Scene(vbox);
        window.setScene(scene);
        window.showAndWait();
    }


}


















//package sample;
//
//import com.jfoenix.controls.JFXButton;
//import com.jfoenix.controls.JFXTextArea;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.Pane;
//import javafx.scene.text.Font;
//import javafx.stage.DirectoryChooser;
//import javafx.stage.Stage;
//
//import java.io.File;
//import java.io.IOException;
//
//public class Controller {
//
//    @FXML
//    private Pane pane;
//
//    @FXML
//    private JFXButton deleteButton;
//
//    @FXML
//    private JFXButton InsertButton;
//
//    @FXML
//    private JFXButton sortButton;
//
//    @FXML
//    private JFXButton pathButton;
//
//    @FXML
//    private JFXTextArea fileDirectory;
//
//    @FXML
//    void InsertFileMethod(MouseEvent event) {
//        
//            File file = new File(Data.text);
//            GeneralTree<String> tree = new GeneralTree<String>(file.getName());
//            enter(file, tree, file.getName());
//            fileDirectory.setText(tree.printTree());
//            fileDirectory.setFont(Font.font ("Courier New", 14));
//            
//        }
//        
//    @FXML
//    void goToTravelsals(MouseEvent event) throws IOException {
//        Pane pane1 = FXMLLoader.load(getClass().getResource("Travelsals.fxml"));
//        pane.getChildren().setAll(pane1);
//    }
//    
//
//    @FXML
//    void changeScreenButtonPushed(MouseEvent event) throws IOException {
//            Pane pane1 = FXMLLoader.load(getClass().getResource("WALEED.fxml"));
//        pane.getChildren().setAll(pane1);
//    }
//
