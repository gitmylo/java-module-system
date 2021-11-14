# Java Module Library

## Readme contents
- ### [Information](#info)
- ### [Usage](#usage)
  - #### [Api](#api)
    - [Intro](#api-intro)
    - [Loader redirect](#loader-redirect)
    - [Mod base class](#mod-base-class)
    - [Custom loader](#custom-loader)
  - #### [Main app](#mainapp)
    - [Intro](#main-intro)
    - [Mounting the modules](#mounting-the-modules)
  - #### [Modules (Plugins)](#plugins)
    - [Intro](#mod-intro)
    - [Custom mod ModMain](#mod-main)
    - [Custom mod module classes](#mod-mods)
- ### [License](#license)


## Information <a name="info" />
Java module library is a lightweight library that allows for a 
modular extensive design 

## Usage <a name="usage" />
### Api <a name="api" />
#### Intro <a name="api-intro">
The api is a class used by both the main application and modules (plugins).
This class should have direct or indirect access to anything you want to be
accessible in the external modules.

#### Loader redirect <a name="loader-redirect" />
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
#### Mod base class <a name="mod-base-class" />
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
#### Custom loader <a name="custom-loader" />
To make a custom loader you start by extending "com.mylo.modlib.loader.SimpleLoader". You could simply change modRoot (the mod folder to check) and modMainLink (the mod's .mod file by default).

### Main app <a name="mainapp" />
#### Intro <a name="main-intro">
Begin by importing the api you made at the previous steps.
#### Mounting the modules <a name="mounting-the-modules" />
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

### Modules (Plugins) <a name="plugins" />
#### Intro <a name="mod-intro">
All modules require the api you made at the previous steps
#### Custom mod ModMain <a name="mod-main">
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
#### Custom mod Module classes <a name="mod-mods">
### License <a name="license" />
Licensed under MIT License.