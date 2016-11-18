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

    @EJB
    private ReservaSession reservaSession;

    public List<Quarto> buscarQuartosVagos() {
        List<Quarto> quartos = new ArrayList<Quarto>();
        quartos = reservaSession.buscarQuartosVagos();
        return quartos;
    }

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

}
