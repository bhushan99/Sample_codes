#include <iostream>
#include <algorithm>
#include <vector>
#include <map>
#include <set>
#include <cstdio>
#include <iomanip>
using namespace std;
int main(){
	int t,n,c,k,l,r;cin>>t;
	while(t--){
		cin>>n>>c>>k;
		double pr[n+1][c][k+1],ans=0.0;
		for(int i=1;i<=n;i++)
			for(int j=0;j<c;j++){
				if(j==1) pr[i][j][0]=1.0;
				else pr[i][j][0]=0.0;
			}
		for(int z=1;z<=k;z++){
			cin>>l>>r;
			for(int i=1;i<=n;i++) for(int j=0;j<c;j++){
				if(i<l || i>r) pr[i][j][z]=pr[i][j][z-1];
				else pr[i][j][z]=pr[i][j][z-1]/2.0;
			} 
				
			for(int i=l;i<=r;i++) for(int j=0;j<c;j++) for(int x=0;x<c;x++)  
				pr[i][(j*x)%c][z]+= 0.5*(pr[i][j][z-1])/c;
		}
		for(int i=1;i<=n;i++) for(int j=0;j<c;j++)
			ans+=pr[i][j][k]*j;
		cout.precision(15);
		cout<<fixed<<ans<<endl;
	}
	return 0;
}