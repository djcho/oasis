package com.oasis.service.impl;

import com.oasis.common.constant.ErrorCode;
import com.oasis.data.entity.InvitationEntity;
import com.oasis.data.entity.MemberEntity;
import com.oasis.exception.CommonException;
import com.oasis.repository.InvitationRepository;
import com.oasis.repository.MemberRepository;
import com.oasis.security.InvitationTokenProvider;
import com.oasis.service.EmailService;
import com.oasis.service.InvitationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class InvitationServiceImpl implements InvitationService {

    private final InvitationRepository invitationRepository;
    private final MemberRepository memberRepository;
    private final InvitationTokenProvider invitationTokenProvider;
    private final EmailService emailService;

    @Override
    @Transactional
    public void sendInvitationLink(String email, String createdBy) {
        Date expiryDate = new Date(new Date().getTime() + 3600000); // 1 hour
        String invitationToken = invitationTokenProvider.genToken(expiryDate);

        MemberEntity member = memberRepository.findByUid(createdBy)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_MEMBER));
        //db 저장
        invitationRepository.save(InvitationEntity.builder()
                .token(invitationToken)
                .expirationTime(LocalDateTime.ofInstant(expiryDate.toInstant(), ZoneId.of("UTC")))
                .createdBy(member)
                .build());

        try {
            String invitationUrl = "http://localhost:8080/v1/signup?token=" + invitationToken;
            String content = "Oasis 초대 링크\n링크 : " + invitationUrl;
            emailService.sendEmail(email, "Oasis 초대 링크", content);
        }
        catch (MessagingException ex){
            throw new CommonException(ErrorCode.MESSAGING_ERROR);
        }
    }

    @Override
    public boolean validateInvitationToken(String token) {
        return invitationTokenProvider.verifyToken(token) && invitationRepository.existsByToken(token);
    }
}
