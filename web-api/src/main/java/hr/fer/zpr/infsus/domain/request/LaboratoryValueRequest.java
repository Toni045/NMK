package hr.fer.zpr.infsus.domain.request;

public class LaboratoryValueRequest {

    private Double value;

    private Integer laboratoryValueNameId;

    public LaboratoryValueRequest() {
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Integer getLaboratoryValueNameId() {
        return laboratoryValueNameId;
    }

    public void setLaboratoryValueNameId(Integer laboratoryValueNameId) {
        this.laboratoryValueNameId = laboratoryValueNameId;
    }
}
