###### CMSI 281: Data Structures

# HW2 Report:

In my opinion programming both the sequential list and the linked list felt really similar. Sequential list was more intuitive since I am more used to working with arrays than I do with references. Furthermore, eventhough we went through the iterator class in class, it was not as intuitive how to do all the methods such that they would work with the LinkedYarn.

On the other hand, I feel like some operatations' logic (such as add, remove all, remove) where harder to do on the Yarn class given that we had no prior experience of how a Yarn worked. When I got to implement the LinkedYarn, I already had an intuition of the steps that such methods needed to follow in order to work correctly, the only thing that changed was doing it in a Linked List rather than an array, but the algorithm was the same. Because of this, I believe the level of "difficulty" between both balanced out such that both were at the same level.

I wold rather use a linked list implementation on a scenario where I do not know a priori how many items will be on the list (and know that it will be large).  An array list would have to extend its size many times if the size is large but for a linked list there is no need. Furthermore I would use linked lists in scenarios where I want to do a stack or queue. In a sequential list this requires constant shifting of the values (either to the right or left). However, on a LinkedList there is no need to do this (can be done easily).

On the other hand I would use an array list in a scenario where I would like to have random access in constant time (need an iterator for LL). For example, I had to code a program last semester for CMSI 282 where ecah time I had to access the last item in a list of items. I used an arraylist for this given that I had to do this so many times.

