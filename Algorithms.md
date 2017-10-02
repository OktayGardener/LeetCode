# Sorting

### Insertion Sort - O(n^2)
* Put first element in first place
* For each new element x:
    * Find spot where x should be
    * Shift all elements to that spot to the right
    * Insert x

### Selection Sort - O(n^2)
* Find the smallest element
* Swap smallest element with the first element
* Do same for rest




### Merge Sort - O(n log n)
* Recursively call itself on left and right halves
* Use final Merge operation to sort into place

```
mergesort(v[i..j]) =
if i < j:
    m = floor(i + j / 2)
    mergesort(v[i..m])
    mergesort(v[m+1..j])
    merge(v[i..j], v[i..m], v[m+1..j])
```

### QuickSort - O(n log n)
Given array A[l..r]:
* Partition A after an element x at index i:
    * All elements < than x to the left
    * All elements > than x to the right
* Call QuickSort recursively on the left side:
    * from index l to i - 1
* Call QuickSort recursively on the right side:
    * from index i + 1 to r
* Stop recursing when l >= r (one element left)
