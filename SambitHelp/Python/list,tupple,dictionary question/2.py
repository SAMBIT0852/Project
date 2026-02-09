def list_comperahenson(n):
    return [[i*j for j in range (1,6)]for i  in range (1,n+1)]
n=int(input("Enter the number : "))
print(list_comperahenson(n))
