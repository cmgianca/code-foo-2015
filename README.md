IGN's Code-Foo 2015
=============

For more information on IGN's Code-Foo 2015, visit [ign.com/code-foo/2015] (http://ign.com/code-foo/2015).

To apply to Code-Foo 2015, fork this repository and answer all questions. Push your answers to your fork and send us an email at ign_code-foo@ign.com to let us know you're finished.

IGN's Code-Foo 2015 is open to US residents only.  All answers must be completed using Java, Scala, PHP, Ruby, Swift, Objective-C, or JavaScript. Application must be turned in by _Friday, May 1st at 5pm PST_. You will be notified by email if you pass the first round of submissions.

1. Create a 2-5 minute video introducing yourself and tell us why we should choose you for the Code-Foo program.

2. How many ping-pong balls can you fit in 747? Describe each step in your thought process.

3. Write a program to find the given words from the included word search. Both word search and words can be found at [word-search.txt] (https://github.com/ign/code-foo-2015/blob/master/word-search.txt)

  I decided to write this program in java, since that was the language I feel most comfortable with at the moment. To run the program, simply compile and run WordSearch.java. It takes no command line arguments, but makes the assumption that word-search.txt is in the same directory, in order to parse the data and come up with a solution. If it all works, a window should appear with a graphic showing the location of every word in the puzzle. 

4.  Using [this API] (http://ign-apis.herokuapp.com), pull and display a list of both articles and videos.

  Goto question 5:

5. Using the results from the previous question, create a web/ios/android application that displays the results and matches [this design] (https://github.com/ign/code-foo-2015/blob/master/design.png). The application should be responsive to common screen/device sizes.

  My application was made through a combination of html, css and javascript. To open and use the application, go into the "IGN_app_files" directory and simply run "a.htm" in a browser. The program was made for firefox, but seems to work fine in chrome aside from one minor graphical inconsistency. Also, you can click on any of the posted links to be linked directly to that webpage. 

Bonus Question
--------------
Programmatically create a game similar to Battleship™ with one AI-controlled opponent and a human-controlled player.
