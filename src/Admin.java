
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Redzer0
 */
public class Admin extends Application {

    private String username;
    private String password;

    private String filename = "Administrator";
    private String[] header = {"ID", "Username", "Password"};

    private Stage window;
    private iHotel main_page;
    private Database db;

    public static void main2(String[] args) {
        launch(args);
    }

    public Admin() throws IOException {
        db = new Database();
        if (db.create(filename)) {
            db.addHeader(filename, header);
        }
    }

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void start(Stage stage) throws Exception {
        //[Inintialize] Stage
        window = stage;
        window.getIcons().add(new Image(new FileInputStream(new File("src/img/icon.png"))));
        window.setTitle("[iHotel] - Administrator");
        window.setResizable(false);

        //[Component]
        Button logout_bt = new Button("Logout");
        logout_bt.setOnMouseClicked(e -> {
            main_page = new iHotel();
            try {
                main_page.start(window);
            } catch (Exception ex) {
                Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        //[Pane Setting]
        StackPane rootPane = new StackPane();

        //[View] --show window
        rootPane.getChildren().addAll(logout_bt);
        Scene user_page = new Scene(rootPane, 1600, 900);
        window.setScene(user_page);
        window.show();
    }

}
