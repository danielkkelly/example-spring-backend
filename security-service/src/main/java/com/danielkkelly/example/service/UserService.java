package com.danielkkelly.example.service;

import java.util.List;

import javax.inject.Inject;

import com.danielkkelly.example.domain.dto.UserCreateRequest;
import com.danielkkelly.example.domain.exception.NotFoundException;
import com.danielkkelly.example.domain.exception.ValidationException;
import com.danielkkelly.example.domain.mapper.UserEditMapper;
import com.danielkkelly.example.domain.model.SecurityPermission;
import com.danielkkelly.example.domain.model.SecurityRole;
import com.danielkkelly.example.domain.model.User;
import com.danielkkelly.example.repository.jpa.UserRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User> implements UserDetailsService {
   @Inject
   private UserRepository repository;

   @Inject
   private UserEditMapper userEditMapper;

   @Inject
   private PasswordEncoder passwordEncoder;

   @Override
   protected JpaRepository<User, Long> getRepository() {
      return repository;
   }

   public User create(final UserCreateRequest request) {
      if (repository.findByUsername(request.username()).isPresent()) {
         throw new ValidationException("username exists");
      }

      if (!request.password().equals(request.rePassword())) {
         throw new ValidationException("passwords don't match");
      }

      User user = userEditMapper.create(request);
      user.setPassword(passwordEncoder.encode(request.password()));

      return this.create(user);
   }

   public User findByUsername(final String username) {
      return repository.findByUsername(username)
               .orElseThrow(
                  () -> new UsernameNotFoundException(
                     String.format("user with username - %s, not found", username)));
   }

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User user = this.findByUsername(username);

      List<SecurityPermission> securityPermissions = 
         findSecurityPermissions(user.getId());

      securityPermissions.forEach(permission -> {
         user.getAuthorities().add(
            new SimpleGrantedAuthority(permission.getName()));
      });

      return user;
   }

   public List<SecurityRole> findSecurityRoles(Long id) {
      User user = repository.findById(id).orElseThrow(
         () -> new NotFoundException(id));
      return user.getSecurityRoles();
   }

   public List<SecurityPermission> findSecurityPermissions(Long id) {
      return repository.findSecurityPermissions(id)
            .orElseThrow(
               () -> new NotFoundException(id));
   }

   /*
   public UserSecurityRole addSecurityRole(final Long id, final Long securityRoleId) {
      
      User user = this.findById(id);
      SecurityRole securityRole = securityRoleService.findById(securityRoleId);

      UserSecurityRole userSecurityRole = new UserSecurityRole();
      userSecurityRole.setUser(user);
      userSecurityRole.setSecurityRole(securityRole);

      return userSecurityRoleService.create(userSecurityRole);
   } */
}