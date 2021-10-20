""" 
Prints a door mat of size N * M (N is an odd natural number, and M is 3 times N).
Input format:
A single line containing the space separated values of N and M.
"""

string_input = input()
measurements = string_input.split()
length = int(measurements[0])
breadth = int(measurements[1])

triangle= ".|."
line_number = 1
half_height = length//2

# top part
for i in range(half_height):
    print((triangle*line_number).center(breadth, '-'))
    line_number += 2

# center 
middle = "WELCOME"
print(middle.center(breadth,'-'))

# lower part
for i in range(half_height):
    print((triangle*(line_number-2)).center(breadth, '-'))
    line_number -= 2