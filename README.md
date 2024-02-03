# People Pairer

A very simple Java console application to read in a file of teams and team members then pair them off.

## Description

An input file is read to input the teams and team members in each team.

The input file also contains a list of previous pairings so the program can avoid paring people together if it can avoid it.

The file format is described below (TBD).

Each team can have zero or more team members. 

If there are an odd number of team members a 'Joker' is added so there is an even number to allow pairing off.  

The application reads in each team and it's members and stores them.

It then picks two random teams and a random team member from each and pairs them off.

A team member should not be paired with someone in the same team.

Once a team member is paired they are unavailable to be paired again.

This will continue until all members are paired off. 

It will create an output file of paired team members.

### Dependencies

This is a Java/Maven project.

This used TestNG for its unit test framework.

### Installing

Download the code and open in IntelliJ or build using Maven.

### Executing program

Run from IntelliJ or Maven.

## Help

I think it might be possible for the code to get 'stuck' and you end up unable to pair everyone if you have one team remaining with unpaired people who can't be paired together.

To avoid this I added a 'retry' mechanism when trying to find the second team with unpaired members where if this fails X number of times we throw away all pairs and try again.

I ran this as a test with the sample data and left the code running in a loop and it failed to deadlock for over 2 Billion(!) iterations. 

## Version History

1.0.0.0 Initial version

