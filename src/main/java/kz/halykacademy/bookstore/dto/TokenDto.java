package kz.halykacademy.bookstore.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public enum TokenDto {
    ;

    private interface Login {
        @NotNull @NotBlank String getLogin();
    }

    private interface Password {
        @NotNull @NotBlank String getPassword();
    }

    private interface Type {
        @NotNull @NotBlank String getType();
    }

    private interface AccessToken {
        @NotNull @NotBlank String getAccessToken();
    }

    private interface RefreshToken {
        @NotNull @NotBlank String getRefreshToken();
    }

    @Data
    public static class Request implements Login, Password {
        String login;
        String password;
    }

    @Data
    public static class Response implements Type, AccessToken, RefreshToken {
        String type = "Bearer";
        String accessToken;
        String refreshToken;
    }
}
