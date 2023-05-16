package hr.fer.zpr.infsus.domain.request;

import java.time.LocalDate;

public class LaboratoryReportRequest {
    private Integer id;

    private LocalDate date;

    private String description;

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
