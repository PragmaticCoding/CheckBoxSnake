# CheckBoxSnake

## Origins

This is a fork of Dennis Nolte's CheckBox Snake.  It was posted on Reddit as a clean-coded
game application, which peaked my interest.  Some suggestions were made to Dennis, but 
then I had a closer look and there was a lot of coupling because it didn't seem to use
an MVC structure.

This project is an attempt to improve on the original by applying the following
techniques to "clean" the code:

- Implementation of an MVC structure
- Adherence to the Single Responsibility principle
- Adherence to the DRY principle
- Adherence to the YAGNI principle
- Use of meaningful names
- Putting functionality in the right classes

The code base shrank by about 30% largely due to YAGNI and the removal of code related
to semi-implemented or abandoned (?) features.  

As of 29 Sept, 2021 there is still some more to do, but the project runs nicely and 
reads fairly easily, with most of code located in the right place.  I'd like to split
out the game logic from the MVC Controller, and turn it into an MVCI framework.

Use of sound is still new to me, and there are still some callbacks into the GUI 
for the Controller to activate the sounds.  These need to be removed, and have the 
sounds triggered through monitoring of the State as represented in the View Model.

Dennis's orignal project is here:

https://gitlab.com/dennis-nolte/checkboxsnake

## The original Notes from Dennis's Project:

A small javafx based snake game made by simple elements of javafx.\
Type ``./gradlew run`` to run the application. Use arrow keys to play.

### Environment
- java 11
- javafx17
- gradle-wrapper 6.4

#### Features

- sounds
- transitions
- animated score view
- special food appears every x game loop count
- different pixel implementation like RadioButtonPixel
- started to use gluon, to bring this application on android or as a native windows application
- GameSettings class file to set configurations of the game

### Whats missing

- unit tests
- javadoc
- a special food implementation for RadionButtonPixel
- enhance the special food feature
- complete gluon support for android, windows

## Screenshots
![Screenshot (1)](https://i.ibb.co/7pnfbxy/checkboxsnake1.png)
![Screenshot (2)](https://i.ibb.co/P5x7WwQ/checkboxsnake2.png)
