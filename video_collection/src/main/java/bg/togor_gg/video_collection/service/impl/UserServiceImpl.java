package bg.togor_gg.video_collection.service.impl;

import bg.togor_gg.video_collection.model.entity.UserEntity;
import bg.togor_gg.video_collection.model.entity.UserRoleEntity;
import bg.togor_gg.video_collection.model.entity.enums.UserRole;
import bg.togor_gg.video_collection.repository.UserRepository;
import bg.togor_gg.video_collection.repository.UserRoleRepository;
import bg.togor_gg.video_collection.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {


    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRoleRepository userRoleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void seedUsers() {

       if (userRepository.count()==0) {
            UserRoleEntity adminRole = new UserRoleEntity().setRole(UserRole.ADMIN);
            UserRoleEntity userRole = new UserRoleEntity().setRole(UserRole.USER);
            userRoleRepository.saveAll(List.of(adminRole, userRole));

        UserEntity admin = new UserEntity().setUsername("admin")
                .setPassword(passwordEncoder.encode("123456"));
            UserEntity user = new UserEntity().setUsername("user")
                    .setPassword(passwordEncoder.encode("123456"));


        admin.setRoles(List.of(adminRole,userRole));
        user.setRoles(List.of(userRole));
        userRepository.saveAll(List.of(admin,user));


        }
        }
}
