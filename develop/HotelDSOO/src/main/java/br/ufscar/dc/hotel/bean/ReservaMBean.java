/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.hotel.bean;

import br.ufscar.dc.hotel.core.MyInterfaceEntity;
import br.ufscar.dc.hotel.core.MyPersistence;
import br.ufscar.dc.hotel.ejb.CidadeSession;
import br.ufscar.dc.hotel.ejb.ReservaSession;
import br.ufscar.dc.hotel.entity.Cidade;
import br.ufscar.dc.hotel.entity.Cliente;
import br.ufscar.dc.hotel.entity.Quarto;
import br.ufscar.dc.hotel.entity.Reserva;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author alex
 */
@Named(value = "reservaMBean")
@ViewScoped
public class ReservaMBean implements Serializable {

    private Date filtroReservaChegada;
    private Date filtroReservaSaida;
    private int filtroNumeroPessoas;
    private int filtroTipoQuarto;
    private List<Quarto> quartosParaReserva;
    private Reserva reserva;
    private List<Cidade> cidades;
    private Cliente cliente;
    private boolean reservaFinalizada;

    @EJB
    private MyPersistence myPersistence;

    @EJB
    private ReservaSession reservaSession;

    @EJB
    protected CidadeSession cidadeSession;

    public ReservaMBean() {
        this.quartosParaReserva = new ArrayList<>();
        this.filtroReservaChegada = new Date();
        this.filtroReservaSaida = new Date();
        this.filtroTipoQuarto = 0;
        this.filtroNumeroPessoas = 1;
    }

    @PostConstruct
    public void init() {
        this.getQuartoSelecionadoParaReserva();
        this.cidades = cidadeSession.all();
        this.cliente = new Cliente();
        this.setReservaFinalizada(false);
    }

    public void getQuartoSelecionadoParaReserva() {
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        this.reserva = (Reserva) flash.get("reserva");
    }

    public void pesquisarReserva() {
        this.quartosParaReserva = reservaSession.buscarQuartosVagos(filtroReservaChegada,
                filtroReservaSaida,
                filtroNumeroPessoas,
                filtroTipoQuarto);
    }

    public String reservarQuarto(Quarto quarto) {
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        this.reserva = new Reserva();
        this.reserva.setQuarto(quarto);
        this.reserva.setDataChegada(filtroReservaChegada);
        this.reserva.setDataSaida(filtroReservaSaida);
        this.reserva.setValorTotal(quarto.getValorDiaria());
        flash.put("reserva", reserva);
        return "pretty:reserve";
    }

    public void confirmarReserva() {
        boolean saveCliente = this.getMyPersistence().save((MyInterfaceEntity) this.cliente);
        if (saveCliente) {
            this.reserva.setCliente(this.cliente);
            boolean save = this.getMyPersistence().save((MyInterfaceEntity) this.reserva);
            if (save) {
                this.setReservaFinalizada(true);
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao realizar reserva!", ""));
            }
        }
    }

    public String cancelarReserva() {
        return "pretty:index";
    }

    /**
     * @return the cidadeSession
     */
    public CidadeSession getCidadeSession() {
        return cidadeSession;
    }

    /**
     * @param cidadeSession the cidadeSession to set
     */
    public void setCidadeSession(CidadeSession cidadeSession) {
        this.cidadeSession = cidadeSession;
    }

    /**
     * @return the cidades
     */
    public List<Cidade> getCidades() {
        return cidades;
    }

    /**
     * @param cidades the cidades to set
     */
    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }

    //<editor-fold defaultstate="collapsed" desc="Get/Set">    
    /**
     * @return the reservaSession
     */
    public ReservaSession getReservaSession() {
        return reservaSession;
    }

    /**
     * @param reservaSession the reservaSession to set
     */
    public void setReservaSession(ReservaSession reservaSession) {
        this.reservaSession = reservaSession;
    }

    /**
     * @return the filtroReservaChegada
     */
    public Date getFiltroReservaChegada() {
        return filtroReservaChegada;
    }

    /**
     * @param filtroReservaChegada the filtroReservaChegada to set
     */
    public void setFiltroReservaChegada(Date filtroReservaChegada) {
        this.filtroReservaChegada = filtroReservaChegada;
    }

    /**
     * @return the filtroReservaSaida
     */
    public Date getFiltroReservaSaida() {
        return filtroReservaSaida;
    }

    /**
     * @param filtroReservaSaida the filtroReservaSaida to set
     */
    public void setFiltroReservaSaida(Date filtroReservaSaida) {
        this.filtroReservaSaida = filtroReservaSaida;
    }

    /**
     * @return the filtroNumeroPessoas
     */
    public int getFiltroNumeroPessoas() {
        return filtroNumeroPessoas;
    }

    /**
     * @param filtroNumeroPessoas the filtroNumeroPessoas to set
     */
    public void setFiltroNumeroPessoas(int filtroNumeroPessoas) {
        this.filtroNumeroPessoas = filtroNumeroPessoas;
    }

    /**
     * @return the filtroTipoQuarto
     */
    public int getFiltroTipoQuarto() {
        return filtroTipoQuarto;
    }

    /**
     * @param filtroTipoQuarto the filtroTipoQuarto to set
     */
    public void setFiltroTipoQuarto(int filtroTipoQuarto) {
        this.filtroTipoQuarto = filtroTipoQuarto;
    }

    /**
     * @return the quartosParaReserva
     */
    public List<Quarto> getQuartosParaReserva() {
        return quartosParaReserva;
    }

    /**
     * @param quartosParaReserva the quartosParaReserva to set
     */
    public void setQuartosParaReserva(List<Quarto> quartosParaReserva) {
        this.quartosParaReserva = quartosParaReserva;
    }

    /**
     * @return the reserva
     */
    public Reserva getReserva() {
        return reserva;
    }

    /**
     * @param reserva the reserva to set
     */
    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    /**
     * @return the myPersistence
     */
    public MyPersistence getMyPersistence() {
        return myPersistence;
    }

    /**
     * @param myPersistence the myPersistence to set
     */
    public void setMyPersistence(MyPersistence myPersistence) {
        this.myPersistence = myPersistence;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the reservaFinalizada
     */
    public boolean isReservaFinalizada() {
        return reservaFinalizada;
    }

    /**
     * @param reservaFinalizada the reservaFinalizada to set
     */
    public void setReservaFinalizada(boolean reservaFinalizada) {
        this.reservaFinalizada = reservaFinalizada;
    }
    //</editor-fold>

}
