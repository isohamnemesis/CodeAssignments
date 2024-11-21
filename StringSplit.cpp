#include "header.h"
using namespace std;

int main(){
    
    string str = "I live in Bangalore. I studied at NITK.";
    string split_str;
    stringstream obj_ss(str);
    vector<string> words;

    while(getline(obj_ss, split_str, ' ')){
        words.push_back(split_str);
    }

    cout<<words.size()<<endl;
    for(string s:words)
    cout<<s<<endl;
}