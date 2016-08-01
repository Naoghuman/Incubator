Lib-Tile
===



<br />
Intention
---

`Lib-Tile` is a multi [Maven] project written in [JavaFX] and [NetBeans IDE] 8.0.2 
and provides the functionalities to use [Tile]s in your [JavaFX] application.

A [Tile] is per definition a little transparent [Image] which overlay a 
background-color or -image with the help of repetitions from the image in a layer.

The current `version` is `0.1.0` from MM.dd.2016.

TODO add screenshots which shows different tiles in the same image.



<br />
Content
---

* [Demo](#Demo)
* [Libraries](#Libraries)
    - [Lib-Tile-Core](#LiTiCo)
    - [Lib-Tile-TransparentTextures](#LiTiTrTe)
    - [Lib-Tile-TransparentTextures-Images](#LiTiTrTeIm)
* [Download](#Download)
* [Requirements](#Requirements)
* [Installation](#Installation)
* [Documentation](#Documentation)
* [Contribution](#Contribution)
* [License](#License)
* [Autor](#Autor)
* [Contact](#Contact)



<br />
Demo<a name="Demo" />
---

The demo application is a `demonstration` to show the features from the project 
`Lib-Tile` with it included sublibraries.

TODO add screenshot from the demo application

Momentary the developer have following features during the demo application:
* Choose your own background image.
* Define your own background color.
* Browse through all `tiles` from the tileset `Transparent Textures`.

For more informations and examples plz see the [ReadMe from Lib-Tile-Demo].



<br />
Libraries<a name="Libraries" />
---

To use `Lib-Tile` in your project you need minimal the library `Lib-Tile-Core` 
plus a concrete implementation from an [AbstractTileLoader] which loads your 
own `Tiles` images. How that work will be explained in the sub-project 
[Lib-Tile-Demo](#LiTiDe).

Natural you can use additional libraries which contains predefined `tilesets`. 
Currently supported is [Transparent Textures] with the libraries 
[Lib-Tile-TransparentTextures](#LiTiTrTe) and [Lib-Tile-TransparentTextures-Images](#LiTiTrTeIm).


<br />
### Lib-Tile-Core<a name="LiTiCo" />

The library `Lib-Tile-Core` provides the main functionalities to load a [Tile] 
as a [Background] or an [Image] with a concrete implementation from an 
[AbstractTileLoader].

For more informations and examples plz see the [ReadMe from Lib-Tile-Core].


<br />
### Lib-Tile-TransparentTextures<a name="LiTiTrTe" />

With the library `Lib-Tile-TransparentTextures` the developer have access to the 
tileset `Transparent Textures` from the internet page https://www.transparenttextures.com/ 
through the enum `TransparentTexturesTile`.

The tile images from this tileset are outsourced in a own library 
`Lib-Tile-TransparentTextures-Images` to reduce the size from this library.

For more informations and examples plz see the [ReadMe from Lib-Tile-TransparentTextures].


<br />
### Lib-Tile-TransparentTextures-Images<a name="LiTiTrTeIm" />

The library `Lib-Tile-TransparentTextures-Images` contains all images from the 
tileset `Transparent Textures` and the loader `TransparentTexturesTileLoader` 
which allowed the developer to load a single [Tile] image.

In the examples from this library is also an example how to load only `xy` tile 
images, so that's not necessary to include the hole library (which size is 
momentary 13MB) in an application.

For more informations and examples plz see the [ReadMe from Lib-Tile-TransparentTextures-Images].



<br />
Download<a name="Download" />
---

There are different `zip` files as download available. Plz choose the right one 
in dependency from your necessities:
* Download Lib-Tile-Core-0.1.0.zip
    * Contains only the library lib-tile-core-0.1.0.jar.
    * You need to define your own [AbstractTileLoader] and your own [Tile]s 
      images in your application.
* Download Lib-Tile-Core-And-TransparentTextures-0.1.0.zip
    * Contains the libraries lib-tile-core-0.1.0.jar, lib-tile-transparenttextures-0.1.0.jar, 
      lib-tile-transparenttextures-images-0.1.0.jar.
    * You can use the predefined tileset `Transparent Textures` in your application.
* Download Lib-Tile-Demo-0.1.0.zip
    * Contains the library lib-tile-demo-0.1.0.jar as an executable jar  
      with included libraries lib-tile-core-0.1.0.jar, lib-tile-transparenttextures-0.1.0.jar, 
      lib-tile-transparenttextures-images-0.1.0.jar.
    * Use the demo application as a preview how the chosen tiles will be looks 
      over your chosen background.



<br />
Requirements<a name="Requirements" />
---

TODO add section



<br />
Installation<a name="Installation" />
---

TODO add section



<br />
Documentation<a name="Documentation" />
---

TODO add section



<br />
Contribution<a name="Contribution" />
---

* If you find a `Bug` I will be glad if you could report an [Issue].
* If you want to `contribute` to the project plz fork the project and do a [Pull Request].



<br />
License<a name="License" />
---

The project `Lib-Tile` and all sub-projects are licensed under [General Public License 3.0].



<br />
Autor<a name="Autor" />
---

The project `Lib-Tile` and all sub-projects are maintained by me, Peter Rogge. See [Contact](#Contact).



<br />
Contact<a name="Contact" />
---

You can reach me under <peter.rogge@yahoo.de>.



[//]: # (Images)

[//]: # (Links)
[AbstractTileLoader]:https://github.com/Naoghuman/lib-tile/blob/master/Lib-Tile-Core/src/main/java/com/github/naoghuman/lib/tile/core/AbstractTileLoader.java
[Background]:https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/Background.html
[General Public License 3.0]:http://www.gnu.org/licenses/gpl-3.0.en.html
[Image]:https://docs.oracle.com/javase/8/javafx/api/javafx/scene/image/Image.html
[Issue]:https://github.com/Naoghuman/lib-tile/issues
[JavaFX]:http://docs.oracle.com/javase/8/javase-clienttechnologies.htm
[Lib-Tile]:https://github.com/Naoghuman/lib-tile
[Maven]:http://maven.apache.org/
[NetBeans IDE]:https://netbeans.org/
[Pull Request]:https://help.github.com/articles/using-pull-requests
[ReadMe from Lib-Tile-Core]:https://github.com/Naoghuman/lib-tile/blob/master/Lib-Tile-Core
[ReadMe from Lib-Tile-Demo]:https://github.com/Naoghuman/lib-tile/blob/master/Lib-Tile-Demo
[ReadMe from Lib-Tile-TransparentTextures]:https://github.com/Naoghuman/lib-tile/blob/master/Lib-Tile-TransparentTextures
[ReadMe from Lib-Tile-TransparentTextures-Images]:https://github.com/Naoghuman/lib-tile/blob/master/Lib-Tile-TransparentTextures-Images
[StartApplication]:https://github.com/Naoghuman/lib-tile/blob/master/Lib-Tile-Demo/src/main/java/com/github/naoghuman/lib/tile/demo/application/StartApplication.java
[Tile]:https://github.com/Naoghuman/lib-tile/blob/master/Lib-Tile-Core/src/main/java/com/github/naoghuman/lib/tile/core/Tile.java
[Transparent Textures]:https://www.transparenttextures.com/
