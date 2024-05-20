package com.example.demo;

import java.io.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Vul, Integer> Id;

    @FXML
    private TableColumn<Vul, String> Soft_version;

    @FXML
    private TableColumn<Vul, String> Software_name;

    @FXML
    private TableColumn<Vul, ComboBox> Status;

    @FXML
    private TableColumn<Vul, String> bdu_id;

    @FXML
    private HBox big_hbox;

    @FXML
    private VBox big_vbox;

    @FXML
    private HBox hbox20;

    @FXML
    private HBox hbox21;

    @FXML
    private HBox hbox22;

    @FXML
    private HBox hbox100;

    @FXML
    private HBox hbox101;

    @FXML
    private Button button_choose;

    @FXML
    private Button close_button;

    @FXML
    private Button button_download;

    @FXML
    private Button button_vendor_list;

    @FXML
    private HBox hbox1;

    @FXML
    private HBox hbox10;

    @FXML
    private VBox vbox102;

    @FXML
    private TextField vendor_choose_text;

    @FXML
    private VBox vbox103;

    @FXML
    private VBox vbox104;

    @FXML
    private VBox vbox105;


    @FXML
    private TextField pdf_text_title;


    @FXML
    private HBox hbox11;

    @FXML
    private HBox hbox12;

    @FXML
    private HBox hbox2;

    @FXML
    private HBox hbox3;

    @FXML
    private Button save_button;

    @FXML
    private TableColumn<Vul, String> measure_num;

    @FXML
    private TableColumn<Vul, String> soft_type;

    @FXML
    private TableView<Vul> table;

    @FXML
    private TextField text_field;

    @FXML
    private TextField text_vendor;

    @FXML
    private VBox vbox2;

    @FXML
    private Label welcomeText;

    @FXML
    private VBox window;

    File fileObject;

    File file_vendors;

    ObservableList<String> status_list = FXCollections.observableArrayList("Исполнено", "Не применимо", "Частично исполнено", "Не исполнено");
    ComboBox<String> statusbox = new ComboBox<String>(status_list);
    ObservableList<Vul> list = FXCollections.observableArrayList();


    File f = new File("result.xlsx");


    Workbook workbook;

    public HelloController() throws IOException, InvalidFormatException {
    }

    public static void close(ActionEvent event) throws IOException {

        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }


    @FXML
    void get_bdu_info(TableColumn.CellEditEvent<Vul, String> event) throws IOException {
        Vul vul_to_get_info = (table.getSelectionModel().getSelectedItem());
        System.out.println(vul_to_get_info.getBdu_id());
        FileWriter writer = new FileWriter("bdu.txt");
        writer.write(vul_to_get_info.getBdu_id());
        writer.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("close_window.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
        Stage stage = new Stage();
        stage.setTitle("Дополнительная информация");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void close_program(ActionEvent event) throws IOException {



//        Node source = (Node) event.getSource();
//        stage = (Stage) source.getScene().getWindow();
//        stage.close();
    }

    @FXML
    public void save_changes(ActionEvent event) throws IOException {
        Workbook wb = new XSSFWorkbook(new FileInputStream("result.xlsx"));
        Sheet sheet =  wb.getSheetAt(0);

       for(int i = 0; i < table.getItems().size(); i++){

           Vul vul_to_save = table.getItems().get(i);
           System.out.println(vul_to_save.getStatus());
           Row row_res = sheet.createRow(i);
           row_res.createCell(0).setCellValue(vul_to_save.getId());
           row_res.createCell(1).setCellValue(vul_to_save.getMeas_num());
           row_res.createCell(2).setCellValue(vul_to_save.getBdu_id());
           row_res.createCell(3).setCellValue(vul_to_save.getSof_name());
           row_res.createCell(4).setCellValue(vul_to_save.getSoft_type());
           row_res.createCell(5).setCellValue(vul_to_save.getSoft_version());
           wb.write(new FileOutputStream(new File("result.xlsx")));

       }
    }

    MeasureList measureList;

    @FXML
    private void addFile(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage primaryStage = (Stage) source.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter pdfFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(pdfFilter);
        fileChooser.getExtensionFilters().addAll(pdfFilter);
        fileChooser.setTitle("Выбор файла");
        fileObject = fileChooser.showOpenDialog(primaryStage);
        try{
            text_field.setText(fileObject.getName());

        }catch (Exception ignored) {}
    }

    @FXML
    void choose_vendor(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage primaryStage = (Stage) source.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter pdfFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(pdfFilter);
        fileChooser.getExtensionFilters().addAll(pdfFilter);
        fileChooser.setTitle("Выбор файла");
        file_vendors = fileChooser.showOpenDialog(primaryStage);
        try{
            text_vendor.setText(file_vendors.getPath());

            VendorList venlist = new VendorList(file_vendors.getPath());
            venlist.getVendorListFromFile();

        }catch (Exception ignored) {}
    }

    List<String> vendors = new ArrayList<>();

    @FXML
    void inn_vendor(ActionEvent event) throws IOException {
        VendorList venlist = new VendorList(file_vendors.getPath());
        venlist.setPath(Path.of(file_vendors.getPath()));

        vendors = venlist.getVendorListFromFile();
        //System.out.println(vendors.getFirst().contains("Alpha"));
    }

    @FXML
    void goSearch(ActionEvent event) throws Exception {


        VulListRequestor vulreq = new VulListRequestor();
        vulreq.get_vul_from_fstec();
        text_field.setText("Загрузка списка уязвимостей");
        //Thread.sleep(500);
        text_field.setText("");
        Xlsx_parser exelparser = new Xlsx_parser(measureList);
        //String[] params = new String[1000];
        list = exelparser.parse(vendors, fileObject.getName(), workbook, status_list, list);
        table.setItems(list);

        //list.add(new Vul(i,123, measureList.get(i).idBDU,"1","1",1, FXCollections.observableArrayList("Исполнено", "Не применимо", "Частично исполнено", "Не исполнено")));


//        Xlsx_parser exel_parser = new Xlsx_parser("asd", "qwe");
//        exel_parser.parse();

    }

    @FXML
    private void inFile(ActionEvent event) throws IOException {
        MeasureLetter measureLetter = new MeasureLetter(fileObject.getPath());
        measureLetter.getMeasuresIDs();
        measureList = measureLetter.getMeasures();
        for(int i = 0; i < measureList.size(); i++){
            System.out.println(measureList.get(i).idBDU);

            }


//        Pdf_parser parser = new Pdf_parser(fileObject.getPath());
//        String res = parser.parce_pdf();
//
//        Xlsx_parser exel_parser = new Xlsx_parser(res, "qwe");
//        String[] params = new String[6];
//        text_field.setText(params[0]);
    }

    @FXML
    void initialize(MouseEvent event) {

    }




    @FXML
    void initialize() throws IOException {
        assert Id != null : "fx:id=\"Id\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert Soft_version != null : "fx:id=\"Soft_version\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert Software_name != null : "fx:id=\"Software_name\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert Status != null : "fx:id=\"Status\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert bdu_id != null : "fx:id=\"bdu_id\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert measure_num != null : "fx:id=\"measure_num\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert soft_type != null : "fx:id=\"soft_type\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert welcomeText != null : "fx:id=\"welcomeText\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert window != null : "fx:id=\"window\" was not injected: check your FXML file 'hello-view.fxml'.";

        if (f.exists()){

            workbook = new XSSFWorkbook(new FileInputStream("result.xlsx"));
            for(Row row : workbook.getSheetAt(0)) {

                int id_saved = row.getCell(0).getRowIndex();
               // statusbox.setValue("Не исполнено");
                ComboBox comboBox_curr = new ComboBox<>(status_list);

                list.add(new Vul(id_saved,row.getCell(1).getStringCellValue(), row.getCell(2).getStringCellValue(), row.getCell(3).getStringCellValue(), row.getCell(4).getStringCellValue(), row.getCell(5).getStringCellValue(), comboBox_curr));
                }
            }else {

        }




        table.setEditable(true);

        Id.setCellValueFactory(new PropertyValueFactory<Vul,Integer>("id"));
        Id.setEditable(true);
        Id.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        Id.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Vul, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Vul, Integer> vulIntegerCellEditEvent) {
                Vul vul = vulIntegerCellEditEvent.getRowValue();
                vul.setId(vulIntegerCellEditEvent.getNewValue());
            }
        });

        measure_num.setCellValueFactory(new PropertyValueFactory<Vul,String>("num"));
        measure_num.setEditable(true);
        measure_num.setCellFactory(TextFieldTableCell.forTableColumn());
        measure_num.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Vul, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Vul, String> vulStringCellEditEvent) {
                Vul vul = vulStringCellEditEvent.getRowValue();
                vul.setMeas_num(Integer.parseInt(vulStringCellEditEvent.getNewValue()));
            }
        });


        Soft_version.setCellValueFactory(new PropertyValueFactory<Vul,String>("soft_version"));
        Soft_version.setEditable(true);
        Soft_version.setCellFactory(TextFieldTableCell.forTableColumn());
        Soft_version.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Vul, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Vul, String> vulStringCellEditEvent) {
                Vul vul = vulStringCellEditEvent.getRowValue();
                vul.setSoft_version(Integer.parseInt((vulStringCellEditEvent.getNewValue())));
            }
        });

                Software_name.setCellValueFactory(new PropertyValueFactory<Vul, String>("sof_name"));
        Software_name.setEditable(true);
        Software_name.setCellFactory(TextFieldTableCell.forTableColumn());
        Software_name.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Vul, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Vul, String> vulStringCellEditEvent) {
                Vul vul = vulStringCellEditEvent.getRowValue();
                vul.setSof_name(vulStringCellEditEvent.getNewValue());
            }
        });


        Status.setCellValueFactory(new PropertyValueFactory<Vul,ComboBox>("status"));
        Status.setEditable(true);


        bdu_id.setCellValueFactory(new PropertyValueFactory<Vul,String>("bdu_id"));
        bdu_id.setEditable(true);
        bdu_id.setCellFactory(TextFieldTableCell.forTableColumn());
        bdu_id.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Vul, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Vul, String> vulStringCellEditEvent) {
                Vul vul = vulStringCellEditEvent.getRowValue();
                vul.setBdu_id(vulStringCellEditEvent.getNewValue());
            }
        });

        soft_type.setCellValueFactory(new PropertyValueFactory<Vul,String>("soft_type"));
        soft_type.setEditable(true);
        soft_type.setCellFactory(TextFieldTableCell.forTableColumn());
        soft_type.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Vul, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Vul, String> vulStringCellEditEvent) {
                Vul vul = vulStringCellEditEvent.getRowValue();
                vul.setSoft_type(vulStringCellEditEvent.getNewValue());
            }
        });

        table.setItems(list);


    }

}
