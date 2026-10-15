package com.fabrizio.gestionenfermedades.domain.model;

public class Enfermedad {

    private Long id;
    private String nombre;
    private String nombreCientifico;
    private String nivelGravedad;
    private String sintomas;
    private String medicamentos;
    private boolean esContagiosa;
    private boolean esCubiertaPorPost;
    private boolean requiereIncapacidad;

    public Enfermedad() {
    }

    public Enfermedad(
            Long id,
            String nombre,
            String nombreCientifico,
            String nivelGravedad,
            String sintomas,
            String medicamentos,
            boolean esContagiosa,
            boolean esCubiertaPorPost,
            boolean requiereIncapacidad) {

        this.id = id;
        this.nombre = nombre;
        this.nombreCientifico = nombreCientifico;
        this.nivelGravedad = nivelGravedad;
        this.sintomas = sintomas;
        this.medicamentos = medicamentos;
        this.esContagiosa = esContagiosa;
        this.esCubiertaPorPost = esCubiertaPorPost;
        this.requiereIncapacidad = requiereIncapacidad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreCientifico() {
        return nombreCientifico;
    }

    public void setNombreCientifico(String nombreCientifico) {
        this.nombreCientifico = nombreCientifico;
    }

    public String getNivelGravedad() {
        return nivelGravedad;
    }

    public void setNivelGravedad(String nivelGravedad) {
        this.nivelGravedad = nivelGravedad;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public String getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

    public boolean isEsContagiosa() {
        return esContagiosa;
    }

    public void setEsContagiosa(boolean esContagiosa) {
        this.esContagiosa = esContagiosa;
    }

    public boolean isEsCubiertaPorPost() {
        return esCubiertaPorPost;
    }

    public void setEsCubiertaPorPost(boolean esCubiertaPorPost) {
        this.esCubiertaPorPost = esCubiertaPorPost;
    }

    public boolean isRequiereIncapacidad() {
        return requiereIncapacidad;
    }

    public void setRequiereIncapacidad(boolean requiereIncapacidad) {
        this.requiereIncapacidad = requiereIncapacidad;
    }
}