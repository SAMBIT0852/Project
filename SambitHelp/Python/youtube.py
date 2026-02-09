# Python 3.13.0 (tags/v3.13.0:60403a5, Oct  7 2024, 09:38:07) [MSC v.1941 64 bit (AMD64)] on win32
# Type "help", "copyright", "credits" or "license()" for more information.
# 2+3
# 5
# 9-8
# 1
# 8/4
# 2.0
# 5//2
# 2
# 8+2*3
# 14
# (8+4)*3
# 36
# 2**4
# 16
# 10%3
# 1
# "SAMBIT"
# 'SAMBIT'
# print('sambit')
# sambit
# print('sambit\'s "laptop")
      
# SyntaxError: unterminated string literal (detected at line 1); perhaps you escaped the end quote?
# print('sambit/'s "laptop")
      
# SyntaxError: invalid syntax. Perhaps you forgot a comma?
# print('sambit \'s "laptop")
      
# SyntaxError: unterminated string literal (detected at line 1); perhaps you escaped the end quote?
# print("sambit's laptop")
      
# sambit's laptop



# print("sambit's laptop" "End of video 1")
      
# sambit's laptopEnd of video 1




# //video 2 //video 2   //video 2      //video 2         //video 2         //video 2           //video 2       //video 2



# x=2
      
# x+3
      
# 5
# y=4
      
# x+4("variables")
      
# Traceback (most recent call last):
#   File "<pyshell#23>", line 1, in <module>
#     x+4("variables")
# TypeError: 'int' object is not callable
# x+y
      
# 6
# x=9
      
# x+y
      
# 13
# x
      
# 9
# _+10
      
# 19
# _+y
      
# 23
# name='sambit'
      
# name
      
# 'sambit'
# name[0]
      
# 's'
# name[-4]      
# 'm'
# name[0:3]
      
# 'sam'
# name[1:]
      
# 'ambit'
# name[4:]
      
# 'it'
# 'Mr.' +name[0:]
      
# 'Mr.sambit'
# my name = "Sambit Kumar Mishra - End of v2"
      
# SyntaxError: invalid syntax
# myname = "Sambit Kumar Mishra - End of v2"
      
# len(myname)
      
# 31
# myname
      
# 'Sambit Kumar Mishra - End of v2'

# //Video 3    //Video 3    //Video 3         //Video 3           //Video 3           //Video 3           //Video 3

# nums=[23,45,56,67]
      
# nums
      
# [23, 45, 56, 67]
# names=['sambit', 'ram', 'john']
      
# names
      
# ['sambit', 'ram', 'john']
# values=[4.5,'sambit',4]
      
# values
      
# [4.5, 'sambit', 4]
# mil=[nums,names]
      
# mil
      
# [[23, 45, 56, 67], ['sambit', 'ram', 'john']]
# nums.insert(56)
      
# Traceback (most recent call last):
#   File "<pyshell#53>", line 1, in <module>
#     nums.insert(56)
# TypeError: insert expected 2 arguments, got 1
# >>> nums.insert[34]
# ...       
# Traceback (most recent call last):
#   File "<pyshell#54>", line 1, in <module>
#     nums.insert[34]
# TypeError: 'builtin_function_or_method' object is not subscriptable
# >>> nums.append(56)
# ...       
# >>> nums
# ...       
# [23, 45, 56, 67, 56]
# >>> del nums[2:]
# ...       
# >>> nums
# ...       
# [23, 45]
# >>> nums.extend([35, 67, 32, 87])
# ...       
# >>> nums
# ...       
# [23, 45, 35, 67, 32, 87]
# >>> min(nums)
# ...       
# 23
# >>> max(nums)
# ...       
# 87
# >>> sum(nums)
# ...       
# 289
# >>> nums.sort()
# ...       
# >>> nums
# ...       
# [23, 32, 35, 45, 67, 87]
# >>> names=['end of list and v3']
# ...       
# >>> names
# ...       
# ['end of list and v3']


# video 4       # video 4       # video 4       # video 4         # video 4         # video 4       # video 4       # video 4

