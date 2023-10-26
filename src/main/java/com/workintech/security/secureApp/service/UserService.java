package com.workintech.security.secureApp.service;

import com.workintech.security.secureApp.repository.MemeberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private MemeberRepository memeberRepository;

    @Autowired
    public UserService(MemeberRepository memeberRepository) {
        this.memeberRepository = memeberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memeberRepository.findMemberByEmail(username).
                orElseThrow(()->  new UsernameNotFoundException("Member is not valid"));
    }
}
