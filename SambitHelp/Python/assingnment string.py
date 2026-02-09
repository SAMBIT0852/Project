# def count_word_string_with_vowel(sentance):
#     vowels='aeiouAEIOU'
#     words=sentance.split()
#     count=0
#     for word in words:
#         if word[0] in vowels:
#             count+=1
#     return count
# sentance=input(" Enter the sentance : ")
# result= count_word_string_with_vowel(sentance)
# print(result)


# def count_word_starting_with_vowel(sentance):
#     vowels='aeiouAEIOU'
#     words=sentance.split()
#     count=0
#     for word in words:
#         if word[0] in  vowels:
#             count+=1
#     return(count)
# sentance=input("Enter the sentance : ")
# result= count_word_starting_with_vowel(sentance)
# print(result)


# def capitalize_words(sentance):
#     result= ''
#     word= ''
#     for i in range(len(sentance)):
#         char=sentance[i]
#         if char == " " or i == len(sentance)-1:
#             if word:
#                 result += word[0].upper() +word[1:].lower()
#                 word=""
#             result+=char
#
#         else:
#             word+=char
#     if word:
#         result += word[0].upper()+word[1:].lower()
#     return result
# sentance=input("Enter the sentance : ")
# result= capitalize_words(sentance)
# print(result)
#
#

# def count_words(sentance):
#     words=sentance.split()
#     return(len(words))
# sentance=input("enter the sentance : ")
# result= count_words(sentance)
# print(result)

# def capitalize_words(sentence):
#     result = ''
#     for word in sentence.split():
#         result += word[0].upper() + word[1:].lower() + ' '
#     return result.strip()
#
# sentence = input("Enter the sentence: ")
# print(capitalize_words(sentence))

# def reverse_string(input_string):
#     reverse_string=input_string[::-1]
#     return(reverse_string)
# input_string=input("Enter the sentence : ")
# print(reverse_string(input_string))


# def reverse(sentence):
#     reverse=sentence[::-1]
#     return(reverse)
# sentence=input("Enter the sentence : ")
# print(reverse(sentence))

# sentence=input("Enter the sentence :")
# words=sentence.split()
# reversed_sentence=" ".join(reversed(words))
# print(reversed_sentence)

# globalVar = 10
# def test():
#     localVar = 20
#     print('Inside function test :globalVar =', globalVar)
#     print('Inside function test : localVar=', localVar)
# test()
# print('Outside function test : globalVar=', globalVar)
# print('Outside function test : localVar=', localVar)

# globalVar = 10
# def test():
#     localVar = 20
#     globalVar = 30
#     print('Inside function test :globalVar =', globalVar)
#     print('Inside function test : localVar=', localVar)
# test()
# print('Outside function test : globalVar=', globalVar)

# globalVar = 10
# def test():
#     global globalVar
# localVar = 20
# globalVar = 30
# print('Inside function test :globalVar =', globalVar)
# print('Inside function test : localVar=', localVar)
# test()
# print('Outside function test : globalVar=', globalVar)


# a = 4
# def f():
#     a = 5
#     def g():
#         nonlocal a
#         a = 10
#     print('inside function g,', 'a = ',a)
#     def h():
#         nonlocal a
#         a = 20
#         print('inside function h,', 'a = ',a)
#         h()
#     g()
#     print('inside function f,', 'a = ', a)
# f()

# x = 2
# def test():
#     x = x + 1
#     print(x)
# print(x)


# x=2
# def test():
#     global x
#     x=x+1
#     print(x)
# print(x)

# def clean(string):
#     clean=string.replace(" ","").lower()
#     if clean==clean[::-1]:
#         return("the string is symmetric")
#     else:
#         return("the string is not symmetric")
# string=input("Enter the string :")
# print(clean(string))

# def contain_digit(string):
#     digit=False
#     alpha=False
#     for char in string:
#         if char.isdigit():
#             digit=True
#         elif char.isalpha():
#             alpha=True
#         if digit and alpha:
#             return True
#     return False
# sentence=input("Enter a string : ")
# if contain_digit(sentence):
#     print("The string contain one alphabet and one digit")
# else:
#     print("Error")


# def duplicat_remove(string):
#     result=""
#     let=set()
#     for char in string:
#         if char  not in let:
#             result+=char
#             let.add(char)
#     return result
# m=input("Enter the string : ")
# result=duplicat_remove(m)
# print("the string after duplicate remove - " , result)

# def count_character_frequency(string):
#     frequency={}
#     for char in string:
#         if char in frequency:
#             frequency[char]+=1
#         else:
#             frequency[char]=1
#     return frequency
# s=input("Enter the string : ")
# result=count_character_frequency(s)
# print("THe number of frequency is :",result,end=" ")


def maximum_frequency(string):
    frequency={}
    for char in string:
        if char in frequency:
            frequency[char]+=1
        else:
            frequency[char]=1
    max_char=max(frequency,key=frequency.get)
    return max_char,frequency[max_char]
s=input("Enter the string : ")
char,frequency=maximum_frequency(s)
print(f"{char} maximum frequency is {frequency}")


