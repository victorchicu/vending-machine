package challenge_1.web.auditors;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            return Optional.empty();
        }
        return Optional.of(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}