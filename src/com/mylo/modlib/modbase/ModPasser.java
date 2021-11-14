package com.mylo.modlib.modbase;

import java.util.ArrayList;
import java.util.List;

public class ModPasser<T extends Mod> {
    List<T> modules;

    public ModPasser(){
        modules = new ArrayList<>();//instantiate the arraylist
    }

    
}
