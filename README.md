# Image-Editor-2.0
## Overview
This is an image processing application using Java and implementing the model view controller
design pattern.
---
## Features
Up to this point this program has this many filters:
1. grayscale filter.<br/>
Basic grayscale filter.
2. sepia filter.<br/>
Sepia Yellowish and black filter.
3. blur filter.<br/>
Basic blur filter.
4. sharpening filter.<br/>
Basic sharpening filter.
5. mosaic filter.<br/>
Mosaic effect divides image into n areas with same color. These areas are defined randomly.
6. dither filter.<br/>
Old newspaper technique to print images in black and white.
7. Edge detection.<br/>
Edge detection converts image to black and white and highlights differences.
8. Grayscale equalization.<br/>
Histogram equalization on grayscale images.
9. Cropping.<br/>
Standard cropping tool.
10. Image generation.<br/>
Creates simple colored patterns.

This program makes a copy in memory
and applies the filters to that copy.

##Limitations
This program cannot undo changes made to an image, it 
is also destructive, meaning that the copy in memory
will keep changing according to the filters applied.
A layering system has not been implemented.

##How to use?
Compile the program like any other java project.
Then use the __load__ command to load an image, enter its filename
and use any filter you wish to try. Before typing the __quit__ command remember
to save to a new file.
---
## Keyboard ShortCuts (GUI)

### Saving & Loading
'CTRL + S' : save image <br>
'CTRL + L' : load image <br>
### Filters
'CTRL + G' : apply grayscale filter <br>
'CTRL + E' : apply sepia filter <br>
'CTRL + B' : apply blur filter <br>
'CTRL + H' : apply blur filter <br>
'CTRL + D' : apply dither filter <br>
'CTRL + M' : apply mosaic filter (Opens settings window) <br>
'CTRL + O' : apply sobel filter <br>
'CTRL + Q' : apply histogram equalizer filter 

### Tools
'CTRL + C' : Crop image (Opens menu)

---
## Text controller example:
(Short example) Mosaic Effect:
`load imagePath mosaic 1000 save newImagePath quit` <br>
The **input.txt** script found in /res works the same way by using __load__ then the name
of the file. Then we can apply any filter in the list from the examples shown, 
if you type __options__ you can get the whole list of commands.
To save we simply type __save__ then the name of our new file.
---
## Credits
This project is part of class CS-5004 course at Northeastern University, taught by professor Maria Jump.
### Images  used:
Brown Squirrel -
License: Free to use
https://www.pexels.com/photo/brown-squirrel-47547/
Pixabay, Brown Squirrel. Pexels, 2016.

Grayscale Photo of a Bengal Tiger Lying on Grass
License: Free to use
https://www.pexels.com/photo/grayscale-photo-of-a-bengal-tiger-lying-on-grass-10597557/
Anil Sharma, Grayscale Photo of a Bengal Tiger Lying on Grass, India. Pexels, 2021.

Nicolas Poupart - Architectural Photography Of City
License: Free to use
https://www.pexels.com/photo/architectural-photography-of-city-2360569/
N. Poupart, Architectural Photography Of City. Pexels, 2019.

freeiconspng.com User: Ahkâm - Simple No PNG Transparent Background
Licence -  Only for personal use
https://www.freeiconspng.com/img/23483
freeiconspng.com - consulted 2022



### Sites  used:
“Java™ Platform Standard Ed. 7,” Java™ Platform, Standard Edition 7 API Specification.
[Online]. Available:
https://docs.oracle.com/javase/7/docs/api/index.html. [Accessed: 01-Apr-2022].