# Python 3.13.0 (tags/v3.13.0:60403a5, Oct  7 2024, 09:38:07) [MSC v.1941 64 bit (AMD64)] on win32
# Type "help", "copyright", "credits" or "license()" for more information.
# tup = (21,33, 45,67,23)
# tup
# (21, 33, 45, 67, 23)
# tup[3]
# 67
# tup[5]=67
# Traceback (most recent call last):
#   File "<pyshell#3>", line 1, in <module>
#     tup[5]=67
# TypeError: 'tuple' object does not support item assignment

# s={22,45,57,78}
# s
# {57, 45, 22, 78}
# s.add(34)
# s
# {34, 45, 78, 22, 57}


#video5       #video5       # video 5       # video 5       # video 5       # video 5         #video5           #video5


# data={1:'sambit', 2:'kiran', 3:'harsh'}
# data
# {1: 'sambit', 2: 'kiran', 3: 'harsh'}
# data[3]
# 'harsh'
# \
# data[1]
# 'sambit'
# data.get(3)
# 'harsh'
# data.get(5)
# print(data.get(5))
# None
# data.get(5,'not found')
# 'not found'
# data[5]
# Traceback (most recent call last):
#   File "<pyshell#9>", line 1, in <module>
#     data[5]
# KeyError: 5

                                                                    
# keys=['sambit', 'kiran', 'harsh']                                   #dictionary with the help of list
# values=['MCA','BCA', 'BSC']
# data=dict(zip(keys,values))
# data
# {'sambit': 'MCA', 'kiran': 'BCA', 'harsh': 'BSC'}


#  data ['monika']='cs'                                                 #add values  to dictionary
# SyntaxError: unexpected indent
# data ['monika']='cs'
# data
# {'sambit': 'MCA', 'kiran': 'BCA', 'harsh': 'BSC', 'monika': 'cs'}
# del data['harsh']
# data
# {'sambit': 'MCA', 'kiran': 'BCA', 'monika': 'cs'}


                                                                    # creating a dictionary in which there is itself dictionary and list in it
    
# prog={'js':''atom', 'cs':'vs', 'python':['pycham','sublime'],'java':{'jse':'netbeans','jee':'eclipse'}}   
# SyntaxError: unterminated string literal (detected at line 1)
# prog={'js':'atom', 'cs':'vs', 'python':['pycham','sublime'],'java':{'jse':'netbeans','jee':'eclipse'}}    
# prog                                                                                        
# {'js': 'atom', 'cs': 'vs', 'python': ['pycham', 'sublime'], 'java': {'jse': 'netbeans', 'jee': 'eclipse'}}
# prog('js')     
# Traceback (most recent call last):
#   File "<pyshell#22>", line 1, in <module>
#     prog('js')
# TypeError: 'dict' object is not callable
# prog['java']     
# {'jse': 'netbeans', 'jee': 'eclipse'}
# prog['java']['jee']    
# 'eclipse'
# prog['python']    
# ['pycham', 'sublime']

##video-6       video-6         video-6     video-6         video-6         video-6
# num=10
# id(num)
# 140724484039880
# name="sambit"
# id(name)
# 2517248313264
# a=10
# b=a
# a
# 10
# b
# 10
# id(a)
# 140724484039880
# id(b)
# 140724484039880
# k=9
# a=k
# id(a)
# 140724484039848
# type(a)
# <class 'int'>
# PI=3.14
# id(PI)
# 2517241723120
# type(PI)
# <class 'float'>
# type(name)
# <class 'str'>

