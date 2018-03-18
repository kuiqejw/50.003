import random, string
chars = string.ascii_uppercase+string.digits+string.ascii_lowercase
def sample_fuzzing_alphanumeric():
	size = random.randint(1,1024)
	return ''.join(random.SystemRandom().choice(chars) for _ in range(size))

def mutation_operator(word):
	lengthString = len(word)
	t = random.randint(0,(lengthString-1))
	print(t)
	print(word[t])
	return ''.join(word[:t-1]+word[t]+word[t-1]+word[t+1:])
	
def trim_operator(word):
	lengthString = len(word)
	t = random.randint(0,(lengthString-1))
	return word[:t]
def mutation_fuzzing():
	newlst = []
	with open('the_file.txt','r') as source:
		data = [line for line in source]
	for x in data:
		print(x)
		y = random.choice(lst)
		print (y)
		x = y(x)
		newlst.append(x)
	with open('another_file.txt','w') as target:
		for line in newlst:
			target.write(line)

def shuffle_string(string):
    chars = list(string)
    random.shuffle(chars)
    return ''.join(chars)

def garble_word(word):
    # No operation needed on sufficiently small words
    # (Also, main algorithm requires word length >= 2)
    if len(word) <= 3:
        return word

    # Split word into first & last letter, and middle letters
    first, mid, last = word[0], word[1:-1], word[-1]

    return first + shuffle_string(mid) + last

def garble(sentence):
    words = sentence.split(' ')
    return ' '.join(map(garble_word, words))
#print(sample_fuzzing_alphanumeric())
#print(mutation_operator("SUTDE"))
lst = [trim_operator,mutation_operator,garble,garble_word,shuffle_string]
mutation_fuzzing()
