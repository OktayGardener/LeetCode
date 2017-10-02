# Linear Structures

# Array/Vector
### Common operations
* Find - O(n)
* Add - O(1)
* Remove - O(1)

### Common problems

# List
### Common operations
* Find - O(n)
* Add - O(1)
* Remove - O(1)

### Usecases

### Common problems

# Queue
First in first out ordering data structure, essentially working as a natural queue.

### Common operations
* add - O(1)
* removeFirst - O(1)
* isEmpty - O(1)

### Usecases

### Common problems


# Priority Queue
### Common operations

### Usecases

### Common problems


# Stack
Last in, first out ordering data structure, essentially working as a stack of plates.
### Common operations
* push - O(1)
* pop - O(1)
* isEmpty - O(1)

### Usecases

### Common problems


# Tree Structures

# Binary Tree
### Common operations

### Usecases

### Common problems

# Binary Search Tree
### Common operations

### Usecases

### Common problems

# Balanced Trees
### Common operations

### Usecases

### Common problems


# Red-Black Trees
### Common operations

### Usecases

### Common problems

# AVL Tree
### Common operations

### Usecases

### Common problems


# Trie
### Common operations

### Usecases

### Common problems

# Heap
### Common operations

### Usecases

### Common problems

# Graph
### Common operations

### Usecases

### Common problems


# Bloom Filter
Data structure for checking if a value exist in a big set.

* Use 14 hash functions, word->index in hashtable
* For each queried word, calculate f1(word),..,f14(word) and investigate these places
* Only accept if all indexes has a 1

### Common operations
* insert(x) - add to set
* isin(x) - check if exist in set

### Characteristics
* Operations take O(1)
* Small memory needed
* Elements not added explicitly
* Small probability that isin(x) is true even though its not in the set

### Usecases
* Storing word lists for checking grammar
* Storing browser history
