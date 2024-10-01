# CSC172 - Data Structures and Algorithms
## Lab 1: Java Review and Generics

---

### Author Information
- **Name:** Eray BOZOGLU
---

### Lab Synopsis

This lab focuses on reviewing Java programming concepts and introducing generics. The main objectives are:

1. Implement and understand generic methods for printing arrays and finding maximum values
2. Explore the use of lambda functions

The lab demonstrates the flexibility and type safety provided by Java generics, allowing for the creation of reusable code that works with different data types. It also touches on functional interfaces and lambda expressions, showcasing modern Java programming techniques.

---

### File Contents

- `LabOne.java`: Contains the main implementation of generic methods for printing arrays and finding maximum values.
- `LabOneFn.java`: Demonstrates the use of lambda functions with a focus on finding the maximum character in an array.

---

### Compilation and Execution

#### For LabOne.java:

1. Compile the code:
   ```
   javac LabOne.java
   ```
2. Run the compiled program:
   ```
   java LabOne
   ```

#### Expected Output for LabOne.java:
```
1 2 3 4 5 
1.1 2.2 3.3 4.4 
H E L L O 
once upon a time 
max Integer is: 5
max Double is: 4.4
max Character is: O
max String is: upon
Process finished with exit code 0
```

#### For LabOneFn.java:

1. Compile the code:
   ```
   javac LabOneFn.java
   ```
2. Run the compiled program:
   ```
   java LabOneFn
   ```

#### Expected Output for LabOneFn.java:
```
max Character is: O
```

---

### Notes

- This lab demonstrates the use of Java generics to create flexible, type-safe methods that work with different data types.
- The `LabOneFn.java` file showcases the use of lambda functions, a feature introduced in Java 8, for finding the maximum character in an array.
- Ensure you have Java Development Kit (JDK) installed on your system to compile and run these programs.
- If you encounter any issues during compilation or execution, verify your Java installation and ensure you are in the correct directory containing the .java files.
