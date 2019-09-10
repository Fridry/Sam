/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converters;

import Models.Evento;
import Models.EventoDAO;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author fridr
 */
@FacesConverter(value = "eventoConverter", forClass = Evento.class)
public class EventoConverter implements Converter{
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null && value.trim().length() > 0){
            Integer id = Integer.valueOf(value);
            EventoDAO eventoDao = new EventoDAO();
            return eventoDao.getById(id);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null){
            Evento evento = (Evento) value;
            return evento.getIdEvento().toString();
        }
        return null;
    }
}
