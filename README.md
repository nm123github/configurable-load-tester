Small utility/framework written to help load test systems..
(This was written awhile back, just porting code from [google code](https://code.google.com/archive/p/configurable-load-tester/).)

## Compile/Download
```
mvn clean install
```
should create a bin\lib folder structure with the executables.

## Run

Goto the bin\lib directory and run the loadperformancetester-1.0-SNAPSHOT.jar using the command
```
java -jar loadperformancetester-1.0-SNAPSHOT.jar
```

The load tester runs in an infinite loop so you would need to stop it yourself after it finishes.

## Results

After running the code you will notice two directories created namely c:\loadtesterout and c:\loadtestertasks. loadtesterout contains performance related data for the tasks and loadtestertasks contains the task objects saved onto disk. This means the tester ran successfully.
