package br.com.portifolio.ProjetoBebidas.Model.entities;

import br.com.portifolio.ProjetoBebidas.Enum.TipoBebida;
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

    //@ManyToOne(fetch = FetchType.LAZY, optional = false) //oque isso faz ?
    //@JoinColumn(name = "tipobebida", nullable = false)
    /*@Enumerated(EnumType.ORDINAL)
    @Column (name = "idtipobebida")
    private TipoDeBebida tipo;*/

    @Column (name = "idtipobebida")
    private int tipo;

    public Bebida() {}
    public Bebida(String nome, TipoBebida tipoBebida,double quantidade) {
        this.nome = nome;
        this.quantidade = quantidade;
        setTipoBebida(tipoBebida);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /*public TipoDeBebida getTipo() {
        return tipo;
    }

    public void setTipo(TipoDeBebida tipo) {
        this.tipo = tipo;
    }*/

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

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

    public TipoBebida getTipoDeBebida() {
        return TipoBebida.valueOf(tipo);
    }

    public void setTipoBebida(TipoBebida tipo) {
        if (tipo != null) {
            this.tipo = tipo.getCodigo();
        }
    }

    @Override
    public String toString() {
        return  "Nome:" + getNome() + " Tipo:" + getTipo() + " Qtde:" + getQuantidade();
    }
}
