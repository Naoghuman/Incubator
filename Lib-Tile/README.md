Lib-Tile
===



<br />
Intention
---

`Lib-Tile` is a multi [Maven] project written in [JavaFX] and [NetBeans IDE] 8.0.2 
and provides the functionalities to use and handle easily [Tile]s in your [JavaFX] 
application.

A [Tile] is per definition a little transparent [Image] which overlay a 
background-color or -image with the help of repetitions from the image in a layer.

The current `version` is `0.1.0` from MM.dd.2016.

TODO add screenshots which shows different [Tile]s in the same image.
Crimson Night &#040;Dark / Landscape&#041;

> __Hint__
> The image is from the webpage https://wall.alphacoders.com. The image haven't 
> neither a `created by` or `shared by` &#040;see http://alphacoders.com/site/faq&#041;. 
> If anyone is the autor and won't that I use this image here in my open souce 
> project plz send me an email &#040;see [Contact](#Contact)&#041; and I will 
> remove it. Thanks for your great work :smiley:.



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

The application [Lib-Tile-Demo] shows a `demonstration` how to use and handle 
the features from the libraries in the project `Lib-Tile`. 

TODO add screenshot from the demo application

Momentary the developer have following features during the demo application:
* Choose your own background image.
    * Currently only images from the internet &#040;http or https&#041; with 
      the size 1280x720 are supported.
    * For example: https://initiate.alphacoders.com/images/722/cropped-1280-720-72270.jpg?8276
    * In a later version I plan to implement the option to load also local images.
* Define your own background color.
    * Currently only single colors are supported. For a later version I planed 
      the option to define a [LinearGradient] or a [RadialGradient] colors 
      through `css` &#040;[JavaFX CSS Reference Guide]&#041;.
* Browse through all [Tile]s from the tileset `Transparent Textures`.

For more informations and examples plz see the [ReadMe from Lib-Tile-Demo].



<br />
Libraries<a name="Libraries" />
---

To use `Lib-Tile` in your project you need minimal the library `Lib-Tile-Core` 
plus a concrete implementation from an [AbstractTileLoader] which loads your 
own [Tile]s images. How that work will be explained in the sub-project 
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

In the examples from this library is also an example how to load only `xy` [Tile] 
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
    * Use the demo application as a preview how the chosen [Tile]s will be looks 
      over your chosen background image/color.



<br />
Requirements<a name="Requirements" />
---

* On your system you need [JRE 8] or [JDK 8] installed.



<br />
Installation<a name="Installation" />
---

Depends on your necessities you have different options like in the section 
[Download](#Download) is shown. Download the right `zip` file, extract it and 
include the jar file&#040;s&#041; into your project. That was it :smile: .



<br />
Documentation<a name="Documentation" />
---

In general `Lib-Tile` and `Lib-Tile-Demo` haven't an official documentation. But 
you can
* read the `README.md` from every project and
* read the [JavaDoc] in the project and libraries.

where examples and the api are shown and explained.



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
[JavaDoc]:http://docs.oracle.com/javase/8/docs/technotes/tools/windows/javadoc.html
[JavaFX]:http://docs.oracle.com/javase/8/javase-clienttechnologies.htm
[JavaFX CSS Reference Guide]:https://docs.oracle.com/javase/8/javafx/api/javafx/scene/doc-files/cssref.html
[JDK 8]:http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
[JRE 8]:http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html
[Lib-Tile]:https://github.com/Naoghuman/lib-tile
[Lib-Tile-Demo]:https://github.com/Naoghuman/lib-tile-demo
[LinearGradient]:https://docs.oracle.com/javase/8/javafx/api/javafx/scene/paint/LinearGradient.html
[Maven]:http://maven.apache.org/
[NetBeans IDE]:https://netbeans.org/
[Pull Request]:https://help.github.com/articles/using-pull-requests
[RadialGradient]:https://docs.oracle.com/javase/8/javafx/api/javafx/scene/paint/RadialGradient.html
[ReadMe from Lib-Tile-Core]:https://github.com/Naoghuman/lib-tile/blob/master/Lib-Tile-Core
[ReadMe from Lib-Tile-Demo]:https://github.com/Naoghuman/lib-tile-demo/blob/master/README.md
[ReadMe from Lib-Tile-TransparentTextures]:https://github.com/Naoghuman/lib-tile/blob/master/Lib-Tile-TransparentTextures
[ReadMe from Lib-Tile-TransparentTextures-Images]:https://github.com/Naoghuman/lib-tile/blob/master/Lib-Tile-TransparentTextures-Images
[StartApplication]:https://github.com/Naoghuman/lib-tile/blob/master/Lib-Tile-Demo/src/main/java/com/github/naoghuman/lib/tile/demo/application/StartApplication.java
[Tile]:https://github.com/Naoghuman/lib-tile/blob/master/Lib-Tile-Core/src/main/java/com/github/naoghuman/lib/tile/core/Tile.java
[Transparent Textures]:https://www.transparenttextures.com/
