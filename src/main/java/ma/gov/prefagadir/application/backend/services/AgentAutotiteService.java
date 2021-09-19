package ma.gov.prefagadir.application.backend.services;

import ma.gov.prefagadir.application.backend.models.AgentAutorite;
import ma.gov.prefagadir.application.backend.repository.AgentAutoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgentAutotiteService {

    @Autowired
    private AgentAutoriteRepository agentAutoriteRepository;

    public AgentAutorite save(AgentAutorite agentAutorite){
        return agentAutoriteRepository.save(agentAutorite);
    }

}
