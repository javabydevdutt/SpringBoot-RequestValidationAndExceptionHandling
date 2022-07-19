package com.devdutt.api.service;

import com.devdutt.api.dao.UserRepository;
import com.devdutt.api.dto.UserDTO;
import com.devdutt.api.entity.UserEntity;
import com.devdutt.api.exception.UserNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public int registerUser(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDTO, userEntity);
        userEntity = userRepository.save(userEntity);
        return userEntity.getUserId();
    }

    public List<UserDTO> fetchAllUser() {
        List<UserEntity> userEntityList = null;
        //create UserDTO
        List<UserDTO> userDTOList = new ArrayList<>();

        //call dao method
        userEntityList = userRepository.findAll();
        userEntityList.forEach(userEntity -> {
            UserDTO dto = new UserDTO();
            BeanUtils.copyProperties(userEntity, dto);
            userDTOList.add(dto);
        });
        return userDTOList;
    }

    public UserDTO fetchUserById(int id) {
        UserDTO userDTO = new UserDTO();
        UserEntity userEntity = null;
        //call service method
        userEntity = userRepository.findByUserId(id).orElseThrow(() -> new UserNotFoundException("User '" + id + "' not existed"));
        BeanUtils.copyProperties(userEntity, userDTO);
        return userDTO;
    }
}
