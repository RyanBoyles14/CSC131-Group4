# Software Requirements Specification
### Metrics Application for Github Repositories
### Version 0.3

### Group 4 Authors
[Johnny Au](https://github.com/johnny-au)

[Nathan Whitfield](https://github.com/natewhitfield)

[Ryan Boyles](https://github.com/RyanBoyles14)

[Tommy Tran](https://github.com/hangrytommy)

[Kyle Yang](https://github.com/kyleYzn)

### CSC 131 - Software Engineering

##### Table Of Contents

1.0     [Introduction](#intro)

1.1.    [Purpose](#purpose)

1.2.    [Scope](#scope)

1.3.    [Definitions](#def)

1.4.    [References](#ref)

1.5.    [Overview](#overview)

2.0     [Overall Description](#desc)

2.1.    [Product Perspective](#perspective)

2.2.    [Product Functions](#functions)

2.3.    [User Characteristics](#userChar)

2.4.    [General Constraints](#genConstr)

2.5.    [Assumptions and Dependencies](#assumptions)

3.0     [Detailed Requirements](#detailedReq)

3.1.    [External Interface Requirements](#intReq)

3.2.    [Functional Requirements](#funcReq)

3.3.    [Performance Requirements](#perfReq)

3.4.    [Design Constraints](#designConstr)

3.5.    [Attributes](#attributes)

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
###### To be completed
    
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
---
        
<a name="intReq"></a>
#### 3.1 External Interface Requirements
###### To be completed
    
<a name="funcReq"></a>
#### 3.2. Functional Requirements
###### To be completed
       
<a name="perfReq"></a>
#### 3.3. Performance Requirements
###### To be completed
    
<a name="designConstr"></a>
#### 3.4. Design Constraints
###### To be completed

<a name="attributes"></a>
#### 3.5. Attributes
###### To be completed

<a name="otherReq"></a>
#### 3.6. Other Requirements
###### To be completed
