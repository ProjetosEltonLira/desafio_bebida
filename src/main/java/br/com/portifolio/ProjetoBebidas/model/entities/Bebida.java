package br.com.portifolio.ProjetoBebidas.model.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "bebida")
public class Bebida implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 60)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "tipobebida_id", nullable = false)
    private TipoBebida tipoBebida;

    @JsonIgnore
    @OneToMany(mappedBy = "id.bebida")
    private Set<BebidaSecao> bebidaSessoes;

    public Bebida(){}
    public Bebida(Integer id, String nome, TipoBebida tipoBebida) {
        this.id = id;
        this.nome = nome;
        this.tipoBebida = tipoBebida;
        //this.bebidaSessoes = bebidaSessoes;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoBebida getTipoBebida() {
        return tipoBebida;
    }

    public void setTipoBebida(TipoBebida tipoBebida) {
        this.tipoBebida = tipoBebida;
    }

    public Set<BebidaSecao> getBebidaSessoes() {
        return bebidaSessoes;
    }

    public void setBebidaSessoes(Set<BebidaSecao> bebidaSessoes) {
        this.bebidaSessoes = bebidaSessoes;
    }
}
