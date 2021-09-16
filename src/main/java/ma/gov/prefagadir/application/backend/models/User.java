package ma.gov.prefagadir.application.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String username;

    @NotBlank
    @JsonIgnore
    private String password;

    @JsonIgnore
    private String secret;

    private boolean active;

    @OneToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @OneToOne
    @JoinColumn(name = "agent_autorite_id")
    private AgentAutorite agentAutorite;

    public User() {
    }

    public User(String username, String password, String secret, boolean active, Profile profile) {
        this.username = username;
        this.password = password;
        this.secret = secret;
        this.active = active;
        this.profile = profile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public AgentAutorite getAgentAutorite() {
        return agentAutorite;
    }

    public void setAgentAutorite(AgentAutorite agentAutorite) {
        this.agentAutorite = agentAutorite;
    }
}
