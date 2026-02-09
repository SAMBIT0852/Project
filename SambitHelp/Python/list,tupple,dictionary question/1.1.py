def generate_multiplies(n):
    result=[]
    for i in range (1,n+1):
        multiplies=[i*j for j in range (1,6)]
        result.append(multiplies)
    return result
n=int(input("enter the number : "))
print(generate_multiplies(n))

    
