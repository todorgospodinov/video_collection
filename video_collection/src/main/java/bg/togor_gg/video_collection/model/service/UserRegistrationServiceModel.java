package bg.togor_gg.video_collection.model.service;

public class UserRegistrationServiceModel {
    private String username;
    private String email;
    private String password;
    private String fullName;

    public String getUsername() {
        return username;
    }

    public UserRegistrationServiceModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegistrationServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegistrationServiceModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserRegistrationServiceModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }
}
