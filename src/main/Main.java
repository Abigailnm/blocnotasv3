/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import controllers.ControllerBloc;
import models.ModelBloc;
import Views.ViewBloc;


public class Main {

   
    public static void main(String[] args) {
        
        ModelBloc modelbloc = new ModelBloc();
        ViewBloc viewbloc = new ViewBloc();
        ControllerBloc controllerbloc = new ControllerBloc(modelbloc, viewbloc); // Integra los componentes del modelo MVC.
        
    }
    
}