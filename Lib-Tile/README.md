Lib-Tile
===



Intention
---

`Lib-Tile` provides the functionalities to use `Tiles` in your [JavaFX] application.

A `Tile` is per definition a little transparent [Image] which overlay a 
background-color or -image with the help of repetitions from the image in a layer.

TODO add screenshot which shows different tiles in the same image.



Content
---

* [Libraries](#Libraries)
    - [Lib-Tile-Core](#LiTiCo)
    - [Lib-Tile-Demo](#LiTiDe)
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



Libraries<a name="Libraries" />
---

`Lib-Tile` is a multi [Maven] project written in [JavaFX] and [NetBeans IDE] 8.0.

To use `Lib-Tile` in your project you need minimal the library `Lib-Tile-Core` 
plus a concrete implementation from an [AbstractTileLoader] which loads your 
own `Tiles` images. How that work will be explained in the sub-project 
[Lib-Tile-Demo](#LiTiDe).

Natural you can use additional libraries which contains predefined `tilesets`. 
Currently supported is [Transparent Textures] with the libraries 
[Lib-Tile-TransparentTextures](#LiTiTrTe) and [Lib-Tile-TransparentTextures-Images](#LiTiTrTeIm).


<br />
##### Lib-Tile-Core<a name="LiTiCo" />

The library `Lib-Tile-Core` provides the main functionalities to load a [Tile] as 
a [Background] or an [Image] with a concrete implementation from an [AbstractTileLoader].

For more informations and examples plz see the [ReadMe from Lib-Tile-Core].


<br />
##### Lib-Tile-Demo<a name="LiTiDe" />

TODO add section

For more informations and examples plz see the [ReadMe from Lib-Tile-Demo].


<br />
##### Lib-Tile-TransparentTextures<a name="LiTiTrTe" />

TODO add section

For more informations and examples plz see the [ReadMe from Lib-Tile-TransparentTextures].


<br />
##### Lib-Tile-TransparentTextures-Images<a name="LiTiTrTeIm" />

TODO add section

For more informations and examples plz see the [ReadMe from Lib-Tile-TransparentTextures-Images].



Download<a name="Download" />
---

TODO add section



Requirements<a name="Requirements" />
---

TODO add section



Installation<a name="Installation" />
---

TODO add section



Documentation<a name="Documentation" />
---

TODO add section



Contribution<a name="Contribution" />
---

* If you find a `Bug` I will be glad if you could report an [Issue].
* If you want to `contribute` to the project plz fork the project and do a [Pull Request].



License<a name="License" />
---

The project `Lib-Tile` and all sub-projects are licensed under [General Public License 3.0].



Autor<a name="Autor" />
---

The project `Lib-Tile` and all sub-projects are maintained by me, Peter Rogge. See [Contact](#Contact).



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
[Tile]:https://github.com/Naoghuman/lib-tile/blob/master/Lib-Tile-Core/src/main/java/com/github/naoghuman/lib/tile/core/Tile.java
[Transparent Textures]:https://www.transparenttextures.com/
