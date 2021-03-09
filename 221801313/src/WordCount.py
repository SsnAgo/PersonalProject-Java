from Detail import CharDeal
import sys


class Main(object):
    def DealMethod(self,input,output):
        CharDeals = CharDeal()
        try:
            FIn = open(input,'r',encoding='utf-8')
            strling = FIn.read()
            lines = CharDeals.LineCount(strling)
            characters = CharDeals.CharCount(strling)
            total_num, new_wordlist, word_num = CharDeals.WordsCount(characters,strling)
        except IOError:
            print("input file open error")
        except ValueError as e:
            print("value error,",e)
        except TypeError as e:
            print("type error",e)
        else:
            try:
                FOut = open(output,'w',encoding='utf-8')
            except IOError:
                print("output file open error")
            else:
                FOut.write("characters : " + str(characters) + "\n")
                FOut.write("words : " + str(total_num) + "\n")
                FOut.write("lines : " + str(lines) + "\n")
                for i in range(len(word_num)):
                    FOut.write(new_wordlist[i] + " : " + str(word_num[i]) + "\n")
                try:
                    FIn.close()
                    FOut.close()
                except IOError:
                    print("file close error")

if __name__ == "__main__":
    input = sys.argv[1]
    output = sys.argv[2]
    main = Main()
    main.DealMethod(input,output)