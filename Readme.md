#Technical problem Intercom
 
We have some customer records in a text file (customers.txt) -- one customer per line, JSON lines formatted. We want to invite any customer within 100km of our Dublin office for some food and drinks on us. Write a program that will read the full list of customers and output the names and user ids of matching customers (within 100km), sorted by User ID (ascending).
 
## Running and testing
 
This code has been run and tested with openJDK 11
 
Compile: ./gradlew clean build

Test:    ./gradle test

Set enviornment variable CUSTOMER_FILE to the path pointing to the customers.txt file

###Running
In build/libs

java -jar CustomerSearch.jar Dublin 100

### Help
java -jar CustomerSearch.jar -h

