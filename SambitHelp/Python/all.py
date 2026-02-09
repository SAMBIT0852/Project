person = {}

print(type(person))

person['name'] = 'Sambit'

person['lastname'] = 'Mishra'

person['age'] = 24

person['adress'] = 'BBSR'

print(person)

print(person['name'])


print('name' in person)

print('fname' in person)


print(len(person))


mydict = {'a':1,'b':2,'c':3}

print(mydict)

mydict.clear()

print(mydict)

print(mydict.get('b'))

print(mydict)

print(mydict.get('z'))

print(list(mydict.items()))

print(list(mydict.keys()))

print(mydict.pop('b'))
print(mydict)

print(mydict.popitem())
print(mydict)


d1={'a':10,'b':34,'c':56}
d2={'d':400,'e':500}

print(d1.update(d2))
print(d1)

