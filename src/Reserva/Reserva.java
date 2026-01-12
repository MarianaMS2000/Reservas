/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reserva;

/**
 *
 * @author miers
 */
public class Reserva {
    private String num_res, id_pas, nom_pas, silla;
    private char morral, maleta, tipo, cat;

    private Reserva(Builder builder) {
        this.num_res = builder.num_res;
        this.id_pas = builder.id_pas;
        this.nom_pas = builder.nom_pas;
        this.silla = builder.silla;
        this.morral = builder.morral;
        this.maleta = builder.maleta;
        this.tipo = builder.tipo;
        this.cat = builder.cat;
    }

    public String getNum_res() {
        return num_res;
    }

    public String getId_pas() {
        return id_pas;
    }

    public String getNom_pas() {
        return nom_pas;
    }

    public String getSilla() {
        return silla;
    }

    public char getMorral() {
        return morral;
    }

    public char getMaleta() {
        return maleta;
    }

    public char getTipo() {
        return tipo;
    }

    public char getCat() {
        return cat;
    }

    public static class Builder {

        private String num_res, id_pas, nom_pas, silla;
        private char morral, maleta, tipo, cat;

        public Builder(String num_res) {
            this.num_res = num_res;
        }

        public Builder id_pas(String id_pas) {
            this.id_pas = id_pas;
            return this;
        }

        public Builder nom_pas(String nom_pas) {
            this.nom_pas = nom_pas;
            return this;
        }

        public Builder silla(String silla) {
            this.silla = silla;
            return this;
        }

        public Builder morral(char morral) {
            this.morral = morral;
            return this;
        }

        public Builder maleta(char maleta) {
            this.maleta = maleta;
            return this;
        }

        public Builder tipo(char tipo) {
            this.tipo = tipo;
            return this;
        }

        public Builder cat(char cat) {
            this.cat = cat;
            return this;
        }

        public Reserva build() {
            return new Reserva(this);
        }

    }
}

