package com.freetube.userservice.controller;

import com.freetube.userservice.dto.UserDtoInfo;
import com.freetube.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.http.HttpRequest;

import static com.freetube.userservice.Utils.Root.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT)
@RequiredArgsConstructor
public class UserApi implements UserController{
    private final UserService userService;
    @Override
    public String register(Authentication authentication) {
        Jwt jwt = (Jwt) authentication.getPrincipal();
        final String tokenValue = jwt.getTokenValue();

        return userService.register("eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6ImE0SEk4czN3VnVPdnRwby1ueUFXZyJ9.eyJpc3MiOiJodHRwczovL2Rldi1qY3NrcXF2N3Q3MmZxbHYwLnVzLmF1dGgwLmNvbS8iLCJzdWIiOiJhdXRoMHw2NTVlMjIzNjAyMmE5ZmMzYmU5ZGUzOWMiLCJhdWQiOlsiaHR0cDovL2xvY2FsaG9zdDo3MTAwIiwiaHR0cHM6Ly9kZXYtamNza3Fxdjd0NzJmcWx2MC51cy5hdXRoMC5jb20vdXNlcmluZm8iXSwiaWF0IjoxNzAwOTkwNDE2LCJleHAiOjE3MDEwNzY4MTYsImF6cCI6InpQY09pNmtGbWlJclFFZDlydVBDcWJtdzFXbWlobVZlIiwic2NvcGUiOiJvcGVuaWQgcHJvZmlsZSBlbWFpbCJ9.J3izwFGswT_xye20mpahMGWmqAbr7nH_2WqB2Mf797T1H3cibshI4UhhYpIu90UVFU5u4iowOtZM-xpA8-BW0g35dlgJ2lViM60ff5gxKurRngoYQdjsaCsTWFii_lWqn4BEEYMFptVCvrxfDisLQnp4VOEEPlWBITzxDqrkMnWQlZoJUq8vqgnxN3rCZy6ekqu_1-rOVFGQUxcsDMCGU-MTpTX_AcoRI9N2cW7MzMHgF6uARKBNpvcu8LFREIYRpPt0ANjWBmwY4kv3imc5VuzsCg3_i4nj17pBQVqqoRET1GaFB-c3Gq4Yqa6iJ0g2CGaTbWHtyks6ocLHbG9DkA");

    }
}
