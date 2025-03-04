package com.example.carwash.service;

import com.example.carwash.constant.Role;
import com.example.carwash.dto.MemberDTO;
import com.example.carwash.entity.Member;
import com.example.carwash.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService, UserDetailsService {

    private final MemberRepository memberRepository;
    private ModelMapper modelMapper = new ModelMapper();
    private final PasswordEncoder passwordEncoder;


    @Override
    public String signUp(MemberDTO memberDTO) {

        //signup진입시 이메일로 가입여부 확인 먼저 실행
        validateDuplicateMember(memberDTO.getEmail());


        //dto를 entity로 변환
        Member member = modelMapper.map(memberDTO, Member.class);

        member.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
        log.info("encoding된 패스워드 반환값 " + member.getPassword());

        member.setRole(Role.ADMIN);

        member =
            memberRepository.save(member);

        memberDTO =
            modelMapper.map(member, MemberDTO.class);

        return memberDTO.getName();
    }

    private void validateDuplicateMember(String email) {
        Member member =
            memberRepository.findByEmail(email);

        if (member != null) {
            log.info("이미 가입한 회원압니다.");
            throw new IllegalStateException("예외처리 :: 이미 가입된 회원입니다.");
        } else {
            log.info("가입되지 않은 회원압니다.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member =
                memberRepository.findByEmail(email);

        if (member == null) {
            log.info("현재 입력하신 email로는 가입된 정보가 없습니다.");
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다."); //차후에 ㄱㄱ
        }
        log.info("로그인 시도하는 사람 :  " + member);

        log.info("로그인 하는 사람의 권한" + member.getRole().name());

        String role = "";

        if (member.getRole().name().equals(Role.ADMIN.name())) {
            log.info("관리자 로그인 시도중");

            role = member.getRole().name();
        } else {
            log.info("일반 유저 로그인 시도중");
            role = member.getRole().name();
        }
        UserDetails user =
                User.builder().username(
                                member.getEmail())
                        .password(member.getPassword())
                        .roles(role)
                        .build();
        return user;
    }
}


