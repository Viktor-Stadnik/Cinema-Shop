package cinema.shop.controller;

import cinema.shop.dto.request.UserRequestDto;
import cinema.shop.dto.response.UserResponseDto;
import cinema.shop.model.User;
import cinema.shop.service.AuthenticationService;
import cinema.shop.service.mapper.ResponseDtoMapper;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final ResponseDtoMapper<UserResponseDto, User> userResponseDtoMapper;

    public AuthenticationController(AuthenticationService authenticationService,
                                    ResponseDtoMapper<UserResponseDto, User>
                                            userResponseDtoMapper) {
        this.authenticationService = authenticationService;
        this.userResponseDtoMapper = userResponseDtoMapper;
    }

    @PostMapping("/register")
    public UserResponseDto register(@RequestBody @Valid UserRequestDto requestDto) {
        User user = authenticationService.register(requestDto.getEmail(),
                requestDto.getPassword());
        return userResponseDtoMapper.mapToDto(user);
    }
}
