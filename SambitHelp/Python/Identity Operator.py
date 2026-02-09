firstlist =[]

secondlist =[]

if firstlist == secondlist:
    print("both are eqall")

else:
    print("Botha are not equall")



if firstlist is secondlist:

    print("both variable are pointing to the same location")

else:
    print("Both variables are not pointing to the same location")


thirdlist = firstlist

if thirdlist is secondlist:
    print("Both are pointing to the same object")

else:
    print("Both are not pointing to the same object")

if firstlist is not secondlist:
    print("Both first and second list are same object")

else:
    print("Both first and second list are  not same object")
