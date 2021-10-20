"""
Converts and Returns all lowercase letters to uppercase letters and vice versa.
"""

def swap_case(string):
    lst= list()
    ascii_val = list(map(ord, string))

    for value in ascii_val:
        if value>64 and value<92:
            value = value + 32
        elif value>96 and value<124:
            value = value - 32
        else:
            value = value
        lst.append(value)
    scase = list(map(chr , lst))

    res = ""
    for c in scase:
        res += c

    return res

str_input = input()
print(swap_case(str_input))