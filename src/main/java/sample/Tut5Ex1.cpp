#include<bits/stdc++.h>
using namespace std;

//Ex1a
int findMaxElement(int A[], int number, int maxElement) {
    if (number == 1) return max(maxElement, A[number - 1]);
    else {
        maxElement = max(maxElement, A[number - 1]);
        return findMaxElement(A, number - 1, maxElement);
    }
}

//Ex1b
int Multiply(int a, int b, int Res) {
    if (a == 0 || b == 0) return 0;
    else if (a == 1) return Res + b;
    else {
        Res += b;
        a--;
        return Multiply(a, b, Res);
    }
}

int main()
{
    int number = 4;
    int A[number] = {3, 5, 7, 1};
    cout << findMaxElement(A, number, -1e9) << endl; // maxElement mặc định là -1e9;
    cout << Multiply(2,5,0); // Res mặc định là 0
}
