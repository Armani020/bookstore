package kz.halykacademy.bookstore.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public enum TokenDto {
    ;

    public enum Request {
        ;

        @Data
        public static class LoginRequest implements Login, Password {
            String login;
            String password;
        }

        @Data
        public static class RefreshJwtRequest implements RefreshToken {
            String refreshToken;
        }
    }

    public enum Response {
        ;

        @Data
        public static class TokenResponse implements Type, AccessToken, RefreshToken {
            String type = "Bearer";
            String accessToken;
            String refreshToken;

            public TokenResponse(String accessToken, String refreshToken) {
                this.accessToken = accessToken;
                this.refreshToken = refreshToken;
            }
        }
    }

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


}