## Video-7      Video-7         Video-7         Video-7         Video-7         Video-7         Video-7
# num =2.5
# type(num)
# <class 'float'>
# num=5
# type(num)
# <class 'int'>
# num = 6+9j
# type(num)
# <class 'complex'>
#  a = 5.6
# SyntaxError: unexpected indent
# a=4.6
# b=int(a)
# type(b)
# <class 'int'>
# b
# 4
# k =float(b)
# k
# 4.0
# k=7
# c=complex(b,k)
# c
# (4+7j)(j=root of -1)
# b>k
# False
# b<k
# True
# bool=b<k
# bool
# True
# type(bool)
# <class 'bool'>
# int(true)
# Traceback (most recent call last):
#   File "<pyshell#21>", line 1, in <module>
#     int(true)
# NameError: name 'true' is not defined. Did you mean: 'True'?
# int(True)
# 1
# int(False)
# 0
# lst=[11,88,78,49,36]
# type9(list)
# Traceback (most recent call last):
#   File "<pyshell#25>", line 1, in <module>
#     type9(list)
# NameError: name 'type9' is not defined. Did you mean: 'type'?
# type(list)
# <class 'type'>
# type(lst)
# <class 'list'>
# s={4,55,11,98,78}
# type(s)
# <class 'set'>
# t=(1,88,32,81)
# type(t)
# <class 'tuple'>
# str="sambit"
# type(str)
# <class 'str'>
# range{0,10}
# SyntaxError: invalid syntax
# range(0,10)
# range(0, 10)
# list(range(10))
# [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
# list(range(2,10,2))
# [2, 4, 6, 8]
# d = {"sambi":"samsung", "rahul":"iphone", "rohit":"oneplus"}
# d
# {'sambi': 'samsung', 'rahul': 'iphone', 'rohit': 'oneplus'}
# d.keys()
# dict_keys(['sambi', 'rahul', 'rohit'])
# d.values()
# dict_values(['samsung', 'iphone', 'oneplus'])
# d["sambi"]
# 'samsung'
# d.get("rahul")
# 'iphone'

# video-8       video-8         video-8         video-8         video-8         video-8         video-8

# x=2
# y=3
# x+y
# 5
# x*y
# 6
# x/y
# 0.6666666666666666
# x=x+2
# x
# 4
# x+=2
# x
# 6
# x*=3
# x
# 18
# a,b=5,6
# a
# 5
# b
# 6
# n=7
# n
# 7
# -n
# -7
# n=-n
# n
# -7
# a<b
# True
# a>b
# False
# a==b
# False
# a=6
# a==b
# True
# a<=b
# True
# a>=b
# True
# a!=b
# False
# b=7
# a!=b
# True
# a=5
# b=4
# a<8and b<5
# True
# a<8 and b<2
# False
# a<8 or b<2
# True
# x=True
# x
# True
# notx
# Traceback (most recent call last):
#   File "<pyshell#36>", line 1, in <module>
#     notx
# NameError: name 'notx' is not defined
# not x
# # False

# video 9           video 9         video 9             video 9         video 9     video 9         video 9
# bin(25)
# '0b11001'
# 0b0101
# 5
# oct(25)
# '0o31'
# hex(25)
# '0x19'
# hex(10)
# '0xa'
# 0xf
# 15

# video10           video10             video10         video10         video10         video10
# 8&3
# 0
# 5&6
# 4
# 33|5
# 37
# 5|3
# 7
# 2^4
# 6
# 6^9
# 15
# 5<<3
# 40
# 8<<3
# 64
# 10>>1
# 5
# 6>>2
# 1

# video11           video11             video11         video11         video11         video11
# x=sqrt(25)
# Traceback (most recent call last):
#   File "<pyshell#0>", line 1, in <module>
#     x=sqrt(25)
# NameError: name 'sqrt' is not defined
# import math
# x=math.sqrt
# x
# <built-in function sqrt>
# print(math.floor(2.6))
# 2
# print(math.ceil(5.2))
# 6
# print(math.pi)
# 3.141592653589793
# print(math.e)
# 2.718281828459045
# m.sqrt
# Traceback (most recent call last):
#   File "<pyshell#8>", line 1, in <module>
#     m.sqrt
# NameError: name 'm' is not defined
# import math as m
# math.sqrt(25)
# 5.0
# m.sqrt(25)
# 5.0
# from math import sqrt,pow
# pow(2,3)
# 8.0
# math.help
# Traceback (most recent call last):
#   File "<pyshell#15>", line 1, in <module>
#     math.help
# AttributeError: module 'math' has no attribute 'help'
# help("math")
# Help on built-in module math:

