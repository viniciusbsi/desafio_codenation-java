package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;


import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;

public class DesafioMeuTimeApplication implements MeuTimeInterface {

    ArrayList<Time> lista_times = new ArrayList<>();
    ArrayList<Jogador> lista_jogadores = new ArrayList<>();

    public boolean existeTime(Long idTime) {
        boolean existe = false;
        for (Time time : this.lista_times) {
            if (time.getId().equals(idTime)) {
                existe = true;
                break;
            }
        }
        return existe;
    }

    public boolean existeJogador(Long idJogador) {
        boolean existe = false;
        for (Jogador jogador : this.lista_jogadores) {
            if (jogador.getId().equals(idJogador)) {
                existe = true;
                break;
            }
        }
        return existe;
    }

    @Desafio("incluirTime")
    public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
        boolean existe = existeTime(id);
        if (!existe) {
            Time time = new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario);
            this.lista_times.add(time);
        } else {
            throw new br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException();
        }
    }

    @Desafio("incluirJogador")
    public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
        boolean existe = existeTime(idTime);
        if (!existe) {
            throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
        }
        existe = existeJogador(id);
        if (!existe) {
            Jogador jogador = new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario);
            this.lista_jogadores.add(jogador);
        } else {
            throw new br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException();
        }
    }

    @Desafio("definirCapitao")
    public void definirCapitao(Long idJogador) {
        Long idTime = null;
        for (Jogador jogador : this.lista_jogadores) {
            if (jogador.getId().equals(idJogador)) {
                jogador.setCapitao(true);
                idTime = jogador.getIdTime();
                break;
            }
        }
        if (idTime != null) {
            for (Jogador jogador : this.lista_jogadores) {
                if (jogador.getIdTime().equals(idTime) && jogador.getCapitao() && !jogador.getId().equals(idJogador)) {
                    jogador.setCapitao(false);
                    break;
                }
            }
        } else {
            throw new br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException();
        }

    }

    @Desafio("buscarCapitaoDoTime")
    public Long buscarCapitaoDoTime(Long idTime) {
        Long idJogador = null;
        boolean existe = existeTime(idTime);
        if (!existe) {
            throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
        }
        for (Jogador jogador : this.lista_jogadores) {
            if (jogador.getIdTime().equals(idTime) && jogador.getCapitao()) {
                idJogador = jogador.getId();
                break;
            }
        }
        if (idJogador == null) {
            throw new br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException();
        }
        return idJogador;
    }

    @Desafio("buscarNomeJogador")
    public String buscarNomeJogador(Long idJogador) {
        String nome = null;
        for (Jogador jogador : this.lista_jogadores) {
            if (jogador.getId().equals(idJogador)) {
                nome = jogador.getNome();
                break;
            }
        }
        if (nome == null) {
            throw new br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException();
        }
        return nome;
    }

    @Desafio("buscarNomeTime")
    public String buscarNomeTime(Long idTime) {
        String nome = null;
        for (Time time : this.lista_times) {
            if (time.getId().equals(idTime)) {
                nome = time.getNome();
                break;
            }
        }
        if (nome == null) {
            throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
        }
        return nome;
    }

    @Desafio("buscarJogadoresDoTime")
    public List<Long> buscarJogadoresDoTime(Long idTime) {
        boolean existe = existeTime(idTime);
        if (!existe) {
            throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
        }
        List<Long> lista = new ArrayList<Long>();
        for (Jogador jogador : this.lista_jogadores) {
            if (jogador.getIdTime().equals(idTime)) {
                lista.add(jogador.getId());
            }
        }
        Collections.sort(lista);
        return lista;
    }

    @Desafio("buscarMelhorJogadorDoTime")
    public Long buscarMelhorJogadorDoTime(Long idTime) {
        boolean existe = existeTime(idTime);
        if (!existe) {
            throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
        }
        List<Jogador> time = new ArrayList<>();
        for (Jogador jogadorl : this.lista_jogadores) {
            if (jogadorl.getIdTime().equals(idTime)) {
                time.add(jogadorl);
            }
        }
        Collections.sort(time);
        return time.get(0).getId();
    }

    @Desafio("buscarJogadorMaisVelho")
    public Long buscarJogadorMaisVelho(Long idTime) {
        boolean existe = existeTime(idTime);
        if (!existe) {
            throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
        }
        Jogador jogador = null;
        for (Jogador jogadorl : this.lista_jogadores) {
            if (jogadorl.getIdTime().equals(idTime)) {
                if (jogador == null) {
                    jogador = jogadorl;
                }
                if ((jogadorl.getDataNascimento().isBefore(jogador.getDataNascimento())) || (jogadorl.getDataNascimento().isEqual(jogador.getDataNascimento()) && jogadorl.getId() < jogador.getId())) {
                    jogador = jogadorl;
                }
            }
        }
        return jogador.getId();
    }

    @Desafio("buscarTimes")
    public List<Long> buscarTimes() {
        List<Long> lista = new ArrayList<>();
        for (Time time : this.lista_times) {
            lista.add(time.getId());
        }
        Collections.sort(lista);
        return lista;
    }

    @Desafio("buscarJogadorMaiorSalario")
    public Long buscarJogadorMaiorSalario(Long idTime) {
        boolean existe = existeTime(idTime);
        if (!existe) {
            throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
        }
        Jogador jogador = null;
        for (Jogador jogadorl : this.lista_jogadores) {
            if (jogadorl.getIdTime().equals(idTime)) {
                if (jogador == null) {
                    jogador = jogadorl;
                }
                if ((jogadorl.getSalario().compareTo(jogador.getSalario()) > 0) || (jogadorl.getSalario().compareTo(jogador.getSalario()) == 0 && jogadorl.getId() < jogador.getId())) {
                    jogador = jogadorl;
                }
            }
        }
        return jogador.getId();
    }

    @Desafio("buscarSalarioDoJogador")
    public BigDecimal buscarSalarioDoJogador(Long idJogador) {
        BigDecimal salario = null;
        for (Jogador jogador : this.lista_jogadores) {
            if (jogador.getId().equals(idJogador)) {
                salario = jogador.getSalario();
                break;
            }
        }
        if (salario == null) {
            throw new br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException();
        }

        return salario;
    }

    @Desafio("buscarTopJogadores")
    public List<Long> buscarTopJogadores(Integer top) {
        List<Long> topster = new ArrayList<>();
        Collections.sort(this.lista_jogadores);
        for (Jogador player : this.lista_jogadores.subList(0, top)) {
            topster.add(player.getId());
        }
        return topster;
    }


    @Desafio("buscarCorCamisaTimeDeFora")
    public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
        Time time_da_casa = null;
        Time time_de_fora = null;
        for (Time time : this.lista_times) {
            if (time.getId().equals(timeDaCasa)) {
                time_da_casa = time;
            }
            if (time.getId().equals(timeDeFora)) {
                time_de_fora = time;
            }
        }
        if (time_de_fora == null || time_da_casa == null) {
            throw new br.com.codenation.desafio.exceptions.TimeNaoEncontradoException();
        }

        if (time_de_fora.getCorUniformePrincipal().equals(time_da_casa.getCorUniformePrincipal())) {
            return time_de_fora.getCorUniformeSecundario();
        }
        return time_de_fora.getCorUniformePrincipal();
    }

}
