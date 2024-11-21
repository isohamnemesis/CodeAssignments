#include<iostream>
#include<queue>
#include<ctime>

using namespace std;

class RateLimiters{
    int windowSize;
    int threshold;
    queue<time_t> timestamps;

public:

    RateLimiters(){

    }

    bool requests(){
        time_t currentTime = time(NULL);
        while(!timestamps.empty() && (int)(difftime(currentTime, timestamps.front()))>windowSize){
            timestamps.pop();
        }

        if(timestamps.size()<threshold){
            timestamps.push(currentTime);
            return true;
        }
        return false;
    }

    void setWindowSize(int windowSize) {
        this->windowSize = windowSize;
    }

    void setThreshold(int threshold) {
        this->threshold = threshold;
    }
};

class Driver{
    unordered_map<int, RateLimiters> rateLimiterMap;
    int windowSize;
    int threshold;

public:

    Driver(){

    }

    bool rateLimit(int customerID){
        bool response = false;
        if(rateLimiterMap.count(customerID)==0){
            RateLimiters rateLimiter;
            rateLimiter.setThreshold(threshold);
            rateLimiter.setWindowSize(windowSize);
            response = rateLimiter.requests();
            rateLimiterMap[customerID] = rateLimiter;
            
        }
        else{
            RateLimiters rateLimiter = rateLimiterMap[customerID];
            response = rateLimiter.requests();
            rateLimiterMap[customerID] = rateLimiter;
           
        }
        return response;
    }

    void setWindowSize(int windowSize) {
        this->windowSize = windowSize;
    }

    void setThreshold(int threshold) {
        this->threshold = threshold;
    }
};

int main(){

    Driver driver;
    driver.setThreshold(5);
    driver.setWindowSize(2);

    cout<<driver.rateLimit(1)<<endl;
    cout<<driver.rateLimit(1)<<endl;
    cout<<driver.rateLimit(1)<<endl;
    cout<<driver.rateLimit(1)<<endl;
    cout<<driver.rateLimit(1)<<endl;
    cout<<driver.rateLimit(1)<<endl;

    cout<<driver.rateLimit(2)<<endl;
    cout<<driver.rateLimit(2)<<endl;
    cout<<driver.rateLimit(2)<<endl;
    cout<<driver.rateLimit(2)<<endl;
    cout<<driver.rateLimit(2)<<endl;

    cout<<driver.rateLimit(3)<<endl;
    cout<<driver.rateLimit(2)<<endl;

    return 0;
}