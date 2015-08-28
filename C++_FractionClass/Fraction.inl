#include "Fraction.hxx"
#include <iostream>
using namespace std;

	const Fraction Fraction::sc_fUnity=Fraction(1,1);
	const Fraction Fraction::sc_fZero=Fraction(0,1);

	Fraction::Fraction(int x,int y)  // Uses default parameters. Overloads to
								// Fraction(int, int); 
								// Fraction(int);	
								// Fraction(); 
	{
		if(x==0) y=1;
		if(y<0) {y=-y;x=-x;}
		iNumerator_=x;
		uiDenominator_=y;
		*this=Normalize();
	}

	// Copy Constructor
	Fraction::Fraction(const Fraction& x){
		iNumerator_=x.iNumerator_;
		uiDenominator_=x.uiDenominator_;
	}

	Fraction::~Fraction(){}  //no need to delete

	Fraction& Fraction::operator=(const Fraction& x){    //assignment operator
		iNumerator_=x.iNumerator_;
		uiDenominator_=x.uiDenominator_;
		if(iNumerator_==0) uiDenominator_=1; //for zero fraction
		if(uiDenominator_<0) {uiDenominator_=-uiDenominator_;iNumerator_=-iNumerator_;} //denominator +ve
		Normalize();
		return *this;
	}
	// Constructor for explicit Conversion
	Fraction::Fraction(double y){
		iNumerator_=Fraction::precision()*y;    // explicit double to Fraction conversion
		uiDenominator_=Fraction::precision();
		if(iNumerator_==0) uiDenominator_=1;
		*this=Normalize();
	}
	ostream& operator<<(ostream& ost, const Fraction& x){    //for output of fraction
		ost<<"("<<x.iNumerator_<<"/"<<x.uiDenominator_<<")"; //form (a/b)
		return ost;
	}
	istream& operator>>(istream& ist, Fraction& x){
		char ch;                  //take input only of form a/b
		ist>>x.iNumerator_;
		ist>>ch;
		if(ch == '/')
		{
			ist>>x.uiDenominator_;
			x.Normalize();
		}
	 	return ist;
	}

	int Fraction::gcd(int x,int y)    //return gcd of absolute values of x,y
	{
		int i,j,z=1;
		if(x<0) x=-x;
		if(y<0) y=-y;
		if(x<y){i=x;j=y;}       //for minimum of two
		else {i=y;j=x;}
		for(int k=1;k<=i;k++){
			if((i%k==0)&&(j%k==0)) z=k;
		}
		return z;
	}
	int Fraction::lcm(int x,int y)
	{
		if(x<0) x=-x;    
		if(y<0) y=-y;          
		return x*y/(gcd(x,y));     //since gcd*lcm=x*y
	}
	Fraction& Fraction::Normalize(){           //normalize to simplest form
		int g=gcd(iNumerator_,uiDenominator_);
		iNumerator_/=g;
		uiDenominator_/=g;
		return *this;
	}
	Fraction Fraction::operator-(){      //unary minus
		Fraction F(-iNumerator_,uiDenominator_);
		return F;
	}
	Fraction Fraction::operator+(){      //unary plus
		Fraction F(iNumerator_,uiDenominator_); 
		return F;
	}
	Fraction& Fraction::operator--()   // Pre-decrement. Dividendo. p/q <-- p/q - 1
	{
		iNumerator_-=uiDenominator_;
		return *this;
	}
	Fraction& Fraction::operator++(){     // Pre-increment. Componendo. p/q <-- p/q + 1
		iNumerator_+=uiDenominator_;
		return *this;
	}
	Fraction Fraction::operator--(int){    // Post-decrement. Lazy Dividendo. p/q <-- p/q - 1. Returns old p/q
		Fraction temp(*this);   //call to copy constructor
		operator--();
		return temp;
	}
	Fraction Fraction::operator++(int){       // Post-increment. Lazy Componendo. p/q <-- p/q + 1. Returns old p/q
		Fraction temp(*this);      //call to copy constructor
		operator++();
		return temp;
	}

	// BINARY ARITHMETIC OPERATORS USING FRIEND FUNCTIONS
	Fraction operator+(const Fraction& x, const Fraction& y){ //binary '+' operator friend to Fraction Class
		int numerator=x.iNumerator_*y.uiDenominator_+y.iNumerator_*x.uiDenominator_;
		int denominator=x.uiDenominator_*y.uiDenominator_;
		Fraction F(numerator,denominator);
		return F;
	}
	Fraction operator-(const Fraction& x, const Fraction& y){ //binary '-' operator friend to Fraction Class
		int numerator=x.iNumerator_*y.uiDenominator_ - y.iNumerator_*x.uiDenominator_;
		int denominator=x.uiDenominator_*y.uiDenominator_;
		Fraction F(numerator,denominator);
		return F;
	}
	Fraction operator*(const Fraction& x, const Fraction& y){ //binary '*' operator friend to Fraction Class
		Fraction F(x.iNumerator_*y.iNumerator_,x.uiDenominator_*y.uiDenominator_);
		return F;
	}
	Fraction operator/(const Fraction& x, const Fraction& y){ //binary '/' operator friend to Fraction Class
		Fraction F(x.iNumerator_*y.uiDenominator_,x.uiDenominator_*y.iNumerator_);
		return F;
	}
	Fraction operator%(const Fraction& x, const Fraction& y){ //binary '%' operator friend to Fraction Class
		Fraction z=x/y;
		int k=z.iNumerator_/z.uiDenominator_;
		Fraction w(y.iNumerator_*k,y.uiDenominator_);
		return x-w;
	}

	// BINARY RELATIONAL OPERATORS
	bool Fraction::operator==(const Fraction& x){ //binary '==' Relational operator
		if(iNumerator_==x.iNumerator_ && uiDenominator_==x.uiDenominator_)
			return true;
		else return false;
	}
	bool Fraction::operator!=(const Fraction& x){ //binary '!=' Relational operator
		if(iNumerator_==x.iNumerator_ && uiDenominator_==x.uiDenominator_)
			return false;
		else return true;
	}
	bool Fraction::operator<(const Fraction& x){  //binary '<' Relational operator
		if(iNumerator_*x.uiDenominator_<x.iNumerator_*uiDenominator_)
			return true;
		else return false;
	}
	bool Fraction::operator>(const Fraction& x){  //binary '>' Relational operator
		if(iNumerator_*x.uiDenominator_>x.iNumerator_*uiDenominator_)
			return true;
		else return false;
	}
	bool Fraction::operator<=(const Fraction& x){ //binary '<=' Relational operator
		if(iNumerator_*x.uiDenominator_<=x.iNumerator_*uiDenominator_)
			return true;
		else return false;
	}
	bool Fraction::operator>=(const Fraction& x){ //binary '>=' Relational operator
		if(iNumerator_*x.uiDenominator_>=x.iNumerator_*uiDenominator_)
			return true;
		else return false;
	}

	// ADVANCED ASSIGNEMENT OPERATORS
	// --------------------------------------------------------
	Fraction& Fraction::operator+=(const Fraction& x){   //binary '+=' advance assignment operator
		*this= *this + x;
		this->Normalize();
		return *this;
	}
	Fraction& Fraction::operator-=(const Fraction& x){    //binary '-=' advance assignment operator
		*this= *this - x;
		this->Normalize();
		return *this;
	}
	Fraction& Fraction::operator*=(const Fraction& x){   //binary '*=' advance assignment operator
		*this= *this * x;
		this->Normalize();
		return *this;
	}
	Fraction& Fraction::operator/=(const Fraction& x){  //binary '/=' advance assignment operator
		*this= *this / x;
		this->Normalize();
		return *this;
	}
	Fraction& Fraction::operator%=(const Fraction& x){   //binary '/=' advance assignment operator
		*this= *this % x;
		this->Normalize();
		return *this;
	}

//invert fraction
	Fraction Fraction::operator!(){ 
	if(iNumerator_<0) {iNumerator_=-iNumerator_;uiDenominator_=-uiDenominator_;}     
		Fraction F(uiDenominator_,iNumerator_);
		return F;
	}
	