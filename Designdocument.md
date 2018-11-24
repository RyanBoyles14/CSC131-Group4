# Software Requirements Specification
### Metrics Application for Github Repositories
### Version 0.1

### Group 4
#### Johnny Au
#### Ryan Boyles
#### Tommy Tran
#### Nathan Whitfield
#### Zining Yang

### CSC 131 - Software Engineering

### 1. Table of Contents:
    1.  Table of Contents
    2.  Introduction
        2.1.    Purpose
        2.2.    Scope
        2.3.    Definitions
        2.4.    References
        2.5.    Overview
    3.  Overall Description
        3.1.    Product Perspective
        3.2.    Product Functions
        3.3.    User Characteristics
        3.4.    General Constraints
        3.5.    Assumptions and Dependencies
    4.  Detailed Requirements
        4.1.    External Interface Requirements
        4.2.    Functional Requirements
        4.3.    Performance Requirements
        4.4.    Design Constraints
        4.5.    Attributes
        4.6.    Other Requirements

### 2. Introduction:

##### 2.1. Purpose
    This CLI application will allow the user to find metrics for a GitHub repository by providing a link. The metrics include counting lines, words, and characters, Halstead Metrics, Depth of Inheritance, Coupling Between Classes, Method Time Complexity, and Git Commits. The user can select which metrics to display when running the application.
    
##### 2.2. Scope
    The user can only submit GitHub repository links and can only request the specified metric options. Only C, C++, and Java files will be processed from the given repository.
    
##### 2.3. Definitions
    - Lines:                    Count all lines in a given file
    - Words:                    Count all words in a given file
    - Characters:               Count all characters a given file
    - Halstead Metrics:         Find...
    - Depth of Inheritance:     Find the depth of inheritance of local Java and C++ files.
    - Coupling Between Classes: Find...
    - Method Time Complexity:   Find...
    - Git Commits:              Find...
    
##### 2.4. References
[JGit](www.eclipse.org/jgit/): We used the external library JGit to clone a repository from Git. This allows us to clone all files using a Git url and calculate metrics on the files in the repository.
[ANTLR](https://antlr.org): We used the external library ANTLR 4 to generate Java files to parse through Java, C, and C++ files.
   
##### 2.5. Overview
    
### 3. Overall Description

##### 3.1. Product Perspective
    
##### 3.2. Product Functions
    When running the application, the user must include a link to a Git repository. The user has the option to choose specific metrics to find.
    
##### 3.3. User Characteristics
    
##### 3.4. General Constraints
    The user will require an internet connection for the application to grab a Git repository.
    
##### 3.5. Assumptions and Dependencies
    We are assuming the user has enough memory to temporarily hold the cloned files from the Git repository.

### 4. Detailed  Requirements 
        
##### 4.1. External Interface Requirements
    
##### 4.2. Functional Requirements
       
##### 4.3. Performance Requirements
    
##### 4.4. Design Constraints

##### 4.5. Attributes

##### 4.6. Other Requirements
