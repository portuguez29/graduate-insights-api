package pe.com.graduate.insights.api.infrastructure.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pe.com.graduate.insights.api.application.ports.input.UserUseCase;
import pe.com.graduate.insights.api.domain.models.request.UserRequest;
import pe.com.graduate.insights.api.domain.models.response.ApiResponse;
import pe.com.graduate.insights.api.domain.models.response.UserResponse;
import pe.com.graduate.insights.api.domain.utils.ResponseUtils;
import pe.com.graduate.insights.api.infrastructure.repository.mapper.PaginateMapper;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

  private final UserUseCase userUseCase;
  private final PaginateMapper paginateMapper;

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<UserResponse>> getUser(@PathVariable Long id) {
    return ResponseUtils.successResponse(userUseCase.getDomain(id));
  }

  @PostMapping
  public ResponseEntity<ApiResponse<Void>> saveUser(@RequestBody UserRequest userRequest) {
    userUseCase.save(userRequest);
    return ResponseUtils.sucessCreateResponse();
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<Void>> updateUser(
      @RequestBody UserRequest userRequest, @PathVariable Long id) {
    userUseCase.update(userRequest, id);
    return ResponseUtils.successUpdateResponse();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id) {
    userUseCase.delete(id);
    return ResponseUtils.successDeleteResponse();
  }

  @GetMapping
  ResponseEntity<ApiResponse<List<UserResponse>>> getListUserPaginate(
      @RequestParam(value = "search", defaultValue = "") String search,
      @RequestParam(value = "page", defaultValue = "1") String page,
      @RequestParam(value = "size", defaultValue = "10") String size) {
    Pageable pageable = PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(size));
    Page<UserResponse> userPage = userUseCase.getPagination(search, pageable);
    return ResponseUtils.successResponsePaginate(
        userPage.getContent(), paginateMapper.toDomain(userPage));
  }

  @GetMapping("/list")
  public ResponseEntity<ApiResponse<List<UserResponse>>> getListLocationWithoutPages() {
    return ResponseUtils.successResponse(userUseCase.getList());
  }
}
