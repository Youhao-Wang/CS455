// Name:
// loginid:
// CS 455 PA5

// pa5list.cpp
// a program to test the linked list code necessary for a hash table chain

// You are not required to submit this program for pa5.

// We gave you this starter file for it so you don't have to figure
// out the #include stuff.  The code that's being tested will be in
// listFuncs.cpp, which uses the header file listFuncs.h

// The pa5 Makefile includes a rule that compiles these two modules
// into one executable.

#include <iostream>
#include <string>
#include <cassert>

using namespace std;

#include "listFuncs.h"


int main() {
    
    ListType list;
    initList(list);
    insertEnd(list, "first", 1);
    insertEnd(list, "second", 2);
    insertEnd(list, "third", 3);
    printList(list);
    cout << sizeOfList(list) << endl;
    
    removeTarget(list, "second");
    printList(list);
    cout << sizeOfList(list) << endl;
    
    removeTarget(list, "third");
    printList(list);
    cout << sizeOfList(list) << endl;
    
    removeTarget(list, "first");
    printList(list);
    cout << sizeOfList(list) << endl;

    


  return 0;
}
