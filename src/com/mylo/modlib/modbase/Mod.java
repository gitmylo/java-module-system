package com.mylo.modlib.modbase;

/**
 * @author GitMylo
 */
public abstract class Mod {
    /**
     * This is where you return the module's name, this can be utilised
     * by the program's creator to order modules by their names.
     * You can use a short name, or a full java style package name.
     * @return the module's name.
     */
    public abstract String getName();
}
