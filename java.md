# Why is Java called the ‘Platform Independent Programming Language’
Platform independence means that execution of your program does not dependent on type of operating system(it could be any : Linux, windows, Mac ..etc)

# Explain Final keyword in java
Final keyword in java is used to restrict usage of variable, class and method.

Variable: Value of Final variable is constant, you can not change it.
Method: you can’t override a Final method.
Class: you can’t inherit from Final class.


# When is the super keyword used?
super keyword is used to refer:

immediate parent class constructor,
immediate parent class variable,
immediate parent class method.

# What is the difference between StringBuffer and String?
String is an Immutable class, i.e. you can not modify its content once created. While StringBuffer is a mutable class, means you can change its content later.

# Why multiple inheritance is not supported in java?
Java supports multiple inheritance but not through classes, it supports only through its interfaces. The reason for not supporting multiple inheritance is to avoid the conflict and complexity arises due to it and keep Java a Simple Object Oriented Language. If we recall this in C++, there is a special case of multiple inheritance (diamond problem) where you have a multiple inheritance with two classes which have methods in conflicts. So, Java developers decided to avoid such conflicts and didn’t allow multiple inheritance through classes at all.

# Can a top level class be private or protected?
Top level classes in java can’t be private or protected, but inner classes in java can. The reason for not making a top level class as private is very obvious, because nobody can see a private class and thus they can not use it. Declaring a class as protected also doesn’t make any sense. The only difference between default visibility and protected visibility is that we can use it in any package by inheriting it. Since in java there is no such concept of package inheritance, defining a class as protected is no different from default.

# What is the difference between ‘throw’ and ‘throws’ in Java Exception Handling?
Following are the differences between two:

throw keyword is used to throw Exception from any method or static block whereas throws is used to indicate that which Exception can possibly be thrown by this method
If any method throws checked Exception, then caller can either handle this exception(using try catch block )or can re throw it by declaring another ‘throws’ clause in method declaration.
throw clause can be used in any part of code where you feel a specific exception needs to be thrown to the calling method

throw
throw new Exception(“You have some exception”)
throw new IOException(“Connection failed!!”)

throws
throws IOException, NullPointerException, ArithmeticException

# What is finalize() method?
Unlike c++ , we don’t need to destroy objects explicitly in Java. ‘Garbage Collector‘ does that automatically for us. Garbage Collector checks if no references to an object exist, that object is assumed to be no longer required, and the memory occupied by the object can be freed. Sometimes an object can hold non-java resources such as file handle or database connection, then you want to make sure these resources are also released before object is destroyed. To perform such operation Java provide protected void finalize() in object class. You can override this method in your class and do the required tasks. Right before an object is freed, the java run time calls the finalize() method on that object.

# Difference in Set and List interface
Set and List both are child interface of Collection interface. There are following two main differences between them

List can hold duplicate values but Set doesn’t allow this.
In List interface data is present in the order you inserted but in the case of Set insertion order is not preserved.

# What will happen if you put System.exit(0) on try or catch block? Will finally block execute?

By Calling System.exit(0) in try or catch block, we can skip the finally block. System.exit(int) method can throw a SecurityException. If Sysytem.exit(0) exits the JVM without throwing that exception then finally block will not execute. But, if System.exit(0) does throw security exception then finally block will be executed.
