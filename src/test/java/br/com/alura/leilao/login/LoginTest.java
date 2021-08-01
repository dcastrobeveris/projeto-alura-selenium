package br.com.alura.leilao.login;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class LoginTest {
	
	private LoginPage paginaDeLogin;
	
	@BeforeEach
	public void beforeEach() {
		this.paginaDeLogin = new LoginPage();
	}
	
	@AfterEach
	public void afterEach() {
		this.paginaDeLogin.fechar();
	}
	
	@Test
	public void deveriaEfetuarLoginComDadosValidos() throws InterruptedException {
		paginaDeLogin.preencheFormularioDeLogin("fulano", "pass");
		paginaDeLogin.efetuaLogin();
		paginaDeLogin.dormir();
		
		Assert.assertFalse(paginaDeLogin.isPaginaDeLogin());
		//Assert.assertEquals("fulano", paginaDeLogin.getNomeUsuarioLogado());
	}
	
	@Test
	public void naoDeveriaLogarComDadosInvalidos() throws InterruptedException {
		paginaDeLogin.preencheFormularioDeLogin("invalido", "123");
		paginaDeLogin.efetuaLogin();
		paginaDeLogin.dormir();
		
		Assert.assertTrue(paginaDeLogin.isPaginaDeLoginComDadosInvalidos());
		Assert.assertNull(paginaDeLogin.getNomeUsuarioLogado());
		Assert.assertTrue(paginaDeLogin.contemTexto("Usuário e senha inválidos."));
	}
	
	@Test
	public void naoDeveriaAcessarPaginaRestritaSemEstarLogado() throws InterruptedException {
		paginaDeLogin.navegaParaPaginaDeLances();
		paginaDeLogin.dormir();
		
		Assert.assertTrue(paginaDeLogin.isPaginaDeLogin());
		Assert.assertFalse(paginaDeLogin.contemTexto("Dados do Leilão"));
	}

}
