# -*- coding:UTF-8 -*-
import os
import re
import copy
import io
# encoding=utf8

#统计文件的字符数（对应输出第一行）
import sys


def count_chars(file_name):
    c_cs = 0
    try:
        with io.open(file_name,"r",encoding='utf-8') as file_obj:
            for content in file_obj:
                c_cs += count_line_chars(content)
        m = "characters: " + str(c_cs)+"\n"
        return m
    except OSError as reason:
        print('FileError'+str(reason))
        sys.exit()

#计算文件中每一行的字符数
def count_line_chars(content):
    result = 0
    for i in set(content):
        result = result + int(content.count(i))
    return result

def analy_word(file_name):
    words = {}
    r = re.compile(r"[,?\t!\*\.]")
    try:
        with io.open(file_name, "r",encoding='utf-8') as f:
            for line in f:
                for word in r.sub("", line.strip()).lower().split(" "):
                    if word in words:
                        words[word] += 1
                    words.setdefault(word, 1)
        return words
    except OSError as reason:
        print('FileError'+str(reason))
        sys.exit()

#统计文件的单词总数（对应输出第2行）
def count_word(file_name):
    words = analy_word(file_name)
    if words is None:
        return 0
    else:
        result = sum(words.values())
        m = "words: "+str(result)+"\n"
        return m

#统计文件的有效行数
def count_lines(file_name):
    try:
        f = io.open(file_name,"r",encoding='utf-8')
        s = 0
        for line in f:
            line = line.strip('\n')
            # line =
            if len(line) == 0:
                continue
            s += 1
        m = "lines："+str(s)+"\n"
        return m
    except OSError as reason:
        print('FileError'+str(reason))
        sys.exit()

#统计文件中的各单词出现次数
def count_words(file_name):
    words = analy_word(file_name)
    if words is None:
        return 0
    else:
        words2 = copy.deepcopy(sorted(words.items(), key=lambda k:(k[1],k[0]),reverse=True))
        m = 0
        s = ""
        for i in words2:
            if (m<10):
                s += str(i[0])+": "+str(i[1])+"\n"
                m+=1
        return s

def out_file(filename,s):
    if s is None:
        return 0
    else:
        try:
            with io.open(filename, 'a',encoding='utf-8') as file_object:
                file_object.write(s)
        except OSError as reason:
            print('FileError'+str(reason))
            sys.exit()

def clear_file(filename):
    try:
        with io.open(filename, 'wb') as f1:
            f1.seek(0)
            f1.truncate()
    except OSError as reason:
        print('FileError'+str(reason))
        sys.exit()

def count_file(file1,file2):
    # print(io.open_file(file1))
    clear_file(file2)
    out_file(file2, count_chars(file1))
    out_file(file2, count_word(file1))
    out_file(file2, count_lines(file1))
    out_file(file2, count_words(file1))

if __name__ == '__main__':
    file1 = input("please input input_file name:")
    file2 = input("please input output_file name:")
    count_file(file1,file2)
© 2021 GitHub, Inc.
