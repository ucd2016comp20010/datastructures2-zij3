LAB1:

Q6:
The end of the circular linked list (tail) points back to the head. 
The tail of a singly linked list is NULL. \
Q7:
When the size of the data is unknown. When you need to edit the data frequently.\
Q8:
Case 1: Traffic lights. You need to go from green to yellow to red and back to green. The .next() changes the lights and the circular linked list makes sure it loops forever. \
Case 2: Image Carousels. On a website, when you want an endless loop of the same images. If you are on the last image and you click next, it loops back to the first image. \

LAB2:

Q2: 

Stack stackIn
Stack stackOut

enqueue(element){

    stackIn.push(element)
}
dequeue(){
    
    if stackOut.isEmpty():
        while stackIn is not empty:   
            temp = stackIn.pop()
            stackOut.push(temp)

    return stackOut.pop()
        
}

Q3:

ALGORITHM ReverseStack(Stack S):
// 1. Create two temporary stacks
Stack A
Stack B

    // 2. Move all elements from S to A
    // (Result: A holds elements in reverse order of S)
    WHILE S is NOT empty:
        element = S.pop()
        A.push(element)
    END WHILE

    // 3. Move all elements from A to B
    // (Result: B holds elements in original order of S)
    WHILE A is NOT empty:
        element = A.pop()
        B.push(element)
    END WHILE

    // 4. Move all elements from B back to S
    // (Result: S holds elements in reverse order)
    WHILE B is NOT empty:
        element = B.pop()
        S.push(element)
    END WHILE

END ALGORITHM

Q4:
Divide by 9



Trees:
Q1:
    h)
        Height is 5
        Diameter is 9

Q2:
    numLeaves():
        if isExternal():
            return 1
        else:
            return numLeaves(left) + numLeaves(right)

Q3:
    numLeftLeaves():
        if p == null:
            return 0

        if left.isExternal():
            return 1
        else:
            return numLeftLeaves(left) + numLeftLeaves(right)

Q4:
Preorder traversal:
    E
     \
      X
       \
        A
         \
          M
           \
            F
             \
              U
               \
                N
Inorder traversal:

    E
     \
      X
       \
        A
         \
          M
           \
            F
             \
              U
               \
                N
Postorder traversal:
N
 \
  U
   \
    F
     \
      M
       \
        A
         \
          X
           \
            E

Q5:
    numDescendants():
        return size - 1

ANALYSIS AND RECURSION:

Q1:
A = {12, 5, 19, 6, 11, 3, 9, 34, 2, 1, 15};

ReverseArray(A, 0, 10)
    A = {15, 5, 19, 6, 11, 3, 9, 34, 2, 1, 12};
ReverseArray(A, 1, 9)
    A = {15, 1, 19, 6, 11, 3, 9, 34, 2, 5, 12};
ReverseArray(A, 2, 8)
    A = {15, 1, 2, 6, 11, 3, 9, 34, 19, 5, 12};
ReverseArray(A, 3, 7)
    A = {15, 1, 2, 34, 11, 3, 9, 6, 19, 5, 12};
ReverseArray(A, 4, 6)
    A = {15, 1, 2, 34, 9, 3, 11, 6, 19, 5, 12};
ReverseArray(A, 5, 5)
    return
