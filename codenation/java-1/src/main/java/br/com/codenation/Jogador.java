package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
public class Jogador implements Comparable<Jogador> {
    @Override
    public String toString() {
        return "Jogador{" +
                "id=" + id +
                ", nivelHabilidade=" + nivelHabilidade +
                '}' + "\n";
    }

    Long id, idTime;
    String nome;
    LocalDate dataNascimento;
    Integer nivelHabilidade;
    BigDecimal salario;
    Boolean capitao;

    Jogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
        this.id = id;
        this.idTime = idTime;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.nivelHabilidade = nivelHabilidade;
        this.salario = salario;
        this.capitao = false;
    }

    public Integer getNivelHabilidade() {
        return this.nivelHabilidade;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdTime() {
        return this.idTime;
    }

    public void setIdTime(Long idTime) {
        this.idTime = idTime;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return this.dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setNivelHabilidade(Integer nivelHabilidade) {
        this.nivelHabilidade = nivelHabilidade;
    }

    public BigDecimal getSalario() {
        return this.salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public Boolean getCapitao() {
        return this.capitao;
    }

    public void setCapitao(Boolean capitao) {
        this.capitao = capitao;
    }


    @Override
    public int compareTo(Jogador outroJogador) {
        if (this.nivelHabilidade > outroJogador.getNivelHabilidade()) {
            return -1;
        }
        if (this.nivelHabilidade < outroJogador.getNivelHabilidade()) {
            return 1;
        }
        if (this.nivelHabilidade.equals(outroJogador.getNivelHabilidade())) {
            if (this.id < outroJogador.getId()) {
                return -1;
            } else {
                return 1;
            }
        }
        return 0;
    }
}
