"""
Replace `sample_file_path.txt` with the location of desired text file to remove duplicate lines(full location path if it is not in the same directory as the code file and only tge name if it is in the same directory as the code file).
A new text file "clean_text.txt" will be created in the saem directory as tge code file with no duplicate lines from `sample_file_path.txt`. 
"""

content = open('sample_file_path.txt','r').readlines()

content_set = set(content)

clean_data = open('clean_text.txt','w')

for line in content_set:
    clean_data.write(line)