import understand

db = understand.open("/home/bhushan/Desktop/JAVA/HelloWorld/HelloWorld.udb")
# print(db.ents("function,method,procedure"))

def closeCalled(ent,paramnum):
	"""
	check whether close() is called in function entity on parameter index paramnum
	"""
	lines=[]
	for r in ent.refs():
		if r.kindname()=="Call" and "cl" in r.ent().longname(): #######################################
			lines.append(r.line())
	tokens=[(i,i.ref().kindname()) for i in ent.lexer().lexemes() if i.ref()]
	ind=0
	while tokens[ind][0].ent().simplename()!=ent.simplename() or tokens[ind][1]!="Define":
		ind=ind+1
	ind=ind+1
	while paramnum>0 :
		while tokens[ind][1]!="Define":
			ind=ind+1
		paramnum=paramnum-1
	e=tokens[ind][0].ent()
	for r in e.refs():
		if r.kindname()=="Use" and r.line() in lines:
			return True
	return False

def filter(buglines,filename):
	"""
	returns False or True for each issue denoted by int in buglines
	"""
	output = [True]*len(buglines)
	file = db.lookup(filename,'File')[0]
	variables=['']*len(buglines) # variables defined on that line
	ind=-1
	for y in buglines:
		ind=ind+1
		for lexeme in file.lexer().lexemes(y,y):
			if lexeme.token()=='Identifier':
				for j in lexeme.ent().refs():
					if j.file().name()==filename and j.kindname()=='Define' and j.line()==y:
						variables[ind]=lexeme.ent()
						break
	# print(variables)
	stack=[]
	scope=[0]*len(buglines) # stores scope ends for each defined variable
	line=1
	here=0
	blockstart=[] # finally block start lines
	blockend=[]	# finally block end lines
	curr=0  	# check if currently in finally block
	calls=[]	# stores line nos of function calls in finally blocks
	if buglines[0]==line:
		stack.append(here)
		here=1
	tokens=file.lexer().lexemes()
	ind=-1
	while ind<len(tokens)-1:
		ind=ind+1
		lexeme=tokens[ind]
		if lexeme.ref() and lexeme.ref().kindname()=='Call' and curr>0:
			ex=lexeme.ent()
			cl=1
			ind=ind+1
			while ind<len(tokens) and tokens[ind].text()!=')':
				if tokens[ind].token()=="Identifier":
					for et in variables:
						if et.simplename()==tokens[ind].text() \
							and (scope[variables.index(et)]==0 or tokens[ind].line_begin() \
							in range(buglines[variables.index(et)],scope[variables.index(et)]+1)) \
							and closeCalled(ex,cl):
							output[variables.index(et)]=False
				elif tokens[ind].text==',':
					cl=cl+1
				ind=ind+1
			# calls.append(lexeme.line_begin())
		elif lexeme.text()=='{':
			stack.append('{')
		elif lexeme.text()=='}':
			while len(stack)>0:
				x=stack.pop()
				if type(x).__name__=='int':
					scope[x]=line
				else:
					if len(stack)>0 and stack[-1]=='finally':
						blockend.append(lexeme.line_begin())
						stack.pop()
						curr=curr-1
					break
		elif lexeme.text()=='finally':
			stack.append('finally')
			curr=curr+1
			blockstart.append(lexeme.line_begin())
		elif lexeme.token()=='Newline':
			line=line+1
			if here<len(buglines) and buglines[here]==line:
				stack.append(here)
				here=here+1
	for j in range(len(scope)):
		if scope[j]==0:
			scope[j]=line
	ind=-1
	# print(calls)
	# for et in variables:
	# 	ind=ind+1
	# 	for j in et.refs():
	# 		# if variable is passed as parameter to a function which calls close(), it is False Positive
	# 		if j.kindname()=='Use' and j.line() in calls:
	# 			output[ind]=False
	return output

print(filter([11,12],"HelloWorld.java"))
