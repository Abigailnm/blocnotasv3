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
import javax.swing.JFileChooser; 
import javax.swing.filechooser.FileNameExtensionFilter; 
import java.io.PrintWriter;


public class ControllerBloc {
    
    ModelBloc modelbloc; // Crea objetos en el  modelo 
    ViewBloc viewbloc; // Crea objetos en la vista
    JFileChooser archivos = new JFileChooser(); // selector de archivos
    FileNameExtensionFilter extensiones = new FileNameExtensionFilter("Archivos de Texto", "txt");
   
    ActionListener actionlistener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
                   if (e.getSource() == viewbloc.jmi_leer) { // verifica si se selecciona Leer
                jmi_leer_action_performed();
            }
            else if (e.getSource() == viewbloc.jmi_guardar) { // verifica si se selecciona Guardar
                jmi_guardar_action_performed();
            }
            else if (e.getSource() == viewbloc.jmi_cifrar) { // verifica que sea seleccionado Cifrar
                jmi_cifrar_action_performed();
            }
            else if (e.getSource() == viewbloc.jmi_descifrar) { // verifica que se seleccione descifrar
                jmi_descifrar_action_performed();
            }
            else if (e.getSource() == viewbloc.jmi_salir) { // verifica que sea salir 
                System.exit(0); // Cierra la aplicación.
            }
        }
    };
    
    
    
    public ControllerBloc(ModelBloc modelBloc, ViewBloc viewBloc) {
        this.modelbloc = modelBloc;
        this.viewbloc = viewBloc;
        
        this.viewbloc.jmi_leer.addActionListener(actionlistener);
        this.viewbloc.jmi_guardar.addActionListener(actionlistener);
        this.viewbloc.jmi_cifrar.addActionListener(actionlistener);
        this.viewbloc.jmi_descifrar.addActionListener(actionlistener);
        this.viewbloc.jmi_salir.addActionListener(actionlistener);
        initComponents();
    }
    
    
    public void jmi_leer_action_performed() {
        archivos.setFileFilter(extensiones); //extensiones 
        archivos.showOpenDialog(viewbloc.jfc_cuadro); // abrir un archivo.
        File archivo = archivos.getSelectedFile(); // Obtiene el archivo y lo guarda em la variable "archivo".
        String ruta = archivo.getPath(); // Guarda la ruta del archivo obtenido en la variable "ruta".
        
        modelbloc.setPath(ruta);
        this.readFile(modelbloc.getPath()); // Invoca método
    }
    
    /**
     * Método para el ítem "Guardar" del menú (ViewBlocNotasv1).
     */
    public void jmi_guardar_action_performed() {
        archivos.setFileFilter(extensiones); // Asigna el filtro de extensión .txt
        archivos.showSaveDialog(viewbloc.jfc_cuadro); //abre el menu para guardar los archivos 
        File archivo = archivos.getSelectedFile(); 
        String ruta = archivo.getPath(); //almacena la ruta del archivo.
        
        
        modelbloc.setPath(ruta);
        modelbloc.setMessage(viewbloc.jta_bloc.getText()); // coloca el contenudo del area de texto en message 
        this.writeFile(modelbloc.getPath(), modelbloc.getMessage()); // Invoca metodo writeFile 
    }
    
    

    
    
    public String readFile (String path) {
        try {
            String abi;
            String texto = "";
            try (FileReader archivo = new FileReader(path)) { // Permite leer el contenido.
                BufferedReader bufferedreader = new BufferedReader(archivo); // Permite almacenar el contenido temporalmente
                while ((abi = bufferedreader.readLine()) != null ) {
                    texto += abi + "\n";
                }
                    viewbloc.jta_bloc.setText(abi);
                
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
    public void jmi_descifrar_action_performed() {
        String area = viewbloc.jta_bloc.getText();
        String descifrado = "";
        for (int i = 0; i < area.length(); i++) { // Ciclo para descifrar cada caracter del área de texto.
            char caracter = area.charAt(i);
            int ascii_char = (int) caracter;
            ascii_char = ascii_char - 3; // Cambia el valor ascii del caracter.
            caracter = (char) ascii_char;
            descifrado += caracter; // Acumula cada caracter descifrado.
        }
        viewbloc.jta_bloc.setText(descifrado); // Muestra el texto descifrado en la JTextAarea de la interfaz.
    }
     public void jmi_cifrar_action_performed() {
        String area = viewbloc.jta_bloc.getText();
        String cifrado = "";
        for (int i = 0; i < area.length(); i++) { // Ciclo para cifrar cada caracter del área de texto.
            char caracter = area.charAt(i);
            int ascii_char = (int) caracter;
            ascii_char = ascii_char + 3; // Cambia el valor ascii del caracter.
            caracter = (char) ascii_char;
            cifrado += caracter; // Acumula cada caracter cifrado.
        }
        viewbloc.jta_bloc.setText(cifrado); // Muestra el texto ya cifrado en la JTextAarea de la interfaz.
    }
    
    
    //accede a la interfaz
    public void initComponents() {
        viewbloc.setVisible(true);
    }
    
}
