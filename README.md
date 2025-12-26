# Test_Utilities

This project contains many utilities classes/methods to test java core functions , any other open source functions etc.

## Project Overview

This Java 21 project demonstrates core Java functionality including:
- Concurrency utilities (ReentrantLock, CountDownLatch, CyclicBarrier, ThreadLocal, etc.)
- Threading and thread pool examples
- Search algorithms (Binary search, quick search)
- Java memory model features (Volatile, CAS)
- Synchronization mechanisms

## Java Core Functionality Demonstrated

### Concurrency and Threading
- [ReentrantLockDemo.java](src/main/java/org/daodao/ReentrantLockDemo.java) - ReentrantLock usage with reentrancy, interruptibility, and timeout
- [ReentrantLockDemo2.java](src/main/java/org/daodao/ReentrantLockDemo2.java) - ReentrantLock with Conditions for thread communication
- [CountDownLatchDemo.java](src/main/java/org/daodao/CountDownLatchDemo.java) - Countdown latch for thread synchronization
- [CyclicBarrierDemo.java](src/main/java/org/daodao/CyclicBarrierDemo.java) - Cyclic barrier for thread coordination
- [ThreadLocalDemo.java](src/main/java/org/daodao/ThreadLocalDemo.java) - ThreadLocal usage
- [InheritableThreadLocalDemo.java](src/main/java/org/daodao/InheritableThreadLocalDemo.java) - InheritableThreadLocal usage

### Thread Pools
- [ThreadPoolDemo.java](src/main/java/org/daodao/ThreadPoolDemo.java) - Basic thread pool examples
- [ThreadPoolDemo2.java](src/main/java/org/daodao/ThreadPoolDemo2.java) - Advanced thread pool examples

### Search Algorithms
- [BinarySearch.java](src/main/java/org/daodao/BinarySearch.java) - Binary search implementation
- [BinaryTreeSearch.java](src/main/java/org/daodao/BinaryTreeSearch.java) - Binary tree search implementation
- [QuickSearch.java](src/main/java/org/daodao/QuickSearch.java) - Quick search implementation

### Java Memory Model and Synchronization
- [VolatileDemo.java](src/main/java/org/daodao/VolatileDemo.java) - Volatile keyword usage
- [CasExample.java](src/main/java/org/daodao/CasExample.java) - Compare-and-swap example

### Other Utilities
- [User.java](src/main/java/org/daodao/User.java) - Simple data class
- [HelloWorld.java](src/main/java/org/daodao/HelloWorld.java) - Basic hello world example
- [ParameterDemo.java](src/main/java/org/daodao/ParameterDemo.java) - Parameter passing demonstration
- [TestFunctions.java](src/main/java/org/daodao/TestFunctions.java) - Additional test functions

## Build Configuration

Built with Maven using:
- Java 21
- Lombok (for simplified code generation)
- SLF4J + Logback (for logging)
- JUnit Jupiter 5.10.1 (for testing)