// Name:Youhao Wang
// Loginid: youhaowa
// CSCI 455 PA5
// Fall 2016

// Table.cpp  Table class implementation


/*
 * Modified 11/22/11 by CMB
 *   changed name of constructor formal parameter to match .h file
 */

#include "Table.h"

#include <iostream>
#include <string>
#include <cassert>


// listFuncs.h has the definition of Node and its methods.  -- when
// you complete it it will also have the function prototypes for your
// list functions.  With this #include, you can use Node type (and
// Node*, and ListType), and call those list functions from inside
// your Table methods, below.

#include "listFuncs.h"


//*************************************************************************


Table::Table() {
    hashSize = HASH_SIZE;
    data = new ListType[hashSize];
    initTable();
}


Table::Table(unsigned int hSize) {
    hashSize = hSize;
    data = new ListType[hashSize];
    initTable();
}


int * Table::lookup(const string &key) {
    int code = hashCode(key);
    return lookUpValue(data[code], key);
}



bool Table::remove(const string &key) {
    int code = hashCode(key);
    return removeTarget(data[code], key);
}



bool Table::insert(const string &key, int value) {
    if(lookup(key) != NULL){
        return false;
    }
    else{
        int code = hashCode(key);
        insertEnd(data[code], key, value);
        return true;
    }
}

int Table::numEntries() const {
    int count = 0;
    for(int i = 0; i < hashSize; i++){
        count += sizeOfList(data[i]);
    }
    return count;
}


void Table::printAll() const {
    int nonEmpty = 0;
    for(int i = 0; i < hashSize; i++){
        int size = sizeOfList(data[i]);
        if ( size != 0){
            nonEmpty++;
        }
        printList(data[i]);
    }
    if(nonEmpty == 0){
        cout << "Empty!"<<endl;
    }
}




void Table::hashStats(ostream &out) const {
    out << "number of buckets: "<< hashSize << endl;
    out << "number of entries: " << numEntries() << endl;
    
    int nonEmpty = 0;
    int max = 0;
    for(int i = 0; i < hashSize; i ++){
        int size = sizeOfList(data[i]);
        if ( size == 0){
            nonEmpty++;
        }
       
        if( max < size){
            max = size;
        }
    }
    
    out << "number of non-empty buckets: "<< nonEmpty << endl;
    out << "longest chain: "<< max << endl;
}


// add definitions for your private methods here

void Table::initTable(){
    
    for(int i = 0; i < hashSize; i++){
        initList(data[i]);
    }
}
