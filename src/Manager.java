
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.converter.DefaultStringConverter;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.NumberStringConverter;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.xb.xsdschema.BlockSet.Member2.Item;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Redzer0
 */
public class Manager extends Application {

    private String username;
    private String password;
    private Stage window;

    private iHotel main_page;
    private BookingManagement book_mg = new BookingManagement();
    private RoomManagement room_ma;
    private TableView<Check> checkin_table = new TableView();

    public static void main2(String[] args) {
        launch(args);
    }

    public Manager() throws IOException {
        this.room_ma = new RoomManagement();
    }

    public Manager(String username, String password) throws IOException {
        this.room_ma = new RoomManagement();
        this.username = username;
        this.password = password;
    }

    @Override
    public void start(Stage stage) throws Exception {
        //[Inintialize] Stage
        window = stage;
        window.getIcons().add(new Image(new FileInputStream(new File("src/img/icon.png"))));
        window.setTitle("[iHotel] - Manager");
        window.setResizable(false);

        //[Pane Setting]
        StackPane rootPane = new StackPane();
        BorderPane border = new BorderPane();

        //[Component] - Logo
        ImageView logo = new ImageView(new Image(new FileInputStream(new File("src/img/logo-s.png"))));
        logo.setFitHeight(73);
        logo.setFitWidth(180);

        //[Component]
        Button logout_bt = new Button("Logout");
        logout_bt.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 26));
        logout_bt.setStyle("-fx-background-radius: 2; -fx-background-color: #F67575; -fx-text-fill: #000000");
        //On mouse idle
        logout_bt.setOnMouseExited(e -> {
            logout_bt.setStyle("-fx-background-radius: 2; -fx-background-color: #F67575; -fx-text-fill: #000000");
            logout_bt.setEffect(null);
        });
        //On mouse hover
        logout_bt.setOnMouseEntered(e -> {
            logout_bt.setStyle("-fx-background-radius: 2; -fx-background-color: #FAACAC; -fx-text-fill: #ffffff");
            logout_bt.setEffect(new DropShadow());
        });
        //On mouse click
        logout_bt.setOnMouseClicked(e -> {
            logout_bt.setStyle("-fx-background-radius: 2; -fx-background-color: #F67575; -fx-text-fill: #000000");
            main_page = new iHotel();
            try {
                main_page.start(window);
            } catch (Exception ex) {
                Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        //[Component] - Room Management  [Label]
        Label room_man_label = new Label("Room Management");
        room_man_label.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 24));

        //[Component] - Room Management - Search bar [Hbox]
        TextField search_box = new TextField();
        search_box.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 18));
        search_box.setStyle("-fx-min-width: 400; ");

        Button search_bt = new Button("ค้นหา");
        search_bt.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 18));

        HBox Search_bar = new HBox(2);
        Search_bar.setMargin(search_box, new Insets(10, 10, 10, 10));
        Search_bar.setMargin(search_bt, new Insets(10, 10, 10, 10));
        Search_bar.setAlignment(Pos.BASELINE_RIGHT);
        Search_bar.getChildren().addAll(search_box, search_bt);

        //[Component] - Room Management Table [Table]
        TableView<Room> room_table = new TableView();
        room_table.setEditable(true);

        for (int t = 0; t < this.fetchRoom().size(); t++) {
            room_table.getItems().add(this.fetchRoom().get(t));
        }

        TableColumn room_tb_col1 = new TableColumn<>("ID");
        room_tb_col1.setCellValueFactory(new PropertyValueFactory<>("roomID"));
        room_tb_col1.setCellFactory(TextFieldTableCell.forTableColumn());
        room_tb_col1.setEditable(false);
        room_tb_col1.setMinWidth(50);
        room_tb_col1.setReorderable(false);
        room_tb_col1.setResizable(false);
        room_tb_col1.setStyle("-fx-alignment: CENTER;");

        TableColumn room_tb_col2 = new TableColumn<>("Room Number");
        room_tb_col2.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        room_tb_col2.setCellFactory(TextFieldTableCell.forTableColumn());
        room_tb_col2.setEditable(true);
        room_tb_col2.setMinWidth(100);
        room_tb_col2.setReorderable(false);
        room_tb_col2.setResizable(false);
        room_tb_col2.setStyle("-fx-alignment: CENTER;");
        room_tb_col2.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Room, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Room, String> t) {
                t.getTableView().getItems().get(t.getTablePosition().getRow()).setRoomNumber(t.getNewValue());
            }
        });

        TableColumn room_tb_col3 = new TableColumn<>("Room Type");
        room_tb_col3.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        room_tb_col3.setCellFactory(TextFieldTableCell.forTableColumn());
        room_tb_col3.setEditable(true);
        room_tb_col3.setCellFactory(ComboBoxTableCell.forTableColumn("Superior", "Delux", "Junior Suite", "Royal Suite"));
        room_tb_col3.setMinWidth(100);
        room_tb_col3.setReorderable(false);
        room_tb_col3.setResizable(false);
        room_tb_col3.setStyle("-fx-alignment: CENTER;");
        room_tb_col3.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Room, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Room, String> t) {
                t.getTableView().getItems().get(t.getTablePosition().getRow()).setRoomType(t.getNewValue());
            }
        });

        TableColumn room_tb_col4 = new TableColumn<>("Room Class");
        room_tb_col4.setCellValueFactory(new PropertyValueFactory<>("roomClass"));
        room_tb_col4.setCellFactory(TextFieldTableCell.forTableColumn());
        room_tb_col4.setCellFactory(ComboBoxTableCell.forTableColumn("Normal", "Premiun"));
        room_tb_col4.setEditable(true);
        room_tb_col4.setMinWidth(100);
        room_tb_col4.setReorderable(false);
        room_tb_col4.setResizable(false);
        room_tb_col4.setStyle("-fx-alignment: CENTER;");
        room_tb_col4.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Room, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Room, String> t) {
                t.getTableView().getItems().get(t.getTablePosition().getRow()).setRoomClass(t.getNewValue());
            }
        });

        TableColumn room_tb_col5 = new TableColumn<>("Building");
        room_tb_col5.setCellValueFactory(new PropertyValueFactory<>("building"));
        room_tb_col5.setCellFactory(TextFieldTableCell.forTableColumn());
        room_tb_col5.setCellFactory(ComboBoxTableCell.forTableColumn("A"));
        room_tb_col5.setEditable(true);
        room_tb_col5.setMinWidth(100);
        room_tb_col5.setReorderable(false);
        room_tb_col5.setResizable(false);
        room_tb_col5.setStyle("-fx-alignment: CENTER;");
        room_tb_col5.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Room, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Room, String> t) {
                t.getTableView().getItems().get(t.getTablePosition().getRow()).setBuilding(t.getNewValue());
            }
        });

        TableColumn room_tb_col6 = new TableColumn<>("Floor");
        room_tb_col6.setCellValueFactory(new PropertyValueFactory<>("floor"));
        room_tb_col6.setCellFactory(TextFieldTableCell.forTableColumn());
        room_tb_col6.setEditable(true);
        room_tb_col2.setMinWidth(50);
        room_tb_col6.setReorderable(false);
        room_tb_col6.setResizable(false);
        room_tb_col6.setStyle("-fx-alignment: CENTER;");
        room_tb_col6.setCellFactory(ComboBoxTableCell.forTableColumn("1", "2", "3", "4", "5", "6", "7", "8", "9"));
        room_tb_col6.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Room, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Room, String> t) {
                t.getTableView().getItems().get(t.getTablePosition().getRow()).setFloor(t.getNewValue());
            }
        });

        TableColumn room_tb_col10 = new TableColumn<>("Number of Bed");
        room_tb_col10.setCellValueFactory(new PropertyValueFactory<>("numBed"));
        room_tb_col10.setCellFactory(TextFieldTableCell.forTableColumn());
        room_tb_col10.setEditable(true);
        room_tb_col10.setVisible(false);

        TableColumn room_tb_col7 = new TableColumn<>("Base Price");
        room_tb_col7.setCellValueFactory(new PropertyValueFactory<Room, Double>("basePrice"));
        room_tb_col7.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
        room_tb_col7.setEditable(true);
        room_tb_col7.setMinWidth(100);
        room_tb_col7.setReorderable(false);
        room_tb_col7.setResizable(false);
        room_tb_col7.setStyle("-fx-alignment: CENTER;");
        room_tb_col7.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Room, Long>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Room, Long> t) {
                t.getTableView().getItems().get(t.getTablePosition().getRow()).setBasePrice(t.getNewValue());
            }
        });

        TableColumn room_tb_col8 = new TableColumn<>("Status");
        room_tb_col8.setCellValueFactory(new PropertyValueFactory<>("Status"));
        room_tb_col8.setCellFactory(ComboBoxTableCell.forTableColumn("Occupied", "Unoccupied"));
        room_tb_col8.setEditable(true);
        room_tb_col8.setMinWidth(100);
        room_tb_col8.setReorderable(false);
        room_tb_col8.setResizable(false);
        room_tb_col8.setStyle("-fx-alignment: CENTER;");
        room_tb_col8.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Room, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Room, String> t) {
                t.getTableView().getItems().get(t.getTablePosition().getRow()).setStatus(t.getNewValue());
            }
        });

        TableColumn room_tb_col9 = new TableColumn<>("Data Created");
        room_tb_col9.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));
        room_tb_col9.setCellFactory(TextFieldTableCell.forTableColumn());
        room_tb_col9.setEditable(true);
        room_tb_col9.setMinWidth(150);
        room_tb_col9.setReorderable(false);
        room_tb_col9.setResizable(false);
        room_tb_col9.setStyle("-fx-alignment: CENTER;");
        room_tb_col9.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Room, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Room, String> t) {
                t.getTableView().getItems().get(t.getTablePosition().getRow()).setDateCreated(t.getNewValue());
            }
        });

        room_table.getColumns().add(room_tb_col1);
        room_table.getColumns().add(room_tb_col2);
        room_table.getColumns().add(room_tb_col3);
        room_table.getColumns().add(room_tb_col4);
        room_table.getColumns().add(room_tb_col5);
        room_table.getColumns().add(room_tb_col6);
        room_table.getColumns().add(room_tb_col10);
        room_table.getColumns().add(room_tb_col7);
        room_table.getColumns().add(room_tb_col8);
        room_table.getColumns().add(room_tb_col9);

        //[Component] - Room Management - Save [Button]
        Button save_room = new Button("บันทึก");
        save_room.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 18));
        //save_room.setStyle("-fx-background-color: #1EB2A6;");
        save_room.setOnMouseClicked(eh -> {
            try {
                this.updateRoom(room_table, "RoomStock");
            } catch (IOException ex) {
                Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidFormatException ex) {
                Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
            }

            room_table.getItems().clear();
            try {
                for (int t = 0; t < this.fetchRoom().size(); t++) {
                    room_table.getItems().add(this.fetchRoom().get(t));
                }
            } catch (IOException ex) {
                Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        //[Component] - Room Management - Delete [Button]
        Button del_room = new Button("ลบ");
        del_room.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 18));
        del_room.setOnAction(eh -> {
            Room selectedItem = room_table.getSelectionModel().getSelectedItem();
            room_table.getItems().remove(selectedItem);

        });

        //[Component] - Room Management - add Room bar [Hbox]
        TextField Room_Number = new TextField();
        Room_Number.setStyle("-fx-max-width: 80; ");

        ChoiceBox<String> Room_Type = new ChoiceBox<>();
        Room_Type.setStyle("-fx-max-width: 80;");
        Room_Type.getItems().addAll("Superior", "Delux", "Junior Suite", "Royal Suite");
        Room_Type.setValue("Superior");

        ChoiceBox<String> Room_Class = new ChoiceBox<>();
        Room_Class.setStyle("-fx-max-width: 80;");
        Room_Class.getItems().addAll("Normal", "Premium");
        Room_Class.setValue("Normal");

        ChoiceBox<String> Building = new ChoiceBox<>();
        Building.setStyle("-fx-max-width: 100;");
        Building.getItems().addAll("A");
        Building.setValue("A");

        ChoiceBox<String> Floor = new ChoiceBox<>();
        Floor.setStyle("-fx-max-width: 100;");
        Floor.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9");
        Floor.setValue("1");

        TextField Base_price = new TextField();
        Base_price.setStyle("-fx-max-width: 80; ");

        ChoiceBox<String> Status = new ChoiceBox<>();
        Status.setStyle("-fx-max-width: 100;");
        Status.getItems().addAll("Occupied", "Unoccupied");
        Status.setValue("Unoccupied");

        Button add_room = new Button("เพิ่ม");
        add_room.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 18));
        add_room.setOnAction(eh -> {

            Room new_room = new Room(Room_Number.getText(), Room_Type.getValue(), Room_Class.getValue(), Building.getValue(), Floor.getValue(), 1, Double.valueOf(Base_price.getText()));

            room_table.getItems().add(new_room);
            room_table.getItems().clear();
            try {
                for (int t = 0; t < this.fetchRoom().size(); t++) {
                    room_table.getItems().add(this.fetchRoom().get(t));
                }
            } catch (IOException ex) {
                Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
            }
            room_table.scrollTo((room_table.getItems().size() - 1));

            try {
                room_ma.addRoom(new_room);
            } catch (IOException ex) {
                Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
            }

            new_room = null;
            Room_Number.setText(" ");
            Base_price.setText(" ");
            room_table.getItems().clear();

            try {
                for (int t = 0; t < this.fetchRoom().size(); t++) {
                    room_table.getItems().add(this.fetchRoom().get(t));
                }
            } catch (IOException ex) {
                Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
            }

            room_table.scrollTo((room_table.getItems().size() - 1));
        });

        HBox add_room_bar = new HBox(11);

        add_room_bar.setMargin(Room_Number, new Insets(0, 0, 0, 90));
        add_room_bar.setMargin(Room_Type, new Insets(0, 0, 0, 10));
        add_room_bar.setMargin(Room_Class, new Insets(0, 0, 0, 10));
        add_room_bar.setMargin(Building, new Insets(0, 0, 0, 30));
        add_room_bar.setMargin(Floor, new Insets(0, 0, 0, 30));
        add_room_bar.setMargin(Base_price, new Insets(0, 0, 0, 30));
        add_room_bar.setMargin(Status, new Insets(0, 0, 0, 10));
        add_room_bar.setMargin(add_room, new Insets(0, 0, 0, 250));

        add_room_bar.setAlignment(Pos.BASELINE_LEFT);
        add_room_bar.getChildren().addAll(Room_Number, Room_Type, Room_Class,
                Building, Floor, Base_price, Status, add_room, del_room, save_room);

        //[Component] - Room Management [All Center Area]
        VBox center_room = new VBox();
        center_room.setVisible(true);

        center_room.setMargin(room_man_label, new Insets(10, 10, 10, 10));
        center_room.setMargin(room_table, new Insets(10, 10, 10, 10));
        center_room.setMargin(add_room_bar, new Insets(0, 10, 10, 10));

        center_room.getChildren().addAll(room_man_label, Search_bar, room_table, add_room_bar);

        //[Component] - Booking Management  [Label]
        Label book_man_label = new Label("Booking Management");
        book_man_label.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 24));

        //[Component] - Booking Management - Search bar [Hbox]
        TextField book_search_box = new TextField();
        book_search_box.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 18));
        book_search_box.setStyle("-fx-min-width: 400; ");

        Button book_search_bt = new Button("ค้นหา");
        book_search_bt.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 18));

        HBox book_Search_bar = new HBox(2);
        book_Search_bar.setMargin(book_search_box, new Insets(10, 10, 10, 10));
        book_Search_bar.setMargin(book_search_bt, new Insets(10, 10, 10, 10));
        book_Search_bar.setAlignment(Pos.BASELINE_RIGHT);
        book_Search_bar.getChildren().addAll(book_search_box, book_search_bt);

        //[Component] - Booking Management Table [Table]
        TableView<Booking> book_table = new TableView();

        book_table.setEditable(true);

        for (int t = 0; t < this.fetchBooking().size(); t++) {
            book_table.getItems().add(this.fetchBooking().get(t));
        }

        TableColumn book_tb_col1 = new TableColumn<Booking, String>("Booking ID");
        book_tb_col1.setCellValueFactory(new PropertyValueFactory<Booking, String>("ID"));
        book_tb_col1.setEditable(false);
        book_tb_col1.setMinWidth(50);
        book_tb_col1.setReorderable(false);
        book_tb_col1.setResizable(false);
        book_tb_col1.setStyle("-fx-alignment: CENTER;");

        TableColumn book_tb_col2 = new TableColumn<>("Customer name");
        book_tb_col2.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
        book_tb_col2.setCellFactory(TextFieldTableCell.forTableColumn());
        book_tb_col2.setEditable(true);
        book_tb_col2.setMinWidth(140);
        book_tb_col2.setReorderable(false);
        book_tb_col2.setResizable(false);
        book_tb_col2.setStyle("-fx-alignment: CENTER;");
        book_tb_col2.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Booking, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Booking, String> t) {
                t.getTableView().getItems().get(t.getTablePosition().getRow()).setCustomerName(t.getNewValue());
            }
        });

        TableColumn book_tb_col3 = new TableColumn<>("Check-in Date");
        book_tb_col3.setCellValueFactory(new PropertyValueFactory<>("CheckInDate"));
        book_tb_col3.setCellFactory(TextFieldTableCell.forTableColumn());
        book_tb_col3.setEditable(true);
        book_tb_col3.setMinWidth(90);
        book_tb_col3.setReorderable(false);
        book_tb_col3.setResizable(false);
        book_tb_col3.setStyle("-fx-alignment: CENTER;");
        book_tb_col3.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Booking, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Booking, String> t) {
                t.getTableView().getItems().get(t.getTablePosition().getRow()).setCheckInDate(t.getNewValue());
            }
        });

        TableColumn book_tb_col4 = new TableColumn<>("Check-out Date");
        book_tb_col4.setCellValueFactory(new PropertyValueFactory<>("CheckOutDate"));
        book_tb_col4.setCellFactory(TextFieldTableCell.forTableColumn());
        book_tb_col4.setEditable(true);
        book_tb_col4.setMinWidth(100);
        book_tb_col4.setReorderable(false);
        book_tb_col4.setResizable(false);
        book_tb_col4.setStyle("-fx-alignment: CENTER;");
        book_tb_col4.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Booking, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Booking, String> t) {
                t.getTableView().getItems().get(t.getTablePosition().getRow()).setCheckOutDate(t.getNewValue());
            }
        });

        TableColumn book_tb_col5 = new TableColumn<>("Room Type");
        book_tb_col5.setCellValueFactory(new PropertyValueFactory<>("RoomType"));
        book_tb_col5.setCellFactory(ComboBoxTableCell.forTableColumn("Superior", "Delux", "Junior Suite", "Royal Suite"));
        book_tb_col5.setEditable(true);
        book_tb_col5.setMinWidth(80);
        book_tb_col5.setReorderable(false);
        book_tb_col5.setResizable(false);
        book_tb_col5.setStyle("-fx-alignment: CENTER;");
        book_tb_col5.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Booking, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Booking, String> t) {
                t.getTableView().getItems().get(t.getTablePosition().getRow()).setRoomType(t.getNewValue());
            }
        });

        TableColumn book_tb_col6 = new TableColumn<>("Room Class");
        book_tb_col6.setCellValueFactory(new PropertyValueFactory<>("RoomClass"));
        book_tb_col6.setCellFactory(ComboBoxTableCell.forTableColumn("Normal", "Premiun"));
        book_tb_col6.setEditable(true);
        book_tb_col6.setMinWidth(80);
        book_tb_col6.setReorderable(false);
        book_tb_col6.setResizable(false);
        book_tb_col6.setStyle("-fx-alignment: CENTER;");
        book_tb_col6.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Booking, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Booking, String> t) {
                t.getTableView().getItems().get(t.getTablePosition().getRow()).setRoomClass(t.getNewValue());
            }
        });

        TableColumn book_tb_col7 = new TableColumn<>("Building");
        book_tb_col7.setCellValueFactory(new PropertyValueFactory<>("Building"));
        book_tb_col7.setCellFactory(ComboBoxTableCell.forTableColumn("A"));
        book_tb_col7.setEditable(true);
        book_tb_col7.setMaxWidth(60);
        book_tb_col7.setReorderable(false);
        book_tb_col7.setResizable(false);
        book_tb_col7.setStyle("-fx-alignment: CENTER;");
        book_tb_col7.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Booking, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Booking, String> t) {
                t.getTableView().getItems().get(t.getTablePosition().getRow()).setBuilding(t.getNewValue());
            }
        });

        TableColumn book_tb_col8 = new TableColumn<>("Floor");
        book_tb_col8.setCellValueFactory(new PropertyValueFactory<>("Floor"));
        book_tb_col8.setCellFactory(ComboBoxTableCell.forTableColumn("1", "2", "3", "4", "5", "6", "7", "8", "9"));
        book_tb_col8.setEditable(true);
        book_tb_col8.setMaxWidth(50);
        book_tb_col8.setReorderable(false);
        book_tb_col8.setResizable(false);
        book_tb_col8.setStyle("-fx-alignment: CENTER;");
        book_tb_col8.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Booking, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Booking, String> t) {
                t.getTableView().getItems().get(t.getTablePosition().getRow()).setFloor(t.getNewValue());
            }
        });

        TableColumn book_tb_col9 = new TableColumn<>("Adult");
        book_tb_col9.setCellValueFactory(new PropertyValueFactory<>("Adult"));
        book_tb_col9.setCellFactory(TextFieldTableCell.forTableColumn());
        book_tb_col9.setEditable(true);
        book_tb_col9.setMaxWidth(50);
        book_tb_col9.setReorderable(false);
        book_tb_col9.setResizable(false);
        book_tb_col9.setStyle("-fx-alignment: CENTER;");
        book_tb_col9.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Booking, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Booking, String> t) {
                t.getTableView().getItems().get(t.getTablePosition().getRow()).setAdult(t.getNewValue());
            }
        });

        TableColumn book_tb_col10 = new TableColumn<>("Young");
        book_tb_col10.setCellValueFactory(new PropertyValueFactory<>("Young"));
        book_tb_col10.setCellFactory(TextFieldTableCell.forTableColumn());
        book_tb_col10.setEditable(true);
        book_tb_col10.setMaxWidth(60);
        book_tb_col10.setReorderable(false);
        book_tb_col10.setResizable(false);
        book_tb_col10.setStyle("-fx-alignment: CENTER;");
        book_tb_col10.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Booking, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Booking, String> t) {
                t.getTableView().getItems().get(t.getTablePosition().getRow()).setYoung(t.getNewValue());
            }
        });

        TableColumn book_tb_col11 = new TableColumn<>("Breakfast");
        book_tb_col11.setCellValueFactory(new PropertyValueFactory<Booking, String>("Breakfast"));
        book_tb_col11.setCellFactory(ComboBoxTableCell.forTableColumn("true", "false"));
        book_tb_col11.setEditable(true);
        book_tb_col11.setMaxWidth(65);
        book_tb_col11.setReorderable(false);
        book_tb_col11.setResizable(false);
        book_tb_col11.setStyle("-fx-alignment: CENTER;");
        book_tb_col11.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Booking, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Booking, String> t) {
                t.getTableView().getItems().get(t.getTablePosition().getRow()).setBreakfast(t.getNewValue());
            }
        });

        TableColumn book_tb_col12 = new TableColumn<>("Dinner");
        book_tb_col12.setCellValueFactory(new PropertyValueFactory<>("Dinner"));
        book_tb_col12.setCellFactory(ComboBoxTableCell.forTableColumn("true", "false"));
        book_tb_col12.setEditable(true);
        book_tb_col12.setMaxWidth(70);
        book_tb_col12.setReorderable(false);
        book_tb_col12.setResizable(false);
        book_tb_col12.setStyle("-fx-alignment: CENTER;");
        book_tb_col12.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Booking, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Booking, String> t) {
                t.getTableView().getItems().get(t.getTablePosition().getRow()).setDinner(t.getNewValue());
            }
        });

        TableColumn book_tb_col13 = new TableColumn<>("Total Price");
        book_tb_col13.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        book_tb_col13.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
        book_tb_col13.setEditable(true);
        book_tb_col13.setMinWidth(80);
        book_tb_col13.setReorderable(false);
        book_tb_col13.setResizable(false);
        book_tb_col13.setStyle("-fx-alignment: CENTER;");
        book_tb_col13.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Booking, Long>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Booking, Long> t) {
                t.getTableView().getItems().get(t.getTablePosition().getRow()).setTotalPrice(t.getNewValue());
            }
        });

        TableColumn book_tb_col14 = new TableColumn<>("Status");
        book_tb_col14.setCellValueFactory(new PropertyValueFactory<>("Status"));
        book_tb_col14.setCellFactory(ComboBoxTableCell.forTableColumn("Booked", "Check-IN", "Check-OUT"));
        book_tb_col14.setEditable(true);
        book_tb_col14.setMinWidth(80);
        book_tb_col14.setReorderable(false);
        book_tb_col14.setResizable(false);
        book_tb_col14.setStyle("-fx-alignment: CENTER;");
        book_tb_col14.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Booking, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Booking, String> t) {
                t.getTableView().getItems().get(t.getTablePosition().getRow()).setStatus(t.getNewValue());
            }
        });

        TableColumn book_tb_col15 = new TableColumn<>("Data Created");
        book_tb_col15.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));
        book_tb_col15.setCellFactory(TextFieldTableCell.forTableColumn());
        book_tb_col15.setEditable(true);
        book_tb_col15.setMinWidth(140);
        book_tb_col15.setReorderable(false);
        book_tb_col15.setResizable(false);
        book_tb_col15.setStyle("-fx-alignment: CENTER;");
        book_tb_col15.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Booking, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Booking, String> t) {
                t.getTableView().getItems().get(t.getTablePosition().getRow()).setDateCreated(t.getNewValue());
            }
        });

        book_table.getColumns().add(book_tb_col1);
        book_table.getColumns().add(book_tb_col2);
        book_table.getColumns().add(book_tb_col3);
        book_table.getColumns().add(book_tb_col4);
        book_table.getColumns().add(book_tb_col5);
        book_table.getColumns().add(book_tb_col6);
        book_table.getColumns().add(book_tb_col7);
        book_table.getColumns().add(book_tb_col8);
        book_table.getColumns().add(book_tb_col9);
        book_table.getColumns().add(book_tb_col10);
        book_table.getColumns().add(book_tb_col11);
        book_table.getColumns().add(book_tb_col12);
        book_table.getColumns().add(book_tb_col13);
        book_table.getColumns().add(book_tb_col14);
        book_table.getColumns().add(book_tb_col15);

        //[Component] - Room Management - Save [Button]
        Button save_book = new Button("Save");
        save_book.setOnMouseClicked(eh -> {

            try {
                this.updateBook(book_table, "BookingStock");
            } catch (IOException ex) {
                Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidFormatException ex) {
                Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
            }

            book_table.getItems().clear();
            try {
                for (int t = 0; t < this.fetchBooking().size(); t++) {
                    book_table.getItems().add(this.fetchBooking().get(t));
                }
            } catch (IOException ex) {
                Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        //[Component] - Room Management - Delete [Button]
        Button del_book = new Button("Delete");
        del_book.setOnAction(eh -> {
            Booking selectedItem = book_table.getSelectionModel().getSelectedItem();
            book_table.getItems().remove(selectedItem);

        });

        //[Component] - Booking Management [All Center Area]
        VBox center_book = new VBox();
        center_book.setVisible(false);

        center_book.setMargin(book_man_label, new Insets(10, 10, 10, 10));
        center_book.setMargin(book_table, new Insets(10, 10, 10, 10));
        center_book.setMargin(save_book, new Insets(10, 10, 10, 10));
        center_book.setMargin(del_book, new Insets(10, 10, 10, 10));

        center_book.getChildren().addAll(book_man_label, book_Search_bar, book_table, save_book, del_book);

        //[Component] - Check-IN Management  [Label]
        Label check_in_label = new Label("Booking Management");
        check_in_label.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 24));

        //[Component] - Room Management - Search bar [Hbox]
        TextField checkin_search_box = new TextField();
        checkin_search_box.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 18));
        checkin_search_box.setStyle("-fx-min-width: 400; ");
        checkin_search_box.setPromptText("Enter your Booking ID");

        Button checkin_search_bt = new Button("CHECK IN");
        checkin_search_bt.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 18));
        checkin_search_bt.setOnAction(eh -> {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Check-IN");
            try {
                alert.setContentText("ห้อของคุณคือ  " + this.CheckIN(checkin_search_box.getText()));
            } catch (IOException ex) {
                Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
            }

            Stage stage2 = (Stage) alert.getDialogPane().getScene().getWindow();
            try {
                stage2.getIcons().add(new Image(new FileInputStream(new File("src/img/icon.png"))));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
            }

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                checkin_table.getItems().clear();
                try {
                    for (int t = 0; t < this.fetchCheck().size(); t++) {
                        checkin_table.getItems().add(this.fetchCheck().get(t));
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
                }
                checkin_search_box.setText(" ");
                checkin_search_box.setPromptText("Enter your Booking ID");
            } else {
                alert.close();
            }

            try {
                System.out.println("Room Number :" + this.CheckIN(checkin_search_box.getText()));
            } catch (IOException ex) {
                Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        //[Component] - Room Management - Search bar [Hbox]
        TextField checkout_search_box = new TextField();
        checkout_search_box.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 18));
        checkout_search_box.setStyle("-fx-min-width: 400; ");
        checkout_search_box.setPromptText("Enter your Room Number");

        Button checkout_search_bt = new Button("CHECK OUT");
        checkout_search_bt.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 18));
        checkout_search_bt.setOnAction(eh -> {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Check-OUT");
            try {
                alert.setContentText("ขอขอบคุณที่ใช้บริการ ครับ คุณ  " + this.CheckOUT(checkout_search_box.getText()));
            } catch (IOException ex) {
                Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
            }

            Stage stage2 = (Stage) alert.getDialogPane().getScene().getWindow();
            try {
                stage2.getIcons().add(new Image(new FileInputStream(new File("src/img/icon.png"))));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
            }

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                checkin_table.getItems().clear();
                try {
                    for (int t = 0; t < this.fetchCheck().size(); t++) {
                        checkin_table.getItems().add(this.fetchCheck().get(t));
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
                }
                checkout_search_box.setText(" ");
                 checkout_search_box.setPromptText("Enter your Room Number");
            } else {
                alert.close();
            }

            try {
                System.out.println("Customer Name : " + this.CheckOUT(checkout_search_box.getText()));
            } catch (IOException ex) {
                Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        HBox checkin_Search_bar = new HBox(2);
        checkin_Search_bar.setMargin(checkin_search_box, new Insets(10, 10, 10, 10));
        checkin_Search_bar.setMargin(checkin_search_bt, new Insets(10, 10, 10, 10));
        checkin_Search_bar.setMargin(checkout_search_box, new Insets(10, 10, 10, 50));
        checkin_Search_bar.setAlignment(Pos.BASELINE_LEFT);
        checkin_Search_bar.getChildren().addAll(checkin_search_box, checkin_search_bt, checkout_search_box, checkout_search_bt);

        HBox checkout_Search_bar = new HBox(2);
        checkout_Search_bar.setMargin(checkout_search_bt, new Insets(10, 10, 10, 10));
        checkout_Search_bar.setAlignment(Pos.BASELINE_LEFT);
        //checkout_Search_bar.getChildren().addAll(, );

        //[Component] - Room Management Table [Table]
        checkin_table = new TableView();

        for (int t = 0; t < this.fetchCheck().size(); t++) {
            checkin_table.getItems().add(this.fetchCheck().get(t));
        }

        TableColumn checkin_tb_col1 = new TableColumn<Check, String>("Booking ID");
        checkin_tb_col1.setCellValueFactory(new PropertyValueFactory<Check, String>("BookedID"));
        checkin_tb_col1.setMinWidth(100);
        checkin_tb_col1.setReorderable(false);
        checkin_tb_col1.setResizable(false);
        checkin_tb_col1.setStyle("-fx-alignment: CENTER;");

        TableColumn checkin_tb_col2 = new TableColumn<Check, String>("Customer Name");
        checkin_tb_col2.setCellValueFactory(new PropertyValueFactory<Check, String>("CustomerName"));
        checkin_tb_col2.setMinWidth(150);
        checkin_tb_col2.setReorderable(false);
        checkin_tb_col2.setResizable(false);
        checkin_tb_col2.setStyle("-fx-alignment: CENTER;");

        TableColumn checkin_tb_col3 = new TableColumn<Check, String>("Room Number");
        checkin_tb_col3.setCellValueFactory(new PropertyValueFactory<Check, String>("CustomerRoom"));
        checkin_tb_col3.setMinWidth(100);
        checkin_tb_col3.setReorderable(false);
        checkin_tb_col3.setResizable(false);
        checkin_tb_col3.setStyle("-fx-alignment: CENTER;");

        TableColumn checkin_tb_col5 = new TableColumn<Check, String>("Status");
        checkin_tb_col5.setCellValueFactory(new PropertyValueFactory<Check, String>("Status"));
        checkin_tb_col5.setMinWidth(100);
        checkin_tb_col5.setReorderable(false);
        checkin_tb_col5.setResizable(false);
        checkin_tb_col5.setStyle("-fx-alignment: CENTER;");

        TableColumn checkin_tb_col4 = new TableColumn<Check, String>("Data Created");
        checkin_tb_col4.setCellValueFactory(new PropertyValueFactory<Check, String>("dateCreated"));
        checkin_tb_col4.setMinWidth(150);
        checkin_tb_col4.setReorderable(false);
        checkin_tb_col4.setResizable(false);
        checkin_tb_col4.setStyle("-fx-alignment: CENTER;");

        checkin_table.getColumns().add(checkin_tb_col1);
        checkin_table.getColumns().add(checkin_tb_col2);
        checkin_table.getColumns().add(checkin_tb_col3);
        checkin_table.getColumns().add(checkin_tb_col5);
        checkin_table.getColumns().add(checkin_tb_col4);

        //[Component] - Booking Management [All Center Area]
        Button refesh = new Button("รีเฟรส");
        refesh.setOnAction(eh -> {
            checkin_table.getItems().clear();
            try {
                for (int t = 0; t < this.fetchCheck().size(); t++) {
                    checkin_table.getItems().add(this.fetchCheck().get(t));
                }
            } catch (IOException ex) {
                Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        VBox center_checkin = new VBox();
        center_checkin.setVisible(false);

        center_checkin.setMargin(check_in_label, new Insets(10, 10, 10, 10));
        center_checkin.setMargin(checkin_table, new Insets(10, 10, 10, 10));
        center_checkin.setMargin(refesh, new Insets(10, 10, 10, 10));

        center_checkin.getChildren().addAll(check_in_label, checkin_Search_bar, checkin_table, refesh);

        //========================= [Menu] Room Management [Button] ========================
        Button room_man_bt = new Button("จัดการห้องพัก");
        room_man_bt.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 26));
        room_man_bt.setStyle("-fx-background-radius: 2; -fx-background-color: #FFA34D; -fx-text-fill: #000000");
        //On mouse idle
        room_man_bt.setOnMouseExited(e -> {
            room_man_bt.setStyle("-fx-background-radius: 2; -fx-background-color: #FFA34D; -fx-text-fill: #000000");
            room_man_bt.setEffect(null);
        });
        //On mouse hover
        room_man_bt.setOnMouseEntered(e -> {
            room_man_bt.setStyle("-fx-background-radius: 2; -fx-background-color: #FFA34D; -fx-text-fill: #ffffff");
            room_man_bt.setEffect(new DropShadow());
        });
        //On mouse click
        room_man_bt.setOnMouseClicked(e -> {
            room_man_bt.setStyle("-fx-background-radius: 2; -fx-background-color: #FFA34D; -fx-text-fill: #000000");

            room_table.getItems().clear();

            try {
                for (int t = 0; t < this.fetchRoom().size(); t++) {
                    room_table.getItems().add(this.fetchRoom().get(t));
                }
            } catch (IOException ex) {
                Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
            }

            border.setCenter(center_room);
            center_room.setVisible(true);
            center_book.setVisible(false);
            center_checkin.setVisible(false);

        });
        //=======================================================================

        //======================== [Menu] Booking Management [Button] =======================        
        Button booking_man_bt = new Button("จัดการการจอง");
        booking_man_bt.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 26));
        booking_man_bt.setStyle("-fx-background-radius: 2; -fx-background-color: #FFA34D; -fx-text-fill: #000000");
        //On mouse idle
        booking_man_bt.setOnMouseExited(e -> {
            booking_man_bt.setStyle("-fx-background-radius: 2; -fx-background-color: #FFA34D; -fx-text-fill: #000000");
            booking_man_bt.setEffect(null);
        });
        //On mouse hover
        booking_man_bt.setOnMouseEntered(e -> {
            booking_man_bt.setStyle("-fx-background-radius: 2; -fx-background-color: #FFA34D; -fx-text-fill: #ffffff");
            booking_man_bt.setEffect(new DropShadow());
        });
        //On mouse click
        booking_man_bt.setOnMouseClicked(e -> {
            booking_man_bt.setStyle("-fx-background-radius: 2; -fx-background-color: #FFA34D; -fx-text-fill: #000000");

            book_table.getItems().clear();

            try {
                for (int t = 0; t < this.fetchBooking().size(); t++) {
                    book_table.getItems().add(this.fetchBooking().get(t));
                }
            } catch (IOException ex) {
                Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
            }

            border.setCenter(center_book);
            center_room.setVisible(false);
            center_book.setVisible(true);
            center_checkin.setVisible(false);
        });
        //=======================================================================

        //============================ [Menu] Check-In [Button] ==========================          
        Button check_in_bt = new Button("CHECK-IN-OUT");
        check_in_bt.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 26));
        check_in_bt.setStyle("-fx-background-radius: 2; -fx-background-color: #FFA34D; -fx-text-fill: #000000 ");
        //On mouse idle
        check_in_bt.setOnMouseExited(e -> {
            check_in_bt.setStyle("-fx-background-radius: 2; -fx-background-color: #FFA34D; -fx-text-fill: #000000");
            check_in_bt.setEffect(null);
        });
        //On mouse hover
        check_in_bt.setOnMouseEntered(e -> {
            check_in_bt.setStyle("-fx-background-radius: 2; -fx-background-color: #FFA34D; -fx-text-fill: #ffffff");
            check_in_bt.setEffect(new DropShadow());
        });
        //On mouse click
        check_in_bt.setOnMouseClicked(e -> {
            check_in_bt.setStyle("-fx-background-radius: 2; -fx-background-color: #FFA34D; -fx-text-fill: #000000");

            border.setCenter(center_checkin);
            center_room.setVisible(false);
            center_book.setVisible(false);
            center_checkin.setVisible(true);
        });
        //=======================================================================

        VBox menu_bar = new VBox();
        //Setting the space between the nodes of a HBox pane 
        menu_bar.setSpacing(10);
        menu_bar.setAlignment(Pos.BASELINE_CENTER);
        menu_bar.setStyle("-fx-background-color: #00A8CC;");

        //Setting the margin to the nodes 
        menu_bar.setMargin(room_man_bt, new Insets(10, 10, 10, 10));
        menu_bar.setMargin(booking_man_bt, new Insets(10, 10, 10, 10));
        menu_bar.setMargin(check_in_bt, new Insets(10, 10, 10, 10));
        menu_bar.setMargin(logout_bt, new Insets(450, 10, 10, 10));

        menu_bar.getChildren().addAll(room_man_bt, booking_man_bt, check_in_bt, logout_bt);

        HBox info_bar = new HBox();
        //Setting the space between the nodes of a HBox pane 
        info_bar.setSpacing(10);
        info_bar.setAlignment(Pos.BASELINE_LEFT);
        info_bar.setStyle("-fx-background-color: #000839;");

        //Setting the margin to the nodes 
        info_bar.setMargin(logo, new Insets(10, 10, 10, 10));
        info_bar.getChildren().addAll(logo);

        //Pane - Proproties
        border.setTop(info_bar);
        border.setLeft(menu_bar);
        border.setCenter(center_room);

        //[View] --show window
        rootPane.getChildren().addAll(border);
        Scene user_page = new Scene(rootPane, 1440, 900);
        window.setScene(user_page);
        window.show();
    }

    private ObservableList<Room> fetchRoom() throws IOException {
        RoomManagement room_man = new RoomManagement();
        return FXCollections.observableArrayList(room_man.searchRoom(true));
    }

    private ObservableList<Booking> fetchBooking() throws IOException {
        return FXCollections.observableArrayList(book_mg.searchBooking("ALL"));
    }

    private ObservableList<Check> fetchCheck() throws IOException {
        CheckManagement ch_mg = new CheckManagement();
        return FXCollections.observableArrayList(ch_mg.searchCheck());
    }

    private void updateRoom(TableView<Room> table, String dbname) throws FileNotFoundException, IOException, InvalidFormatException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(dbname);

        Row firstRow = sheet.createRow(0);

        ///set titles of columns
        for (int i = 0; i < table.getColumns().size(); i++) {
            firstRow.createCell(i).setCellValue(table.getColumns().get(i).getText());
        }

        for (int row = 0; row < table.getItems().size(); row++) {
            Row hssfRow = sheet.createRow(row + 1);
            for (int col = 0; col < table.getColumns().size(); col++) {
                Object celValue = table.getColumns().get(col).getCellObservableValue(row).getValue();
                try {
                    if (celValue != null && Double.parseDouble(celValue.toString()) != 0.0) {
                        hssfRow.createCell(col).setCellValue(Double.parseDouble(celValue.toString()));
                    }
                } catch (NumberFormatException e) {
                    hssfRow.createCell(col).setCellValue(celValue.toString());
                }
            }
        }

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("src/db/" + dbname + ".xlsx");
        workbook.write(fileOut);
        fileOut.close();
    }

    private void updateBook(TableView<Booking> table, String dbname) throws FileNotFoundException, IOException, InvalidFormatException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(dbname);

        Row firstRow = sheet.createRow(0);

        ///set titles of columns
        for (int i = 0; i < table.getColumns().size(); i++) {
            firstRow.createCell(i).setCellValue(table.getColumns().get(i).getText());
        }

        for (int row = 0; row < table.getItems().size(); row++) {
            Row hssfRow = sheet.createRow(row + 1);
            for (int col = 0; col < table.getColumns().size(); col++) {
                Object celValue = table.getColumns().get(col).getCellObservableValue(row).getValue();
                try {
                    if (celValue != null && Double.parseDouble(celValue.toString()) != 0.0) {
                        hssfRow.createCell(col).setCellValue(String.valueOf(celValue.toString()));
                    } else {
                        hssfRow.createCell(col).setCellValue("0");
                    }
                } catch (NumberFormatException e) {
                    hssfRow.createCell(col).setCellValue(String.valueOf(celValue.toString()));
                }
            }
        }

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("src/db/" + dbname + ".xlsx");
        workbook.write(fileOut);
        fileOut.close();
    }

    private String CheckIN(String bookingID) throws IOException {
        String dbname = "Check_IN_OUT";
        Database db = new Database();
        int row = db.getRowNum(bookingID, dbname);
        db.writeCell("CHECK-IN", row, 3, dbname);
        return db.readCell(row, 2, dbname);
    }

    private String CheckOUT(String RoomNum) throws IOException {
        String dbname = "Check_IN_OUT";
        Database db = new Database();
        int row = db.getRowNum(RoomNum, dbname);
        db.writeCell("CHECK-OUT", row, 3, dbname);
        
        return db.readCell(row, 1, dbname);
    }

}
