/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.hotel.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author alex
 */
@Entity
@Table(name = "cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c")
    , @NamedQuery(name = "Cliente.findById", query = "SELECT c FROM Cliente c WHERE c.id = :id")
    , @NamedQuery(name = "Cliente.findByNome", query = "SELECT c FROM Cliente c WHERE c.nome = :nome")
    , @NamedQuery(name = "Cliente.findByDataNascimento", query = "SELECT c FROM Cliente c WHERE c.dataNascimento = :dataNascimento")
    , @NamedQuery(name = "Cliente.findByDocRg", query = "SELECT c FROM Cliente c WHERE c.docRg = :docRg")
    , @NamedQuery(name = "Cliente.findByDocCpf", query = "SELECT c FROM Cliente c WHERE c.docCpf = :docCpf")
    , @NamedQuery(name = "Cliente.findByDocPassaporte", query = "SELECT c FROM Cliente c WHERE c.docPassaporte = :docPassaporte")
    , @NamedQuery(name = "Cliente.findByContatoTelefone", query = "SELECT c FROM Cliente c WHERE c.contatoTelefone = :contatoTelefone")
    , @NamedQuery(name = "Cliente.findByContatoCelular", query = "SELECT c FROM Cliente c WHERE c.contatoCelular = :contatoCelular")
    , @NamedQuery(name = "Cliente.findByContatoEmail", query = "SELECT c FROM Cliente c WHERE c.contatoEmail = :contatoEmail")
    , @NamedQuery(name = "Cliente.findByEndLogradouro", query = "SELECT c FROM Cliente c WHERE c.endLogradouro = :endLogradouro")
    , @NamedQuery(name = "Cliente.findByEndNumero", query = "SELECT c FROM Cliente c WHERE c.endNumero = :endNumero")
    , @NamedQuery(name = "Cliente.findByEndComplemento", query = "SELECT c FROM Cliente c WHERE c.endComplemento = :endComplemento")
    , @NamedQuery(name = "Cliente.findByEndBairro", query = "SELECT c FROM Cliente c WHERE c.endBairro = :endBairro")
    , @NamedQuery(name = "Cliente.findByEndCep", query = "SELECT c FROM Cliente c WHERE c.endCep = :endCep")})
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_nascimento")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    @Size(max = 15)
    @Column(name = "doc_rg")
    private String docRg;
    @Size(max = 14)
    @Column(name = "doc_cpf")
    private String docCpf;
    @Size(max = 20)
    @Column(name = "doc_passaporte")
    private String docPassaporte;
    @Size(max = 20)
    @Column(name = "contato_telefone")
    private String contatoTelefone;
    @Size(max = 20)
    @Column(name = "contato_celular")
    private String contatoCelular;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "contato_email")
    private String contatoEmail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "end_logradouro")
    private String endLogradouro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "end_numero")
    private String endNumero;
    @Size(max = 30)
    @Column(name = "end_complemento")
    private String endComplemento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "end_bairro")
    private String endBairro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "end_cep")
    private String endCep;
    @JoinColumn(name = "end_fk_cidade", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cidade endFkCidade;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkCliente")
    private List<Reserva> reservaList;

    public Cliente() {
    }

    public Cliente(Integer id) {
        this.id = id;
    }

    public Cliente(Integer id, String nome, Date dataNascimento, String contatoEmail, String endLogradouro, String endNumero, String endBairro, String endCep) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.contatoEmail = contatoEmail;
        this.endLogradouro = endLogradouro;
        this.endNumero = endNumero;
        this.endBairro = endBairro;
        this.endCep = endCep;
    }

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

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getDocRg() {
        return docRg;
    }

    public void setDocRg(String docRg) {
        this.docRg = docRg;
    }

    public String getDocCpf() {
        return docCpf;
    }

    public void setDocCpf(String docCpf) {
        this.docCpf = docCpf;
    }

    public String getDocPassaporte() {
        return docPassaporte;
    }

    public void setDocPassaporte(String docPassaporte) {
        this.docPassaporte = docPassaporte;
    }

    public String getContatoTelefone() {
        return contatoTelefone;
    }

    public void setContatoTelefone(String contatoTelefone) {
        this.contatoTelefone = contatoTelefone;
    }

    public String getContatoCelular() {
        return contatoCelular;
    }

    public void setContatoCelular(String contatoCelular) {
        this.contatoCelular = contatoCelular;
    }

    public String getContatoEmail() {
        return contatoEmail;
    }

    public void setContatoEmail(String contatoEmail) {
        this.contatoEmail = contatoEmail;
    }

    public String getEndLogradouro() {
        return endLogradouro;
    }

    public void setEndLogradouro(String endLogradouro) {
        this.endLogradouro = endLogradouro;
    }

    public String getEndNumero() {
        return endNumero;
    }

    public void setEndNumero(String endNumero) {
        this.endNumero = endNumero;
    }

    public String getEndComplemento() {
        return endComplemento;
    }

    public void setEndComplemento(String endComplemento) {
        this.endComplemento = endComplemento;
    }

    public String getEndBairro() {
        return endBairro;
    }

    public void setEndBairro(String endBairro) {
        this.endBairro = endBairro;
    }

    public String getEndCep() {
        return endCep;
    }

    public void setEndCep(String endCep) {
        this.endCep = endCep;
    }

    public Cidade getEndFkCidade() {
        return endFkCidade;
    }

    public void setEndFkCidade(Cidade endFkCidade) {
        this.endFkCidade = endFkCidade;
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
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ufscar.dc.hotel.model.Cliente[ id=" + id + " ]";
    }
    
}
