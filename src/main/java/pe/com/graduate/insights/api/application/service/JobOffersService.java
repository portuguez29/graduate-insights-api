package pe.com.graduate.insights.api.application.service;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pe.com.graduate.insights.api.application.ports.input.JobOffersUseCase;
import pe.com.graduate.insights.api.application.ports.output.JobOffersRepositoryPort;
import pe.com.graduate.insights.api.domain.models.request.JobOffersRequest;
import pe.com.graduate.insights.api.domain.models.response.JobOffersResponse;

@Service
@AllArgsConstructor
public class JobOffersService implements JobOffersUseCase {

  private final JobOffersRepositoryPort jobOffersRepositoryPort;

  @Override
  public List<JobOffersResponse> getJobsList(Long employerId) {
    return jobOffersRepositoryPort.getJobsList(employerId);
  }

  @Override
  public JobOffersResponse getDomain(Long employerId, Long jobOfferId) {
    return jobOffersRepositoryPort.getDomain(employerId, jobOfferId);
  }

  @Override
  public void save(JobOffersRequest jobOffersRequest, Long employerId) {
    jobOffersRepositoryPort.save(jobOffersRequest, employerId);
  }

  @Override
  public void update(JobOffersRequest jobOffersRequest, Long employerId, Long jobOfferId) {
    jobOffersRepositoryPort.update(jobOffersRequest, employerId, jobOfferId);
  }

  @Override
  public void delete(Long employerId, Long jobOfferId) {
    jobOffersRepositoryPort.delete(employerId, jobOfferId);
  }

  @Override
  public Page<JobOffersResponse> getPagination(String search, Pageable pageable, Long employerId) {
    return jobOffersRepositoryPort.getPagination(search, pageable, employerId);
  }
}
