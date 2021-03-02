# -*- coding:UTF-8 -*-
import re
import copy

#统计文件的字符数（对应输出第一行）
def count_chars(file_name):
    c_cs = 0
    with open(file_name,"r",encoding='utf-8') as file_obj:
        for content in file_obj:
            c_cs += count_line_chars(content)
    m = "characters：" + str(c_cs)+"\n"
    return m

#计算文件中每一行的字符数
def count_line_chars(content):
    result = 0
    for i in set(content):
        result = result + int(content.count(i))
    return result

def analy_word(file_name):
    words = {}
    r = re.compile(r"[,!\*\.]")
    with open(file_name, "r",encoding='utf-8') as f:
        for line in f:
            for word in r.sub("", line.strip()).lower().split(" "):
                if word in words:
                    words[word] += 1
                words.setdefault(word, 1)
    return words

#统计文件的单词总数（对应输出第2行）
def count_word(file_name):
    words = analy_word(file_name)
    result = sum(words.values())
    m = "words："+str(result)+"\n"
    return m

#统计文件的有效行数
def count_lines(file_name):
    f = open(file_name,encoding='utf-8')
    s = 0
    for line in f:
        line = line.strip('\n')
        if len(line) == 0:
            continue
        s += 1
    m = "lines："+str(s)+"\n"
    return m

#统计文件中的各单词出现次数
def count_words(file_name):
    words = analy_word(file_name)
    words2 = copy.deepcopy(sorted(words.items(), key=lambda k:(k[1],k[0]),reverse=True))
    m = 0
    s = ""
    for i in words2:
        if (m<10):
            s += str(i[0])+":"+str(i[1])+"\n"
            m+=1
    return s

def out_file(filename,s):
    with open(filename, 'a',encoding='utf-8') as file_object:
        file_object.write(s)

def clear_file(filename):
    with open(filename, 'w',encoding='utf-8') as f1:
        f1.seek(0)
        f1.truncate()

def count_file(file1,file2):

    clear_file(file2)
    out_file(file2, count_chars(file1))
    out_file(file2, count_word(file1))
    out_file(file2, count_lines(file1))
    out_file(file2, count_words(file1))

if __name__ == '__main__':
    file1 = input("请输入文件路径：")
    file2 = input("请输入输出文件名称：")
    count_file(file1,file2)
