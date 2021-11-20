# Java Module Library

## Readme contents
- ### [Information](#info-link)
- ### [Usage](#usage-link)
  - #### [Api](#api-link)
    - [Intro](#api-intro-link)
    - [Loader redirect](#loader-redirect-link)
    - [Mod base class](#mod-base-class-link)
    - [Custom loader](#custom-loader-link)
  - #### [Main app](#mainapp-link)
    - [Intro](#main-intro-link)
    - [Mounting the modules](#mounting-the-modules-link)
  - #### [Modules (Plugins)](#plugins-link)
    - [Intro](#mod-intro-link)
    - [Custom mod ModMain](#mod-main-link)
    - [Custom mod module classes](#mod-mods-link)
- ### [License](#license-link)


## Information <a name="info-link" />
Java module library is a lightweight library that allows for a 
modular extensive design 

## Usage <a name="usage-link" />
### Api <a name="api-link" />
#### Intro <a name="api-intro-link">
The api is a class used by both the main application and modules (plugins).
This class should have direct or indirect access to anything you want to be
accessible in the external modules.

#### Loader redirect <a name="loader-redirect-link" />
An example on how to create a bridge to the loader:
```java
package com.mylo.usedlibrary;

import com.mylo.modlib.loader.SimpleLoader;
import java.util.List;

/*
        A really basic mod loader
 */
public class LoadMods {
    public static List<CustomMod> loadMods(){
        return new SimpleLoader<CustomMod>().loadModules().getLoadedMods();
    }
}
```
#### Mod base class <a name="mod-base-class-link" />
An example mod base class:
```java
package com.mylo.usedlibrary;

import com.mylo.modlib.modbase.Mod;

/*
        a custom mod class using an interface,
        so you could even have this extend a
        class made by you.
 */
public abstract class CustomMod implements Mod {
    public abstract String anotherString();
}
```
#### Custom loader <a name="custom-loader-link" />
To make a custom loader you start by extending "com.mylo.modlib.loader.SimpleLoader". You could simply change modRoot (the mod folder to check) and modMainLink (the mod's .mod file by default).

### Main app <a name="mainapp-link" />
#### Intro <a name="main-intro-link">
Begin by importing the api you made at the previous steps.
#### Mounting the modules <a name="mounting-the-modules-link" />
Main class loading all modules and calling functions example:
```java
package com.mylo.ModuleExample;

import com.mylo.usedlibrary.CustomMod;
import com.mylo.usedlibrary.LoadMods;

public class Main {
  public static void main(String[] args){
    System.out.println("Starting module load");
    for (CustomMod mod: LoadMods.loadMods()){//from before, loadmods gives a list of CustomMod
      System.out.println("Loaded Module: " + mod.getName());//mod.getName is built in, used for sorting on your end
      System.out.println("Module's custom string: " + mod.anotherString());//mod.anotherString was added with the CustomMod
      System.out.println("-----------------------------------------------------------");
    }
    System.out.println("Done");
  }
}
```

### Modules (Plugins) <a name="plugins-link" />
#### Intro <a name="mod-intro-link">
All modules require the api you made at the previous steps
#### Custom mod ModMain <a name="mod-main-link">
```java
package com.mylo.exampleMod;

import com.mylo.modlib.modbase.ModMain;

import java.util.Arrays;
import java.util.List;

public class Main extends ModMain {
    @Override
    public List getModules() {
        return Arrays.asList(new TestMod());
        //this just instantiates the test mod created in the next step
    }
}
```
#### Custom mod Module classes <a name="mod-mods-link">
```java
package com.mylo.exampleMod;

import com.mylo.usedlibrary.CustomMod;

public class TestMod extends CustomMod {
    @Override
    public String getName() {//getName is from the base mod class
        return "TestMod";
    }

    @Override
    public String anotherString() {//anotherString is from CustomMod
        return "Another String Lmao";
    }
}
```
  
To then install the module, grab the compiled .class files, put them in a folder with a .mod file in the root with the path to the mod loader class. As would be in a meta-inf/manifest.mf file, but this isn't a jar, so you simply specify it in a .mod file. if you want a version that loads mods from a jar or a zip, you're free to make a fork.

### License <a name="license-link" />
Licensed under [MIT License](LICENSE).
