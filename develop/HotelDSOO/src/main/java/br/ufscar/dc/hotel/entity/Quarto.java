/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.hotel.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author alex
 */
@Entity
@Table(name = "quarto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Quarto.findAll", query = "SELECT q FROM Quarto q")
    , @NamedQuery(name = "Quarto.findById", query = "SELECT q FROM Quarto q WHERE q.id = :id")
    , @NamedQuery(name = "Quarto.findByDescricao", query = "SELECT q FROM Quarto q WHERE q.descricao = :descricao")
    , @NamedQuery(name = "Quarto.findByTipo", query = "SELECT q FROM Quarto q WHERE q.tipo = :tipo")
    , @NamedQuery(name = "Quarto.findByNumero", query = "SELECT q FROM Quarto q WHERE q.numero = :numero")
    , @NamedQuery(name = "Quarto.findByCapacidade", query = "SELECT q FROM Quarto q WHERE q.capacidade = :capacidade")
    , @NamedQuery(name = "Quarto.findByAndar", query = "SELECT q FROM Quarto q WHERE q.andar = :andar")
    , @NamedQuery(name = "Quarto.findByOcupado", query = "SELECT q FROM Quarto q WHERE q.ocupado = :ocupado")
    , @NamedQuery(name = "Quarto.findByValorDiaria", query = "SELECT q FROM Quarto q WHERE q.valorDiaria = :valorDiaria")})
public class Quarto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2048)
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo")
    private int tipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "numero")
    private String numero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "capacidade")
    private int capacidade;
    @Basic(optional = false)
    @NotNull
    @Column(name = "andar")
    private int andar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ocupado")
    private boolean ocupado;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_diaria")
    private BigDecimal valorDiaria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkQuarto")
    private List<Reserva> reservaList;

    public Quarto() {
    }

    public Quarto(Integer id) {
        this.id = id;
    }

    public Quarto(Integer id, String descricao, int tipo, String numero, int capacidade, int andar, boolean ocupado, BigDecimal valorDiaria) {
        this.id = id;
        this.descricao = descricao;
        this.tipo = tipo;
        this.numero = numero;
        this.capacidade = capacidade;
        this.andar = andar;
        this.ocupado = ocupado;
        this.valorDiaria = valorDiaria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public int getAndar() {
        return andar;
    }

    public void setAndar(int andar) {
        this.andar = andar;
    }

    public boolean getOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public BigDecimal getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(BigDecimal valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    @XmlTransient
    public List<Reserva> getReservaList() {
        return reservaList;
    }

    public void setReservaList(List<Reserva> reservaList) {
        this.reservaList = reservaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Quarto)) {
            return false;
        }
        Quarto other = (Quarto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufscar.dc.hotel.model.Quarto[ id=" + id + " ]";
    }
    
}
