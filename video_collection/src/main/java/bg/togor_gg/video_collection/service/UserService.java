package bg.togor_gg.video_collection.service;

import bg.togor_gg.video_collection.model.service.UserRegistrationServiceModel;

public interface UserService {

    void seedUsers();

    default void registerAndLoginUser(UserRegistrationServiceModel serviceModel){

    }
}
