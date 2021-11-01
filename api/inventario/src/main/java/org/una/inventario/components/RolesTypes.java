package org.una.inventario.components;

public enum RolesTypes {
    ROLE_COLABORADOR("COLABORADOR"),
    ROLE_USUARIO("USUARIO"),
    ROLE_AUDITOR("AUDITOR"),
    ROLE_CONTADOR("CONTADOR"),
    ROLE_ADMINISTRADOR("ADMINISTRADOR");

    private final String codigo;

    RolesTypes(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

}
