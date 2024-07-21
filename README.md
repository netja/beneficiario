<h1 align="center"> API REST para manter o cadastro de beneficiários de um plano de saúde - SpringBoot</h1>

<p align="center">
  <a href="#-swagger">Swagger</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-endpoints">Lista de endpoints</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-projeto">Desenvolvimento</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-build">Build</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
</p>

## 🚀 | swagger
	/swagger-ui/
## 🚀 | endpoints

Esse projeto foi desenvolvido com as seguintes tecnologias:

**Lista de endpoints**     
- POST - Cadastrar um beneficiário junto com seus documentos - /api/beneficiario
- GET - Listar todos os beneficiários cadastrados - /api/beneficiario
- GET - Listar todos os documentos de um beneficiário a partir de seu id - /api/beneficiario/3
- PUT - Atualizar os dados cadastrais de um beneficiário - /api/beneficiario/1
- DELETE - Remover um beneficiário - /api/beneficiario/1

**BACK END**
- Java
- SpringBoot
- Spring Security

**BANCO DE DADOS**
- Embarcado - H2

## 💻 | Projeto

Essa é uma aplicação utilizando Java e Spring Boot que forneça uma API REST para manter o cadastro de beneficiários de um plano de saúde
com as funções de listar, cadastrar, editar e remover,
Autenticação com Spring Security!😉

## 📌 | Extras
- Validação de Nome duplicado

## 🔖 | Build

- Para executar a aplicação, basta invocar o Maven com mvn spring-boot:run

---
