const { calCharacterCount } = require("../character");

test("can ignore chinese character", () => {
  expect(calCharacterCount("嗨")).toBe(0);
});

test("can calculate ASCII character", () => {
  expect(calCharacterCount("abc")).toBe(3);
});

test("can calculate ' ', '\t', '\n'", () => {
  expect(calCharacterCount("\n\t ")).toBe(3);
});
