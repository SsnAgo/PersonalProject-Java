# -*- coding:UTF-8 -*-
import re
import copy

#统计文件的字符数（对应输出第一行）
def count_chars(file_name):
    c_cs = 0
    with open(file_name) as file_obj:
        for content in file_obj:
            c_cs += count_line_chars(content)
    m = "characters：" + str(c_cs)+"\n"
    # print(m)
    out_file("output.txt",m)


#计算文件中每一行的字符数
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
            for word in r.sub("", line.strip()).lower().split(" "):
                if word in words:
                    words[word] += 1
                words.setdefault(word , 1)
    result = sum(words.values())
    #print(words)
    m = "words："+str(result)+"\n"
    out_file("output.txt",m)


#统计文件的有效行数
def count_lines(file_name):
    f = open(file_name)
    s = 0
    for line in f:
        line = line.strip('\n')
        if len(line) == 0:
            continue
        s += 1
    m = "lines："+str(s)+"\n"
    out_file("output.txt",m)


#统计文件中的各单词出现次数
def count_words(file_name):
    words = {}
    r = re.compile(r"[,!?\*\.]")
    with open(file_name, "r") as f:
        for line in f:
            for word in r.sub("", line.strip()).lower().split(" "):
                if word in words:
                    words[word] += 1
                words.setdefault(word, 1)
    # words = sorted(words.keys())
    # print(type(words))
    words2 = copy.deepcopy(sorted(words.items(), key=lambda k:(k[1],k[0]),reverse=True))
    # print(sorted(words.items(), key=lambda k:(k[1],k[0]),reverse=True)[:10])
    # print(words2[:10])
    # print(words2)
    m = 0
    for i in words2:
        if (m<10):
            s = str(i[0])+":"+str(i[1])+"\n"
            out_file("output.txt", s)
            m+=1

def out_file(filename,s):
    filename = 'output.txt'
    with open(filename, 'a') as file_object:
        file_object.write(s)

def clear_file(filename):
    with open(filename, 'w') as f1:
        f1.seek(0)
        f1.truncate()
    # f1.close()

if __name__ == '__main__':

    file1 = input("请输入文件路径：")
    # file2 = input("请输入输出文件名称：")
    # file = str
    clear_file("output.txt")
    count_chars(file1)
    count_word(file1)
    count_lines(file1)
    count_words(file1)