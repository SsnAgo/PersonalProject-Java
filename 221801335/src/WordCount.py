import sys
import os
import re
def count_char(filename):
    if not filename.endswith(".txt"):
        filename+=".txt"
    dir=os.getcwd()+"/"+filename
    count=0
    with open(dir,"r",encoding="utf-8") as f:
        for line in f:
            count+=len(line)
    return count

def count_word(filename):
    if not filename.endswith(".txt"):
        filename+=".txt"
    dir=os.getcwd()+"/"+filename
    count=0
    with open(dir,"r",encoding="utf-8") as f:
        for line in f:
            lis=re.split(r"[^a-zA-Z0-9]",line)
            new_lis=[x for x in lis if x!=""]
            print(new_lis)
            for item in new_lis:
                if not re.match(r"[a-zA-Z]{4}[a-zA-Z0-9]*",item)==None:
                    count+=1
    print(count)
    return count

def count_row(filename):
    if not filename.endswith(".txt"):
        filename+=".txt"
    dir=os.getcwd()+"/"+filename
    count=0
    with open(dir,"r",encoding="utf-8") as f:
        for line in f:
            new_line=line.strip('\n')
            if new_line!="":
                count+=1
    print(count)
    return count


def count_fword(filename):
    if not filename.endswith(".txt"):
        filename+=".txt"
    dir=os.getcwd()+"/"+filename
    dict={}
    with open(dir,"r",encoding="utf-8") as f:
        for line in f:
            lis=re.split(r"[^a-zA-Z0-9]",line)
            new_lis=[x for x in lis if x!=""]
            print(new_lis)
            for item in new_lis:
                new_item=item.lower()
                if not re.match(r"[a-zA-Z]{4}[a-zA-Z0-9]*",new_item)==None:
                    if dict.__contains__(new_item):
                        dict[new_item]+=1
                    else:
                        dict[new_item]=1
    return sorted(dict.items(),key=lambda x:(-x[1],x[0]))

# dir=os.getcwd()
# # 打开一个文件
# f = open(dir+"/result.txt", "w",encoding="utf-8")
# f.write(str(count_char("foo")))
# f.close()
# count_word("foo")
print(count_fword("foo"))