package com.example.springbootthymeleaftw.service;

import com.example.springbootthymeleaftw.model.entity.RolesEnum;
import com.example.springbootthymeleaftw.model.entity.UserBusiness;
import com.example.springbootthymeleaftw.model.entity.UserBusinessEntity;
import com.example.springbootthymeleaftw.model.entity.UserEntity;
import com.example.springbootthymeleaftw.repository.UserBusinessRepository;
import com.example.springbootthymeleaftw.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class UserService implements UserDetailsService {


    private final UserRepository userRepository;
    private final UserBusinessRepository userBusinessRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    private List<UserBusiness> businessUsersList = new ArrayList<UserBusiness>();




    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<UserEntity> optUser = userRepository.findByEmail(email);
        if (optUser.isPresent()) {
            UserEntity appUser = optUser.get();
            return new User(
                    appUser.getEmail(), appUser.getPassword(), true, true, true, true,
                    new ArrayList<>(List.of(new SimpleGrantedAuthority(appUser.getRole().name())))
            );
        }
      throw new UsernameNotFoundException(email);
    }


    public void saveClient(UserEntity user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
    public void saveBusiness(UserBusinessEntity userBusiness, UserEntity user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        userBusinessRepository.save(userBusiness);
    }
    public void login(String email, String password){
        UserDetails userDetails = this.loadUserByUsername(email);
        if(Objects.isNull(userDetails))
            return;
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }


    public List<UserBusiness> getAllBusinessUsers(){

        List<UserEntity> businessUsers = new ArrayList<UserEntity>();
        UserBusiness userBusiness;
        businessUsersList.clear();
        businessUsers = getAllBusinessUsersFromUser(userRepository);

        for(var user:businessUsers){

            Optional<UserBusinessEntity> userBusinessEntity = userBusinessRepository.findByUserEntity(user);
            if(userBusinessEntity.get().getIsApproved()==false) {
                userBusiness = new UserBusiness();
                businessUsersList.add(getUserBusiness(userBusiness, userBusinessEntity, user));
            }
        }
        return businessUsersList;
    }

    private List<UserEntity> getAllBusinessUsersFromUser(UserRepository userRepository){

        List<UserEntity> businessUsers = new ArrayList<UserEntity>();
        List<UserEntity> bbUsers = userRepository.getAllByRole(RolesEnum.BUSINESS_TO_BUSINESS);
        List<UserEntity> bcUsers = userRepository.getAllByRole(RolesEnum.BUSINESS_TO_CUSTOMER);
        businessUsers.addAll(bbUsers);
        businessUsers.addAll(bcUsers);

        return businessUsers;
    }

    private UserBusiness getUserBusiness(UserBusiness userBusiness, Optional<UserBusinessEntity> userBusinessEntity, UserEntity user){

        userBusiness.setCompanyName(userBusinessEntity.get().getCompanyName());
        userBusiness.setCompanyAddress(userBusinessEntity.get().getCompanyAddress());
        userBusiness.setCompanyIdentificationCode(userBusinessEntity.get().getCompanyIdentificationCode());
        userBusiness.setIsApproved(userBusinessEntity.get().getIsApproved());
        userBusiness.setId(user.getId());
        userBusiness.setEmail(user.getEmail());
        userBusiness.setRole(user.getRole().name());

        return userBusiness;

    }

    public void userIsAccepted(Long id){
        Optional<UserEntity> user = userRepository.findById(id);
        Optional<UserBusinessEntity> userBusiness = userBusinessRepository.findByUserEntity(user.get());
        UserBusinessEntity userBusinessEntity = userBusiness.get();
        userBusinessEntity.setIsApproved(true);
        userBusinessRepository.save(userBusinessEntity);
    }

    public void userIsDeclined(Long id){
        Optional<UserEntity> user = userRepository.findById(id);
        UserEntity userEntity = user.get();

        Optional<UserBusinessEntity> userBusiness = userBusinessRepository.findByUserEntity(user.get());
        UserBusinessEntity userBusinessEntity = userBusiness.get();
        userBusinessEntity.setIsApproved(true);

        userBusinessRepository.delete(userBusinessEntity);
        userRepository.delete(userEntity);
    }

    public boolean isAcceptedForLogin(UserEntity userEntity){
        Optional<UserBusinessEntity> userBusinessEntity = userBusinessRepository.findByUserEntity(userEntity);
        if(userBusinessEntity.get().getIsApproved() == true)
            return true;
        return false;
    }

}
