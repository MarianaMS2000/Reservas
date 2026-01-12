/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Reserva;

import Vista.Wing;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * Este programa simula una plataforma de reservación de vuelos, debe tener en cuenta ciertas restricciones en cuanto al tipo
 * de pasajero
 */
public class Principal {

    public static Wing wind;
    public static Reserva vres[] = new Reserva[50];
    public static int etr = 0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        wind = new Wing();
        wind.setVisible(true);
        wind.setTitle("Reservas");
        wind.setLocationRelativeTo(null);
    }

    public static boolean registrarReserva(String id_pas, String nom_pas, char tipore, String silla) {

        for (int i = 0; i < etr; i++) {
            if (vres[i] != null && vres[i].getId_pas().equalsIgnoreCase(id_pas)) {
                JOptionPane.showMessageDialog(null, "Ya existe una reserva registrada con ese ID.");
                return false;
            }
        }

        String num_res = generarCodigoReserva();
        char cmorral, cmaleta, ccat, ctipo;
        String sillaFinal;

        switch (Character.toUpperCase(tipore)) {
            case 'B':
                ctipo = 'B';
                cmorral = 'S';
                cmaleta = 'N';
                ccat = 'N';
                sillaFinal = asignarSillaAleatoria(21, 30);
                break;

            case 'M':
                ctipo = 'M';
                cmorral = 'S';
                cmaleta = 'S';
                ccat = 'N';
                sillaFinal = asignarSillaAleatoria(11, 20);
                break;

            case 'E':
                ctipo = 'E';
                cmorral = 'S';
                cmaleta = 'S';
                ccat = 'S';

                if (!validarSilla(silla.toUpperCase())) {
                    return false;
                }
                sillaFinal = silla.toUpperCase();
                break;

            default:
                JOptionPane.showMessageDialog(null, "Tipo inválido.");
                return false;
        }
        //HACEMOS LA RESERVA 
        Reserva nueva = new Reserva.Builder(num_res).id_pas(id_pas).nom_pas(nom_pas).silla(sillaFinal).morral(cmorral).maleta(cmaleta)
                .tipo(ctipo).cat(ccat).build();

        vres[etr] = nueva;
        JOptionPane.showMessageDialog(null, "Reserva registrada correctamente\nCódigo: " + num_res);
        return true;
    }

    public static boolean sillaOcupada(String silla) {
        for (int i = 0; i < etr; i++) {
            if (vres[i] != null && vres[i].getSilla().equalsIgnoreCase(silla)) {
                return true;
            }
        }
        return false;
    }

    public static String asignarSillaAleatoria(int inicio, int fin) {
        String letras = "ACDEFK";
        String silla;

        do {
            int numero = (int) (Math.random() * (fin - inicio + 1)) + inicio;
            char letra = letras.charAt((int) (Math.random() * letras.length()));
            silla = letra + String.format("%02d", numero);
        } while (sillaOcupada(silla));

        return silla;
    }

    public static String generarCodigoReserva() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int pos = (int) (Math.random() * caracteres.length());
            sb.append(caracteres.charAt(pos));
        }
        return sb.toString();
    }

    public static boolean validarSilla(String silla) {
        silla = silla.toUpperCase();

        if (silla.length() < 2 || silla.length() > 3) {
            JOptionPane.showMessageDialog(null, "Formato inválido. Ej: A01");
            return false;
        }

        char letra = silla.charAt(0);

        if ("ACDEFK".indexOf(letra) == -1) {
            JOptionPane.showMessageDialog(null, "Letra inválida. Solo A, C, D, E, F, K.");
            return false;
        }

        int numero;
        try {
            numero = Integer.parseInt(silla.substring(1));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Luego de la letra debe ir un número.");
            return false;
        }

        if (numero < 1 || numero > 10) {
            JOptionPane.showMessageDialog(null, "Ejecutiva: 1-10");
            return false;
        }

        if (sillaOcupada(silla)) {
            JOptionPane.showMessageDialog(null, "La silla ya está ocupada.");
            return false;
        }

        return true;
    }

    public static void consultarPorCodigo(String codigo, DefaultTableModel tbmod) {
        tbmod.setRowCount(0);
        boolean encontrado = false;
        for (int i = 0; i < etr; i++) {
            if (vres[i] != null && vres[i].getNum_res().equalsIgnoreCase(codigo)) {
                tbmod.addRow(new Object[]{vres[i].getNum_res(), vres[i].getId_pas(), vres[i].getNom_pas(), vres[i].getTipo(), vres[i].getSilla(),
                    vres[i].getMorral(), vres[i].getMaleta(), vres[i].getCat()});
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            JOptionPane.showMessageDialog(null, "No existe una reserva con ese código.");
        }
    }

    public static void consultarPorPasajero(String id, DefaultTableModel tbmod) {
        tbmod.setRowCount(0);
        boolean encontrado = false;

        for (int i = 0; i < etr; i++) {
            if (vres[i] != null && vres[i].getId_pas().equalsIgnoreCase(id)) {
                tbmod.addRow(new Object[]{vres[i].getNum_res(), vres[i].getId_pas(), vres[i].getNom_pas(), vres[i].getTipo(), vres[i].getSilla(),
                    vres[i].getMorral(), vres[i].getMaleta(), vres[i].getCat()});
                encontrado = true;
            }
        }
        if (!encontrado) {
            JOptionPane.showMessageDialog(null, "El pasajero no tiene reservas o el ID es incorrecto.");
        }
    }

    public static void consultarPorTipo(String tipoSel, DefaultTableModel tbmod) {
        tbmod.setRowCount(0);
        boolean encontrado = false;

        char tipoChar = tipoSel.toUpperCase().charAt(0);

        for (int i = 0; i < etr; i++) {
            if (vres[i] != null && vres[i].getTipo() == tipoChar) {
                tbmod.addRow(new Object[]{vres[i].getNum_res(), vres[i].getId_pas(), vres[i].getNom_pas(), vres[i].getTipo(), vres[i].getSilla(),
                    vres[i].getMorral(), vres[i].getMaleta(), vres[i].getCat()});
                encontrado = true;
            }
        }
        if (!encontrado) {
            JOptionPane.showMessageDialog(null, "No hay reservas de ese tipo.");
        }
    }
}
