package br.com.portifolio.ProjetoBebidas.Model.entities;

import br.com.portifolio.ProjetoBebidas.Enum.TipoBebidaEnum;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "bebida")
public class Bebida implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @Column (name = "nome")
    private String nome;

    @Column (name = "quantidade")
    private double quantidade;

    @ManyToOne
    @JoinColumn(name = "idtipobebida")
    private TipoBebida tipoBebida;

    //private int tipo;

    public Bebida() {}

    public Bebida(String nome, double quantidade, TipoBebida tipoBebida) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.tipoBebida = tipoBebida;
        //this.tipo = tipo;
    }
    //setTipoBebida(tipoBebida);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoBebida getTipoBebida() {
        return tipoBebida;
    }

    public void setTipoBebida(TipoBebida tipoBebida) {
        this.tipoBebida = tipoBebida;
    }

   /* public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }*/

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

   /* public TipoBebidaEnum getTipoDeBebida() {
        return TipoBebidaEnum.valueOf(tipo);
    }

    public void setTipoBebida(TipoBebidaEnum tipo) {
        if (tipo != null) {
            this.tipo = tipo.getCodigo();
        }
    }*/

    @Override
    public String toString() {
        return  "Nome:" + getNome() + " Tipo:" + tipoBebida.getDescricao() + " Qtde:" + getQuantidade();
    }
}
