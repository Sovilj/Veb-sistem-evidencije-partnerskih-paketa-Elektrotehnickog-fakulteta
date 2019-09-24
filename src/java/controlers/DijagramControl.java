/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import helper.PretragaHelper;
import helper.KompHelper;
import beans.KompUgovor;
import database.*;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.timeline.TimelineSelectEvent;
import org.primefaces.model.timeline.TimelineEvent;
import org.primefaces.model.timeline.TimelineModel;

/**
 *
 * @author Stefan
 */
@Named(value = "dijagramControl")
@SessionScoped
public class DijagramControl implements Serializable {

    private TimelineModel model;

    private boolean selectable = true;
    private boolean zoomable = true;
    private boolean moveable = true;
    private boolean stackEvents = true;
    private String eventStyle = "box";
    private boolean axisOnTop;
    private boolean showCurrentTime = true;
    private boolean showNavigation = false;

    private PretragaHelper helper = null;
    private KompHelper kompHelper = null;
    private List<Kompanija> kompanije = null;
    private List<KompUgovor> kompUgovor = null;

    private String selected;
    private Kompanija odabrana;
    private boolean izmena = true;
    private List<Kompanija> kompIsticu;
    private List<Kompanija> kompIstekle;

    private UIComponent component;

    public boolean isIzmena() {
        return izmena;
    }

    public void setIzmena(boolean izmena) {
        this.izmena = izmena;
    }

    public UIComponent getComponent() {
        return component;
    }

    public void setComponent(UIComponent component) {
        this.component = component;
    }

    public List<Kompanija> getKompIsticu() {
        return kompIsticu;
    }

    public void setKompIsticu(List<Kompanija> kompIsticu) {
        this.kompIsticu = kompIsticu;
    }

    public List<Kompanija> getKompIstekle() {
        return kompIstekle;
    }

    public void setKompIstekle(List<Kompanija> kompIstekle) {
        this.kompIstekle = kompIstekle;
    }

    public List<Kompanija> getKompanije() {
        return kompanije;
    }

    public void setKompanije(List<Kompanija> kompanije) {
        this.kompanije = kompanije;
    }

    public List<KompUgovor> getKompUgovor() {
        return kompUgovor;
    }

    public void setKompUgovor(List<KompUgovor> kompUgovor) {
        this.kompUgovor = kompUgovor;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public Kompanija getOdabrana() {
        return odabrana;
    }

    public void setOdabrana(Kompanija odabrana) {
        this.odabrana = odabrana;
    }

    public TimelineModel getModel() {
        return model;
    }

    public boolean isSelectable() {
        return selectable;
    }

    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
    }

    public boolean isZoomable() {
        return zoomable;
    }

    public void setZoomable(boolean zoomable) {
        this.zoomable = zoomable;
    }

    public boolean isMoveable() {
        return moveable;
    }

    public void setMoveable(boolean moveable) {
        this.moveable = moveable;
    }

    public boolean isStackEvents() {
        return stackEvents;
    }

    public void setStackEvents(boolean stackEvents) {
        this.stackEvents = stackEvents;
    }

    public String getEventStyle() {
        return eventStyle;
    }

    public void setEventStyle(String eventStyle) {
        this.eventStyle = eventStyle;
    }

    public boolean isAxisOnTop() {
        return axisOnTop;
    }

    public void setAxisOnTop(boolean axisOnTop) {
        this.axisOnTop = axisOnTop;
    }

    public boolean isShowCurrentTime() {
        return showCurrentTime;
    }

    public void setShowCurrentTime(boolean showCurrentTime) {
        this.showCurrentTime = showCurrentTime;
    }

    public boolean isShowNavigation() {
        return showNavigation;
    }

    public void setShowNavigation(boolean showNavigation) {
        this.showNavigation = showNavigation;
    }

    public DijagramControl() {
        init();
    }

    public void init() {
        kompHelper = new KompHelper();
        helper = new PretragaHelper();
        model = new TimelineModel();
        kompanije = helper.getKompanije();
        
        kompIsticu = helper.getKompIsticu();
        kompIstekle = helper.getKompIstekle();
               

        int i = 0;
        while (i < kompanije.size()) {

            kompUgovor = helper.getKompUgovor(kompanije.get(i++));

            int j = 0;
            while (j < kompUgovor.size()) {
                KompUgovor ku = kompUgovor.get(j++);
                if (ku.getDatumIsticanja().after(new Date())) {
                    model.add(new TimelineEvent(ku.getKompanija().getNaziv(), ku.getDatumIsticanja()));
                }

            }
        }

    }

    public String izmeniBool() {
        izmena = !izmena;
        return null;
    }

    public void snimi() {
        if (izmena == false) {
            kompHelper.updateKomp(odabrana);

            izmena = true;
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Uspesno promenjeni podaci"));

        }

    }

    public void onSelect(TimelineSelectEvent e) {
        TimelineEvent timelineEvent = e.getTimelineEvent();
        /*
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected event:", timelineEvent.getData().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);*/

        selected = timelineEvent.getData().toString();
        otvoriDosije();

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("dosije.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(DijagramControl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void otvoriDosije() {

        int i = 0;
        Kompanija k = null;
        while (i < kompanije.size()) {
            k = kompanije.get(i++);
            if (k.getNaziv().equals(selected)) {
                break;
            }
        }
        odabrana = k;
        spremiOdabranu();

    }

    public void spremiOdabranu() {
        if (odabrana != null) {
            kompUgovor = helper.getKompUgovor(odabrana);

        }
    }

    public void onRowSelect1(SelectEvent event) {

        spremiOdabranu();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("dosije.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(DijagramControl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void onRowSelect2(SelectEvent event) {

        spremiOdabranu();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("dosije.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(DijagramControl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
