package hr.fer.zpr.infsus.domain.dto;

import hr.fer.zpr.infsus.domain.LaboratoryReport;
import hr.fer.zpr.infsus.domain.LaboratoryValueName;
import jakarta.persistence.*;

public class LaboratoryValueDTO {
    private Integer id;

    private String value;

    private Integer laboratoryReportId;

    private Integer laboratoryValueNameId;
    private String laboratoryValueName;

    public LaboratoryValueDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getLaboratoryReportId() {
        return laboratoryReportId;
    }

    public void setLaboratoryReportId(Integer laboratoryReportId) {
        this.laboratoryReportId = laboratoryReportId;
    }

    public Integer getLaboratoryValueNameId() {
        return laboratoryValueNameId;
    }

    public void setLaboratoryValueNameId(Integer laboratoryValueNameId) {
        this.laboratoryValueNameId = laboratoryValueNameId;
    }

    public String getLaboratoryValueName() {
        return laboratoryValueName;
    }

    public void setLaboratoryValueName(String laboratoryValueName) {
        this.laboratoryValueName = laboratoryValueName;
    }
}
