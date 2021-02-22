import {
  calCharacterCount,
  calWordCount,
  calNoEmptyRowsCount,
  calSortedWordsFrequency,
} from './lib/wordCount';

const str = 'hello worklajf jskdj jjj\ndjks sldj shfdh skj 2fhg axfkh hello asll 120f';

console.log(calCharacterCount(str));
console.log(calWordCount(str));
console.log(calNoEmptyRowsCount(str));
console.log(calSortedWordsFrequency(str, 10));
