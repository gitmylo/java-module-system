package com.mylo.modlib.modbase;

import java.util.List;

/**
 * @author GitMylo
 */
public abstract class ModMain<T extends Mod> {
    /**
     * This is where you return all your mods, allows multiple mods.
     * @return the mods you want to return.
     */
    public abstract List<T> getModules();
}
