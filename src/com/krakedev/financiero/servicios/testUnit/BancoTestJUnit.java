package com.krakedev.financiero.servicios.testUnit;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.krakedev.financiero.servicios.Banco;
import com.krakedev.financiero.entidades.Cliente;
import com.krakedev.financiero.entidades.Cuenta;

public class BancoTestJUnit {
	
	@Test
	public void testCrearCuenta() {

	    Banco banco = new Banco();
	    Cliente cliente = new Cliente("123", "Ale", "Lopez");

	    Cuenta c1 = banco.crearCuenta(cliente);
	    Cuenta c2 = banco.crearCuenta(cliente);

	    assertEquals("1000", c1.getId());
	    assertEquals("1001", c2.getId());
	}
	
	@Test
	public void testDepositar() {

	    Banco banco = new Banco();
	    Cliente cliente = new Cliente("123", "Ale", "Lopez");
	    Cuenta cuenta = banco.crearCuenta(cliente);

	    boolean resultado = banco.depositar(100, cuenta);

	    assertTrue(resultado);
	    assertEquals(100.0, cuenta.getSaldoActual(), 0.001);
	}
	
	@Test
	public void testRetirar() {

	    Banco banco = new Banco();
	    Cliente cliente = new Cliente("123", "Ale", "Lopez");
	    Cuenta cuenta = banco.crearCuenta(cliente);

	    banco.depositar(200, cuenta);

	    boolean resultado = banco.retirar(100, cuenta);

	    assertTrue(resultado);
	    assertEquals(100.0, cuenta.getSaldoActual());
	}
	
	@Test
	public void testTransferir() {

	    Banco banco = new Banco();
	    Cliente cliente = new Cliente("123", "Ale", "Lopez");

	    Cuenta c1 = banco.crearCuenta(cliente);
	    Cuenta c2 = banco.crearCuenta(cliente);

	    banco.depositar(200, c1);

	    boolean resultado = banco.transferir(c1, c2, 100);

	    assertTrue(resultado);
	    assertEquals(100.0, c1.getSaldoActual());
	    assertEquals(100.0, c2.getSaldoActual());
	}
	
	//test de casos negativos 
	
	@Test
	public void testDepositoInvalido() {
	    Banco banco = new Banco();
	    Cliente cliente = new Cliente("123", "Ale", "Lopez");
	    Cuenta cuenta = banco.crearCuenta(cliente);

	    boolean resultado = banco.depositar(-50, cuenta);

	    assertFalse(resultado);
	    assertEquals(0.0, cuenta.getSaldoActual(), 0.001);
	}
	
	@Test
	public void testRetiroInvalido() {
	    Banco banco = new Banco();
	    Cliente cliente = new Cliente("123", "Ale", "Lopez");
	    Cuenta cuenta = banco.crearCuenta(cliente);

	    banco.depositar(100, cuenta);

	    boolean resultado = banco.retirar(200, cuenta);

	    assertFalse(resultado);
	    assertEquals(100.0, cuenta.getSaldoActual(), 0.001);
	}
	
	@Test
	public void testTransferenciaFallida() {
	    Banco banco = new Banco();
	    Cliente cliente = new Cliente("123", "Ale", "Lopez");

	    Cuenta c1 = banco.crearCuenta(cliente);
	    Cuenta c2 = banco.crearCuenta(cliente);

	    banco.depositar(50, c1);

	    boolean resultado = banco.transferir(c1, c2, 100);

	    assertFalse(resultado);
	    assertEquals(50.0, c1.getSaldoActual(), 0.001);
	    assertEquals(0.0, c2.getSaldoActual(), 0.001);
	}
}
