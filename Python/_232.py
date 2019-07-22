from collections import deque


class MyQueue:
    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.s = []

    def push(self, x: int) -> None:
        """
        Push element x to the back of queue.
        """
        self.s.insert(0, x)

    def pop(self) -> int:
        """
        Removes the element from in front of queue and returns that element.
        """
        if self.s:
            return self.s.pop()
        return None

    def peek(self) -> int:
        """
        Get the front element.
        """
        if self.s:
            return self.s[-1]
        return None

    def empty(self) -> bool:
        """
        Returns whether the queue is empty.
        """
        return self.s == []


# Your MyQueue object will be instantiated and called as such:
# obj = MyQueue()
# obj.push(x)
# param_2 = obj.pop()
# param_3 = obj.peek()
# param_4 = obj.empty()
