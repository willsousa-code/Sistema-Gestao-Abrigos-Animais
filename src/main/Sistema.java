package main;

import view.MenuPrincipal;

public class Sistema {

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println(" SISTEMA DE ABRIGOS E RESGATES ");
        System.out.println("====================================");

        MenuPrincipal menu = new MenuPrincipal();

        menu.exibir();
    }
}