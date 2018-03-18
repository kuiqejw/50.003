import random
def calculator_grammar_fuzzing():
	print("Calculator_grammar_Fuzzing")
	print(Expr())
def Expr():
	lst = ['equals', 'plus', 'minus']
	func = random.choice(lst)
	print("Expr")
	if func == 'equals':
		print("Term")
		return Term()
	elif func == 'plus':
		print ("Expr + Term")
		return Expr() + '+' + Term()
	else:
		print("Expr + Term")
		return Expr() +'-' +  Term()
def Term():
	lst = ['equals', 'multiply','divide']
	func = random.choice(lst)
	if func == 'equals':
		print('equals')
		return Factor()
	elif func == 'multiply':
		print('multiply')
		return Term() + '*'+  Factor()
	else:
		print('divide')
		return Term() +'/' +  Factor()


def Factor():
	lst = ['minus', 'bracket','rawInt', 'decimal']
	func = random.choice(lst)
	print(func)
	if func == 'minus':
		return '-' + Factor()
	elif func == 'bracket':
		return '(' + Expr() + ')'
	elif func == 'rawInt':
		return Integer()
	else:
		return Factor() +'.' + Integer()

def Integer():
	lst = ['digit', 'integerdigit']
	func = random.choice(lst)
	print(func)
	if func == 'digit':
		return Digit()
	else:
		return Integer()+Digit()

def Digit():
	lst = [1,2,3,4,5,6,7,8,9]
	func = random.choice(lst)
	print(func)
	return str(func)

calculator_grammar_fuzzing()
