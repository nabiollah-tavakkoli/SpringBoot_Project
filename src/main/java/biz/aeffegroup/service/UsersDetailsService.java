package biz.aeffegroup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import biz.aeffegroup.entity.Users;
import biz.aeffegroup.model.UsersPrincipal;
import biz.aeffegroup.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UsersDetailsService implements UserDetailsService {
	
	@Autowired
	private UsersRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userRepository.findByUsername(username);
		
		if(user == null) {
			log.error("username = " + username +", not found");
			throw new UsernameNotFoundException(username);
		}
		
		return new UsersPrincipal(user);
	}
	
	public List<Users> fetchUsers(){
		List<Users> usersList = userRepository.findAll();
		return usersList;
	}


}
