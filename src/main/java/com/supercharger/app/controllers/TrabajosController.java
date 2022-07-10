package com.supercharger.app.controllers;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.supercharger.app.models.Especialidad;
import com.supercharger.app.models.Trabajo;
import com.supercharger.app.models.tablas.MecanicosTableModel;
import com.supercharger.app.models.tablas.TrabajosTableModel;
import com.supercharger.app.services.TrabajosService;
import com.supercharger.app.utils.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class TrabajosController implements Initializable {

    TrabajosService trabajosService = new TrabajosService();
    List<TrabajosTableModel> trabajosTableModels = new ArrayList<>();

    @FXML
    private TableColumn<TrabajosTableModel, String> mecanico;
    @FXML
    private TableColumn<TrabajosTableModel, String> cliente;
    @FXML
    private TableColumn<TrabajosTableModel, String> especialidad;
    @FXML
    private TableColumn<TrabajosTableModel, String> tiempo;
    @FXML
    private TableView<TrabajosTableModel> table;

    @FXML
    private ComboBox espComboBox = new ComboBox();

    @FXML
    private DatePicker fecha = new DatePicker();

    @FXML
    private Label textoOK = new Label();

    @FXML
    private Button volverButton;

    @FXML
    private void onImprimirClick() {
        imprimir();
    }

    @FXML
    private void onEspChange(){
        Trabajo trabajo = null;

        getTrabajos();
    }

    @FXML
    private void onFechaChange() {
        if ( this.espComboBox.getValue() != null){
            getTrabajos();
        }
    }

    @FXML
    private void onVolverClick() {
        Utils.newWindow("", "", "main");
        volverButton.getScene().getWindow().hide();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.fecha.setValue(LocalDate.now());
        this.espComboBox.getItems().addAll(Especialidad.getNombres());

    }

    public void getTrabajos(){
        if (this.table != null) {
            this.mecanico.setCellValueFactory(new PropertyValueFactory<>("mecanico"));
            this.cliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
            this.especialidad.setCellValueFactory(new PropertyValueFactory<>("especialidad"));
            this.tiempo.setCellValueFactory(new PropertyValueFactory<>("tiempo"));

            LocalDate fecha = this.fecha.getValue();
            Especialidad especialidad = Especialidad.getByNombre(this.espComboBox.getValue().toString());

            this.trabajosTableModels = this.trabajosService.findAllTrabajosByFechaByEsp(fecha, especialidad);
            this.table.getItems().setAll(trabajosTableModels);

            table.setRowFactory(tr -> {
                TableRow<TrabajosTableModel> row = new TableRow<>();
                row.setOnMouseClicked(e -> {

                    TrabajosTableModel clickedRow = row.getItem();
                    System.out.println(clickedRow.toString());
                    System.out.println(clickedRow.getCliente());
                });
                return row;
            });
        }
    }

    private void imprimir() {
        OutputStream file = null;
        File newFile = null;
        try {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String fecha = this.fecha.getValue().format(formatter);

            StringBuilder fileName = new StringBuilder();
            fileName.append("Listado diario de ");
            fileName.append(this.espComboBox.getValue());
            fileName.append(" del ");
            fileName.append(fecha);
            fileName.append(".pdf");

            newFile = new File(fileName.toString());
            file = new FileOutputStream(newFile);

            // Create a new Document object
            Document document = new Document();

            // You need PdfWriter to generate PDF document
            PdfWriter.getInstance(document, file);

            // Opening document for writing PDF
            document.open();
            PdfPTable tabla = new PdfPTable(4);
            PdfPCell cel = null;

            StringBuilder titulo = new StringBuilder();
            titulo.append("Listado diario del ");
            titulo.append(fecha);

            cel = new PdfPCell(new Phrase(titulo.toString(), FontFactory.getFont("arial",16)));
            cel.setColspan(4);
            cel.setBorder(Rectangle.NO_BORDER);
            cel.setHorizontalAlignment(Element.ALIGN_CENTER);
            cel.setPaddingBottom(5f);
            tabla.addCell(cel);

            cel = new PdfPCell(new Phrase("Especialidad: " + this.espComboBox.getValue(), FontFactory.getFont("arial",16)));
            cel.setColspan(4);
            cel.setBorder(Rectangle.NO_BORDER);
            cel.setHorizontalAlignment(Element.ALIGN_CENTER);
            cel.setPaddingBottom(15f);
            tabla.addCell(cel);

            // ENCABEZADO

            // celda Cliente
            cel = new PdfPCell(new Phrase("Cliente", FontFactory.getFont("arial",12)));
            tabla.addCell(cel);

            // celda Cliente
            cel = new PdfPCell(new Phrase("Mecanico", FontFactory.getFont("arial",12)));
            tabla.addCell(cel);

            // celda Cliente
            cel = new PdfPCell(new Phrase("Especialidad", FontFactory.getFont("arial",12)));
            tabla.addCell(cel);

            // celda Cliente
            cel = new PdfPCell(new Phrase("Tiempo", FontFactory.getFont("arial",12)));
            tabla.addCell(cel);

            // FIN ENCABEZADO
            document.add(tabla);

            for (TrabajosTableModel trabajosTableModel: trabajosTableModels) {
                tabla = new PdfPTable(4);
                cel = new PdfPCell(new Phrase(trabajosTableModel.getCliente(), FontFactory.getFont("arial",11)));
                tabla.addCell(cel);
                cel = new PdfPCell(new Phrase(trabajosTableModel.getMecanico(), FontFactory.getFont("arial",11)));
                tabla.addCell(cel);
                cel = new PdfPCell(new Phrase(trabajosTableModel.getEspecialidad(), FontFactory.getFont("arial",11)));
                tabla.addCell(cel);
                cel = new PdfPCell(new Phrase(trabajosTableModel.getTiempo(), FontFactory.getFont("arial",11)));
                tabla.addCell(cel);
                document.add(tabla);
            }

            // close the document
            document.close();

            System.out.println("Listado de trabajos creado correctamente!");

        } catch (Exception e) {
            e.printStackTrace();

        } finally {

            // closing FileOutputStream
            try {
                if (file != null) {

                    file.close();
                    this.textoOK.setText("Listado generado correctamente en " + newFile.getAbsolutePath());
                    this.textoOK.setVisible(true);
                }
            } catch (IOException io) {
                System.out.println("No se pudo cerrar el stream del archivo generado");
            }

        }
    }
}
