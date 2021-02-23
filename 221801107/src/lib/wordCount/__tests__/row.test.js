const { calNoEmptyRowsCount, calRowsCount, calEmptyRowsCount } = require("../row");

test("can get right rows count", () => {
  expect(calRowsCount("xxx")).toBe(1);
  expect(calRowsCount("xxx\nxxx\n")).toBe(3);
});

test("can get right empty rows", () => {
  expect(calEmptyRowsCount("xxx")).toBe(0);
  expect(calEmptyRowsCount("xxx\nxxx\n")).toBe(1);
});

test("can get right no-empty rows", () => {
  expect(calNoEmptyRowsCount("xxx\nxxx\n")).toBe(2);
});
