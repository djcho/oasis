package com.oasis.service.impl;

import com.oasis.common.constant.ErrorCode;
import com.oasis.data.entity.InvitationEntity;
import com.oasis.exception.CommonException;
import com.oasis.repository.InvitationRepository;
import com.oasis.security.InvitationTokenProvider;
import com.oasis.service.EmailService;
import com.oasis.service.InvitationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;

@Service
@RequiredArgsConstructor
public class InvitationServiceImpl implements InvitationService {

    private final InvitationRepository invitationRepository;
    private final InvitationTokenProvider invitationTokenProvider;
    private final EmailService emailService;

    @Override
    @Transactional
    public void sendInvitationLink(String email) {
        String invitationLink = "http://localhost:8080/api/v1/members?token=" + invitationTokenProvider.genToken();
        //db 저장
        invitationRepository.save(InvitationEntity.builder()
                .link(invitationLink)
                .build());

        try {
            String content = "Oasis 초대 링크\n링크 : " + invitationLink;
            emailService.sendEmail(email, "Oasis 초대 링크", content);
        }
        catch (MessagingException ex){
            throw new CommonException(ErrorCode.MESSAGING_ERROR);
        }
    }
}
