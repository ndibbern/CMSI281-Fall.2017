# CMSI 281: Homework 3



## Problem 1

<u>**Let n = u = uniqueSize**</u>

### removeAll:

##### find:

```java
private int find (String s) {
        for (int i = 0; i < uniqueSize; i++) { //n
            if (items[i].text.equals(s)) { // C1
                return i;					// C2
            }
        }
        return -1;
    }
```

$T(n) = n(C1+C2) \implies O(n)$ 

##### removeFromBack:

```java
    private void replaceFromBack (int index) {
        items[index] = items[uniqueSize-1]; //C1
        items[uniqueSize-1] = null; //C2
    }
```

$T(n) = C1 + C2 \implies O(1)$ 

##### removeOccurrances:

```java
 private int removeOccurrences (String text, int count) {
        int index = find(text); //n
        
        // Case: no such string toRemove
        if (index == -1) {     //C1
            return 0;
        }
        
        Strand found = items[index];       // C2
        int newCount = found.count - count;//C3
        
        // Case: last instance to remove
        if (newCount <= 0) {// everything in here is constant lets just say C4 (all together)
            replaceFromBack(index); // it is constant (analysed before)
            size -= found.count;
            uniqueSize--;
            return 0;
        
        // Case: more than 1 Strand left
        } else {           // everything in here is also constatnso either way is constant worst case ( we will just keep the C4 above)
            found.count = newCount;
            size -= count;
            return newCount;
        }
    }
```

$T(n)= n+C1+C2+C3+C4 \implies O(n)$ 

##### Then removeAll is...

```java
public void removeAll (String toNuke) {
        int index = find(toNuke); // n
        if (index != -1) { //C1
            removeOccurrences(toNuke, items[index].count); //n
        }
    }
```

$T(n) = n + C1n \implies O(n)$ 

### getNth:

```java
public String getNth (int n) {
        if (n >= size || n < 0) {                    //C1
            throw new IllegalArgumentException();    //C2
        }
        
        for (int i = 0; i < uniqueSize; i++) {    //n
            Strand currentStrand = items[i];     //C3
            if (n < currentStrand.count) {      //C4 
                return currentStrand.text;     //in worst case should never get here
            } else {
                n -= currentStrand.count;      //C5
            }
        }
        
        // Should never get here...
        return null;                        //C6
    }
```

$T(n) = C1+C2+n(C3+C4 + C5)+ C6 \implies O(n)$ 

## Problem 2

<u>**Let n =u = uniqueSize**</u>

### insert:

##### prependNode

```java
    private void prependNode (Node n) {
        Node oldHead = head;      //C1
        head = n;                //C2
        if (oldHead != null) {   //C3
            head.next = oldHead;  //C4
            oldHead.prev = head;   //C5
        }
    }
```

$T(n) = C1+C2+C3+C4+C5 \implies O(1)$ 

##### find

```java
private Node find (String toFind) {
        for (Node curr = head; curr != null; curr = curr.next) { //n
            if (curr.text.equals(toFind)) {  //C2
                return curr;				//worst case never gets gere
            }
        }
        return null;                      //C3
    }
```

$T(n) = nC2 + C3 \implies O(n)$ 

##### insertOccurrences:

```java
    private boolean insertOccurrences (String text, int count) {
        Node found = find(text);   //n
        
        // Case: new string, so add new Node
        if (found == null) {//C1(both cases in if and else are constant so either case worst case constant) lets just consider it goes in the if statement
            prependNode(new Node(text, count));           //C2
            uniqueSize++;                                 //C3
            
        // Case: existing string, so update count
        } else {
            found.count += count;                     
        }
        size += count;                             		//C4
        modCount++;										//C5
        
        return true;									//C6
    }
```

$T(n) = n+ C1+C2+C3+C4+C5+C6 \implies O(n)$ 

```java
public void insert (String toAdd) {
        insertOccurrences(toAdd, 1); //n
    }
```

$T(n)= n \implies O(n)$

### swap:

```java
public void swap (LinkedYarn other) {
        Node tempHead = head;            //C1
        int tempSize = size,            //C2
            tempUniqueSize = uniqueSize; //C3

        head = other.head;               //C4
        size = other.size;				//C5
        uniqueSize = other.uniqueSize;  //C6

        other.head = tempHead;          //C7
        other.size = tempSize;         //C8
        other.uniqueSize = tempUniqueSize;   //C9
        modCount++;                       //C10
        other.modCount++;                //C11
    }
```

$T(n)=C1+C2+C3+C4+C5+C6+C7+C8+C9+C10+C11 \implies O(1)$

## Problem 3

<u>**Let u1,u2 = uniqueSizes y1, y2 respectively**</u>

### knit:



##### Constructor:

```java
    LinkedYarn (LinkedYarn other) {
        for (Node n = other.head; n != null; n = n.next) { //u1
            prependNode(new Node(n.text, n.count)); //C1 (constant from above's analysis)
            size += n.count; //C2
            uniqueSize++; //C3
        }
    }
```

$T(n) =u1(C1 + C2+C3) \implies O(u1)$ 

```java
    public static LinkedYarn knit (LinkedYarn y1, LinkedYarn y2) {
        LinkedYarn result = new LinkedYarn(y1); //u1
        for (Node n = y2.head; n != null; n = n.next) { //u2
            result.insertOccurrences(n.text, n.count);  //u1
        }
        return result; //C1
    }
```

$T(n) = u1+ (u2u1)+C1 = u1^2 + u1u2+C1 \implies O(u1u2) $

### 2



```java
  /*
   * commonThreads returns a new LinkedYarn composed of all
   * occurrences that are common between y1 and y2
   * [X] Warning: this implementation is not very good
   */
  public static LinkedYarn commonThreads (LinkedYarn y1, LinkedYarn y2) {
      LinkedYarn result = new LinkedYarn(),
                 y2Clone = y2.clone();
      
      for (LinkedYarn.Iterator i1 = y1.getIterator(); i1.hasNext(); i1.next()) {
          String current = i1.getString();
          if (y2Clone.contains(current)) {
              result.insert(current);
              y2Clone.remove(current);
          }
      }
      
      return result;
  }
```

### 3



```java
  /*
   * Alternative implementation of the above that is slightly better
   */
  public static LinkedYarn betterCommonThreads (LinkedYarn y1, LinkedYarn y2) {
      LinkedYarn result = new LinkedYarn();
      for (Node curr1 = y1.head; curr1 != null; curr1 = curr1.next) {
          String text = curr1.text;
          int count1 = curr1.count,
              count2 = y2.count(text);
          if (count2 > 0) {
              result.insertOccurrences(text, Math.min(count1, count2));
          }
      }
      
      return result;
  }
```

