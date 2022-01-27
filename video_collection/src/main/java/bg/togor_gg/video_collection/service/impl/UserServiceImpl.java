package bg.togor_gg.video_collection.service.impl;

import bg.togor_gg.video_collection.model.entity.UserEntity;
import bg.togor_gg.video_collection.model.entity.UserRoleEntity;
import bg.togor_gg.video_collection.model.entity.enums.UserRole;
import bg.togor_gg.video_collection.model.service.UserRegistrationServiceModel;
import bg.togor_gg.video_collection.repository.UserRepository;
import bg.togor_gg.video_collection.repository.UserRoleRepository;
import bg.togor_gg.video_collection.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {


    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final VideoCollectionUserService videoCollectionUserService;

    public UserServiceImpl(UserRoleRepository userRoleRepository, UserRepository userRepository,
                           PasswordEncoder passwordEncoder, ModelMapper modelMapper,
                           VideoCollectionUserService videoCollectionUserService) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.videoCollectionUserService = videoCollectionUserService;
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

    @Override
    public void registerAndLoginUser(UserRegistrationServiceModel serviceModel) {
UserEntity newUser = modelMapper.map(serviceModel,UserEntity.class);
        newUser.setPassword(passwordEncoder.encode(serviceModel.getPassword()));

       UserRoleEntity userRole = userRoleRepository.
                findByRole(UserRole.USER).orElseThrow(
                () -> new IllegalStateException("USER role not found. Please seed the roles."));
        newUser.addRole(userRole);
        newUser = userRepository.save(newUser);

        UserDetails principal = videoCollectionUserService.loadUserByUsername(newUser.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                newUser.getPassword(),
                principal.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

    }

    @Override
    public boolean userNameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}
