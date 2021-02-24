const fs = require('fs');

let content = "";
const chars = ["a", "b", "c", "d", "e", "f",
  "g", "h", "i", "j", "k", "l", "m", "n", "o",
  "p", "q", "r", "s", "t", "u", " ", "\t", "\n",
  "#", "?"];
for (let i = 0; i < 1e7; i += 1) {
  content += chars[(Math.floor(Math.random() * chars.length)) % chars.length];
}

fs.writeFileSync("big.txt", content);
