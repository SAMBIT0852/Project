# km=int(input("Enter the distance in km : "))
# m=km/1000
# inch=km*39270.1
# cm=km*100000
# print("km in meater is",m)
# print("km in inch is ",inch)
# print("km in cm is ",cm)


# m=int(input("Enter the subject mark of math : "))
# e=int(input("Enter the subject mark of eng : "))
# c=int(input("Enter the subject mark of cum : "))
# l=int(input("Enter the subject mark of logic : "))
# p=int(input("Enter the subject mark of python : "))
# total_mark=m+e+c+l+p
# percentage =(total_mark/500)*100
# print("Total mark obtained by the student is : ",total_mark )
# print("Percentage of the student is : " , percentage,"%")


# principal=int(input("Enter the principal amount : "))
# rate=int(input("Enter the rate : "))
# time=int(input("Enter the time period(in year) : "))
# Simple_Interest=(principal*rate*time)/100
# print("The Simple Interest is ",Simple_Interest)


# W=float(input("Enter the Weight in pound : "))
# h=float(input("Enter the height in cm : "))
# Weight=W*0.45359237
# height=h*0.0254
# print("the Weight of the person is ",Weight,"kg")
# print("the height of the person is ",height,"inch")
# bmi=Weight/height**2
# print("The body mass index is : ",bmi)


# a=int(input("Enter a number between 100 and 999 : "))
# b=0
# while a>0:
#     digit=a%10
#     b+=digit
#     a=a//10
# print("The digit sum is ",b)


# num=int(input("Enter a number between 100 and 999 : "))
#
# if(num>100 and num<=999):
#     b = 0
#     original_num=num
#     while num>0:
#         digit=num%10
#         b+=digit
#         num=num//10
#
#     print("The digit sum of original_num is ",b)
# else:
#     print("Invalid in put.")


# num = int(input("Enter a number between 100 and 999: "))
#
# if 100 <= num <= 999:  # Validate input first
#     b = 0
#     original_num = num  # Save the original number for later use
#     while num > 0:
#         digit = num % 10
#         b += digit
#         num = num // 10
#     print(f"The digit sum of {original_num} is {b}")
# else:
#     print("Invalid input. Please enter a number between 100 and 999.")


# x=int(input("Enter the number : "))
# if(x>9 and x<=999):
#     b=0
#     original_num=x
#     while(x>0):
#         digit=x%10
#         b+=digit
#         x=x//10
#     print(f"The digit sum of the {original_num} is {b}")
# else:
#     print("Invalid Input")


# s=int(input("Enter a  number : "))
# if(s>9 and s<=10000):
#     q=0
#     Original_num=s
#     while(s>0):
#         digit=s%10
#         q+=digit
#         s= s//10
#     print(f"The digit sum of the {Original_num} is {q}")
# else:
#     print("Invalid Input")


# height=int(input("Enter the height of the triangle : "))
# base=int(input("Enter the base of the triangle : "))
# Area=1/2*base*height
# print("Area of the Right angled Triangle is  " , Area)


# a=int(input("Enter the first number : "))
# b=int(input("Enter the second number : "))
# c=int(input("Enter the third number: "))
# if(a>b and a>c):
#     print(f"{a} is largest")
#
# elif(b>a and b>c):
#     print(f"{b} is largest")
# else:
#     print(f"{c} is largest")


# n=int(input("Enter the number : "))
# if(n%2==0):
#     print("The number is even ")
# else:
#     print("The number is odd ")


# a=int(input("Enter the first number : "))
# b=int(input("Enter the second number : "))
# if(a%b==0):
#     print("The number are evenly divided")
#
# else:
#     print("The number are  not evenly divided")


# import math
# a=int(input("Enter the first number : "))
# b=int(input("Enter the second number : "))
# c=int(input("Enter the third number : "))
# d=b*b-4*a*c
# if(d>0):
#     root1=(-b+math.sqrt(d))/2*a
#     root2=(-b-math.sqrt(d))/2*a
#
#     print("The root are two distinct real root" , root1 , root2)
# elif(d==0):
#     root=-b/2*a
#     print("The root is real and repeted ", root)
#
# else:
#     print("There is no real root")


# import math
# a=int(input("Enter the first number : "))
# b=int(input("Enter the second number : "))
# c=int(input("Enter the third number : "))
# d=b*b-4*a*c
# if(d>0):
#     root1=(-b+math.sqrt(d))/2*a
#     root2=(-b-math.sqrt(d))/2*a
#     print("The equation has two real root ", root1 , root2 )
#
# elif(d==0):
#     root=-b/2*a
#     print("The equation has one distinct root ", root)
#
# else:
#     print("the equation has no real root.")
#


# mark=int(input("Enter the  mark of the student  : "))
# if (mark>90 and mark<=100):
#     print("The student get O grade")
# elif(mark>80 and mark<=89):
#     print("The student get A grade")
# elif(mark>70 and mark<=79):
#     print("The student get B grade")
# elif(mark>60 and mark<=69):
#     print("The student get C grade")
# elif(mark>50 and mark<=59):
#     print("The student get D grade")
# elif(mark>40 and mark<=49):
#     print("The student get E grade")
# else:
#     print("The student fail in the examination")


# a=int(input("Enter a three digit number : "))
# h=a//100
# t=(a//10)%10
# d=(a%10)
# sum=h**3+t**3+d**3
# if(sum==a):
#     print("The number is a armstrong number ")
# else:
#     print("The number is not a armstrong number")


# a=int(input("Enter the first number : "))
# b=int(input("Enter the second number : "))
# c=int(input("Enter the third number : "))
# d=int(input("Enter the fourth number : "))
# sum=a+b
# product=c*d
# print("The sum of the first two number is ", sum)
# print("The product of last two number is ", product)


# m=int(input("Enter the Month(1-12) : "))
# n=int(input("Enter the day (1-31) : "))
# if(m==3 and n>=20) or (m==4) or (m==5) or (m==6) and n<=20 :
#     print("True")
#
# else:
#     print("false")


# amount=int(input("Enter the totall money : "))
# n20=amount//20
# rem=amount%20
# n10=amount//10
# rem=amount%10
# n5=amount//5
# rem=amount%5
# n1=rem
# print("20 rupees note " , n20)
# print("10 rupees note " , n10)
# print("5 ruppes note " , n5)
# print("1 rupees coin " , n1)


