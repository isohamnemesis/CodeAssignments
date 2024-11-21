#include "header.h"

using namespace std;

string wordMatch(vector<string> &words, string &note){

    unordered_map<char, int> notemap;
    for(int i=0; i<note.size(); i++){
        notemap[note[i]] += 1;
    }

    string result;
    for(string word:words){
        unordered_map<char, int> wordmap;
        for(char c: word){
            wordmap[c] += 1;
        }
        bool flag = true;
        for(auto it=wordmap.begin(); it!=wordmap.end(); it++){
            char c = it->first;
            int cnt = it->second;

            if(notemap.count(c)==0 || notemap[c]<cnt){
                flag = false;
                break;
            }
        }

        if(flag){
            return word;
        }
    }

    return "-";
}
int main(){

    vector<string> words; 
    words.push_back("baby");
    words.push_back("referee");
    words.push_back("cat");
    words.push_back("dada");
    words.push_back("dog");
    words.push_back("bird");
    words.push_back("ax");
    words.push_back("baz");

    string note = "ctay";
    string result = wordMatch(words, note);
    cout<<result<<endl;
    cout<<endl;

    note = "bcanihjsrrrferet";
    result = wordMatch(words, note);
    cout<<result<<endl;
    cout<<endl;

    note = "tbaykkjlga";
    result = wordMatch(words, note);
    cout<<result<<endl;
    cout<<endl;

    note = "bbblkkjbaby";
    result = wordMatch(words, note);
    cout<<result<<endl;
    cout<<endl;

    note = "dad";
    result = wordMatch(words, note);
    cout<<result<<endl;
    cout<<endl;

    note = "breadmaking";
    result = wordMatch(words, note);
    cout<<result<<endl;
    cout<<endl;

    note = "dadaa";
    result = wordMatch(words, note);
    cout<<result<<endl;
    cout<<endl;

}