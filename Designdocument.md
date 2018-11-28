# Software Requirements Specification
### Metrics Application for Github Repositories
### Version 0.1

### Group 4 Authors
[Johnny Au](https://github.com/johnny-au)

[Nathan Whitfield](https://github.com/natewhitfield)

[Ryan Boyles](https://github.com/RyanBoyles14)

[Tommy Tran](https://github.com/hangrytommy)

[Kyle Yang](https://github.com/kyleYzn)

### CSC 131 - Software Engineering

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
---
<a name="purpose"></a>
#### 1.1. Purpose
    This CLI application will allow the user to find metrics for a GitHub repository by providing a link. The metrics include counting lines, words, and characters, Halstead Metrics, Depth of Inheritance, Coupling Between Classes, Method Time Complexity, and Git Commits. The user can select which metrics to display when running the application.
    
<a name="scope"></a>
#### 1.2. Scope
    The user can only submit GitHub repository links and can only request the specified metric options. Only C, C++, and Java files will be processed from the given repository.
    
<a name="def"></a>
#### 1.3. Definitions
    - Lines:                    Count all lines in a given file
    - Words:                    Count all words in a given file
    - Characters:               Count all characters a given file
    - Halstead Metrics:         Find...
    - Depth of Inheritance:     Find the local depth of inheritance for Java and C++ classes.
    - Coupling Between Classes: Find...
    - Method Time Complexity:   Find...
    - Git Commits:              Find...
    
<a name="ref"></a>
#### 1.4. References
[JGit](www.eclipse.org/jgit/): We used the external library JGit to clone a repository from Git. This allows us to clone all files using a Git url and calculate metrics on the files in the repository.
[ANTLR](https://antlr.org): We used the external library ANTLR 4 to generate Java files to parse through Java, C, and C++ files.
   
<a name="overview"></a>
#### 1.5. Overview

<a name="desc"></a>
### 2.0 Overall Description
---

<a name="perspective"></a>
#### 2.1. Product Perspective
    
<a name="functions"></a>
#### 2.2. Product Functions
    When running the application, the user must include a link to a Git repository. The user has the option to choose specific metrics to find.

<a name="userChar"></a>
#### 2.3. User Characteristics
    
<a name="genConstr"></a>
#### 2.4. General Constraints
    The user will require an internet connection for the application to grab a Git repository.
    
<a name="assumptions"></a>
#### 2.5. Assumptions and Dependencies
    We are assuming the user has enough memory to temporarily hold the cloned files from the Git repository.

    We are assuming the user's Java, C, and C++ files function and can be properly parsed.

<a name="detailedReq"></a>
### 3.0 Detailed  Requirements 
---
        
<a name="intReq"></a>
#### 3.1 External Interface Requirements
    
<a name="funcReq"></a>
#### 3.2. Functional Requirements
       
<a name="perfReq"></a>
#### 3.3. Performance Requirements
    
<a name="designConstr"></a>
#### 3.4. Design Constraints

<a name="attributes"></a>
#### 3.5. Attributes

<a name="otherReq"></a>
#### 3.6. Other Requirements
