# i=int(input("Enter the stating number : "))
# j=int(input("Enter the ending number : "))
# k=int(input("Enter the stepping number : "))
# while(i<=j):
#     print(i,end=" , ")
#     i+=k



# i=1000
# while(i<=2000):
#     print(i,end=" , ")
#     if(i%5==0):
#      print()
#     i+=1


# n=int(input("Enter a number : "))
# sum=0
# for i in range(n):
#     if(i%3==0 and i%5==0):
#         sum+=i
# print("the sum of the multiple of 3 and 5 is : ", sum)


# i=1
# n=int(input("Enter the rows : "))
# while(i<=n):
#     j=1
#     while(j<=i):
#         print("*",end=" ")
#         j+=1
#     print()
#     i+=1


# i=1
# n=int(input("Enter the nuber of rows : "))
# while(i<=n):
#     j=1
#     while(j<=i):
#         print("*",end=" ")
#         j+=1
#     print()
#     i+=1


# i=1
# n=int(input("enter the number of rows : "))
# while(i<=n):
#     j=1
#     while(j<=i):
#         print("*",end=" ")
#         j+=1
#     print()
#     i+=1

# i=1
# n=int(input("Enter the number of rows : "))
# while(i<=n):
#     j=1
#     while(j<=i):
#         print(i,end=" ")
#         j+=1
#     print()
#     i+=1


# n=int(input("Enter the number of rows : "))
# for i in range(n):
#     for j in range(n-i-1):
#         print(" ",end=" ")
#     for k in range(2*i+1):
#         print("*",end=" ")
#
#     print()


# n=int(input("Enter the number of rows : "))
# for i in range (n):
#     for j in range(n-i-1):
#         print(" ",end=" ")
#     for k in range (2*i-1):
#         print(i,end=" ")
#     print()

# n=int(input("Enter the vallue : "))
# deno=0
# for i in range(1,n+1):
#     deno=deno+1/i
# print("The harmonic mean is ",n/deno)


# n=int(input("Enter the number : "))
# deno=0
# for i in range (1,n+1):
#     deno=deno+1/i
# print("The harmonic mean of the number is :",n/deno)

# sum=0
# n=int(input("Enter the number : "))
# for i in range(1,n+1,2):
#     if(i//2)%2==0:
#         sum=sum+i
#     if(i//2)%2!=0:
#         sum=sum-i
# print("The sum of the series is ", sum)


# n=int(input("Enter the number :  "))
# fact=1
# for i in range(1,n+1):
#     fact*=i
# print("The factorial of the number is ",fact)

# x=int(input("Enter the value of x : "))
# n=int(input("Enter the value of n : "))
# fact=1
# for i in range (1,n+1):
#     fact*=i
#     value=x**n/fact
# print("the required value is : ", value)


# n=int(input("Enter the no of terms : "))
# if(n>=1):
#     a=0
#     b=1
#     i=1
#     while(i<=n):
#         print(a,end=", ")
#         next=a+b
#         a=b
#         b=next
#         i+=1


# sentence=str(input("Enter the sentence : "))
# words=sentence.split()
# reversed_sentence=" ".join(reversed(words))
# print(reversed_sentence)


# n=int(input ("Enter the number of rows : "))
# i=1
# while(i<+n):
#     j=1
#     while(j<=i):
#         print("*",end=" ")
#         j+=1
#     print()
#     i+=1


# n=int(input("Enter the nuber of rows : "))
# for i in range(n):
#     for j in range(n-i-1):
#         print(" ",end=" ")
#     for k in range (2*i-1):
#         print("*", end=" ")
#     print()


# n=int(input("Enter the number of rows : "))
# i=1
# while(i<=n):
#     j=1
#     while(j<=i):
#         print(i,end=" ")
#         j+=1
#     print()
#     i+=1


# n=int(input("Enter the the number : "))
# deno=0
# for i in range (1,n+1):
#     deno=deno+1/i
# print("the harmonic mean of the number is ",n/deno)

# n=int(input("Enter the number : "))
# fact=1
# for i in range (1,n+1):
#     fact*=i
# print("The factorial of the number is ",fact)

# n=int(input("Enter the number : "))
# sum=0
# for i in range (1,n+1,2):
#     if(i//2)%2==0:
#         sum=sum+i
#     if(i//2)%2!=0:
#         sum=sum-i
# print("the sum of the series is : ",sum)

# n=int(input("Enter the number : "))
# if(n>=1):
#     a=0
#     b=1
#     i=1
#     while(i<=n):
#         print(a,end=" ")
#         next=a+b
#         a=b
#         b=next
#         i+=1

# n=int(input("Enter the number : "))
# if(n>=1):
#     a=0
#     b=1
#     c=2
#
#     for i in range(1,n+1):
#         print(b,end=" ")
#         d=a+b+c
#         a=b
#         b=c
#         c=d


# n=int(input("Enter the number : "))
# if(n<=0):
#     print("Enter a  positive number ")
# else:
#     i=0
#     while(n>0):
#         d=n%10
#         i=i*10+d
#         n//=10
#     print("The reversed number is ", i)

# n=int(input("Enter the number : "))
# if(n<0):
#     print("Enter a higher number")
# else:
#     fact0=1
#     for i in range(1,n+1):
#
#         fact=1
#         for j in range(1,i+1):
#             fact*=j
#         fact0+=fact
#     print(fact0)

# for num in range(1,501):
#     sum=0
#     for i in range(1,num):
#         if num%i==0:
#             sum=sum+i
#     if sum==num:
#         print(num)

# for num in range (1,501):
#     sum=0
#     for i in range (1,num):
#         if num%i==0:
#             sum=sum+i
#     if sum==num:
#         print(num)

