package bg.togor_gg.video_collection;

import bg.togor_gg.video_collection.model.entity.UserRoleEntity;
import bg.togor_gg.video_collection.model.entity.enums.UserRole;
import bg.togor_gg.video_collection.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VideoCollectionApplicationInit implements CommandLineRunner {

    private final UserService userService;

    public VideoCollectionApplicationInit(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
userService.seedUsers();
    }
}
