

class CharDeal(object):
    def WordsCount(self,characters,strling):
        start = 0
        end = 0
        startlist = []
        endlist = []
        i = 0
        while i != characters - 1:
            for i in range(end,characters):
                if self.IsLetter(strling[i]) == True:
                    if i <= characters - 4:
                        if self.IsLetter(strling[i+1]) and self.IsLetter(strling[i+2]) and self.IsLetter(strling[i+3]):
                            key = i + 4
                            start = i
                            end = key + 1
                            for j in range(key,characters):
                                if self.IsSpace(strling[j]):
                                    end = j
                                    break
                            startlist.append(start)
                            endlist.append(end)
                            break
        wordlist = []
        for i in range(len(startlist)):
            wordlist.append(strling[startlist[i]:endlist[i]])
        new_wordlist,word_num = self.WordCaculate(wordlist)
        return len(wordlist),new_wordlist,word_num

    def CharCount(self,strling):
        return len(strling)

    def LineCount(self,strling):
        num = strling.count("\n",0,len(strling))
        return num

    def IsSpace(self,key):
        if (key > "Z" or key < "A") and (key > "z" or key < "a") and (key > "9" or key < "0"):
            return True
        return False

    def IsLetter(self,key):
        if (key <= "Z" and key >= "A") or (key <= "z" and key >= "a"):
            return True
        return False

    def WordCaculate(self,wordlist):
        dic = {}
        for i in range(len(wordlist)):
            if wordlist[i].upper() in dic.keys():
                dic[wordlist[i].upper()] = dic[wordlist[i].upper()] + 1
            else:
                dic[wordlist[i].upper()] = 1
        result = sorted(dic.items(),key=lambda item:item[1],reverse=False)
        new_wordlist = []
        word_num = []
        key = 10
        if len(wordlist) < 10:
            key = len(wordlist)
        for i in range(key):
            new_wordlist.append(result[i][0].lower())
            word_num.append(result[i][1])
        for i in range(len(new_wordlist)):
            for j in range(len(new_wordlist)-1,i,-1):
                if new_wordlist[j] < new_wordlist[j-1]:
                    temp = new_wordlist[j]
                    new_wordlist[j] = new_wordlist[j-1]
                    new_wordlist[j-1] = temp
                    temp = word_num[j]
                    word_num[j] = word_num[j-1]
                    word_num[j-1] = temp
        return new_wordlist,word_num


if __name__ == "__main__":
    a = CharDeal()
    a.DealMethod('./example.txt'," ")

