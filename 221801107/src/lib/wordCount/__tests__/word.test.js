const { calWordCount, calSortedWordsFrequency, calSortedWordsFrequencyByHeap } = require("../word");

test("can get right word", () => {
  expect(calWordCount("abc123")).toBe(0);
  expect(calWordCount("abc")).toBe(0);
  expect(calWordCount("abcd123")).toBe(1);
  expect(calWordCount("abcd")).toBe(1);
  expect(calWordCount("abcde")).toBe(1);
  expect(calWordCount("abcd##")).toBe(1);
});

test("can calculate right words count", () => {
  expect(calWordCount("")).toBe(0);
  expect(calWordCount("abc123 abcd123")).toBe(1);
  expect(calWordCount("abcd123 abcd123")).toBe(2);
});

test("can calculate right sort", () => {
  const test1 = "huro huro lero";
  expect(
    calSortedWordsFrequency(test1)
      .map((item) => `${item.word}: ${item.count}\n`)
      .join(""),
  ).toBe("huro: 2\nlero: 1\n");
  const test2 = "windows95 windows2000 windows98";
  expect(
    calSortedWordsFrequency(test2)
      .map((item) => `${item.word}: ${item.count}\n`)
      .join(""),
  ).toBe("windows2000: 1\nwindows95: 1\nwindows98: 1\n");
});

test("can calculate right sort by heap", () => {
  const test1 = "huro huro lero";
  expect(
    calSortedWordsFrequencyByHeap(test1)
      .map((item) => `${item.word}: ${item.count}\n`)
      .join(""),
  ).toBe("huro: 2\nlero: 1\n");
  const test2 = "windows95 windows2000 windows98";
  expect(
    calSortedWordsFrequencyByHeap(test2)
      .map((item) => `${item.word}: ${item.count}\n`)
      .join(""),
  ).toBe("windows2000: 1\nwindows95: 1\nwindows98: 1\n");
});

test("can ignore uppercase", () => {
  const test = "huro Huro lero";
  expect(
    calSortedWordsFrequency(test)
      .map((item) => `${item.word}: ${item.count}\n`)
      .join(""),
  ).toBe("huro: 2\nlero: 1\n");
});
