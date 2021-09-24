package org.una.inventario.components;

public enum RolesTypes {
    COLABORADOR("COLABORADOR"),
    USUARIO("USUARIO"),
    AUDITOR("AUDITOR"),
    CONTADOR("CONTADOR"),
    ADMINISTRADOR("ADMINISTRADOR");
    private final String codigo;

    RolesTypes(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

}
