package com.example.employee_department.service;

import com.example.employee_department.dto.EmployeeDTO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

@Service
public class ReportService {

    public byte[] exportEmployeeReport(List<EmployeeDTO> employeeList) throws JRException {
        InputStream reportStream = getClass().getResourceAsStream("/report/rep.jrxml");
        JasperReport report = JasperCompileManager.compileReport(reportStream);

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employeeList);
        JasperPrint print = JasperFillManager.fillReport(report, new HashMap<>(), dataSource);

        return JasperExportManager.exportReportToPdf(print);
    }
}
