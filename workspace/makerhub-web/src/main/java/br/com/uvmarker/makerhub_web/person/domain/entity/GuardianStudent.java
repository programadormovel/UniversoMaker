package br.com.uvmarker.makerhub_web.person.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "guardian_student")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuardianStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "guardian_id")
    private Person guardian;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Person student;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "relationship_id")
    private RelationshipType relationship;

    @Size(max = 400)
    private String notes;

    @Builder.Default
    private Boolean isActive = true;

    @NotNull
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.isActive = true;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
