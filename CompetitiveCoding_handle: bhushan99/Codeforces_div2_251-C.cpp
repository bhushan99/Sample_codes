#include <iostream>
#include <algorithm>
#include <cmath>
#include <vector>
using namespace std;
int main(){
	long int n,k,p,even=0,odd=0;
	cin>>n>>k>>p;
	long long int a[n];
	for(long int i=0;i<n;i++) {cin>>a[i];if(a[i]%2==0) even++;else odd++;}
	if(k-p>odd || (odd-k+p)%2==1 || p>even+(odd-k+p)/2) {cout<<"NO";return 0;}
	cout<<"YES"<<endl;
	long int y,z=0;
	if(p!=0){
	for(long int i=0;i<k-p;) {
		if(a[z]%2==1) {
			cout<<"1 "<<a[z]<<endl;
			a[z]=-1; odd--;
			z++; i++;
		}
		else z++;
	}
	z=0;
	for(long int i=0;i<p-1;) {
		for(;a[z]==-1;z++);
		if(a[z]%2==0) {
			cout<<"1 "<<a[z]<<endl;
			a[z]=-1; even--;
			z++; i++;
		}
		else {
			cout<<"2 "<<a[z]<<" ";
			a[z]=-1; odd--;
			y=z+1; for(;a[y]==-1 || a[y]%2==0;y++);
			cout<<a[y]<<endl; a[y]=-1;odd--;
			i++;
		}
	}
	if(even+odd!=0){
	cout<<(even+odd)<<" ";
	for(z=0;z<n;z++){
		if(a[z]!=-1) cout<<a[z]<<" ";
	}
	cout<<endl;}}
	else {
		for(long int i=0;i<k-p-1;) {
		if(a[z]%2==1) {
			cout<<"1 "<<a[z]<<endl;
			a[z]=-1; odd--;
			z++; i++;
		}
		else z++;
	}
	if(even+odd!=0){
	cout<<(even+odd)<<" ";
	for(z=0;z<n;z++){
		if(a[z]!=-1) cout<<a[z]<<" ";
	}
	cout<<endl;}
	}
	return 0;
}

