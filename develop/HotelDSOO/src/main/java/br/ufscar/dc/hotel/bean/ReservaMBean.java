/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.hotel.bean;

import br.ufscar.dc.hotel.ejb.ReservaSession;
import br.ufscar.dc.hotel.entity.Quarto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
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
    private List<Quarto> quartosParaReserva;

    @EJB
    private ReservaSession reservaSession;
    
    public ReservaMBean(){
        this.quartosParaReserva = new ArrayList<>();
        this.filtroNumeroPessoas = 1;
        this.filtroReservaChegada = new Date();
        this.filtroReservaSaida = new Date();                
    }
    
    public void pesquisarReserva() {
        this.quartosParaReserva = reservaSession.buscarQuartosVagos();
    }
    
    public void reservarQuarto(Quarto quarto) {

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
    //</editor-fold>
    
}
