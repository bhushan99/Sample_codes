#include <vector>
#include <list>
#include <map>
#include <set>
#include <queue>
#include <deque>
#include <stack>
#include <bitset>
#include <algorithm>
#include <functional>
#include <numeric>
#include <utility>
#include <sstream>
#include <iostream>
#include <iomanip>
#include <cstdio>
#include <cmath>
#include <cstdlib>
#include <ctime>
#include <climits>
#include <string>
#include <cstring>
#include <cassert>
#include <limits>

using namespace std;

typedef vector<int> vi; 
typedef vector<vi> vvi; 
typedef pair<int,int> ii;
typedef vector<ii> vii;
typedef long long ll;

#define sz(a) int((a).size()) 
#define pb push_back 
#define all(c) (c).begin(),(c).end() 
#define tr(c,i) for(typeof((c).begin()) i = (c).begin(); i != (c).end(); i++) 
#define present(c,x) ((c).find(x) != (c).end()) 
#define cpresent(c,x) (find(all(c),x) != (c).end()) 
#define rep(i, n) for(int i = 0; i < (n); ++i)
#define imax numeric_limits<int>::max()
#define imin numeric_limits<int>::min()
#define lmax numeric_limits<ll>::max()
#define lmin numeric_limits<ll>::min()
#define sqr(x) (x)*(x)

typedef struct stree
{
	int presum,sum;
}node;

node segtree[65536]; 
int n;
char arr[32768];

void init(int root,int l,int r) {
	if(root>=65536) return;
	if(l==r) {
		segtree[root].sum=segtree[root].presum=(arr[l]=='(')?1:-1;
		return;
	}
	init(2*root+1,l,(l+r)/2);
	init(2*root+2,1+(l+r)/2,r);
	segtree[root].sum=segtree[2*root+1].sum+segtree[2*root+2].sum;
	segtree[root].presum=min(segtree[2*root+1].presum,segtree[2*root+1].sum+segtree[2*root+2].presum);
}

void update(int root,int l,int r,int ask,bool b) {
	int diff=b?2:-2;
	if(ask<l||ask>r) return;
	if(root>=65536) return;
	segtree[root].sum+=diff;
	if(l==r) {
		segtree[root].presum=(arr[l]=='(')?1:-1;
		return;
	}
	update(2*root+1,l,(l+r)/2,ask,b);
	update(2*root+2,1+(l+r)/2,r,ask,b);
	segtree[root].presum=min(segtree[2*root+1].presum,segtree[2*root+1].sum+segtree[2*root+2].presum);
}

int main(){
	rep(t,10) {
		cout<<"Test "<<t+1<<":\n";
		cin>>n;
		rep(i,n) cin>>arr[i];
		init(0,0,n-1);
		int q;cin>>q;
		while(q--){
			int ask;cin>>ask;
			if(ask){
				ask--;
				arr[ask]=(arr[ask]=='(')?')':'(';
				update(0,0,n-1,ask,arr[ask]=='(');
			}
			else {
				if(segtree[0].sum==0&&segtree[0].presum==0) cout<<"YES\n";
				else cout<<"NO\n";
			}
		}
	}
	return 0;
}