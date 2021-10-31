"""
Input: First name and last name in different lines (seperated by \n).
"""

def print_full_name(first, last):
    print("Hello",first,last, end="!")
    print(" You just delved into python.")

first_name = input()
last_name = input()
print_full_name(first_name, last_name)