package hr.fer.zpr.infsus.domain.request;

import hr.fer.zpr.infsus.domain.validator.ComplexValidation;

import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

public class LaboratoryReportRequest {
    @Positive(message = "ID must be a positive value")
    private Integer id;

    @Past(message = "Event date must be in the past")
    private LocalDate date;

    @ComplexValidation
    private String description;

    @Positive(message = "ID must be a positive value")
    private Integer userId;

    public LaboratoryReportRequest() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
