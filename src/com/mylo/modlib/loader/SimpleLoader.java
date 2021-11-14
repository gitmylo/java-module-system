package com.mylo.modlib.loader;

import com.mylo.modlib.modbase.Mod;
import com.mylo.modlib.modbase.ModMain;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author GitMylo
 */
public class SimpleLoader<T extends ModMain<Mod>> implements Loader<Mod> {
    /**
     * A list of the loaded modules.
     */
    public ArrayList<Mod> loadedMods;

    /**
     * Instantiate a new SimpleLoader.
     */
    public SimpleLoader(){
        loadedMods = new ArrayList<>();
    }

    /**
     * The directory the mods are located in.
     */
    public String modRoot = "mods/";

    /**
     * Makes ".mod" the file to read to
     * find the main class of a module.
     * Mod config path should be:
     * modRoot/[mod]/modMainLink
     */
    public String modMainLink = ".mod";

    /**
     * Load all the modules found in modRoot
     * into the loadedMods list.
     */
    public void loadModules(){
        for (String m: getModules()){
            loadedMods.addAll(getModule(m));
        }
    }

    /**
     * Gets the modules from the modRoot.
     * @return the list of modules found.
     */
    public List<String> getModules(){
        File modDir = new File(modRoot);
        if (modDir.exists() && modDir.isDirectory()){
            List<String> out = new ArrayList<>();
            for (String p: modDir.list()){
                if (isValidMod(p)){
                    out.add(p);
                }
            }
            return out;
        }
        return new ArrayList<>();
    }

    /**
     * Returns if module exists and has config file.
     * Does not tell you if the main path is valid.
     * @param dir The directory of the mod to check.
     * @return if the mod exists and has a config file.
     */
    public boolean isValidMod(String dir){
        try {
            File modDir = new File(dir);
            String modConfPath = dir+"/"+modMainLink;
            if (modDir.exists() && modDir.isDirectory()){
                File modConf = new File(modConfPath);
                if (modConf.exists() && modConf.isFile()){
                    return readFirstLine(modConfPath) != null;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Get a module list from a path
     * @param dir The directory to get the module from.
     * @return a list of modules.
     */
    public List<Mod> getModule(String dir){
        try {
            File modDir = new File(dir);
            String modConfPath = dir+"/"+modMainLink;
            if (modDir.exists() && modDir.isDirectory()){
                File modConf = new File(modConfPath);
                if (modConf.exists() && modConf.isFile()){
                    return classGetMods(getModMainClass(dir, readFirstLine(modConfPath)));
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private Class<ModMain> getModMainClass(String modPath, String modMainLink){
        File file = new File(modPath);
        try {
            URL url = file.toURI().toURL();
            URL[] urls = new URL[]{url};
            ClassLoader cl = new URLClassLoader(urls);
            return (Class<ModMain>) cl.loadClass(modMainLink);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Mod> classGetMods(Class<ModMain> modMainClass){
        try {
            ModMain instance = modMainClass.getConstructor().newInstance();//instantiates the module
            return (List<Mod>) modMainClass.getMethod("getModules").invoke(instance);//gets the modules list
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private String readFirstLine(String path) throws IOException {
        File f = new File(path);
        if (f.exists() && f.isFile()){
            return Files.readAllLines(Paths.get(path)).get(0);
        }
        return null;
    }
}
