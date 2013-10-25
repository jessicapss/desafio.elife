package br.com.elife.jdbc.model;

import java.util.Calendar;

public class User {

  private Long id;
  private String nome;
  private String endereco;  
  private Calendar dataNascimento;
  private String pais;
  private String email;

  // métodos get e set para id, nome, email, endereço e dataNascimento

  public String getNome() {
    return this.nome;
  }
  public void setNome(String novo) {
    this.nome = novo;
  }

  public String getEmail() {
    return this.email;
  }
  public void setEmail(String novo) {
    this.email = novo;
  }

  public String getEndereco() {
    return this.endereco;
  }
  public void setEndereco(String novo) {
    this.endereco = novo;
  }

  public Long getId() {
    return this.id;
  }
  public void setId(Long novo) {
    this.id = novo;
  }

  public Calendar getDataNascimento() {
    return this.dataNascimento;
  }
  public void setDataNascimento(Calendar dataNascimento) {
    this.dataNascimento = dataNascimento;
  }
  public String getPais() {
	return pais;
  }
  public void setPais(String pais) {
	this.pais = pais;
  }
}