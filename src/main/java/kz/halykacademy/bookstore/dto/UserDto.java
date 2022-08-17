package kz.halykacademy.bookstore.dto;

import kz.halykacademy.bookstore.entity.Role;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;

public enum UserDto {
    ;

    public enum Request {
        ;

        @Data
        public static class Create implements Login, Password {
            String login;
            String password;
        }

        @Data
        public static class Update implements Login, Password {
            String login;
            String password;
        }

        @Data
        public static class UpdateStatus implements Status {
            String status;
        }
    }

    public enum Response {
        ;

        @Data
        public static class Created implements Id, Login, Status, Roles {
            Long id;
            String login;
            String status;
            List<Role> roles;
        }

        @Data
        public static class All implements Id, Login, Status, Roles, Orders {
            Long id;
            String login;
            String status;
            List<Role> roles;
            List<OrderDto.Response.Slim> orders;
        }

        @Data
        public static class Slim implements Id, Login, Status {
            Long id;
            String login;
            String status;
        }
    }

    private interface Id {
        @Positive Long getId();
    }

    private interface Login {
        @NotBlank String getLogin();
    }

    private interface Password {
        @NotBlank String getPassword();
    }

    private interface Status {
        @NotBlank String getStatus();
    }

    private interface Roles {
        List<Role> getRoles();
    }

    private interface Orders {
        List<OrderDto.Response.Slim> getOrders();
    }
}
