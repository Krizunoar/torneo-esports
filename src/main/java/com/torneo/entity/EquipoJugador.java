package com.torneo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "equipo_jugadores")
public class EquipoJugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_equipo_jugador")
    private Integer idEquipoJugador;

    @ManyToOne
    @JoinColumn(name = "id_equipo")
    private Equipo equipo;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public Integer getIdEquipoJugador() {
        return idEquipoJugador;
    }

    public void setIdEquipoJugador(Integer idEquipoJugador) {
        this.idEquipoJugador = idEquipoJugador;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}