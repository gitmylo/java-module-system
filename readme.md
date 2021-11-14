# Java Module Library

## Readme contents
- ### [Information](#info)
- ### [Usage](#usage)
  - #### [Api](#api)
  - #### [Main app](#mainapp)
  - #### [Modules (Plugins)](#plugins)


## Information <a name="info" />
Java module library is a lightweight library that allows for a 
modular extensive design 

## Usage <a name="usage" />
### Api <a name="api" />
#### intro
The api is a class used by both the main application and modules (plugins).
This class should have direct or indirect access to anything you want to be
accessible in the external modules.

#### loader redirect
An example on how to 
```java
package com.mylo.usedlibrary;

import com.mylo.modlib.loader.SimpleLoader;
import java.util.List;

public class LoadMods {//a really basic mod loader
    public static List<CustomMod> loadMods(){
        return new SimpleLoader<CustomMod>().loadModules().getLoadedMods();
    }
}
```
#### mod base class
```java
package com.mylo.usedlibrary;

import com.mylo.modlib.modbase.Mod;

public abstract class CustomMod implements Mod {//
    public abstract String anotherString();
}

```

### Main app <a name="mainapp" />

### Modules (Plugins) <a name="plugins" />