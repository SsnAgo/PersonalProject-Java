class Heap {
  /**
   * 堆构造函数
   * @param cmp 值比较函数返回正数代表顺序不变, 非正数代表交换顺序
   */
  constructor(maxSize, cmp) {
    const defaultCmp = (v1, v2) => v1 - v2;
    this.data = [];
    this.maxSize = maxSize;
    this.cmp = cmp || defaultCmp;
  }

  size() {
    return this.data.length;
  }

  empty() {
    return this.size() === 0;
  }

  add(val) {
    this.data.push(val);
    this.siftUp(this.size() - 1);
    if (this.maxSize && this.size() > this.maxSize) {
      this.pop();
    }
  }

  top() {
    if (this.size() !== 0) {
      return this.data[0];
    }
    return null;
  }

  pop() {
    if (this.size() !== 0) {
      const ret = this.top();
      this.swap(0, this.size() - 1);
      this.data.pop();
      this.siftDown(0);
      return ret;
    }
    return null;
  }

  parent(index) {
    if (index === 0) {
      return undefined;
    }
    return Math.floor((index - 1) / 2);
  }

  leftChild(index) {
    return index * 2 + 1;
  }

  rightChild(index) {
    return index * 2 + 2;
  }

  swap(index1, index2) {
    const temp = this.data[index1];
    this.data[index1] = this.data[index2];
    this.data[index2] = temp;
  }

  siftUp(index) {
    while (
      index > 0
      && this.cmp(this.data[this.parent(index)], this.data[index]) < 0
    ) {
      this.swap(this.parent(index), index);
      index = this.parent(index);
    }
  }

  siftDown(index) {
    while (this.leftChild(index) < this.size()) {
      let childIndex = this.leftChild(index);
      if (
        childIndex + 1 < this.size()
        && this.cmp(this.data[childIndex], this.data[childIndex + 1]) < 0
      ) {
        childIndex += 1;
      }
      if (this.cmp(this.data[index], this.data[childIndex]) >= 0) break;
      this.swap(childIndex, index);
      index = childIndex;
    }
  }
}

module.exports = Heap;
