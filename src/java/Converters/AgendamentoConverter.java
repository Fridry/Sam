/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converters;

import Models.Agendamento;
import Models.Agendamento;
import Models.AgendamentoDAO;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author fridr
 */

@FacesConverter(value = "agendamentoConverter", forClass = Agendamento.class)
public class AgendamentoConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null && value.trim().length() > 0){
            Integer id = Integer.valueOf(value);
            AgendamentoDAO agendamentoDao = new AgendamentoDAO();
            return agendamentoDao.getById(id);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null){
            Agendamento agendamento = (Agendamento) value;
            return agendamento.getIdAgendamento().toString();
        }
        return null;
    }
    
}
