// Name: Youhao Wang	
// Loginid: youhaowa
// CSCI 455 PA5
// Fall 2016


//*************************************************************************
// Node class definition 
// and declarations for functions on ListType

// Note: we don't need Node in Table.h
// because it's used by the Table class; not by any Table client code.


#ifndef LIST_FUNCS_H
#define LIST_FUNCS_H
  
using namespace std;

struct Node {
  string key;
  int value;

  Node *next;

  Node(const string &theKey, int theValue);

  Node(const string &theKey, int theValue, Node *n);
};


typedef Node * ListType;

//*************************************************************************
//add function headers (aka, function prototypes) for your functions
//that operate on a list here (i.e., each includes a parameter of type
//ListType or ListType&).  No function definitions go in this file.


//make the empty list
void initList(ListType &list);


// inserts val and the key at the end of list
// PRE: list is a well-formed list
void insertEnd(ListType &list, string key, int val);


// romove the Node which key is target
bool removeTarget(ListType &list, string target);



//print all the Node in the list
void printList(ListType list);


// Returns the size of the given list.
// PRE: Assuming the list size is <= INT_MAX.

int sizeOfList(ListType list);

// Return the address of target key
int *lookUpValue(ListType list, string target);


// keep the following line at the end of the file
#endif
