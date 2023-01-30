package com.keonah.stanbytest.service;

import com.keonah.stanbytest.dto.MemberCreateDto;
import com.keonah.stanbytest.dto.SignupDto;

public interface MemberService {
    void createMember(MemberCreateDto memberCreateDto);

    boolean checkNameAndTeamDuplication(String name, String team);
}
