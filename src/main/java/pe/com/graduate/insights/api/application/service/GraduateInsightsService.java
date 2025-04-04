package pe.com.graduate.insights.api.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.com.graduate.insights.api.application.ports.generic.GenericCreate;
import pe.com.graduate.insights.api.domain.models.request.GraduateRequest;
import pe.com.graduate.insights.api.infrastructure.adapter.repository.GraduateRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class GraduateInsightsService implements GenericCreate<GraduateRequest> {

  private final GraduateRepository graduateRepository;

  @Override
  public void save(GraduateRequest request) {}
}
