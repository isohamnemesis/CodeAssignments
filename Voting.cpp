#include "header.h"

using namespace std;

class Ballot{

    vector<string> ideas;
    int weight;

    public:

    Ballot(){
        
    }

    void setWeight(int weight){
        this->weight = weight;
    }

    void setIdeas(vector<string> &ideas){
        this->ideas = ideas;
    }

    int getSize(){
        return ideas.size();
    }

    string getIdea(int pos){
        if(pos>=ideas.size())
        return "";

        return ideas[pos];
    }

    int getWeight(int pos){
        if(pos>=ideas.size())
        return 0;

        return (weight-pos);
    }

};

class BallotOrder{
    vector<Ballot> ballots;

    public:

    BallotOrder(){
        
    }

    vector<Ballot> getBallots() {
        return this->ballots;
    }

    void setBallots(vector<Ballot> ballots) {
        this->ballots = ballots;
    }

    void addBallot(Ballot ballot){
        ballots.push_back(ballot);
    }

    vector<string> rankIdeas(){

        vector<string> result;
        unordered_map<string, int> store;
        priority_queue<pair<int, string> > qstore;
        for(int i=0; i<ballots.size(); i++){
            Ballot ballot = ballots[i];

            for(int j=0; j<ballot.getSize(); j++){
                store[ballot.getIdea(j)] += ballot.getWeight(j);
            }
        }

        for(auto it=store.begin(); it!=store.end(); it++){
            qstore.push(make_pair(it->second, it->first));
        }

        while(!qstore.empty()){
            result.push_back(qstore.top().second);
            qstore.pop();
        }

        return result;
    }
};

int main(){

    vector<string> b1, b2, b3, b4;

    b1.push_back("candidate2"); 
    b1.push_back("candidate3");
    b1.push_back("candidate1"); 

    b2.push_back("candidate4"); 
    b2.push_back("candidate2");
    b2.push_back("candidate1"); 

    b3.push_back("candidate3"); 
    b3.push_back("candidate2");
    b3.push_back("candidate1"); 

    b4.push_back("candidate2"); 
    b4.push_back("candidate4");
    b4.push_back("candidate3");

    Ballot ballot1, ballot2, ballot3;
    
    ballot1.setIdeas(b1);
    ballot1.setWeight(3);

    ballot2.setIdeas(b2);
    ballot2.setWeight(3);

    ballot3.setIdeas(b3);
    ballot3.setWeight(3);

    vector<Ballot> ballots;
    ballots.push_back(ballot1);
    ballots.push_back(ballot2);
    ballots.push_back(ballot3);

    BallotOrder ballotOrder;
    ballotOrder.addBallot(ballot1);
    ballotOrder.addBallot(ballot2);
    ballotOrder.addBallot(ballot3);

    vector<string> result = ballotOrder.rankIdeas();
    cout<<result.size()<<endl;
    for(int i=0; i<result.size(); i++){
        cout<<result[i]<<endl;
    }

}