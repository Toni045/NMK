package hr.fer.zpr.infsus.domain.dto;

public class UserDropdownDTO {
    private Integer id;
    private String email;

    public UserDropdownDTO() {
    }

    public UserDropdownDTO(Integer id, String email) {
        this.id = id;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
