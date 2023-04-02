package com.oasis.controller;

import com.oasis.service.InvitationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/v1")
public class SignUpController {
    private final InvitationService invitationService;

    @GetMapping("/signup")
    public String showSignupForm(@RequestParam String token, Model model) {
        if (!invitationService.validateInvitationToken(token)) {
            return "exception";
        }
        model.addAttribute("token", token);
        return "signup";
    }
}
