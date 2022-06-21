#The Files that are required to Test using JUnit:

- Card.java
- CardDeck.java
- CardGame.java
- ClearFileMethodTest.java
- FileManagement.java
- GetCardFromPackTest.java
- NumberOfPlayerInputTest.java
- Player.java
- ReadFileMethodTest.java
- TestAllUserInput.java
- WriteFileMethodTest.java

- Card.class
- CardDeck.class
- CardGame.class
- ClearFileMethodTest.class
- FileManagement.class
- GetCardFromPackTest.class
- NumberOfPlayerInputTest.class
- Player.class
- ReadFileMethodTest.class
- TestAllUserInput.class
- WriteFileMethodTest.class

- testpackempty.txt
- testpack4player.txt
- testpackempty.txt
- testpacknotint.txt
- testpackread.txt
- testpackwritefile.txt

-----------STARTING THE TEST-------------------------------------------------------

Compile all .java files. To test with JUnit, choose to run ClearFileMethodTest.java, 
GetCardFromPackTest.java, NumberOfPlayerInputTest.java, ReadFileMethodTest.java,
WriteFileMethodTest.java . Otherwise, to test all at the same time, run
TestAllUserInput.java with JUnit.

-----------------------------------------------------------------------------------

-- Test Details --

#ClearFileMethodTest.java

This tests creates a new temporary folder called "myfile1.txt".It will then clear the file
after it is created. The test will run successfully if the file length is equal to 0.

#GetCardFromPackTest.java

This tests each the different contents of each input pack. For example, if there 
is an invalid number of cards, invalid number of players, invalid file path,
it will display an error message.

validPack()
This tests the file pack path to see if there is 8n number of cards inside the pack.
In this case, n is the number of players in the game. For the test to run, numberOfPlayer
= 4, and the pack should contain 8 * 4 = 32 number of integers.

twoPlayersWithPack4() / fourPlayersWithPack2
This tests the file to see if there are too many or too little cards for the number 
of players in the game. The number of cards should be equal to the ArrayList, otherwise
a error message will be displayed.

missingPack()
This tests to see if the pack path inputted actually exists. The test will run successfully
if the file given doesn't exist.

packContainsString()
This tests to see if the pack path given contains anything other than an integer. The test
will successfully run if the pack is empty.

#NumberOfPlayerInputTest.java

This tests all input that is related to the player when the user is prompted with:
"Enter number of players: "

playerValidInput()
Test will run successfully if the ideal integer is inputted, such as 4 or 2.

playerNegativeInput()
This tests to see when a negative number is inputted, would the system catch the error.
Will run successfully if the error is caught and the value is detected.

noPlayersInput()
This tests to see if a zero is inputted. Will run successfully if the error is caught
and the value is detected.

playerStringInput()
This tests to see if a string is inputted. Will run successfully if the error is caught
and the string value is detected.

playerEmptyInput()
This tests to see if the user inputs nothing (empty value). Will run successfully if the 
error is caught.

#ReadFileMethodTest.java

This tests if the files given can be read from their path. It will also test if it can 
read inside the file.

successfulReadTest()
This tests to see if it can successfully read the file path and detect that the file exists
on the directory.

contentNotInteger()
This tests to see if the file read does not contain an integer.

invalidFilePath()
This tests is successful if the file name given cannot be read as it does not exist at that
directory given.

#TestAllUserInput.java

This tests all the other tests.java all together. It can be run in JUnit to test all
at once.

#WriteFileMethodTest.java

This Test creates a temporary folder to be written in. It will test to see if the write
was successful

successfulWriteTest()
This will test to see if it can successfully write in a temporary file. It will succeed
if it detects the integer it wrote in the file.



