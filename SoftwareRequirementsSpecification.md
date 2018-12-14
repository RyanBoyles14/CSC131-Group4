# Software Requirements Specification
### Metrics Application for Github Repositories
### Version 1.0

### Group 4 Authors
[Johnny Au](https://github.com/johnny-au)

[Nathan Whitfield](https://github.com/natewhitfield)

[Ryan Boyles](https://github.com/RyanBoyles14)

[Tommy Tran](https://github.com/hangrytommy)

[Kyle Yang](https://github.com/kyleYzn)

### CSC 131 - Software Engineering

##### Table Of Contents

1.0     [Introduction](#intro)<br>
1.1.    [Purpose](#purpose)<br>
1.2.    [Scope](#scope)<br>
1.3.    [Definitions](#def)<br>
1.4.    [References](#ref)<br>
1.5.    [Overview](#overview)

2.0     [Overall Description](#desc)<br>
2.1.    [Product Perspective](#perspective)<br>
2.2.    [Product Functions](#functions)<br>
2.3.    [User Characteristics](#userChar)<br>
2.4.    [General Constraints](#genConstr)<br>
2.5.    [Assumptions and Dependencies](#assumptions)

3.0     [Detailed Requirements](#detailedReq)<br>
3.1   [External Interface Requirements](#intReq)<br>
 3.1.1   [User Interface](#userInt)<br>
 3.1.2   [Hardware Interface](#hardwarInt)<br>
 3.1.3   [Software Interface](#softwareInt)<br>
 3.1.4   [Communication Interface](#commInt)<br>
3.2.    [Functional Requirements](#funcReq)<br>
3.3.    [Performance Requirements](#perfReq)<br>
3.4.    [Design Constraints](#designConstr)<br>
3.5.    [Attributes](#attributes)<br>
3.6.    [Other Requirements](#otherReq)

<a name="intro"></a>
### 1.0 Introduction:
###### The introduction for this SRS document provides an overview on the entire document, including the purpose, scope, definitions, and references.
---
<a name="purpose"></a>
#### 1.1. Purpose
###### The purpose of this document is to cover all of the ideas and definitions that define our system, the requirements in respect to the user, and the intended use.
###### Its purpose is to provide an overview of the software, its parameters, uses, and goals. It describes the target audience, user interface, hardware and software requirements, and functionality.

<a name="scope"></a>
#### 1.2. Scope
###### This software is developed as a group project for a Computer Science 131 - Computer Software Engineering at California State University - Sacramento.
###### The scope of this project is to provide software metrics of a Git repository. Through providing a GitHub url, the user can obtain informative software metrics about Java, C, and C++ projects.

<a name="def"></a>
#### 1.3. Definitions
###### - Metrics                   Methods for measuring some statistics or properties
###### - Halstead Metrics:         Measurable properties of software, including length, difficulty, and bugs
###### - Depth of Inheritance:     The maximum length of a class to its root of inheritance
###### - Coupling Between Classes: The connection between classes
###### - Method Time Complexity:   Time taken for a method to compute
###### - Git Commits:              Git contributors and their changes to a Git repository

<a name="ref"></a>
#### 1.4. References
###### Picocli: <https://picocli.info>:         Used to parse cli arguments
###### Maven: <https://maven.apache.org>:       Used to create the .jar file to run the software in CLI. It compiles the require external libraries and packages them with our developed classes.
###### JGit: <https://www.eclipse.org/jgit>:    Used to clone a repository from Git. This allows us to clone all files using a Git url and calculate metrics on the files in the repository.
###### ANTLR: <https://antlr.org>:              Used to generate Java files based on Java and C++ grammar. Used to build a parse tree for interpreting Java and C++ files.

<a name="overview"></a>
#### 1.5. Overview
###### The rest of this document provides a general description of the software in Section 2, including product functionality, characteristics of the user, and the assumptions, dependencies, and constraints of the hardware and software.
###### Section 3 gives the requirements of the system, including functional and external interface requirements.

<a name="desc"></a>
### 2.0 Overall Description
###### This software takes in a GitHub url provided by the user. With the url, it will find the Git repository, clone its files locally, and compute metrics on those files. Some metrics only work on Java, C, and C++ files.
---

<a name="perspective"></a>
#### 2.1. Product Perspective
###### The software cannot work on its own. It requires JGit to locate Git repositories and clone files to a local directory.
###### The depth of inheritance metric requires ANTLR 4 to generate Java files based on Java and C++ grammar, to build parse trees for Java and C++ files, and to walk through the parse trees.

<a name="functions"></a>
#### 2.2. Product Functions
###### When running the application, the user must include a link to a Git repository. The user has the option to choose specific metrics to find.
###### The options include:
    -c, --coupling          Finds coupling between classes
    -C, --commits           Gathers Git contributions and authors
    -h, --help              Displays instructions on running the software
    -H, --halstead          Computes Halstead metrics
    -I, --inheritanceDepth  Finds the depth of inheritance of classes
    -t, --timeComplexity    Finds the time complexity of methods
###### The metrics will be saved to txt files in the Group4 directory

<a name="userChar"></a>
#### 2.3. User Characteristics
###### The users expected to use the software are those knowledgeable of GitHub. As such, they are required to provide a valid GitHub URL for the software to run.

<a name="genConstr"></a>
#### 2.4. General Constraints
###### The user will require an internet connection for the application to grab a Git repository.

###### The user requires enough hardware space for temporarily holding cloned Git files

<a name="assumptions"></a>
#### 2.5. Assumptions and Dependencies
###### - This software requires the user to have Maven installed and implemented to package our software with external libraries.

###### - The depth of inheritance metrics requires parsed files to work with Java 8 or C++14 to generate an accurate result.

###### - We are assuming the user has enough memory to temporarily hold the cloned files from the Git repository.

###### - We are assuming the Java, C, and C++ files in given Git repositories function and can be properly parsed.

<a name="detailedReq"></a>
### 3.0 Detailed  Requirements
###### This section of the SRS details the requirements necessary for the software to function and what is expected of the different use cases
---

<a name="intReq"></a>
#### 3.1 External Interface Requirements
###### The external interfact requirements in this section specify the interface of the software in regards to users, other software, and hardware.

<a name="userInt"></a>
#### 3.1.1 User Interface
###### The user interacts with this software through running a .jar file on a command line. When running the command, the user has the option of choosing specific metrics to return.

<a name="hardwareInt"></a>
#### 3.1.2 Hardware Interface
###### The software will save metrics results to .txt files within the Group4 repository for the user to view. The software also temporarily clones and stores Git repository files locally to run metrics on.

<a name="softwareInt"></a>
#### 3.1.3 Software Interface
###### The software works with Picocli, JGit, Maven, and ANTLR
(add version numbers)

<a name="commInt"></a>
#### 3.1.4 Communication Interface
###### The software communicates with Maven to package external libraries with our developed code into a .jar file. The software also requires a GitHub URL to clone Git repositories.

<a name="funcReq"></a>
#### 3.2. Functional Requirements
###### This section of the SRS specifies the functional requirements of the software in terms of use cases and their behavior with the software.
(Use cases require: request from actor to system, reply to request. Actor's point of view. Focus on interaction, not internal activities.)

`User requests metrics and options to the software`

| Use Case 1 (UC-1)      | User Interface
| -----------------      |--------------
| Primary Actor          |User
| Actor's Goal           |Receive metrics on a Git repository
| Brief Description      |Return metrics based on the User's input
| Scope                  |Driver, picocli
| Precondition           |User specified valid options and inputs
| Main Success Condition |Receives txt files of metrics
| Trigger                |User runs the software on cli

`Driver pulls files using a GitHub URL`

| Use Case 2 (UC-2)     | User Interface
| -----------------     | --------------
| Primary Actor         | Driver
| Actor's Goal          | Clone files from Git repository
| Brief Description     | Use a User's GitHub Url to clone a Git repository
| Scope                 | Driver, JGit
| Precondition          | User passed in URL
| Main Success Condition| Driver finds a repository on GitHub and successfully clones it
| Trigger               | User specified a valid GitHub URL

`Repository requests author stats for a repository`

| Use Case 3 (UC-3)      |User Interface
| -----------------      |--------------
| Primary Actor          |Repository
| Actor's Goal           |Retrieve that stats of repository contributors from AuthorStats
| Brief Description      |Communicate with Contributor to computer information commits
| Scope                  |Repository, JGit, Contributor, ContributorMetricsCalculator
| Precondition           |JGit cloned a GitHub repository
| Main Success Condition |ContributorMetricsCalculator computes and returns statistics on each contributor of the given repository
| Trigger                |User specified the author stats in cli

`Repository requests the inheritance depth of a list of files`

| Use Case 4 (UC-4)      |User Interface
| -----------------      |--------------
| Primary Actor          |Repository
| Actor's Goal           |Retrieve the inheritance depth of classes from DepthOfInheritance
| Brief Description      |Communicate with DepthOfInheritance to compute the inheritance depth of JavaC++ files
| Scope                  |Repository, DepthOfInheritance, DepthOfInheritanceMetricsCalculator, ANTLR
| Precondition           |An ArrayList of Git repository files is compiled
| Main Success Condition |DepthOfInheritanceMetricsCalculator computes each class' inheritance and returns a list of Class objects
| Trigger                |User specified the inheritance depth metric in cli

`Repository requests the class coupling of a list of files`

| Use Case 5 (UC-5)      |User Interface
| -----------------      |--------------
| Primary Actor          |Repository
| Actor's Goal           |Retrieve the coupling of classes from Coupling
| Brief Description      |Communicate with Coupling to compute the coupling of classes
| Scope                  |Repository, Coupling
| Precondition           |An ArrayList of Git repository files is compiled
| Main Success Condition |Coupling computes the coupling between classes and returns a list of ClassStats
| Trigger                |User specified the coupling metric in cli

`Repository requests the time complexity of a list of files`

| Use Case 6 (UC-6)      |User Interface
| -----------------      |--------------
| Primary Actor          |Repository
| Actor's Goal           |Retrieve a class' time complexity
| Brief Description      |Communicate with TimeComplexity to compute the time complexity of classes
| Scope                  |Repository, TimeComplexity
| Precondition           |An ArrayList of Git repository files is compiled
| Main Success Condition |TimeComplexity computes the time complexity between classes and returns the results
| Trigger                |User specified the time complexity metric in cli

`Repository requests the Halstead metrics of a list of files`

| Use Case 7 (UC-7)      |User Interface
| -----------------      |--------------
| Primary Actor          |Repository
| Actor's Goal           |Retrieve a class' time complexity
| Brief Description      |Communicate with Halstead to software metrics on Java, C, and C++ files
| Scope                  |Repository, Halstead, HalsteadMetricsCalculator
| Precondition           |An ArrayList of Git repository files is compiled
| Main Success Condition |HalsteadMetricsCalculator computes the software metrics and returns the results in a list of HalsteadBuilder objects
| Trigger                |User specified the time complexity metric in cli

`Output Metrics`

| Use Case 8 (UC-8)      |User Interface
| -----------------      |--------------
| Primary Actor          |Driver
| Actor's Goal           |Send output to stdout
| Brief Description      |Print the User's chosen metrics with stdout or to a JSON or XML file
| Scope			          |Communicate with XMLSerializer or JSONSerializer to convert the metric results into an output stream to then either print in stdout or save to a specified file
| Precondition           |User specified the output in cli, either through default options or specifying a valid file type or file name
| Main Success Condition |Print out all metric results using stdout
| Trigger                |All metric results are computed and returned


<a name="perfReq"></a>
#### 3.3. Performance Requirements
###### This section specifies the performance requirements of the system. These requirements can be either static or dynamic. Any factor that constrains or limits the system design is listed.

    3.3.1 Packaging Files
    The software should be built so the user can easily use Maven to package it into a .jar file and run it successfully.

    3.3.2 Options and URLs
    This software should properly deal with given options and URLs. If any user argument is not valid, it should give some kind of help message

<a name="designConstr"></a>
#### 3.4. Design Constraints
###### This section specifies all the constraints affecting the design, including security, fault tolerance, and standard compliance.

    3.4.1 Software Language
    The language used to built the software is Java.

    3.4.2 External Libraries
    The software relies on external libraries and must conform to the standards and designs of other developers

    3.4.3 Languages Supporter
    The software only parses and runs metrics on Java, C, and C++ files.


<a name="attributes"></a>
#### 3.5. Attributes
###### This section specifies the overall attributes the system should have.

    3.5.1 Accuracy
    The software should provide accurate results for a given Git repository's files

    3.5.2 Assessible Data
    The software should provide the user the data in an accessible format via text files.


<a name="otherReq"></a>
#### 3.6. Other Requirements
###### This sections specifies all requirements not listed in the previous sections.
    No other requirements exist as of this release.