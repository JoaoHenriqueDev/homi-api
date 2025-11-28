package app.Homi.HomiApp.service;

import app.Homi.HomiApp.repository.familyRepository;
import app.Homi.HomiApp.repository.inviteRepository;
import app.Homi.HomiApp.repository.userRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class familyService {
    private final familyRepository familyRepository;
    private final userRepository userRepository;
    private final inviteRepository inviteRepository;


}
