/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converters;

import Models.Horario;
import Models.Horario;
import Models.HorarioDAO;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author fridr
 */

@FacesConverter(value = "horarioConverter", forClass = Horario.class)
public class HorarioConverter implements Converter {
        @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null && value.trim().length() > 0){
            Integer id = Integer.valueOf(value);
            HorarioDAO horarioDao = new HorarioDAO();
            return horarioDao.getById(id);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null){
            Horario horario = (Horario) value;
            return horario.getIdHorario().toString();
        }
        return null;
    }
    
}
