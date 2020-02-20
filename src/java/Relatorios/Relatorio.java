/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Relatorios;

import Util.HibernateUtil;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.apache.commons.collections.map.HashedMap;

/**
 *
 * @author fridr
 */
public class Relatorio {
    private HttpServletResponse response;
    private FacesContext context;
    private ByteArrayOutputStream baos;
    private InputStream stream;
    private Connection con;

    public Relatorio() {
        this.context = FacesContext.getCurrentInstance();
        this.response = (HttpServletResponse) context.getExternalContext().getResponse();
    }
    
    //Adicionar duas propriedades
    //1 - Caminho/arquivo .jasper
    //2 - Nome do relatório
    public void getRelatorio(String arquivoJasper, String nomeRelatorio) {
        stream = this.getClass().getResourceAsStream(arquivoJasper);
        Map<String, Object> params = new HashMap<String, Object>();
        baos = new ByteArrayOutputStream();
        try {
            JasperReport report = (JasperReport) JRLoader.loadObject(stream);            
            Connection conexao = HibernateUtil.getConexao();
            JasperPrint print = JasperFillManager.fillReport(report, params, conexao);
            JasperExportManager.exportReportToPdfStream(print, baos);

            response.reset();
            response.setContentType("application/pdf");
            response.setContentLength(baos.size());
            response.setHeader("Content-disposition", "inline; filename=" + nomeRelatorio);
            response.getOutputStream().write(baos.toByteArray());
            response.getOutputStream().flush();
            response.getOutputStream().close();

            context.responseComplete();

        } catch (JRException ex) {
            Logger.getLogger(Relatorio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Relatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getRelatorioEspecialidade(String arquivoJasper, String nomeRelatorio, int idEspecialidade) {
        stream = this.getClass().getResourceAsStream(arquivoJasper);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("idEspecialidade", idEspecialidade);
        baos = new ByteArrayOutputStream();
        try {
            JasperReport report = (JasperReport) JRLoader.loadObject(stream);            
            Connection conexao = HibernateUtil.getConexao();
            JasperPrint print = JasperFillManager.fillReport(report, params, conexao);
            JasperExportManager.exportReportToPdfStream(print, baos);

            response.reset();
            response.setContentType("application/pdf");
            response.setContentLength(baos.size());
            response.setHeader("Content-disposition", "inline; filename=" + nomeRelatorio);
            response.getOutputStream().write(baos.toByteArray());
            response.getOutputStream().flush();
            response.getOutputStream().close();

            context.responseComplete();

        } catch (JRException ex) {
            Logger.getLogger(Relatorio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Relatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getRelatorioPeriodo(String arquivoJasper, String nomeRelatorio, Date inicio, Date fim) {
        stream = this.getClass().getResourceAsStream(arquivoJasper);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("paramInicio", inicio);
        params.put("paramFim", fim);
        baos = new ByteArrayOutputStream();
        try {
            JasperReport report = (JasperReport) JRLoader.loadObject(stream);            
            Connection conexao = HibernateUtil.getConexao();
            JasperPrint print = JasperFillManager.fillReport(report, params, conexao);
            JasperExportManager.exportReportToPdfStream(print, baos);

            response.reset();
            response.setContentType("application/pdf");
            response.setContentLength(baos.size());
            response.setHeader("Content-disposition", "inline; filename=" + nomeRelatorio);
            response.getOutputStream().write(baos.toByteArray());
            response.getOutputStream().flush();
            response.getOutputStream().close();

            context.responseComplete();

        } catch (JRException ex) {
            Logger.getLogger(Relatorio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Relatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
