package hr.foi.si.projekt.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@Table(name = "[user]")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name cannot be empty")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = "Address cannot be empty")
    @Column(name = "address")
    private String address;

    @NotBlank(message = "City cannot be empty")
    @Column(name = "city")
    private String city;

    @Email(message = "Invalid email address")
    @NotBlank(message = "Email cannot be empty")
    @Column(name = "email", unique = true)
    private String email;

    @NotBlank(message = "Password cannot be empty")
    private String password;

    @NotNull(message = "Date created cannot be null")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_created")
    private Date dateCreated;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_modified")
    private Date dateModified;

    @NotNull(message = "User type cannot be null")
    @Column(name = "user_type")
    private Integer userType;

    @Column(name = "number_of_attempts")
    private Integer numberOfAttempts = 0;

    @Column(name = "blocked")
    private Boolean blocked = false;

    @PrePersist
    public void onCreate() {
        Date now = new Date();
        this.dateCreated = now;
        this.dateModified = now;
        this.numberOfAttempts = 0;
        this.blocked = false;
    }

    @PreUpdate
    public void onUpdate() {
        this.dateModified = new Date();
    }
}
