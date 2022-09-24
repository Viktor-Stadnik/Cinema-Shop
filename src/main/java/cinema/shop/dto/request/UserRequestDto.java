package cinema.shop.dto.request;

import cinema.shop.lib.FieldsValueMatch;
import cinema.shop.lib.ValidEmail;
import javax.validation.constraints.Size;
import lombok.Getter;

@FieldsValueMatch(
        field = "password",
        fieldMatch = "repeatPassword",
        message = "Passwords do not match!"
)
@Getter
public class UserRequestDto {
    @ValidEmail
    private String email;
    @Size(min = 8, max = 40)
    private String password;
    private String repeatPassword;
}
