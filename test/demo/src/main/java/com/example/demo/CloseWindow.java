package com.example.demo;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CloseWindow {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField text_OS;

    @FXML
    private TextField text_base_vector;

    @FXML
    private TextField text_dang_lvl;

    @FXML
    private TextField text_date;

    @FXML
    private TextField text_diskr;

    @FXML
    private TextField text_elim;

    @FXML
    private TextField text_elim_info;

    @FXML
    private TextField text_err;

    @FXML
    private TextField text_expliot;

    @FXML
    private TextField text_id_err;

    @FXML
    private TextField text_id_sys;

    @FXML
    private TextField text_info;

    @FXML
    private TextField text_measures;

    @FXML
    private TextField text_name;

    @FXML
    private TextField text_id;

    @FXML
    private TextField text_type;

    @FXML
    private TextField text_usage;

    @FXML
    private TextField text_vendor;

    @FXML
    private TextField text_version;

    @FXML
    private TextField text_vul_class;

    @FXML
    private TextField text_vul_status;

    @FXML
    void initialize() throws IOException {
        assert text_OS != null : "fx:id=\"text_OS\" was not injected: check your FXML file 'close_window.fxml'.";
        assert text_base_vector != null : "fx:id=\"text_base_vector\" was not injected: check your FXML file 'close_window.fxml'.";
        assert text_dang_lvl != null : "fx:id=\"text_dang_lvl\" was not injected: check your FXML file 'close_window.fxml'.";
        assert text_date != null : "fx:id=\"text_date\" was not injected: check your FXML file 'close_window.fxml'.";
        assert text_diskr != null : "fx:id=\"text_diskr\" was not injected: check your FXML file 'close_window.fxml'.";
        assert text_elim != null : "fx:id=\"text_elim\" was not injected: check your FXML file 'close_window.fxml'.";
        assert text_elim_info != null : "fx:id=\"text_elim_info\" was not injected: check your FXML file 'close_window.fxml'.";
        assert text_err != null : "fx:id=\"text_err\" was not injected: check your FXML file 'close_window.fxml'.";
        assert text_expliot != null : "fx:id=\"text_expliot\" was not injected: check your FXML file 'close_window.fxml'.";
        assert text_id_err != null : "fx:id=\"text_id_err\" was not injected: check your FXML file 'close_window.fxml'.";
        assert text_id_sys != null : "fx:id=\"text_id_sys\" was not injected: check your FXML file 'close_window.fxml'.";
        assert text_info != null : "fx:id=\"text_info\" was not injected: check your FXML file 'close_window.fxml'.";
        assert text_measures != null : "fx:id=\"text_measures\" was not injected: check your FXML file 'close_window.fxml'.";
        assert text_name != null : "fx:id=\"text_name\" was not injected: check your FXML file 'close_window.fxml'.";
        assert text_type != null : "fx:id=\"text_type\" was not injected: check your FXML file 'close_window.fxml'.";
        assert text_usage != null : "fx:id=\"text_usage\" was not injected: check your FXML file 'close_window.fxml'.";
        assert text_vendor != null : "fx:id=\"text_vendor\" was not injected: check your FXML file 'close_window.fxml'.";
        assert text_version != null : "fx:id=\"text_version\" was not injected: check your FXML file 'close_window.fxml'.";
        assert text_vul_class != null : "fx:id=\"text_vul_class\" was not injected: check your FXML file 'close_window.fxml'.";
        assert text_vul_status != null : "fx:id=\"text_vul_status\" was not injected: check your FXML file 'close_window.fxml'.";
        System.out.println('s');

        BufferedReader reader = new BufferedReader(new FileReader("bdu.txt"));
        String idbdu = reader.readLine();
        reader.close();

        Workbook wb = new XSSFWorkbook(new FileInputStream("vullist.xlsx"));

        //ObservableList<Vul> vul_list = FXCollections.observableArrayList();

        for(Row row : wb.getSheetAt(0)) {
            String row_str = row.getCell(0).getStringCellValue();
            if (idbdu.equals(row_str)){
                text_id.setText(row_str);
                text_diskr.setText(String.valueOf(row.getCell(2)));
                text_vendor.setText(String.valueOf(row.getCell(3)));
                text_name.setText(String.valueOf(row.getCell(4)));
                text_version.setText(String.valueOf(row.getCell(5)));
                text_type.setText(String.valueOf(row.getCell(6)));
                text_OS.setText(String.valueOf(row.getCell(7)));
                text_err.setText(String.valueOf(row.getCell(21)));
                text_id_err.setText(String.valueOf(row.getCell(22)));
                text_vul_class.setText(String.valueOf(row.getCell(8)));
                text_date.setText(String.valueOf(row.getCell(9)));
                text_base_vector.setText(String.valueOf(row.getCell(4)));
                text_dang_lvl.setText(String.valueOf(row.getCell(12)));
                text_measures.setText(String.valueOf(row.getCell(13)));
                text_vul_status.setText(String.valueOf(row.getCell(14)));
                text_expliot.setText(String.valueOf(row.getCell(15)));
                //text_usage.setText(String.valueOf(row.getCell(4)));
                //text_elim.setText(String.valueOf(row.getCell(4)));
                text_elim_info.setText(String.valueOf(row.getCell(16)));
                text_id_sys.setText(String.valueOf(row.getCell(18)));
                text_info.setText(String.valueOf(row.getCell(19)));
                wb.close();
                break;

            }
        }

    }

}
