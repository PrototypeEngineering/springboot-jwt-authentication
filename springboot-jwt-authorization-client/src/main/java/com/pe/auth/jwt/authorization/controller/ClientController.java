package com.pe.auth.jwt.authorization.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ClientController {

	@GetMapping("/isHealthy")
	public String serverStatus() {
		return "Running";
	}

}
