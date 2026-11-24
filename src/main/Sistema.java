
package main;

import view.MenuPrincipal;

public class Sistema {

    public static void main(String[] args) {

        System.out.println("========================================");
        System.out.println(" SISTEMA DE GERENCIAMENTO DE ABRIGOS");
        System.out.println("       E RESGATES DE ANIMAIS");
        System.out.println("========================================");

        try {
            MenuPrincipal menu = new MenuPrincipal();
            menu.exibir();

        } catch (Exception e) {
            System.out.println("Erro ao iniciar o sistema: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
