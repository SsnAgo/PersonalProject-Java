import unittest
import os
import WordCount


class TestWordCount(unittest.TestCase):
    def test_count_char(self):
        """
        统计多字符数测试（一般情况）
        """
        filename = "test_count_char.txt"
        dir = os.getcwd() + "/" + filename
        with open(dir, "w", encoding="utf-8") as f:
            i = 0
            j = 0
            str = ""
            while i < 100:
                str += "test,"
                i += 1
                j += 1
                if j % 5 == 0:
                    j = 0
                    str += "\n"
                    f.write(str)
                    str = ""
            f.write(str)
        WordCount.file_read_out(filename, "testout_count_char")

    def test_count_char1(self):
        """
        统计特殊字符数测试（转义字符）
        """
        filename = "test_count_char1.txt"
        dir = os.getcwd() + "/" + filename
        str="1\r\n2\n3\'\t4\"5\f\a"
        with open(dir, "w", encoding="utf-8") as f:
           f.write(str)
        WordCount.file_read_out(filename, "testout_count_char1")

    def test_count_word(self):
        """
        统计单词数测试（一般情况）
        """
        filename = "test_count_word.txt"
        dir = os.getcwd() + "/" + filename
        with open(dir, "w", encoding="utf-8") as f:
            str = "file123,filE,ahbyfgy12\n"
            i = 0
            while i < 100:
                f.write(str)
                i += 1
        WordCount.file_read_out(filename, "testout_count_word")

    def test_count_word1(self):
        """
        统计单词数测试（英文大小写不区分情况）
        """
        filename = "test_count_word1.txt"
        dir = os.getcwd() + "/" + filename
        with open(dir, "w", encoding="utf-8") as f:
            str = "WwwW;wwww;wWwW,yyyyyy\n"
            i = 0
            while i < 100:
                f.write(str)
                i += 1
        WordCount.file_read_out(filename, "testout_count_word1")

    def test_count_word2(self):
        """
        统计单词数测试（判断是不是单词情况）
        """
        filename = "test_count_word2.txt"
        dir = os.getcwd() + "/" + filename
        with open(dir, "w", encoding="utf-8") as f:
            str = "123file;wwww;wWwW,file123;file;fil,\n"
            i = 0
            while i < 100:
                f.write(str)
                i += 1
        WordCount.file_read_out(filename, "testout_count_word2")

    def test_count_row(self):
        """
        行数测试（只有换行符情况）
        """
        str = "whuihu\n\n\nwww"
        filename = "test_count_row.txt"
        dir = os.getcwd() + "/" + filename
        with open(dir, "w", encoding="utf-8") as f:
            f.write(str)
        WordCount.file_read_out(filename, "testout_count_row")

    def test_count_row1(self):
        """
        行数测试(有空行包括非空白字符)
        """
        str = "whuihu\n\t\n     \nwww"
        filename = "test_count_row1.txt"
        dir = os.getcwd() + "/" + filename
        with open(dir, "w", encoding="utf-8") as f:
            f.write(str)
        WordCount.file_read_out(filename, "testout_count_row1")

    def test_count_fword(self):
        """
        统计最多的10个单词及其词频测试(未超过10个单词)
        """
        str = ('filr yyyyt  NUgYTR OOOO NUGYTR ttyw buiygy TCTihrr\n')
        filename = "test_count_fword.txt"
        dir = os.getcwd() + "/" + filename
        with open(dir, "w", encoding="utf-8") as f:
            i = 0
            while i < 20:
                f.write(str)
                i += 1
        WordCount.file_read_out(filename, "testout_count_fword")

    def test_count_fword1(self):
        """
        统计最多的10个单词及其词频测试(超过10个单词,最终只输出频率最高的10个)
        """
        str = ('windows95 windows95 windows98 windows96 '
               'windows2000 teee file123 123file file325 file666 '
               'filr yyyyt  NUGYTR OOOO NUGYTR ttyw buiygy TCTihrr\n')
        filename = "test_count_fword1.txt"
        dir = os.getcwd() + "/" + filename
        with open(dir, "w", encoding="utf-8") as f:
            i = 0
            while i < 20:
                f.write(str)
                i += 1
        WordCount.file_read_out(filename, "testout_count_fword1")

    def test_count_fword2(self):
        """
        统计最多的10个单词及其词频测试(频率相同的单词，优先输出字典序靠前的单词)
        """
        str = ('windows95 windows95 windows98 windows96 '
               'windows2000\n')
        filename = "test_count_fword2.txt"
        dir = os.getcwd() + "/" + filename
        with open(dir, "w", encoding="utf-8") as f:
            i = 0
            while i < 20:
                f.write(str)
                i += 1
        WordCount.file_read_out(filename, "testout_count_fword2")

    def test_count_fword3(self):
        """
        统计最多的10个单词及其词频测试(大小写测试)
        """
        str = ('WWWWW EGFERGeeeeeWW\n')
        filename = "test_count_fword3.txt"
        dir = os.getcwd() + "/" + filename
        with open(dir, "w", encoding="utf-8") as f:
            i = 0
            while i < 20:
                f.write(str)
                i += 1
        WordCount.file_read_out(filename, "testout_count_fword3")

    def test_huge_data(self):
        """
        测试大数据量100000  0.645s
        """
        filename = "test_huge_data.txt"
        dir = os.getcwd() + "/" + filename
        with open(dir, "w", encoding="utf-8") as f:
            i = 0
            str="test test\n"
            while i < 100000:
                f.write(str)
                i+=1
        WordCount.file_read_out(filename, "testout_huge_data")
    

if __name__ == "__main__":
    unittest.main()