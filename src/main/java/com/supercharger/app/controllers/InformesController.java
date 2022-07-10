package com.supercharger.app.controllers;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.supercharger.app.models.Seguro;
import com.supercharger.app.models.tablas.TrabajosTableModel;
import com.supercharger.app.services.SegurosService;
import com.supercharger.app.services.TrabajosService;
import com.supercharger.app.utils.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

public class InformesController implements Initializable {

    TrabajosService trabajosService = new TrabajosService();
    SegurosService segurosService = new SegurosService();
    List<TrabajosTableModel> trabajosTableModels = new ArrayList<>();

    @FXML
    private TableColumn<TrabajosTableModel, String> mecanico;
    @FXML
    private TableColumn<TrabajosTableModel, String> cliente;
    @FXML
    private TableColumn<TrabajosTableModel, String> especialidad;
    @FXML
    private TableColumn<TrabajosTableModel, String> fecha;
    @FXML
    private TableView<TrabajosTableModel> table;

    @FXML
    private ComboBox seguros = new ComboBox();

    @FXML
    private ComboBox month = new ComboBox();

    @FXML
    private ComboBox year = new ComboBox();

    @FXML
    private Label textoOK = new Label();

    @FXML
    private Button volverButton;

    @FXML
    private void onImprimirClick() {

        imprimir();

    }

    @FXML
    private void onSeguroChange(){
        getTrabajos();
    }

    @FXML
    private void onMonthChange(){
        if(this.seguros.getValue() != null){
            getTrabajos();
        }
    }
    @FXML
    private void onYearChange(){
        if(this.seguros.getValue() != null){
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

        List<Seguro> seguroList = this.segurosService.findAll();
        for (Seguro seguro: seguroList) {
            this.seguros.getItems().add(seguro.getNombre());
        }

        for (int i = 1; i <= 12; i++) {
            this.month.getItems().add(i);
        }
        this.year.getItems().addAll(Arrays.asList(2021,2022,2023,2024));

        this.month.setValue(LocalDate.now().getMonthValue());
        this.year.setValue(LocalDate.now().getYear());


    }

    public void getTrabajos(){
        if (this.table != null) {
            this.mecanico.setCellValueFactory(new PropertyValueFactory<>("mecanico"));
            this.cliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
            this.especialidad.setCellValueFactory(new PropertyValueFactory<>("especialidad"));
            this.fecha.setCellValueFactory(new PropertyValueFactory<>("tiempo"));


            LocalDate fecha = LocalDate.of((Integer)year.getValue(),(Integer)month.getValue(),1);
            Seguro seguro = this.segurosService.findByNombre(this.seguros.getValue().toString());
            this.trabajosTableModels = this.trabajosService.findAllTrabajosBySeguroByFecha(seguro,fecha);
            this.table.getItems().setAll(trabajosTableModels);

        }
    }

    public void imprimir(){
        OutputStream file = null;
        File newFile = null;
        try {
            StringBuilder fileName = new StringBuilder();
            fileName.append("informe mensual de ");
            fileName.append(this.seguros.getValue());
            fileName.append(" del ");
            fileName.append(this.month.getValue());
            fileName.append("-");
            fileName.append(this.year.getValue());
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
            titulo.append("Informe mensual del ");
            titulo.append(this.month.getValue());
            titulo.append("/");
            titulo.append(this.year.getValue());

            cel = new PdfPCell(new Phrase(titulo.toString(), FontFactory.getFont("arial",16)));
            cel.setColspan(4);
            cel.setBorder(Rectangle.NO_BORDER);
            cel.setHorizontalAlignment(Element.ALIGN_CENTER);
            cel.setPaddingBottom(5f);
            tabla.addCell(cel);

            cel = new PdfPCell(new Phrase("Seguro: " + this.seguros.getValue(), FontFactory.getFont("arial",16)));
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
            cel = new PdfPCell(new Phrase("Fecha", FontFactory.getFont("arial",12)));
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

            System.out.println("Informe creado correctamente!");

        } catch (Exception e) {
            e.printStackTrace();

        } finally {

            // closing FileOutputStream
            try {
                if (file != null) {

                    file.close();
                    this.textoOK.setText("Informe generado correctamente en " + newFile.getAbsolutePath());
                    this.textoOK.setVisible(true);
                }
            } catch (IOException io) {
                System.out.println("No se pudo cerrar el stream del archivo generado");
            }

        }
    }
}
