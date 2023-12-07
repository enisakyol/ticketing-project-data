package com.cydeo.service.impl;

import com.cydeo.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserService {
    List<UserDTO> listAllUsers(); //sonraki aşama, henüz aşağısı yok
    UserDTO findByUserName (String username);
    void save(UserDTO user);
    //    void deleteByUserName (String username);

        UserDTO update(UserDTO user);

        void delete(String username);

        List<UserDTO> listAllByRole(String role);

}
