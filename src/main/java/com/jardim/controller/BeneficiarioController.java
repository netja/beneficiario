package com.jardim.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


import com.jardim.dto.BeneficiarioDTO;
import com.jardim.dto.BeneficiarioRequestDTO;
import com.jardim.service.BeneficiarioService;

@Validated
@RestController
@RequestMapping("/api/beneficiario")
public class BeneficiarioController {
	
	private final BeneficiarioService beneficiarioService;

    public BeneficiarioController(BeneficiarioService beneficiarioService) {
        this.beneficiarioService = beneficiarioService;
    }
	
	@GetMapping
    public  List<BeneficiarioDTO> findAll() {
    	return beneficiarioService.findAll();
    }
	
	@PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public BeneficiarioDTO create(@RequestBody @Valid BeneficiarioRequestDTO beneficiario) {
		return beneficiarioService.create(beneficiario);
    }
	
	@GetMapping("/{id}")
    public BeneficiarioDTO findById(@PathVariable @Positive @NotNull Long id) {
		return beneficiarioService.findById(id);
    }

    @PutMapping(value = "/{id}")
    public BeneficiarioDTO update(@PathVariable @Positive @NotNull Long id,
            @RequestBody @Valid BeneficiarioRequestDTO beneficiario) {
    	return beneficiarioService.update(id, beneficiario);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @Positive @NotNull Long id) {
        beneficiarioService.delete(id);
    }
}
