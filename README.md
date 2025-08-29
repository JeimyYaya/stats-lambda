# Statistics Calculator with Custom Linked List
- Jeimy Yaya
## Project Overview
The goal of this project was to implement a program in Java that calculates the **mean** and **sample standard deviation** of a set of real numbers. The program reads the data from a file, stores the numbers in a **custom implementation of a singly linked list**, and performs statistical calculations.  

The requirements included:
- Using **Maven** for project management.
- Hosting the project on **GitHub**.
- Writing a **custom linked list** that complies with the `java.util.List` interface.
- Testing the program thoroughly.

---

## Project Structure

The project is organized into three main components:

### 1. `SinglyLinkedList<E>` (Custom Linked List)
- Package: `edu.eci.stats.collections`
- Implements the `java.util.List<E>` interface.
- Stores elements using nodes (`Node<E>`) with references to the next element.
- Provides common operations such as:
  - `add(E e)`
  - `remove(Object o)`
  - `get(int index)`
  - `iterator()`
- Some non-essential methods from the `List` interface are marked as **unsupported**.

This class ensures that the numbers are stored using our own data structure instead of Java’s built-in `LinkedList`.

---

### 2. `Stats` (Statistics Utility Class)
- Package: `edu.eci.stats`
- Provides two static methods:
  - `mean(Collection<Double> data)` → calculates the arithmetic mean.
  - `sampleStdDev(Collection<Double> data)` → calculates the sample standard deviation (using n−1 in the denominator).
- Uses **Java Streams** for concise numeric computations:
  ```java
  return data.stream().mapToDouble(Double::doubleValue).average().orElse(Double.NaN);
---
### 3. `StatsApplication` (Main Program)
- Package: `edu.eci.stats`   
- Reads input data from a file (`input.txt`) located in `src/main/resources/`.

#### Formato del archivo:
  ```
C   # number of cases
N   # number of values in the first case
v1
v2
...
N   # number of values in the second case
...
  ```
Example:
 ```
1
3
4
5
6
 ```
For each case, it calculates and prints the mean and standard deviation with two decimals:
 ```
Case 1:
Mean = 5.00
StdDev = 1.00
 ```
---

### Key Concepts Used
- **Custom Linked List:** Reinforced understanding of nodes, pointers, and data structures.
- **Generics (<E>):** Enabled type safety in the linked list, making it reusable for any type of object.
- **Java Collections API Compliance:** Ensured the custom list works like any other List in Java.
- **Streams and Lambdas:** Used in the Stats class for concise and efficient calculations.
- **Maven:** Used as the build automation and dependency management tool.
- **GitHub:** Version control and project hosting.