# video12           video12             video12         video12         video12 

# x=input("Enter 1st number")
# y=input("Enter 2nd number") it always gives the string vallue

# x=input("Enter 1st number")
# a=int(x)
# y=input("Enter 2nd number")    --- another way of writing the user input code but it will waste some integer.
# b=int(y)
# z=a+b

# x=int(input("Enter 1st number"))
# y=int(input("Enter 2nd number"))
# z=x+y
#
# print(z)

# ch=input("enter a char")[0]
# print(ch)

# result=eval(input("Enter an expr "))       -----we can use the expration of evaluation.
# print (result)

# video12           video12             video12         video12         video12

a=6
b=8

# a=a+b
# a=a-b
# a=a-b

# a=a^b
# a=a^b
# a=a^b

# temp=a
# a=b
# b=temp

a,b=b,a
# only happen in python if the variable are in the same line.
print(a)
print(b)

# video13           video13             video13         video13         video13

# i=1
# while(i<=5):
#     print("Sambit")
#     i=i+1

# i=1
# while(i<=5):
#     print("Sambit" , i)
#     j=1
#     while(j<=5):
#         print("Cool")
#         j=j+1
#
#
#     i=i+1

# i=1
# while(i<=5):
#     print("Sambit" , i , end="")
#     j=1
#     while(j<=5):
#         print("Cool" , end="")
#         j=j+1
#     i=i+1

# i=1
# while(i<=5):
#     print("Sambit" , i , end="")
#     j=1
#     while(j<=5):
#         print("Cool" , end="")
#         j=j+1
#     i=i+1
#     print()

# video14           video14            video14         video14         video14


# x=("sambit",89,23)
# for i in x:
#     print(i)

# x=("sambit")
# for i in x:
#     print(i)

#
# for i in [2,5,"lipu"]:
#     # print(i)

# for i in range (12):
#     print(i)

# for i in range (11,21,1):
#     print(i)

# for i in range (11,21,2):
#     print(i)

# for i in range (20,10,-1):
#     print(i)

# for i in range (1,21):
#     if i%5!=0:
#         print(i)

# for i in range (1,21):
#     if i%3!=0:
#         print(i)


# import math
# for i in range (1,501):
#     root=int(math.sqrt(i))            # print all the perfect squre number between 1 and 500
#     if(root * root == i):
#         print(i,)

#  video14           video14            video14         video14         video14

# x=int(input("How many candies : "))
#
# i=1
# while i<=x:
#     print("candy")
#     i+=1

#

#
# for i in range(1,101):
#     if(i%3==0):
#         continue
#     print(i)
#
# print("bye")

# for i in range(1,101):
#     if(i%3==0) or (i%5==0):
#         continue
#     print(i)

# print("bye")

# for i in range(1,101):
#     if(i%2!=0):
#         pass
#     else:
#         continue
#     print(i)

# print("bye")


# video15          video15            video15         video15         video15


# for i in range(4):                    # innitialize and gives how many rows.
#       for j in range (4):             # the j vallue is known as the coloumn value and depends upon the i vallue.
#          print("#",end =" ")          # end is written because for give the out put in desire manner if it not given
#                                       #the whole out put comes under a single coloumn
#       print()                         #if  it is not  written the entire out put comes in a single row.

# for i in range(5):
#     for j in range(4-i):
#         print("*",end = " ")
#     print()
# out put-
#     * * * *
#     * * *
#     * *
#     *

# for i in range(5):
#     for j in range (i+1):
#         print("@", end = " ")
#     print()
# output-
# @
# @ @
# @ @ @
# @ @ @ @
# @ @ @ @ @

# for i in range(5):
#     for j in range(5-i):
#         print(" ",end =" ")
#     for k in range(2*i+1):
#         print("*",end=" ")
#     print()
# output-
#             *
#           * * *
#         * * * * *
#       * * * * * * *
#     * * * * * * * * *


# for i in range (5):
#     for j in range(5):
#         print("#",end =" ")
#     print()
# output-
# # # # # # 
# # # # # # 
# # # # # # 
# # # # # # 
# # # # # # 



















































