import sys
import os
import re


def count_char(filename):
    if not filename.endswith(".txt"):
        filename += ".txt"
    dir = os.getcwd() + "/" + filename
    count = 0
    with open(dir, "r", encoding="utf-8") as f:
        for line in f:
            count += len(line)
    return count

def count_word(filename):
    if not filename.endswith(".txt"):
        filename += ".txt"
    dir = os.getcwd() + "/" + filename
    count = 0
    with open(dir, "r", encoding="utf-8") as f:
        for line in f:
            lis = re.split(r"[^a-zA-Z0-9]", line)
            new_lis = [x for x in lis if x != ""]
            for item in new_lis:
                if not re.match(r"^[a-zA-Z]{4}[a-zA-Z0-9]*", item) == None:
                    count += 1
    return count

def count_row(filename):
    if not filename.endswith(".txt"):
        filename += ".txt"
    dir = os.getcwd() + "/" + filename
    count = 0
    with open(dir, "r", encoding="utf-8") as f:
        for line in f:
            new_line = line.strip('\n')
            new_line=re.sub(r'\s','',new_line)
            if new_line != "":
                count += 1
    return count

def count_fword(filename):
    if not filename.endswith(".txt"):
        filename += ".txt"
    dir = os.getcwd() + "/" + filename
    dict = {}
    with open(dir, "r", encoding="utf-8") as f:
        for line in f:
            lis = re.split(r"[^a-zA-Z0-9]", line)
            new_lis = [x for x in lis if x != ""]
            for item in new_lis:
                new_item = item.lower()
                if not re.match(r"^[a-zA-Z]{4}[a-zA-Z0-9]*", new_item) == None:
                    if dict.__contains__(new_item):
                        dict[new_item] += 1
                    else:
                        dict[new_item] = 1
    return sorted(dict.items(), key=lambda x: (-x[1], x[0]))[:10]

def file_read_out(intxt,outtxt):
    if not outtxt.endswith(".txt"):
        outtxt += ".txt"
    dir = os.getcwd() + "/" + outtxt
    with open(dir, "w", encoding="utf-8") as f:
        f.write("统计字符数: "+str(count_char(intxt))+"\n")
        f.write("统计单词数: "+str(count_word(intxt))+"\n")
        f.write("统计行数: "+str(count_row(intxt))+"\n")
        for x in count_fword(intxt):
            f.write(str(x[0])+": "+str(x[1])+"\n")


if __name__=="__main__":
    if len(sys.argv)<3:
        raise Exception('未输入输入输出参数')
    intxt=sys.argv[1]
    outtxt=sys.argv[2]
    file_read_out(intxt,outtxt)