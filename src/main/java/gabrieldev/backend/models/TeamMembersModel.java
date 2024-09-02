package gabrieldev.backend.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "team_members")
public class TeamMembersModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private TeamModel team;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserModel user;

    @Column(name = "role")
    private String role;

    @Column(name = "joined_at")
    private LocalDateTime joinedAt;

    public TeamMembersModel() {
    }

    public TeamMembersModel(TeamModel team, UserModel user, String role, LocalDateTime joinedAt) {
        this.team = team;
        this.user = user;
        this.role = role;
        this.joinedAt = joinedAt;
    }

    @PrePersist
    public void onCreate() {
        this.joinedAt = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public TeamModel getTeam() {
        return team;
    }

    public void setTeam(TeamModel team) {
        this.team = team;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(LocalDateTime joinedAt) {
        this.joinedAt = joinedAt;
    }
}
