package co.com.aca.ventas.e_commerce.controller;

import co.com.aca.ventas.e_commerce.dto.request.AuthenticationRequest;
import co.com.aca.ventas.e_commerce.dto.response.AuthenticationResponse;
import co.com.aca.ventas.e_commerce.service.impl.AuthenticationImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"http://localhost:4200"})
public class AuthenticationController {

    @Autowired
    private AuthenticationImpl authentication;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody @Valid AuthenticationRequest request){
        AuthenticationResponse jwtDTO = this.authentication.login(request);

        return ResponseEntity.ok(jwtDTO);
    }
}
