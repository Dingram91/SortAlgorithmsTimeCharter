# SortAlgorithmsTimeCharter
This program generates data and creates a chart comparing the execution time of different sort algorithms. Arguments can be passed in with the command line args to test on 
different sizes and arangements of data. The program generates a .jpeg chart comparing NumberCount vs. SortTime as well as a log file containing the sort times for each sort run. This project was completed as an assigment for the Algorithms (COT 3400) at FGCU.

## Running
The project is run from the command line with arguments passed in on the command line. Running just the jar will print an example input for the program.

### Example Input
"java -jar SortAlgorithmsTimeCharter-1.0.jar  -numberCount 10000 100000 1000000 -numberLength 50000 -preSort forward reverse random"
This input would generate 3*3*1 = 9 sets of numbers. One for each numberCount numberLength and presort combination. The program would then sort the each set of numbers with 
merge sort and insert sort and then generate a graph comparing the dataSize vs executionTime for each run.

### Example Output

![Example output sort times comparison chart](SortTimesChart.jpeg)

### License

MIT License

Copyright (c) [year] [fullname]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
