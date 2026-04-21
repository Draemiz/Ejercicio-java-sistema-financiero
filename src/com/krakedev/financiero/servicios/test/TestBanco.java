package com.krakedev.financiero.servicios.test;

import com.krakedev.financiero.entidades.Cliente;
import com.krakedev.financiero.entidades.Cuenta;
import com.krakedev.financiero.servicios.Banco;

public class TestBanco {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Banco banco = new Banco();

		Cliente cliente = new Cliente("123", "Ale", "Moreno");

		Cuenta c1 = banco.crearCuenta(cliente);

		System.out.println("----ESTADO INICIAL----");

		System.out.println("ID: " + c1.getId());
		System.out.println("Saldo: " + c1.getSaldoActual());
		System.out.println("Tipo: " + c1.getTipo());
		System.out.println("Cédula: " + c1.getPropietario().getCedula());
		System.out.println("Nombre: " + c1.getPropietario().getNombre());
		System.out.println("Apellido: " + c1.getPropietario().getApellido());

		// Depósito 1
		boolean resultado = banco.depositar(100, c1);
		System.out.println("\nDepósito 100: " + resultado);
		System.out.println("Saldo: " + c1.getSaldoActual());

		// Depósito inválido
		resultado = banco.depositar(-100, c1);
		System.out.println("\nDepósito -100: " + resultado);
		System.out.println("Saldo: " + c1.getSaldoActual());

		// Depósito 2
		resultado = banco.depositar(80, c1);
		System.out.println("\nDepósito 80: " + resultado);
		System.out.println("Saldo: " + c1.getSaldoActual());

		// Retiro válido
		resultado = banco.retirar(50, c1);
		System.out.println("\nRetiro 50: " + resultado);
		System.out.println("Saldo: " + c1.getSaldoActual());

		// Retiro inválido
		resultado = banco.retirar(500, c1);
		System.out.println("\nRetiro 500: " + resultado);
		System.out.println("Saldo: " + c1.getSaldoActual());


		// Segunda cuenta
		Cuenta c2 = banco.crearCuenta(cliente);

		banco.depositar(200, c1);

		System.out.println("\n---ANTES DE TRANSFERIR---");
		System.out.println("Cuenta 1 saldo: " + c1.getSaldoActual());
		System.out.println("Cuenta 2 saldo: " + c2.getSaldoActual());

		// Transferencia
		resultado = banco.transferir(c1, c2, 100);

		System.out.println("\nTransferencia 100: " + resultado);

		System.out.println("\n---DESPUÉS DE TRANSFERIR---");
		System.out.println("Cuenta 1 saldo: " + c1.getSaldoActual());
		System.out.println("Cuenta 2 saldo: " + c2.getSaldoActual());
    }

}
