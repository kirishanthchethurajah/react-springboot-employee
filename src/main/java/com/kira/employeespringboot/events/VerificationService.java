/*
package com.kira.employeespringboot.events;

import com.kira.employeespringboot.model.Verification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VerificationService {
    private final VerificationCodeRepository repository;

    public String getVerifictionIdByUsername(String username) {
        Verification verification = repository.findByUsername(username);
        if(verification != null) {
            return verification.getId();
        }
        return null;
    }

    public String createVerification(String username) {
        if (!repository.existsByUserName(username)) {
            Verification verification = new Verification(username);
            verification = repository.save(verification);
            return verification.getId();
        }
        return getVerifictionIdByUsername(username);
    }

    public String getUsernameForId(String id) {
        Optional<Verification> verification = repository.findById(id);
        if(verification.isPresent()) {
            return verification.get().getUserName();
        }
        return null;
    }
}
*/
