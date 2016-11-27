/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.hotel.bean;

import br.ufscar.dc.hotel.ejb.ReservaSession;
import br.ufscar.dc.hotel.entity.Quarto;
import java.io.Serializable;
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

    @EJB
    private ReservaSession reservaSession;

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

    public List<Quarto> buscarQuartosVagos() {
        return reservaSession.buscarQuartosVagos();
    }

    public void reservarQuarto(Quarto quarto) {

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

}
