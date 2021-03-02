import unittest
import os
import WordCount
import warnings

class TestWordCount(unittest.TestCase):

    def test_count_char(self):
        '''
        统计字符数测试
        :return:
        '''
        warnings.simplefilter('ignore', ResourceWarning)
        str = "I have! a brother? \n He * is four years older than me. "
        filename = "test_count_char.txt"
        dir = os.getcwd() + "/" + filename
        with open(dir,"w",encoding="utf-8") as f:
            f.write(str)
        WordCount.count_file(dir,"output_count_char.txt")

    def test_count_word(self):
        '''
        统计单词数测试
        :return:
        '''
        warnings.simplefilter('ignore', ResourceWarning)
        str = "I have a brother. He is four years older than me. "
        filename = "test_count_word.txt"
        dir = os.getcwd() + "/" + filename
        with open(dir,"w",encoding='utf-8') as f:
            f.write(str)
        WordCount.count_file(dir,"output_count_word.txt")



    def test_count_words(self):
        '''
        统计单词词频测试
        :return:
        '''
        warnings.simplefilter('ignore', ResourceWarning)
        str = "I have a brother.have a brother. He is four years older than me. Now he is fifteen years old， and he is a student of Grade Nine. He is tall and handsome. His classmates like playing with him. He works hard in study. His teachers speak highly of him. Besides， basketball and running are his favorites."
        filename = "test_count_words.txt"
        dir = os.getcwd() + "/" + filename
        with open(dir, "w", encoding='utf-8') as f:
            f.write(str)
        WordCount.count_file(dir, "output_count_words.txt")

    def test_count_line(self):
        '''
        统计文件行数
        :return:
        '''
        warnings.simplefilter('ignore', ResourceWarning)
        str = "hello\nworld\nwwww"
        filename = "test_count_line.txt"
        dir = os.getcwd()+"/"+filename
        with open(dir,"w",encoding="utf-8") as f:
            f.write(str)
        WordCount.count_file(dir,"output_count_line.txt")

if __name__ == '__main__':
    unittest.main()
