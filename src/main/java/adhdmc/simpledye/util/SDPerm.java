package adhdmc.simpledye.util;

import org.bukkit.permissions.Permission;

public enum SDPerm {
    RELOAD_PERM(new Permission("sd.reload")),
    DYE_BASIC_PERM(new Permission("sd.dye.basic")),
    DYE_RGB_PERM(new Permission("sd.dye.rgb"))
    ;
    private final Permission perm;
    SDPerm(Permission perm) {
        this.perm = perm;
    }
    public Permission getPerm(){
        return perm;
    }
}
