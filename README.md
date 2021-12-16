bundle-calculator
===================

Problem Description
-----------
[Click here](problem.md)

## Dependencies

- Project management tool: Gradle 7.3.1
- JDK Version: Java 8
- Dependencies: JUnit 5, Mockito, log4j2, etc.


## Usage
Artifact Name: bundle-calculator-1.0.jar
```
  Usage: 
    java -jar bundlecalcultor.jar <bundleFileName> <orderFileName>
```
### Sample
Use the bundles.cvs and order.txt in sampledata, it can be run like bellow:
```
➜  jar java -jar bundle-calculator-1.0.jar bundles.csv order.txt 
10 IMG $800.00
  1 x 10 $800.00
15 FLAC $1237.50
  1 x 6 $810.00
  1 x 3 $427.50
13 VID $2370.00
  2 x 5 $1800.00
  1 x 3 $570.00
```

## About the algorithms
The project implements 2 algorithms to break down the order to bundles.

### Greedy algorithm
First break the order down to as many largest bundles as posible, the go on and break down the reminder by using the next laregest bundle, and so on.
When the process is done, if there is a reminder, add an additional smallest bundle to round it up.

### Dynamic Programming
First use Dynamic Programming to find out the most posible combination of bundles for the order. 
When the process is done, if there is a reminder, add an additional smallest bundle to round it up.

### Conclusion:
The greedy algorithm can get the minimal number of bundles most of the time, but sometines the sum of the bundles will exceeds the order which cost extra fee.
possible result:
```
10 IMG $800.00
  1 x 10 $800.00
15 FLAC $1957.50
  1 x 9 $1147.50
  1 x 6 $810.00
13 VID $2670.00
  1 x 9 $1530.00
  2 x 3 $1140.00
```
The DP algorithm tries its best to break down the order into bundles without round up, and it works best for the sample data given in the problem description.
possible result:
```
10 IMG $800.00
  1 x 10 $800.00
15 FLAC $1237.50
  1 x 6 $810.00
  1 x 3 $427.50
13 VID $2370.00
  2 x 5 $1800.00
  1 x 3 $570.00
```
So that's why it is chosen for the solution.
