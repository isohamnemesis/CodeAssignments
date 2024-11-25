#include "header.h"
using namespace std;

int main(){

    string str = "123";
    int value = stoi(str);
    cout<<(value*3)<<endl;
    cout<<(value*2)<<endl;

    int k = 256;
    string s = to_string(k);
    cout<<(s+"MB")<<endl;

    return 0;
}
