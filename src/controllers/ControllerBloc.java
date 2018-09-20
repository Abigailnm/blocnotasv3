/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.ModelBloc;
import Views.ViewBloc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class ControllerBloc {
    
    ModelBloc modelbloc; // Crea objetos en el  modelo 
    ViewBloc viewbloc; // Crea objetos en la vista
   
    ActionListener actionlistener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == viewbloc.jmi_leer) { 
                jmi_leer_action_performed();
            }
            else if (e.getSource() == viewbloc.jmi_guardar) { 
                jmi_guardar_action_performed();
            }
        }
    };
    
    
    public ControllerBloc(ModelBloc modelBloc, ViewBloc viewBloc) {
        this.modelbloc = modelBloc;
        this.viewbloc = viewBloc;
        
        this.viewbloc.jmi_leer.addActionListener(actionlistener);
        this.viewbloc.jmi_guardar.addActionListener(actionlistener);
        initComponents();
    }
    
    
    public void jmi_leer_action_performed() {
        this.readFile(modelbloc.getPath()); // Invoca al método "readFile" 
    }
    
    /**
     * Método para el ítem "Guardar" del menú (ViewBlocNotasv1).
     */
    public void jmi_guardar_action_performed() {
        modelbloc.setMessage(viewbloc.jta_bloc.getText()); // Asigna el contenido del área de texto (interfaz) a la variable "message".
        this.writeFile(modelbloc.getPath(), modelbloc.getMessage()); // Invoca al método "writeFile" y le da como parámetros el contenido de la variable "path" y de la variable "message" (ubicadas en el Model).
    }
    
    

    
    
    public String readFile (String path) {
        try {
            String row; // Variable que indica una "fila".
            try (FileReader archivo = new FileReader(path)) { // Permite leer el contenido del archivo.
                BufferedReader bufferedreader = new BufferedReader(archivo); // Permite almacenar el contenido del archivo de texto de forma temporal.
                while ((row = bufferedreader.readLine()) != null ) {
                    viewbloc.jta_bloc.setText(row);
                }
            }
        }
        catch (FileNotFoundException err) { 
            System.err.println("No esta disponible el archivo: " + err.getMessage());
        }
        catch (IOException err) { 
            System.err.println("Error entrada y salida: " + err.getMessage());
        }
        return null;
    };
    
    /**
     * Método para escribir 
     * @param path: Indica la ruta 
     * @param message: almacena el contenido del área de texto.
     */
    public void writeFile (String path, String message) {
        try {
            File archivo = new File(path); // Abre el archivo y si no existe lo crea
            FileWriter filewriter = new FileWriter(archivo, false); // Escribe en el archivo especificado.
            
            try (PrintWriter printwriter = new PrintWriter(filewriter) ) { // Guarda el nuevo contenido
                printwriter.println(message);
                printwriter.close();
            }
        }
        catch (FileNotFoundException err) { 
            System.err.println("Archivo no encontrado: " + err.getMessage());
        }
        catch (IOException err) { 
            System.err.println("Error entrada y salida " + err.getMessage());
        }
    }
    
    //accede a la interfaz
    public void initComponents() {
        viewbloc.setVisible(true);
    }
    
}
