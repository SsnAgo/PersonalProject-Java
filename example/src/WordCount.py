# -*- coding:UTF-8 -*-
import re

def count_chars(file_name):
    c_cs = 0
    with open(file_name) as file_obj:
        for content in file_obj:
            c_cs += count_line_chars(content)
    print("characters：" + str(c_cs))

#统计文件的字符数（对应输出第一行）
def count_line_chars(content):
    result = 0
    for i in set(content):
        result = result + int(content.count(i))
    return result


#统计文件的单词总数（对应输出第2行）
def count_word(file_name):
    words = {}
    r = re.compile(r"[,!\*\.]")
    with open(file_name , "r") as f:
        for line in f:
            for word in r.sub("", line.strip()).split(" "):
                if word in words:
                    words[word] += 1
                words.setdefault(word , 1)
    result = sum(words.values())
    #print(words)
    print("words："+str(result))


#统计文件的有效行数
def count_lines(file_name):
    f = open(file_name)
    s = 0
    for line in f:
        line = line.strip('\n')
        if len(line) == 0:
            continue
        s += 1
    print("lines："+str(s))


#统计文件中的各单词出现次数

if __name__ == '__main__':
    file = "input.txt"
    count_chars(file)
    count_word(file)
    count_lines(file)
