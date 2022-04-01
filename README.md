# Image-Editor-2.0
##Overview
This is an image processing application using Java and implementing the model view controller
design pattern.

##Features
Up to this point this program has this many filters:
1. grayscale filter.
2. sepia filter.
3. blur filter.
4. sharpening filter.
5. mosaic filter.
6. dither filter.

It makes a copy of a given image in memory
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

##Examples
### Text controller example:
Mosaic Effect:
`load imagePath mosaic 1000 save newImagePath quit`

##Credits
This project is part of class CS-5004 course at Northeastern University.
### Images  used:
Brown Squirrel -
License: Free to use
https://www.pexels.com/photo/brown-squirrel-47547/
Pixabay, Brown Squirrel. Pexels, 2016.

Nicolas Poupart - Architectural Photography Of City
License: Free to use
https://www.pexels.com/photo/architectural-photography-of-city-2360569/
N. Poupart, Architectural Photography Of City. Pexels, 2019.

### Sites  used:
“Java™ Platform Standard Ed. 7,” Java™ Platform, Standard Edition 7 API Specification.
[Online]. Available:
https://docs.oracle.com/javase/7/docs/api/index.html. [Accessed: 01-Apr-2022]. 