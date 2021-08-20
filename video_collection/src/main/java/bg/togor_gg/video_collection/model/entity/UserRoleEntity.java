package bg.togor_gg.video_collection.model.entity;


import bg.togor_gg.video_collection.model.entity.enums.UserRole;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "roles")
public class UserRoleEntity extends BaseEntity {
    private UserRole role;

    public UserRoleEntity() {
    }

    @Enumerated(EnumType.STRING)
    public UserRole getRole() {
        return role;
    }

    public UserRoleEntity setRole(UserRole role) {
        this.role = role;
        return this;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        UserRoleEntity role = (UserRoleEntity) o;
//        return role == role.role;
//    }

    @Override
    public int hashCode() {
        return Objects.hash(role);
    }
}