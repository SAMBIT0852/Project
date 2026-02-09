def multiply(a,b):
    if b==1:
        return a
    else:
        return a+multiply(a,b-1)
a=int(input("Enter the number 1 : "))
b=int(input("Enter the number 2 : "))
print(multiply(a,b))
