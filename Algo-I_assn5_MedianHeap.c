#include<stdio.h>
#include<stdlib.h>
#include<time.h>

int max(int a,int b)                //to return maximum of 2 integers
{
  if(a>b) return a;
  else return b;
}

int min(int a,int b)                //to return minimum of 2 integers
{
  if(a<b) return a;
  else return b;
}

void swap(int *x,int*y)              //to swap 2 integers
{
  int temp= *x ;
  *x = *y;
  *y = temp;
}

void insert_maxheap(int *n1,int *h,int x)   /* n1 is pointer to current number of nodes in max_heap in array h[] */       
{
  int i, j= *n1;                           // i is index of parent node. j is index of child node
  h[*n1]=x;                                //assign x to end of max_heap
  for(i=(*n1-1)/2;i>=0;i=(j-1)/2)          //loop starting from parent of x,each time going to parent of h[j]
    {
      if(h[i]<h[j]) {swap(&h[i],&h[j]);j=i;}      //if parent value is smaller, swap. 
      else break;                                 //else no need to check further
    }
  (*n1)++;                                       //increment no. of elements in max_heap

}

void insert_minheap(int n,int *n2,int *h,int y)          /* n2 is pointer to current number of nodes in min_heap in array h[] */ 
{
  int i,j= *n2;                                           // i is index of parent node. j is index of child node
  h[n-*n2-1]=y;                                           //assign y to end of min_heap
  for(i=(*n2-1)/2;i>=0;i=(j-1)/2)                         //loop starting from parent of y,each time going to parent of h[j]
    {
      if(h[n-i-1]>h[n-1-j]) {swap(&h[n-i-1],&h[n-1-j]);j=i;}         //if parent value is larger, swap.
      else break;                                                    //else no need to check further
    }
  (*n2)++;                         //increment no. of elements in min_heap
  
}

int delete_max(int *n1,int *h)                       //to delete max element from max_heap in h[] and return it
{
  int a=h[0],i,k,c,j=0;                              //a stores element to be deleted
  h[0]=h[*n1-1];                                     //max element h[0] deleted and last element of max_heap assigned to h[0]
  k=h[0];                                           //k stores value of element which is to be positioned
  for(i=1;i<*n1-1;i=2*j+1)                         // i travers through children of h[j]
    {
      c=max(h[i],h[i+1]);

      //c stores larger of two children h[i] and h[i+1]
      //if h[j] less than c then swap else no need to further check

      if(k<c&&c==h[i])                           
      {
      swap(&h[i],&h[j]);
      j=i;
      }
      else if(k<c&&c==h[i+1])
      {
      swap(&h[i+1],&h[j]);
      j=i+1;
      }
      else break;
    }
  (*n1)--;                           //decrement number of elements in max_heap
  return a;                          //return deleted element
}

int delete_min(int n,int *n2,int *h)                //to delete min element from min_heap in h[] and return it
{
  int a=h[n-1],i,k,c,j=0;                           //a stores element to be deleted
  h[n-1]=h[n-*n2];                                  //min element h[n-1] deleted and last element of min_heap assigned to h[n-1]
  k=h[n-1];                                         //k stores value of element which is to be positioned
  for(i=1;i<*n2-1;i=2*j+1)                          // i travers through children of h[n-1-j]
    {
      c=min(h[n-1-i],h[n-2-i]);

      //c stores smaller of two children of h[n-1-j]
      //if h[n-1-j] larger than c then swap else no need to further check

      if(k>c&&c==h[n-1-i])
      {
      swap(&h[n-1-i],&h[n-1-j]);
      j=i;
      }
      else if(k>c&&c==h[n-2-i])
      {
      swap(&h[n-2-i],&h[n-1-j]);
      j=i+1;
      }
      else break;
    }
  (*n2)--;                         //decrement number of elements in min_heap
  return a;                        //return deleted element
}

int find_med(int n,int n1,int n2,int *h)            //to find median of med_heap h[]
{
  //if no. of elements in max_heap and min_heap equal then h[0] is median
  if(n1==n2) return h[0];
  //if n2==n1+1 h[n-1] is median        
  else return h[n-1];
}

void insert_medheap(int n,int *n1,int *n2,int *h,int x)           //insert element x in med_heap
{
  int u;
  if(*n1==0 && *n2==0) 
    {
      insert_minheap(n,n2,h,x);return;                         //insert 1st element
    }
  if(find_med(n,*n1,*n2,h)>x)                               //if current median is larger than x
    {
      if(*n2==*n1+1) {insert_maxheap(n1,h,x);}             //proper to insert in max_heap
      else
     {
      u=delete_max(n1,h);                               //if n1==n2 delete 1 elt from maxheap
      insert_minheap(n,n2,h,u);                         //insert it in minheap
      insert_maxheap(n1,h,x);                           //then insert x in max_heap
     }
    }
  else                                                 //if current median is smaller than x
    {
      if(*n1==*n2) {insert_minheap(n,n2,h,x);}          //proper to insert in min_heap
      else
     { 
      u=delete_min(n,n2,h);                             //if n1+1==n2 delete 1 elt from minheap
      insert_maxheap(n1,h,u);                           //insert it in maxheap
      insert_minheap(n,n2,h,x);                         //then insert x in min_heap
     }
    }
}

int delete_median(int n,int *n1,int *n2,int *h)        //to delete current median
{
  int m;
  if(*n1==*n2) m=delete_max(n1,h);            //h[0] is current median,delete it 
  else m=delete_min(n,n2,h);                 //h[n-1] is current median,delete it
  return m;
}

void med_heapsort(int n,int *n1,int *n2,int *h)     //to sort med_heap h[]
{
  int m,p=*n1,q=*n2;
  for(; *n1!=0 && *n2!=0 ;)                //traverse till all elements finish
  {
    m=delete_median(n,n1,n2,h);
    if(p>*n1) {h[*n1]=m;p-=1;}           //if median deleted from max_heap 
    else {h[n-1-*n2]=m;q-=1;}           //if median deleted from min_heap 
  }
}

int main()
{
  int n,i,j=0,n1=0,n2=0;        //n1 and n2 store current number of elements in max and min heaps respectively
  printf("\nn=");
  scanf("%d",&n);
  int heap[n];
  srand((unsigned int)time(NULL));
  
  printf("\n+++ MedHeap initialized\n\n+++ Going to insert elements one by one in MedHeap\n");
  
  for(i=0;i<n;i++)
    {
      j=rand()%10000;
      insert_medheap(n,&n1,&n2,heap,j);
      printf("\tInsert(%4d) done. Current median = %4d.\n",j,find_med(n,n1,n2,heap));
    }
    
  med_heapsort(n,&n1,&n2,heap);
  
  printf("\n\n+++ Median Heap Sort done\n\t");
  
  for(i=0;i<n;i++)
   {
    printf("%4d\t",heap[i]);
    if((i+1)%7==0) printf("\n\t");
   }
  printf("\n\n");

  return 0;
}
