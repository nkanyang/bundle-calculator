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
15 FLAC $1957.50
  1 x 9 $1147.50
  1 x 6 $810.00
13 VID $2370.00
  2 x 5 $1800.00
  1 x 3 $570.00
```

## About the algorithms
The project implements 2 algorithms to break down the order to bundles.

### 1. Greedy algorithm
First break the order down to as many largest bundles as posible, then go on and break down the reminder by using the next laregest bundle, and so on.
When the process is done, if there is a reminder, add an additional smallest bundle to round it up.
Result for sample data:
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
### 2. Dynamic Programming
First use dynamic programming to find out the most possible combination of bundles for the order. 
When the process is done, if there is a reminder, add an additional smallest bundle to round it up.
Result for sample data:
```
10 IMG $800.00
  1 x 10 $800.00
15 FLAC $1957.50
  1 x 9 $1147.50
  1 x 6 $810.00
13 VID $2370.00
  2 x 5 $1800.00
  1 x 3 $570.00
```
### Conclusion:
The greedy algorithm can get the minimal number of bundles most of the time, but sometimes the sum of the bundles will exceed the order which will end up costing more than necessary.

The DP algorithm tries its best to break down the order into bundles without rounding up, and it works best for the sample data given in the problem description.

So that's why I use DP as a solution for order breakdown.
