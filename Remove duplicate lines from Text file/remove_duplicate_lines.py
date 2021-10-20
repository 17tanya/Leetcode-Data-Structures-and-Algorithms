content = open('sample_file_path.txt','r').readlines()

content_set = set(content)

clean_data = open('clean_text.txt','w')

for line in content_set:
    clean_data.write(line)