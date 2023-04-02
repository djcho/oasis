package com.oasis.service;

public interface InvitationService {
    void sendInvitationLink(String email, String createdBy);

    boolean validateInvitationToken(String token);
}

