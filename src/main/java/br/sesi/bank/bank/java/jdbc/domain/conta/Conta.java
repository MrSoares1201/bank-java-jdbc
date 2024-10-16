package br.sesi.bank.bank.java.jdbc.domain.conta;

import br.sesi.bank.bank.java.jdbc.domain.cliente.Cliente;

import java.math.BigDecimal;
import java.util.Objects;

public class Conta {
    private Integer numero;
    private BigDecimal saldo;
    private Cliente titular;

    public Conta(Integer numero, BigDecimal saldo, Cliente titular){
        this.numero = numero;
        this.saldo = saldo;
        this.titular = titular;
    }
    public boolean possuiSaldo(){
        return this.saldo.compareTo(BigDecimal.ZERO) != 0;
    }
    public void sacar(BigDecimal valor){
        this.saldo = this.saldo.subtract(valor);
    }
    public void depositar(BigDecimal valor){
        this.saldo = this.saldo.add(valor);
    }
    public Integer getNumero(){
        return numero;
    }
    public BigDecimal getSaldo(){
        return saldo;
    }
    public Cliente getTitular(){
        return titular;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }

    @Override
    public String toString(){
        return "Conta{" +
                "numero'" + numero + '\'' +
                ", saldo=" + saldo +
                ", titular=" + titular +
                '}';
    }


}