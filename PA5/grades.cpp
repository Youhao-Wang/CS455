// Name:Youhao Wang
// Loginid: youhaowa
// CSCI 455 PA5
// Fall 2016

/*
 * grades.cpp
 * A program to test the Table class.
 * How to run it:
 *      grades [hashSize]
 * 
 * the optional argument hashSize is the size of hash table to use.
 * if it's not given, the program uses default size (Table::HASH_SIZE)
 *
 */

#include "Table.h"

// cstdlib needed for call to atoi
#include <cstdlib>


// this function will print instruction of how to use the command
void printHelp(){
    
    cout << "               ******************************************************* "<<endl;
    cout << "    insert <name> <score>" << endl;
    cout << "        Insert this name and score in the grade table. If this name was already " << endl;
    cout << "        present, print a message to that effect, and don't do the insert." << endl;
    cout << endl;
    cout << "    change <name> <newscore>" << endl;
    cout << "        Change the score for name. Print an appropriate message if this name " << endl;
    cout << "        isn't present." << endl;
    cout << endl;
    cout << "    lookup <name>" << endl;
    cout << "        Lookup the name, and print out his or her score, or a message indicating" << endl;
    cout << "        that student is not in the table." << endl;
    cout << endl;
    cout << "    remove <name>" << endl;
    cout << "        Remove this student. If this student wasn't in the grade table, print a " << endl;
    cout << "        message to that effect." << endl;
    cout << endl;
    cout << "    print" << endl;
    cout << "        Prints out all names and scores in the table." << endl;
    cout << endl;
    cout << "    size" << endl;
    cout << "        Prints out the number of entries in the table." << endl;
    cout << endl;
    cout << "    stats" << endl;
    cout << "        Prints out statistics about the hash table at this point." << endl;
    cout << endl;
    cout << "    help" << endl;
    cout << "        Prints out a brief command summary." << endl;
    cout << endl;
    cout << "    quit" << endl;
    cout << "        Exits the program." << endl;
    cout << endl;
}









int main(int argc, char * argv[]) {

  // gets the hash table size from the command line

  int hashSize = Table::HASH_SIZE;

  Table * grades;  // Table is dynamically allocated below, so we can call
                   // different constructors depending on input from the user.

  if (argc > 1) {
    hashSize = atoi(argv[1]);  // atoi converts c-string to int

    if (hashSize < 1) {
      cout << "Command line argument (hashSize) must be a positive number" 
	   << endl;
      return 1;
    }

    grades = new Table(hashSize);

  }
  else {   // no command line args given -- use default table size
    grades = new Table();
  }


  grades->hashStats(cout);


    while(1){
        cout << "cmd> ";
        string command = "";
        string key = "";
        string value = "";
        
        cin >> command;
        if(cin.fail()){
            cout << "ERROR: invalid command!" << endl;
            printHelp();
            continue;
        }
        
        if(command == "insert"){
            cin >> key;
            cin >> value;
            int score = atoi(value.c_str());
            if (grades ->insert(key, score)){
                cout << "Insert Successfully!"<<endl;
            }
            else{
                cout << "Cannot insert because "<<key << " already exists with score " << *(grades -> lookup(key)) << endl;
            }
        }
        
        
        else if(command == "change"){
            cin >> key;
            cin >> value;
            int newScore = atoi(value.c_str());
            int *score = grades -> lookup(key);
            if(score == NULL){
                cout << "The name does not exists!"<<endl;
            }
            else{
                int oldScore = *score;
                *score = newScore;
                cout << "Change Successfully from "<<oldScore<<" to "<<newScore<<" with "<<key<<endl;
            }
        }
        
        else if(command == "lookup"){
            cin >> key;
            int *score = grades -> lookup(key);
            if(score == NULL){
                cout << "The name does not exists!"<<endl;
            }
            else{
                cout << "The score of "<<key<<" is "<< *score<<endl;
            }
        }
        
        else if(command == "remove"){
            cin >> key;
            int *score = grades -> lookup(key);
            if(score == NULL){
                cout << "The name does not exists!"<<endl;
            }
            else{
                grades -> remove(key);
                cout << "Remove Successfully for " << key <<endl;
            }
        }
        
        else if(command == "print"){
            grades -> printAll();
        }
        
        else if(command == "size"){
            cout << "the number of entries in the table is "<< grades -> numEntries()<<endl;
        }
        
        else if(command == "stats"){
            grades->hashStats(cout);
        }
        
        else if(command == "help"){
            printHelp();
        }
        
        else if(command == "quit"){
            break;
        }
        
        else{
            cout << "ERROR: invalid command!" << endl;
            printHelp();
        }
        
    }


  return 0;
}
