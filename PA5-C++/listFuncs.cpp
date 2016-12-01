// Name: Youhao Wang	
// Loginid: youhaowa
// CSCI 455 PA5
// Fall 2016


#include <iostream>

#include <cassert>

#include "listFuncs.h"

using namespace std;

Node::Node(const string &theKey, int theValue) {
  key = theKey;
  value = theValue;
  next = NULL;
}

Node::Node(const string &theKey, int theValue, Node *n) {
  key = theKey;
  value = theValue;
  next = n;
}




//*************************************************************************
// put the function definitions for your list functions below


void initList(ListType &list){
    list = NULL;
}



void insertEnd(ListType &list, string key, int val){
    Node *p = list;
    
    if( p == NULL){
        Node *n = new Node(key, val);
        list = n;
        return;
    }
    
    while(p -> next != NULL){
        p = p ->next;
    }
    
    Node *n = new Node(key,val);
    p->next = n;
    
}


bool removeTarget(ListType &list, string target){
    if(list == NULL){
        return false;
    }
    
    Node *p = list;
    if(p->key == target){
        list = list -> next;
        delete p;
        return true;
    }
    
    while(p -> next != NULL && p -> next -> key != target){
        p = p-> next;
    }
    
    if(p -> next == NULL){
        return false;
    }
    else{
        Node *temp = p;
        p = p -> next;
        temp-> next = temp-> next -> next;
        delete p;
        return true;
        
    }
}


void printList(ListType list){
    if(list == NULL){
        return;
    }
    
    Node *p = list;
    while(p != NULL){
        cout << p->key << " " << p->value << endl;
        p = p -> next;
    }
}


int sizeOfList(ListType list){
    int count = 0;
    if(list == NULL){
        return count;
    }
    
    Node *p = list;
    while(p != NULL){
        count ++;
        p = p -> next;
    }
    return count;
}

int *lookUpValue(ListType list, string target){
    if(list == NULL)
        return NULL;
    
    while(list != NULL){
        if(list -> key == target){
            return (& (list-> value));
        }
        list = list -> next;
    }
    
    return NULL;
}
