/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converters;

import Models.Especialidade;
import Models.EspecialidadeDAO;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author fridr
 */

@FacesConverter(value = "especialidadeConverter", forClass = Especialidade.class)
public class EspecialidadeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null && equals(value)){
            Integer id = Integer.valueOf(value);
            EspecialidadeDAO especialidadeDao = new EspecialidadeDAO();
            return especialidadeDao.getById(id);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null && equals(value)){
            Especialidade especialidade = (Especialidade) value;
            return especialidade.getIdEspecialidade().toString();
        }
        return null;
    }
    
    
}
