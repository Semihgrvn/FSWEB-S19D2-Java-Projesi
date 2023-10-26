package com.workintech.security.secureApp.service;

import com.workintech.security.secureApp.entity.Member;
import com.workintech.security.secureApp.entity.Role;
import com.workintech.security.secureApp.repository.MemeberRepository;
import com.workintech.security.secureApp.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthenticationService {

    private MemeberRepository memeberRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(MemeberRepository memeberRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.memeberRepository = memeberRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Member register(String email, String password) {

        Optional<Member> foundMember = memeberRepository.findMemberByEmail(email);
        if (foundMember.isPresent()) {
            return null;
        }

        String encodedPassword = passwordEncoder.encode(password);
        Role memberRole = roleRepository.findByAuthority("USER").get();
        Set<Role> roles = new HashSet<>();
        roles.add(memberRole);

        Member member = new Member();
        member.setEmail(email);
        member.setPassword(encodedPassword);
        member.setAuthorities(roles);
        return memeberRepository.save(member);
    }

}
