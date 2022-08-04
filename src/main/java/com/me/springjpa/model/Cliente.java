package com.me.springjpa.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString
@Table(name = "clientes")
public class Cliente {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        @Setter
        @Column(name = "name")
        private String nome;
        @Setter
        @Column(name = "idade")
        private int idade;
        @Setter
        @Column(name = "conta")
        private int conta;

    public Cliente() {
    }

    public Cliente(String nome, int idade, int conta) {
        this.nome = nome;
        this.idade = idade;
        this.conta = conta;
    }

    public Cliente(int id, String nome, int idade, int conta) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.conta = conta;
    }
}
