package com.torneo.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "partidas")
public class Partida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_partida")
    private Integer idPartida;

    @Column(name = "ronda")
    private String ronda;

    @Column(name = "fecha_partida")
    private LocalDateTime fechaPartida;

    @ManyToOne
    @JoinColumn(name = "equipo1_id")
    private Equipo equipo1;

    @ManyToOne
    @JoinColumn(name = "equipo2_id")
    private Equipo equipo2;

    @Column(name = "score_equipo1")
    private Integer scoreEquipo1;

    @Column(name = "score_equipo2")
    private Integer scoreEquipo2;

    @ManyToOne
    @JoinColumn(name = "equipo_ganador_id")
    private Equipo equipoGanador;

    @Column(name = "estado")
    private String estado;

    @ManyToOne
    @JoinColumn(name = "id_torneo")
    private Torneo torneo;

    // Getters y Setters

    public Integer getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(Integer idPartida) {
        this.idPartida = idPartida;
    }

    public String getRonda() {
        return ronda;
    }

    public void setRonda(String ronda) {
        this.ronda = ronda;
    }

    public LocalDateTime getFechaPartida() {
        return fechaPartida;
    }

    public void setFechaPartida(LocalDateTime fechaPartida) {
        this.fechaPartida = fechaPartida;
    }

    public Equipo getEquipo1() {
        return equipo1;
    }

    public void setEquipo1(Equipo equipo1) {
        this.equipo1 = equipo1;
    }

    public Equipo getEquipo2() {
        return equipo2;
    }

    public void setEquipo2(Equipo equipo2) {
        this.equipo2 = equipo2;
    }

    public Integer getScoreEquipo1() {
        return scoreEquipo1;
    }

    public void setScoreEquipo1(Integer scoreEquipo1) {
        this.scoreEquipo1 = scoreEquipo1;
    }

    public Integer getScoreEquipo2() {
        return scoreEquipo2;
    }

    public void setScoreEquipo2(Integer scoreEquipo2) {
        this.scoreEquipo2 = scoreEquipo2;
    }

    public Equipo getEquipoGanador() {
        return equipoGanador;
    }

    public void setEquipoGanador(Equipo equipoGanador) {
        this.equipoGanador = equipoGanador;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Torneo getTorneo() {
        return torneo;
    }

    public void setTorneo(Torneo torneo) {
        this.torneo = torneo;
    }
}