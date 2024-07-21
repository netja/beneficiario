package com.jardim.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.Valid;
import jakarta.persistence.OrderBy;

@Entity
public class Beneficiario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotBlank
    @NotNull
    @Length(min = 5, max = 50)
    @Column(length = 50, nullable = false)    
    private String nome;
    
    @NotBlank
    @NotNull
    @Length(min = 8, max = 20)
    @Column(length = 20, nullable = false)    
    private String telefone;
    
    @NotNull
    @Column(nullable = true)    
    private Date dataNascimento;
    
    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Date dataInclusao;
    
    private Date dataAtualizacao;
    
    @NotNull
    @NotEmpty
    @Valid
    @OneToMany(mappedBy = "beneficiario", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("id")
    private Set<Documento> documentos = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	
	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public Set<Documento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(Set<Documento> documentos) {
		if (documentos == null) {
            throw new IllegalArgumentException("Documentos cannot be null.");
        }
        this.documentos.clear();
		this.documentos = documentos;
	}
	
	public void addDocumento(Documento documento) {
        if (documento == null) {
            throw new IllegalArgumentException("Documento cannot be null.");
        }
        documento.setBeneficiario(this);
        this.documentos.add(documento);
    }
	
	public void removeDocumento(Documento documento) {
        if (documento == null) {
            throw new IllegalArgumentException("Documento cannot be null.");
        }
        documento.setBeneficiario(null);
        this.documentos.remove(documento);
    }
    
}