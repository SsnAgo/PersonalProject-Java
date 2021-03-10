import unittest
import os
import WordCount
import warnings

class TestWordCount(unittest.TestCase):

    def test_count_char(self):
        '''
        统计字符数测试 包含对特殊字符的测试
        :return:
        '''
        warnings.simplefilter('ignore', ResourceWarning)
        str = "I have! a \t \r brother? \n"
        filename = "test_count_char.txt"
        dir = os.getcwd() + "/" + filename
        with open(dir,"w",encoding="utf-8") as f:
            f.write(str)
        WordCount.count_file(dir,"output_count_char.txt")

    def test_count_word(self):
        '''
        统计单词数测试  包含对特殊字符的测试
        :return:
        '''
        warnings.simplefilter('ignore', ResourceWarning)
        str = "I have! a \t \r brother? \n He is four years older than me. "
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
        str = "hello\nworld\t\nwwwwI \thave! a \t \r brother? \naaaaa\n"
        filename = "test_count_line.txt"
        dir = os.getcwd()+"/"+filename
        with open(dir,"w",encoding="utf-8") as f:
            f.write(str)
        WordCount.count_file(dir,"output_count_line.txt")
        
    def test_chars(self):
        '''
        单独测试总字符数
        :return:
        '''
        warnings.simplefilter('ignore', ResourceWarning)
        str = "I have! a \t \r brother? \n"
        filename = "s_test_count_char.txt"
        dir = os.getcwd() + "/" + filename
        with open(dir, "w", encoding="utf-8") as f:
            f.write(str)
        s = WordCount.count_chars(dir)
        WordCount.clear_file("s_output_count_chars.txt")
        WordCount.out_file("s_output_count_chars.txt",s)

    def test_word(self):
        '''
        单独测试单词总数
        :return:
        '''
        warnings.simplefilter('ignore', ResourceWarning)
        str = "I have! a \t \r brother? \n He is four years older than me. "
        filename = "s_test_count_word.txt"
        dir = os.getcwd() + "/" + filename
        with open(dir,"w",encoding='utf-8') as f:
            f.write(str)
        s = WordCount.count_word(dir)
        WordCount.clear_file("s_output_count_word.txt")
        WordCount.out_file("s_output_count_word.txt", s)

    def test_words(self):
        '''
        单独测试单词词频
        :return:
        '''
        warnings.simplefilter('ignore', ResourceWarning)
        str = "I have a brother.have a brother. He is four years older than me. Now he is fifteen years old， and he is a student of Grade Nine. He is tall and handsome. His classmates like playing with him. He works hard in study. His teachers speak highly of him. Besides， basketball and running are his favorites."
        filename = "s_test_count_words.txt"
        dir = os.getcwd() + "/" + filename
        with open(dir, "w", encoding='utf-8') as f:
            f.write(str)
        s = WordCount.count_words(dir)
        WordCount.clear_file("s_output_count_words.txt")
        WordCount.out_file("s_output_count_words.txt", s)

    def test_line(self):
        '''
        单独测试文件行数
        :return:
        '''
        warnings.simplefilter('ignore', ResourceWarning)
        str = "hello\nworld\t\nwwwwI \thave! a \t \r brother? \naaaaa\n"
        filename = "s_test_count_line.txt"
        dir = os.getcwd()+"/"+filename
        with open(dir,"w",encoding="utf-8") as f:
            f.write(str)
        s = WordCount.count_lines(dir)
        WordCount.clear_file("s_output_count_line.txt")
        WordCount.out_file("s_output_count_line.txt", s)
if __name__ == '__main__':
    unittest.main()
