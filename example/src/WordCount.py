

def count_chars(file_name):
    c_cs = 0
    with open(file_name) as file_obj:
        for content in file_obj:
            c_cs += count_line_chars(content)
    print("characters：" + str(c_cs)),

#统计文件的字符数（对应输出第一行）
def count_line_chars(content):
    result = 0
    for i in set(content):
        result = result + int(content.count(i))
    return result


#统计文件的单词总数（对应输出第2行）

#统计文件的有效行数

#统计文件中的各单词出现次数

if __name__ == '__main__':
    file = "input.txt"
    count_chars(file)