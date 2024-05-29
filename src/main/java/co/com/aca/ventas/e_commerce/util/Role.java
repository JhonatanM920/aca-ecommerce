package co.com.aca.ventas.e_commerce.util;

import java.util.Arrays;
import java.util.List;

public enum Role {
    ADMINISTRATOR(Arrays.asList(
            Permission.ACTUALIZAR_USUARIO,
            Permission.CREAR_USUARIO,
            Permission.CREAR_PRODUCTO,
            Permission.ACTUALIZAR_PRODUCTO,
            Permission.LISTAR_TODOS_LOS_PRODUCTOS,
            Permission.ACTUALIZAR_UNIDADES)),
    CUSTOMER(Arrays.asList(
            Permission.ACTUALIZAR_USUARIO,
            Permission.CREAR_USUARIO,
            Permission.LISTAR_TODOS_PRODUCTOS_ACTIVOS));

    private List<Permission> permissions;

    Role(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
