Group 4 Design Document
======

##### Audience:
Primarily programmers. As such, our program should focus on delivering metrics that pertain to performance. Alongside Halstead performance metrics, the user may want to know how many classes a file contains, the depth of inheritance for each class, and the amount of coupling occuring between classes.

##### Steps:
1. Use Picocli to parse cli
1. Use a library (possibly JGit) to traverse Git repositories based on a given link and grab their files
1. Parse through those files to get only the files with specified extensions
1. Create a FileObject with the repository files, which will then calculate specified metrics
1. Add the FileObject into an ArrayList to store for displaying metrics.

##### Software Metrics:
* Halstead
* Coupling
* Number of classes
* Number of files and extensions
* Depth of inheritance 

